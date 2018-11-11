import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by אורי on 11/11/2018.
 */

enum Operators {
    UP, DOWN, LEFT, RIGHT
}

enum SearchAlgorithm {
    IDS , BFS, A_STAR
}


public class Main {
    // Members
    static SearchAlgorithm algoType;
    static int boardSize;
    static Integer[][] initialState;

    public static void main(String[] args) {
        System.out.println("Hi");
        getProblemDefinesFromFile("input.txt");
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
            algoType = SearchAlgorithm.values()[Integer.parseInt(line) - 1];
            line = br.readLine().trim();
            boardSize = Integer.parseInt(line);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
