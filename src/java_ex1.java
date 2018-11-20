/*
Ori Ben-Zaken
311492110
 */
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java_ex1 class.
 * AI - Ex1
 * Submitted by: Ori Ben-Zaken, ID: 311492110
 */
public class java_ex1 {
    // Members
    static CommonEnums.SearchAlgorithms algoType;
    static int boardSize;
    static Integer[][] initialState;

    /**
     * Read the input.txt file with the problem definitions,
     * runs the suitable search algorithm and writes the results
     * to output.txt file
     * @param args command line arguments
     */
    public static void main(String[] args) {
        getProblemDefinesFromFile("input_elad3.txt");
        ISearchAlgorithm searchAlgorithm = null;
        switch (algoType) {
            case BFS:
                searchAlgorithm = new BFS(initialState);
                break;
            case IDS:
                searchAlgorithm = new IDS(initialState);
                break;
            case A_STAR:
                searchAlgorithm = new A_Star(initialState);
                break;
                default:
                    System.exit(1);
        }
        // runs the search algorithm
        searchAlgorithm.runSearch();
        writeResultsToFile(searchAlgorithm, "output.txt");
        return;
    }

    /**
     * reads input.txt file and gets the problem definitions:
     * algorithm type, board size, board initial state.
     * @param fileName
     */
    public static void getProblemDefinesFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Couldn't file input file");
            System.exit(1);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            line = br.readLine().trim();
            // get algorithm type
            algoType = CommonEnums.SearchAlgorithms.values()[Integer.parseInt(line) - 1];
            line = br.readLine().trim();
            // get board size
            boardSize = Integer.parseInt(line);
            line = br.readLine().trim();
            List<Integer> ints = Arrays.stream(line.split("-"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            initialState = new Integer[boardSize][boardSize];
            // build the initial board
            for (int j = 0; j < boardSize; j++) {
                for (int k = 0; k < boardSize; k++) {
                    initialState[j][k] = ints.get(boardSize * j + k);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * write results to output file
     * @param algorithm the search algorithm
     * @param fileName file name
     */
    public static void writeResultsToFile(ISearchAlgorithm algorithm, String fileName) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(algorithm.getSolutionPath() + " ");
        sb.append(algorithm.getClosedListSize() + " ");
        sb.append(algorithm.getCost());
        pw.write(sb.toString());
        pw.close();
    }
}
