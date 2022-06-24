package org.kaczucha.repository;

import org.kaczucha.Client;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class InMemoryClientRepository implements ClientRepository {
    private List<Client> clients;

    public InMemoryClientRepository(List<Client> clients) {
        this.clients = clients;
    }

    public void save(Client client) {
        clients.add(client);
    }

    public Client findByEmail(String email) {
        return clients
                .stream()
                .filter(client -> Objects.equals(client.getEmail(), email))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Client with following email: %s not found!", email)
                ));
    }
}
