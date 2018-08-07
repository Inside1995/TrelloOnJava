package ru.artur.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.artur.trello.dao.BoardDAO;
import ru.artur.trello.model.Board;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAO dao;

    @Transactional
    @Override
    public List<Board> getAllBoards() {
        return dao.getAllBoards();
    }

    @Transactional
    @Override
    public Board getBoardById(Long id) {
        return dao.getBoardById(id);
    }

    @Transactional
    @Override
    public void save(Board board) {
        dao.save(board);
    }

    @Transactional
    @Override
    public void update(Board board) {
        dao.update(board);
    }

    @Transactional
    @Override
    public void delete(Board boardById) {
        dao.delete(boardById);
    }
}
