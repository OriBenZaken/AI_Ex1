import java.util.ArrayList;
import java.util.List;

/**
 * Created by אורי on 11/11/2018.
 */
public class BoardState implements IState<Integer[][], CommonEnums.Operators> {
    // Members
    Integer[][] board;
    BoardState parent;
    CommonEnums.Operators originOperator;

    public BoardState(Integer[][] state, BoardState parent, CommonEnums.Operators originOperator ) {
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
    public CommonEnums.Operators getOriginOpertaor() {
        return this.originOperator;
    }

    public List<BoardState> getSuccessors() {
        List<BoardState> successors = new ArrayList<>();
        int row = 0;
        int col = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].intValue() == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        List<CommonEnums.Operators> validSteps = getValidOperatorsForCurrentState(row,col);
        for (CommonEnums.Operators opertor : validSteps) {
            successors.add(developeSuccessors(getBoardCopy(), row, col, opertor));
        }
        return successors;
    }

    private List<CommonEnums.Operators> getValidOperatorsForCurrentState(int row, int col) {
        List<CommonEnums.Operators> validSteps = new ArrayList<>();
        if (row > 0) {
            validSteps.add(CommonEnums.Operators.UP);
        }
        if (row < board.length - 1) {
            validSteps.add(CommonEnums.Operators.DOWN);
        }
        if (col > 0) {
            validSteps.add(CommonEnums.Operators.LEFT);
        }
        if (col < board.length - 1) {
            validSteps.add(CommonEnums.Operators.RIGHT);
        }
        return validSteps;
    }

    private BoardState developeSuccessors(Integer[][] boardCopy, int row, int col, CommonEnums.Operators operator) {
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

    public boolean isGoal() {
        int count = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != count) {
                    if (i == board.length - 1 && j == board.length - 1 && board[i][j] == 0) {
                        continue;
                    }
                    return false;
                }
                count++;
            }
        }
        return true;
    }
}
