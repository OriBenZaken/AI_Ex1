/*
Ori Ben-Zaken
311492110
 */
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractSearchAlgo class.
 * An  abstract class which implements the ISearchAlgorithm interface.
 */
abstract public class AbstractSearchAlgo implements ISearchAlgorithm {
    // Members
    protected List<BoardState> closedList = new ArrayList<>();
    protected int cost;
    protected BoardState currentState;

    /**
     * runs the search algorithm
     * @return true - if goal state was found, false - else
     */
    @Override
    abstract public boolean runSearch();

    /**
     * gets the size of the closed list at the end of the algorithm.
     * @return size of the closed list
     */
    @Override
    public int getClosedListSize() {
        return closedList.size();
    }

    /**
     * return the specific cost of the search
     * @return
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * get the path to the goal from the initial state.
     * @return solution path
     */
    @Override
    public String getSolutionPath() {
        StringBuilder stringBuilder = new StringBuilder();
        BoardState state = currentState;
        // starts from the goal state, following up the tree to the root state
        while (state.getParent() != null) {
            stringBuilder.insert(0, CommonEnums.getOperatorAbbrevation(state.getOriginOperator()));
            state = state.getParent();
        }
        return stringBuilder.toString();
    }
}
