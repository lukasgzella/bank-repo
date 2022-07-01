package org.kaczucha.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.kaczucha.repository.annotation.HibernateRepository;
import org.kaczucha.repository.entity.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@HibernateRepository
public class HibernateClientRepository implements ClientRepository {
    @Override
    public void save(Client client) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        client.getAccounts().forEach(session::save);
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Client findByEmail(String email) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        final Query<Client> query = session.createQuery("from Client where mail=:mail", Client.class);
        query.setParameter("mail",email);
        final Client client = query.uniqueResult();
        session.close();
        return client;
    }
}
