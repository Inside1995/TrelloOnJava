package ru.artur.trello.dao;

import ru.artur.trello.model.Action;

public interface ActionDAO {
    void save(Action action);
    Action findByDescriptionId(Long id);
    void update(Action action);
}
