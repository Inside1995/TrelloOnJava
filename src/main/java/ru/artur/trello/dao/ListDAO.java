package ru.artur.trello.dao;

import ru.artur.trello.model.BoardList;

public interface ListDAO {
    void save(BoardList list);
    BoardList getListById(Long id);
    void update(BoardList listById);
    void delete(BoardList listById);
}
