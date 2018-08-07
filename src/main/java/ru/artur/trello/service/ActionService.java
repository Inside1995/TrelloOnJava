package ru.artur.trello.service;

import ru.artur.trello.model.Action;

public interface ActionService {
    void save(Action action);
    Action findActionById(Long id);
    void update(Action action);
}
