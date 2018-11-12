import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by אורי on 11/11/2018.
 */
public class A_Star extends AbstractSearchAlgo {
    // Members
    private PriorityQueue<BoardState> openList;

    public A_Star(Integer[][] initialBoard) {
        this.currentState = new BoardState(initialBoard, null, null);
        this.openList = new PriorityQueue<>(new ManhattanDistances());
    }

    @Override
    public boolean runSearch() {
        openList.add(this.currentState);
        while (!this.openList.isEmpty()) {
            this.currentState = this.openList.remove();
            this.closedList.add(currentState);
            if (currentState.isGoal()) {
                this.cost = calculateTripCost(this.currentState);
                return true;
            }
            List<BoardState> successors = this.currentState.getSuccessors();
            for (BoardState successor : successors) {
                this.openList.add(successor);
            }
        }
        return false;
    }

    private int getF(BoardState state) {
        return state.getDepth() + getManhattanDistancesSum(state);
    }


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

    public int getManhattanDistance(int size, int num, int row, int col) {
        if (num == 0) {
            return Math.abs(row - (size - 1)) + Math.abs(col - (size - 1));
        }
        int expectedRow =  (int) (Math.ceil((float)num/size) ) -1;
        int expectedCol = (num % size + (size -1)) % size;
        return Math.abs(row - expectedRow) + Math.abs(col - expectedCol);
    }

    public int calculateTripCost(BoardState state) {

        int cost = 0;
        while (state != null) {
            cost += getF(state);
            state = state.getParent();
        }
        return cost;
    }

    public class ManhattanDistances implements Comparator<BoardState> {

        @Override
        public int compare(BoardState o1, BoardState o2) {
            return getF(o1) - getF(o2);
        }
    }
}
