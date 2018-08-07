package ru.artur.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.trello.dao.ActionDAO;
import ru.artur.trello.model.Action;

@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    private ActionDAO dao;

    @Transactional
    @Override
    public void save(Action action) {
        dao.save(action);
    }

    @Transactional
    @Override
    public Action findActionById(Long id) {
        return dao.findByDescriptionId(id);
    }

    @Transactional
    @Override
    public void update(Action action) {
        dao.update(action);
    }


}
