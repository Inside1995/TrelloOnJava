package ru.artur.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.trello.dao.ListDAO;
import ru.artur.trello.model.BoardList;

@Service
public class ListServiceImpl implements ListService {
    @Autowired
    private ListDAO dao;

    @Transactional
    @Override
    public void save(BoardList list) {
        dao.save(list);
    }

    @Transactional
    @Override
    public BoardList getListById(Long id) {
        return dao.getListById(id);
    }

    @Transactional
    @Override
    public void update(BoardList listById) {
        dao.update(listById);
    }

    @Transactional
    @Override
    public void delete(BoardList listById) {
        dao.delete(listById);
    }
}
