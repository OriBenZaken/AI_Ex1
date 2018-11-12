import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by אורי on 11/11/2018.
 */
public class IDS extends AbstractSearchAlgo {
    // Members
    private Stack<BoardState> openList;

    public IDS(Integer[][] initialBoard) {
        this.currentState = new BoardState(initialBoard, null, null);
        this.openList = new Stack<>();
        this.cost = 0;
    }

    @Override
    public boolean runSearch() {
        int limit = 0;
        BoardState root = this.currentState;
        while (true) {
            this.closedList.clear();
            this.openList.clear();
            boolean result = limitedDFS(root, limit);
            if (result) {
                this.cost = this.currentState.getDepth();
                return true;
            }
            limit++;
        }
    }

    private boolean limitedDFS(BoardState root, int limit) {
        this.openList.push(root);
        while (!this.openList.isEmpty()) {
            this.currentState = this.openList.pop();
            this.closedList.add(this.currentState);
            if (this.currentState.isGoal()) {
                return true;
            }
            if (currentState.getDepth() == limit) {
                continue;
            }
            List<BoardState> successors = this.currentState.getSuccessors();
            for (int i = successors.size() - 1; i > -1; i--) {
                this.openList.push(successors.get(i));
            }
        }
        return false;
    }
}
