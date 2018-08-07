package ru.artur.trello.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "list")
public class BoardList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @ManyToOne
    @JoinTable(name = "board_list",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id"))
    private Board board;
    @OneToMany(mappedBy = "boardList", fetch = FetchType.EAGER)
    private List<Action> actions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    @Override
    public String toString() {
        return "BoardList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + actions +
                '}';
    }
}
