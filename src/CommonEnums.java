/**
 * Created by אורי on 11/11/2018.
 */
public class CommonEnums {
    enum Operators {
        UP, DOWN, LEFT, RIGHT
    }

    enum SearchAlgorithms {
        IDS , BFS, A_STAR
    }

    public static String getOperatorAbbrevation(Operators operator) {
        String  enumName = operator.name();
        return enumName.substring(0,1);
    }
}
