package org.kaczucha.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kaczucha.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryClientRepositoryTest {
    private InMemoryClientRepository repository;
    private List<Client> clients;

    @BeforeEach
    public void setup() {
        clients = new ArrayList<>();
        repository = new InMemoryClientRepository(clients);


    }

    @Test
    public void verifyIfUserIsAddingCorrectlyToTheRepository() {
        //given
        final Client client = new Client("Alek", "e@3", 100);
        final Client expectedClient = new Client("Alek", "e@3", 100);
        //when
        repository.save(client);
        final Client actualClient = clients.stream().findFirst().get();
        //then
        assertEquals(expectedClient, actualClient);


    }

    @Test
    public void test2() {

    }
}
