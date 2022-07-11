package org.kaczucha.repository;

import org.kaczucha.repository.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientSpringJpaRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

}
