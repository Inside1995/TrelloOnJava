package ru.artur.trello.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artur.trello.model.BoardList;

@Repository
public class ListHibernateDAO implements ListDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(BoardList list) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(list);
    }

    @Override
    public BoardList getListById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        BoardList list = (BoardList) currentSession.createQuery("from BoardList b where b.id=:id")
                .setParameter("id", id)
                .uniqueResult();
        return list;
    }

    @Override
    public void update(BoardList listById) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(listById);
    }

    @Override
    public void delete(BoardList listById) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(listById);
    }
}
