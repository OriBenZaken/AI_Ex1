/*
Ori Ben-Zaken
311492110
 */
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A* Algorithm Class
 * Implements ISearchAlgorithm and A* algorithm.
 */
public class A_Star extends AbstractSearchAlgo {
    // Members
    private PriorityQueue<BoardState> openList;

    /**
     * A_Star constructor.
     * @param initialBoard initial board state
     */
    public A_Star(Integer[][] initialBoard) {
        this.currentState = new BoardState(initialBoard, null, null);
        // the open list in A* is a priority queue
        this.openList = new PriorityQueue<>(new ManhattanDistances());
    }

    /**
     * runs the search algorithm
     * @return true - if goal state was found, false - else
     */
    @Override
    public boolean runSearch() {
        int timeStamp = 0;
        this.currentState.setTimeStamp(timeStamp);
        timeStamp++;
        openList.add(this.currentState);
        while (!this.openList.isEmpty()) {
            this.currentState = this.openList.remove();
            this.closedList.add(currentState);
            if (currentState.isGoal()) {
                // sum of all the values of f(n) in the path from the root to the goal state
                this.cost = this.currentState.getDepth();
                return true;
            }
            List<BoardState> successors = this.currentState.getSuccessors();
            for (BoardState successor : successors) {
                successor.setTimeStamp(timeStamp);
                this.openList.add(successor);
                timeStamp++;
            }
        }
        return false;
    }

    /**
     * returns the calculation of f(n) = g(n) + h(n) for the given state
     * @param state state
     * @return
     */
    private int getF(BoardState state) {
        return state.getDepth() + getManhattanDistancesSum(state);
    }

    /**
     * returns the sum all manhattan distances in the board
     * @param state state
     * @return sum all manhattan distances in the board
     */
    private int getManhattanDistancesSum(BoardState state) {
        Integer[][] board = state.getBoard();
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                sum += getManhattanDistance(board.length, board[i][j], i, j);
            }
        }
        return sum;
    }

    /**
     * returns the manhattan distance for a number in the board
     * @param size board size
     * @param num number in the board
     * @param row current row of the number's cell
     * @param col current col of the number's cell
     * @return manhattan distance
     */
    public int getManhattanDistance(int size, int num, int row, int col) {
        if (num == 0) {
            return 0;
        }
        int expectedRow =  (int) (Math.ceil((float)num/size) ) -1;
        int expectedCol = (num % size + (size -1)) % size;
        return Math.abs(row - expectedRow) + Math.abs(col - expectedCol);
    }

    /**
     * calculate cost of the trop from the given state to the root
     * @param state state
     * @return cost
     */
    public int calculateTripCost(BoardState state) {
        int cost = 0;
        while (state != null) {
            cost += getF(state);
            state = state.getParent();
        }
        return cost;
    }

    /**
     * comparator for board states according to f(n) values
     */
    public class ManhattanDistances implements Comparator<BoardState> {

        @Override
        public int compare(BoardState o1, BoardState o2) {
            if (getF(o1) != getF(o2)) {
                return getF(o1) - getF(o2);
            } else {
                if (o1.getTimeStamp() < o2.getTimeStamp()) {
                    return -1;
                }
                return 1;
            }
        }
    }
}