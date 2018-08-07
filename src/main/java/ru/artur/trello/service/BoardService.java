package ru.artur.trello.service;

import ru.artur.trello.model.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();
    Board getBoardById(Long id);
    void save(Board board);
    void update(Board board);
    void delete(Board boardById);
}
