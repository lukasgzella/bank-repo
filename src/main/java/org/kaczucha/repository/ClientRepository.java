package org.kaczucha.repository;

import org.kaczucha.repository.entity.Client;

public interface ClientRepository {
    void save(Client client);

    Client findByEmail(String email);
}
