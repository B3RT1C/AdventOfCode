package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/day4/input4.txt"));

        char[] searchOrder = new char[]{'X','M','A','S'};

        char[][] puzzle = new char[lines.size()][lines.getFirst().length()];
        for (int i = 0; i < lines.size(); i++) {
            puzzle[i] = lines.get(i).toCharArray();
        }

        List<int[]> lastIndexes = new ArrayList<>();
        List<int[]> newIndexes = new ArrayList<>();
        for (char c : searchOrder) {
            if (lastIndexes.isEmpty()) {
                lastIndexes = findLetter(puzzle, c);

            } else {
                for (int[] searchPoint : lastIndexes) {
                    newIndexes.addAll(searchAround(puzzle, c, searchPoint));
                }
                lastIndexes = newIndexes;
                newIndexes = new ArrayList<>();
            }

        }

        System.out.println(lastIndexes.size());
    }

    private static List<int[]> findLetter(char[][] puzzle, char c) {
        List<int[]> foundIndexes = new ArrayList<>();

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if (puzzle[i][j] == c) {
                    foundIndexes.add(new int[]{i, j});
                }
            }
        }

        return foundIndexes;
    }

    //Returns indexes of found letters in the surrounding 8 squares of the searchPoint
    private static List<int[]> searchAround(char[][] puzzle, char c, int[] searchPoint) {
        List<int[]> foundIndexes = new ArrayList<>();
            /*
                0           1           2
            0   x-1/y-1     x/y-1       x+1/y-1
            1   x-1/y       x/y         x+1/y
            2   x-1/y+1     x/y+1       x+1/y+1
            */

            int y, x;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    y = searchPoint[0] + i;
                    x = searchPoint[1] + j;

                    if (!(i == 0 && j == 0) &&
                            x >= 0 && x < puzzle[0].length &&
                                y >= 0 && y < puzzle.length &&
                                    puzzle[y][x] == c) {

                        if (searchPoint.length == 2 || (i == searchPoint[2] && j == searchPoint[3])) {
                            foundIndexes.add(new int[]{y,x,i,j}); //i and j indicates de direction from the previous to the actual letter
                        }
                    }
                }
            }
        return foundIndexes;
    }

}