import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
        while (state.getParent() != null) {
            stringBuilder.insert(0, CommonEnums.getOperatorAbbrevation(state.getOriginOpertaor()));
            state = state.getParent();
        }
        return stringBuilder.toString();
    }
}
