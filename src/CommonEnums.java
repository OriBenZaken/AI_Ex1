/*
Ori Ben-Zaken
311492110
 */
public class CommonEnums {
    enum Operators {
        UP, DOWN, LEFT, RIGHT
    }

    enum SearchAlgorithms {
        IDS , BFS, A_STAR
    }

    /**
     * returns the first letter of operator from the Operators enum
     * @param operator operator
     * @return
     */
    public static String getOperatorAbbrevation(Operators operator) {
        String  enumName = operator.name();
        return enumName.substring(0,1);
    }
}
