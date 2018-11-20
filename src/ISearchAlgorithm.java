/*
Ori Ben-Zaken
311492110
 */
/**
 * Interface of AI search algorithm.
 * Possible implementations: IDS, BFS, A*,...
 */
public interface ISearchAlgorithm {
    /**
     * runs the search algorithm
     * @return true - if goal state was found, false - else
     */
    boolean runSearch();

    /**
     * gets the size of the closed list at the end of the algorithm.
     * @return size of the closed list
     */
    int getClosedListSize();

    /**
     * return the specific cost of the search
     * @return
     */
    int getCost();

    /**
     * get the path to the goal from the initial state.
     * @return solution path
     */
    String getSolutionPath();
}
