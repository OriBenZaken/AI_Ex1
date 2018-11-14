import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by אורי on 11/11/2018.
 */
public class BFS extends AbstractSearchAlgo {
    // Members
    private Queue<BoardState> openList;

    public BFS(Integer[][] initialBoard) {
        this.currentState = new BoardState(initialBoard, null, null);
        // the open list in BFS is a regular queue.
        this.openList = new LinkedList<>();
        // the cost defined to be 0
        this.cost = 0;
    }

    @Override
    public boolean runSearch() {
        openList.add(this.currentState);
        while (!this.openList.isEmpty()) {
            this.currentState = this.openList.remove();
            this.closedList.add(currentState);
            if (currentState.isGoal()) {
                return true;
            }
            List<BoardState> successors = this.currentState.getSuccessors();
            for (BoardState successor : successors) {
                this.openList.add(successor);
            }
        }
        return false;
    }

    /**
     * checks if the state already exists in the given list
     * @param statesList collection of states
     * @param newState state
     * @return true if the state in in the states collection, false - else
     */
    private boolean isStateInList(Collection<BoardState> statesList, BoardState newState) {
        for (BoardState state : statesList) {
            if (newState.compareBoardState(state)) {
                return true;
            }
        }
        return false;
    }
}
