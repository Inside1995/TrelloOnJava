package ru.artur.trello.service;

import ru.artur.trello.model.BoardList;

public interface ListService {
    void save(BoardList list);
    BoardList getListById(Long id);
    void update(BoardList listById);
    void delete(BoardList listById);
}
