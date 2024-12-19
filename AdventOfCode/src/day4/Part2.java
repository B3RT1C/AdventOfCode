package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/day4/input4.txt"));

        char[][] puzzle = new char[lines.size()][lines.getFirst().length()];
        for (int i = 0; i < lines.size(); i++) {
            puzzle[i] = lines.get(i).toCharArray();
        }

        int count = 0;
        List<int[]> aIndexes = findLetter(puzzle, 'A');
        for (int[] indx : aIndexes) {
            String tbt = getThreeByThree(puzzle, indx);

            // My goals are beyond your understanding

            String pattern = "M.M" +
                             ".A." +
                             "S.S";

            boolean exit = true;
            for (int i = 0; i < 4 && exit; i++) {
                if (tbt.matches(pattern)) {
                    count++;
                    exit = false;

                } else {
                   pattern = rotateThreeByThree(pattern);
                }
            }
        }
        System.out.println(count);
    }

    private static List<int[]> findLetter(char[][] puzzle, char c) {
        List<int[]> foundIndexes = new ArrayList<>();

        // From 1 to length -1 in order to not find letters on the side of the matrix, we dont need those
        for (int i = 1; i < puzzle.length-1; i++) {
            for (int j = 1; j < puzzle[0].length-1; j++) {
                if (puzzle[i][j] == c) {
                    foundIndexes.add(new int[]{i, j});
                }
            }
        }

        return foundIndexes;
    }

    private static String getThreeByThree(char[][] puzzle, int[] searchPoint) {
        String threeByThree = "";

        int y = searchPoint[0];
        int x = searchPoint[1];

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                threeByThree += puzzle[y+i][x+j];
            }
        }

        return threeByThree;
    }

    private static String rotateThreeByThree(String tbt) {
        char[] letters = tbt.toCharArray();

        letters[0] = tbt.charAt(2);
        letters[2] = tbt.charAt(8);
        letters[6] = tbt.charAt(0);
        letters[8] = tbt.charAt(6);

        return new String(letters);
    }
}
