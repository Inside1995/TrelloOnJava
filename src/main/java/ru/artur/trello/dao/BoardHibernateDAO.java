package ru.artur.trello.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.artur.trello.model.Board;
import ru.artur.trello.model.BoardList;

import java.util.List;

@Repository
public class BoardHibernateDAO implements BoardDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Board> getAllBoards() {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Board> boards = currentSession.createQuery("from Board")
                .list();
        return boards;
    }

    @Override
    public Board getBoardById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Board board = (Board) currentSession.createQuery("from Board b where b.id=:id")
                .setParameter("id", id)
                .uniqueResult();
        return board;
    }

    @Override
    public void save(Board board) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(board);
    }

    @Override
    public void update(Board board) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(board);
    }

    @Override
    public void delete(Board boardById) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(boardById);
    }
}
