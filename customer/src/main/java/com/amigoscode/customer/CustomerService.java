package com.amigoscode.customer;

import com.amigoscode.amqp.RabbitMQMessageProducer;
import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository repository,
        RestTemplate client,
        FraudClient feignClient,
        RabbitMQMessageProducer producer) {
    public void     registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        // TODO: 21.09.2023 check if email valid;
        //  check if email not taken
        repository.saveAndFlush(customer);

        // Using RestTemplate
//        FraudCheckResponse checkResponse = client.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        // Using OpenFeign
        FraudCheckResponse fraudster = feignClient.isFraudster(customer.getId());

        if (fraudster.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Hello from %s %s".formatted(customer.getFirstName(), customer.getLastName())
        );

        producer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
