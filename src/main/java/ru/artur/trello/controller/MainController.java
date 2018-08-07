package ru.artur.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.artur.trello.model.Action;
import ru.artur.trello.model.Board;
import ru.artur.trello.model.BoardList;
import ru.artur.trello.service.ActionService;
import ru.artur.trello.service.BoardService;
import ru.artur.trello.service.ListService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ListService listService;
    @Autowired
    private ActionService actionService;


    @RequestMapping("/")
    public String home(Model model) {
        List<Board> allBoards = boardService.getAllBoards();
        model.addAttribute("allBoards", allBoards);
        return "main";
    }

    @RequestMapping("/create_board")
    public String createBoard(@RequestParam("board_name") String boardName) {
        Board board = new Board();
        board.setName(boardName);
        boardService.save(board);
        return "redirect:/";
    }

    @RequestMapping("/delete_board/**")
    public String deleteBoard (@RequestParam("id") Long id) {
        Board boardById = boardService.getBoardById(id);
        boardService.delete(boardById);
        return "redirect:/";
    }

    @RequestMapping("/show_board/**")
    public String showBoard(@RequestParam("id") Long id,
                            Model model) {
        Board board = boardService.getBoardById(id);
        List<BoardList> lists = board.getLists();
        model.addAttribute("board", board);
        model.addAttribute("lists", lists);
        return "board";
    }

    @RequestMapping("/create_list")
    public String createList(@RequestParam("list_name") String listName,
                             @RequestParam("board_id") Long id) {
        Board board = boardService.getBoardById(id);
        BoardList boardList = new BoardList();
        boardList.setName(listName);
        boardList.setBoard(board);
        board.addList(boardList);
        listService.save(boardList);
        boardService.update(board);
        return "redirect:/show_board/?id=" + board.getId();
    }

    @RequestMapping("/delete_list/**")
    public String deleteList(@RequestParam("id") Long id) {
        BoardList listById = listService.getListById(id);
        Board board = listById.getBoard();
        listService.delete(listById);
        return "redirect:/show_board/?id=" + board.getId();
    }


    @RequestMapping("/create_action")
    public String createAction(@RequestParam("list_id") Long id,
                               @RequestParam("description") String description,
                               Model model) {
        BoardList listById = listService.getListById(id);
        Action action = new Action();
        action.setDescription(description);
        action.setBoardList(listById);
        action.setDone(false);
        listById.addAction(action);
        actionService.save(action);
        listService.update(listById);
        return "redirect:/show_board/?id=" + listById.getBoard().getId();
    }

    @RequestMapping("/done_action")
    public @ResponseBody String doneAction(@RequestParam("action_id") Long actionId,
               @RequestParam("done") boolean done) {
        Action action = actionService.findActionById(actionId);
        action.setDone(done);
        actionService.update(action);
        return "successful";
    }
}
