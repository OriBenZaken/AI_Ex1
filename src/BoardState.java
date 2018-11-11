import java.util.ArrayList;
import java.util.List;

/**
 * Created by אורי on 11/11/2018.
 */
public class BoardState implements IState<Integer[][], Operators> {
    // Members
    Integer[][] board;
    BoardState parent;
    Operators originOperator;

    public BoardState(Integer[][] state, BoardState parent, Operators originOperator ) {
        this.board = state;
        this.parent = parent;
        this.originOperator = originOperator;
    }
    @Override
    public Integer[][] getBoard() {
        return this.board;
    }

    @Override
    public BoardState getParent() {
        return this.parent;
    }

    @Override
    public Operators getOriginOpertaor() {
        return this.originOperator;
    }

    public List<BoardState> getSons() {
        List<BoardState> sons = new ArrayList<>();
        int row = 0;
        int col = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if (board[i][j].intValue() == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        if (row == 0) {
            return null;
        }

        List<Operators> validSteps = getValidOperatorsForCurrentState(row,col);
        for (Operators opertor : validSteps) {
            sons.add(developSon(getBoardCopy(), row, col, opertor));
        }
        return sons;
    }

    private List<Operators> getValidOperatorsForCurrentState(int row, int col) {
        List<Operators> validSteps = new ArrayList<>();
        if (row > 0) {
            validSteps.add(Operators.UP);
        }
        if (row < board.length - 1) {
            validSteps.add(Operators.DOWN);
        }
        if (col > 0) {
            validSteps.add(Operators.LEFT);
        }
        if (col < board.length - 1) {
            validSteps.add(Operators.RIGHT);
        }
        return validSteps;
    }

    private BoardState developSon(Integer[][] boardCopy,int row, int col, Operators operator) {
        switch (operator) {
            case UP:
                boardCopy[row][col] = boardCopy[row-1][col];
                boardCopy[row-1][col] = 0;
                break;
            case DOWN:
                boardCopy[row][col] = boardCopy[row+1][col];
                boardCopy[row+1][col] = 0;
                break;
            case LEFT:
                boardCopy[row][col] = boardCopy[row][col-1];
                boardCopy[row][col-1] = 0;
                break;
            case RIGHT:
                boardCopy[row][col] = boardCopy[row][col+1];
                boardCopy[row][col+1] = 0;
                break;
            default:
                break;
        }
        return new BoardState(boardCopy, this, operator);
    }

    private Integer[][] getBoardCopy() {
        Integer[][] boardCopy = new Integer[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    }

    public boolean compareBoardState(BoardState other) {
        Integer[][] otherBoard = other.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != otherBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
