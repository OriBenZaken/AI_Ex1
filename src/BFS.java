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
        this.openList = new LinkedList<>();
    }

    @Override
    public boolean runSearch() {
        openList.add(this.currentState);
        int i = 0;
        while (!this.openList.isEmpty()) {
            this.currentState = this.openList.remove();
            this.closedList.add(currentState);
            if (currentState.isGoal()) {
                return true;
            }
            List<BoardState> successors = this.currentState.getSuccessors();
            for (BoardState successor : successors) {
                if (! isStateInList(openList, successor) && ! isStateInList(closedList, successor)) {
                    this.openList.add(successor);
                }
            }
            System.out.println("Iteration: " + i);
            i++;
        }
        return false;
    }

    private boolean isStateInList(Collection<BoardState> statesList, BoardState newState) {
        for (BoardState state : statesList) {
            if (newState.compareBoardState(state)) {
                return true;
            }
        }
        return false;
    }
}
