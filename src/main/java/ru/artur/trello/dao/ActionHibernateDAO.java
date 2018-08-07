package ru.artur.trello.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artur.trello.model.Action;

@Repository
public class ActionHibernateDAO implements ActionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Action action) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(action);
    }

    @Override
    public Action findByDescriptionId(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Action action = (Action) currentSession.createQuery("from Action a where a.id=:id")
                .setParameter("id", id)
                .uniqueResult();
        return action;
    }

    @Override
    public void update(Action action) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(action);
    }
}
