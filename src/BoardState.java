/*
Ori Ben-Zaken
311492110
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Board state class.
 * Describes a state in the tails puzzle game.
 */
public class BoardState {
    // Members
    private Integer[][] board;
    // the BoardState that this state was developed from
    private BoardState parent;
    // the operator that was used for the creation of this state from the parent
    private CommonEnums.Operators originOperator;
    // depth in the states graph
    private int depth;
    // creation stamp of the state
    private int timeStamp;

    /**
     * Board state constructor
     * @param state board
     * @param parent the BoardState that this state was developed from
     * @param originOperator the operator that was used for the creation of this state from the parent
     */
    public BoardState(Integer[][] state, BoardState parent, CommonEnums.Operators originOperator ) {
        this.board = state;
        this.parent = parent;
        this.originOperator = originOperator;
        // the state is the root, the initial state
        if (parent == null) {
            this.depth = 0;
        } else {
            this.depth = this.parent.getDepth() + 1;
        }
    }

    /**
     * returns the board
     * @return board
     */
    public Integer[][] getBoard() {
        return this.board;
    }

    /**
     * returns time stamp
     * @return time stamp
     */
    public int getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * sets the time stamp
     * @param timeStamp time stamp
     */
    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Returns the parent of the state
     * @return parent
     */
    public BoardState getParent() {
        return this.parent;
    }

    /**
     * return the origin operator
     * @return origin operator
     */
    public CommonEnums.Operators getOriginOperator() {
        return this.originOperator;
    }

    /**
     * return the depth
     * @return depth
     */
    public int getDepth() {
        return this.depth;
    }

    /**
     * returns the list of successors of the state
     * @return successors
     */
    public List<BoardState> getSuccessors() {
        List<BoardState> successors = new ArrayList<>();
        int row = 0;
        int col = 0;
        // finds the row and col of the empty cell, board[row][col] = 0
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
        for (CommonEnums.Operators operator : validSteps) {
            successors.add(developSuccessor(getBoardCopy(), row, col, operator));
        }
        return successors;
    }

    /**
     * returns a list of all the valid operators to operate on this state
     * @param row row of the empty cell
     * @param col col of the empty cell
     * @return valid operations
     */
    private List<CommonEnums.Operators> getValidOperatorsForCurrentState(int row, int col) {
        List<CommonEnums.Operators> validSteps = new ArrayList<>();
        if (row < board.length - 1) {
            validSteps.add(CommonEnums.Operators.UP);
        }
        if (row > 0) {
            validSteps.add(CommonEnums.Operators.DOWN);
        }
        if (col < board.length - 1) {
            validSteps.add(CommonEnums.Operators.LEFT);
        }
        if (col > 0) {
            validSteps.add(CommonEnums.Operators.RIGHT);
        }
        return validSteps;
    }

    /**
     * returns a new board state according to the given operator
     * @param boardCopy copy of the board
     * @param row row of the empty cell
     * @param col col of the empty cell
     * @param operator operator
     * @return new board state
     */
    private BoardState developSuccessor(Integer[][] boardCopy, int row, int col, CommonEnums.Operators operator) {
        switch (operator) {
            case UP:
                boardCopy[row][col] = boardCopy[row+1][col];
                boardCopy[row+1][col] = 0;
                break;
            case DOWN:
                boardCopy[row][col] = boardCopy[row-1][col];
                boardCopy[row-1][col] = 0;
                break;
            case LEFT:
                boardCopy[row][col] = boardCopy[row][col+1];
                boardCopy[row][col+1] = 0;
                break;
            case RIGHT:
                boardCopy[row][col] = boardCopy[row][col-1];
                boardCopy[row][col-1] = 0;
                break;
            default:
                break;
        }
        return new BoardState(boardCopy, this, operator);
    }

    /**
     * returns a copy of the board
     * @return copy of the board
     */
    private Integer[][] getBoardCopy() {
        Integer[][] boardCopy = new Integer[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    }

    /**
     * gets a state and returns true if the boards are identical
     * @param other other state
     * @return
     */
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

    /**
     * return true if the state is a goal state, false - else
     * @return
     */
    public boolean isGoal() {
        int count = 1;
        // cjecks if the number in the board are arranged in an ascending order from left
        // to right
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