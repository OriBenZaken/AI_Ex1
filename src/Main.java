import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by אורי on 11/11/2018.
 */




public class Main {
    // Members
    static CommonEnums.SearchAlgorithms algoType;
    static int boardSize;
    static Integer[][] initialState;

    public static void main(String[] args) {
        System.out.println("Hi");
        getProblemDefinesFromFile("input.txt");
        ISerachAlgorithm serachAlgorithm = new BFS(initialState);
        serachAlgorithm.runSearch();
        System.out.println(serachAlgorithm.getSolutionPath());
        return;
    }

    public static void getProblemDefinesFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Couldn't file input file");
            System.exit(1);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            line = br.readLine().trim();
            algoType = CommonEnums.SearchAlgorithms.values()[Integer.parseInt(line) - 1];
            line = br.readLine().trim();
            boardSize = Integer.parseInt(line);
            line = br.readLine().trim();
            List<Integer> ints = Arrays.stream(line.split("-"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            initialState = new Integer[boardSize][boardSize];
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
}
