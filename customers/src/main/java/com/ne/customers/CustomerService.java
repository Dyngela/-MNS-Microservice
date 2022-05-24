package com.ne.customers;

import com.ne.amqp.RabbitMQMessageProducer;
import com.ne.clients.fraud.FraudCheckResponse;
import com.ne.clients.fraud.FraudClient;
import com.ne.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer  producer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        System.out.println(fraudCheckResponse);

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(),
                customer.getEmail(),
                "hi from java");

        producer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");


    }


}
