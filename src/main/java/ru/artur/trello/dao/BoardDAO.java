package ru.artur.trello.dao;

import ru.artur.trello.model.Board;

import java.util.List;

public interface BoardDAO {
    List<Board> getAllBoards();
    Board getBoardById(Long id);
    void save(Board board);
    void update(Board board);
    void delete(Board boardById);
}
