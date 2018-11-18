/*
Ori Ben-Zaken
311492110
 */
import java.util.List;
import java.util.Stack;

public class IDS extends AbstractSearchAlgo {
    // Members
    private Stack<BoardState> openList;

    /**
     * IDS constructor
     * @param initialBoard
     */
    public IDS(Integer[][] initialBoard) {
        this.currentState = new BoardState(initialBoard, null, null);
        // IDS uses stack as the open list
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
                // cost is defined to ne the depth of the goal state in the graph.
                this.cost = this.currentState.getDepth();
                return true;
            }
            limit++;
        }
    }

    /**
     * Runs a depth limited DFS
     * @param root root node
     * @param limit depth limit
     * @return true if a goal state was found, false- else
     */
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
