import java.util.ArrayList;
import java.util.List;

/**
 * Created by אורי on 11/11/2018.
 */
abstract public class AbstractSearchAlgo implements ISerachAlgorithm {
    // Members
    protected List<BoardState> closedList = new ArrayList<>();
    protected int cost;
    protected BoardState currentState;

    @Override
    abstract public boolean runSearch();

    @Override
    public int getClosedListSize() {
        return closedList.size();
    }

    @Override
    public int getCost() {
        return cost;
    }

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
