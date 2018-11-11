/**
 * Created by אורי on 11/11/2018.
 */
public interface ISerachAlgorithm {
    boolean runSearch();
    int getClosedListSize();
    int getCost();
    String getSolutionPath();
}
