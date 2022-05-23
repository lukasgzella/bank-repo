package org.kaczucha.service;

import org.kaczucha.Client;
import org.kaczucha.repository.ClientRepository;

import java.util.Objects;

public class BankService {
    private final ClientRepository clientRepository;
    private Client client;

    public BankService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {
        if (Objects.isNull(client.getEmail())) {
            throw new IllegalArgumentException();
        }
        if (Objects.isNull(client.getName())) {
            throw new IllegalArgumentException();
        }
        clientRepository.save(client);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public void transfer(
            String fromEmail,
            String toEmail,
            double amount) {
        validateAmount(amount);
        if (fromEmail.equals(toEmail)) {
            throw new IllegalArgumentException("cannot transfer to same client");
        }
        Client fromClient = findByEmail(fromEmail);
        Client toClient = findByEmail(toEmail);

        if (fromClient.getBalance() - amount >= 0) {
            fromClient.setBalance(fromClient.getBalance() - amount);
            toClient.setBalance(toClient.getBalance() + amount);
        } else {
            throw new NoSufficientFundException("no funds");
        }

    }

    public void withdraw(
            final String email,
            final double amount) {
        validateAmount(amount);
        if (Objects.isNull(email)){
            throw new IllegalArgumentException("email cant be null");
        }

        final String lowerCaseEmail = email.toLowerCase();
        final Client client = findByEmail(lowerCaseEmail);
        if (amount > client.getBalance()) {
            throw new NoSufficientFundException("amount bigger than balance");
        }
        final double newBalance = client.getBalance() - amount;
        client.setBalance(newBalance);


    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
    }
}
