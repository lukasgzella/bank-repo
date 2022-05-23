package org.kaczucha.repository;

import org.kaczucha.Client;

import java.util.List;
import java.util.NoSuchElementException;

public class InMemoryClientRepository implements ClientRepository {
    private List<Client> clients;

    public InMemoryClientRepository(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clients
                .stream()
                .filter(client -> client.getEmail().equals(email))
                //.filter(client -> Objects.equals(client.getEmail(),email))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Client with following email: %s is not found.".formatted(email)));
    }
}
