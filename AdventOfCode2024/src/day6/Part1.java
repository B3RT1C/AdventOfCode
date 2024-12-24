package day6;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {
    public static void main(String[] args) {

        int[] mapBounds = new int[2];
        List<int[]> obstructionsCoords = new ArrayList<>();
        List<int[]> visitedCoords = new ArrayList<>();

        // 0 and 1 = cords, 2(y) and 3(x) = direction vector
        int[] guardInfo = new int[4];

        gatherData(mapBounds, obstructionsCoords, guardInfo);

        // Adds the starting cords of the guard to the visited list because the move() method only adds the cords that the guard will move to
        visitedCoords.add(new int[]{guardInfo[0], guardInfo[1]});

        while (!move(mapBounds, guardInfo, obstructionsCoords, visitedCoords));

        System.out.println(visitedCoords.size());

    }

    public static void gatherData(int[] mapBounds, List<int[]> obstructionsCoords, int[] guardInfo) {
        try {
            Scanner in = new Scanner(Paths.get("src/day6/input6.txt"));
            int y = 0;
            int x = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();

                x = 0;
                for (char c : line.toCharArray()) {
                    if (c == '#') {
                        obstructionsCoords.add(new int[]{y, x});

                    } else if (c != '.') {
                        guardInfo[0] = y;
                        guardInfo[1] = x;

                        switch(c) {
                            case '^':
                                guardInfo[2] = -1;
                                guardInfo[3] = 0;
                                break;
                            case '>':
                                guardInfo[2] = 0;
                                guardInfo[3] = 1;
                                break;
                            case 'v':
                                guardInfo[2] = 1;
                                guardInfo[3] = 0;
                                break;
                            case '<':
                                guardInfo[2] = 0;
                                guardInfo[3] = -1;
                                break;
                        }
                    }
                    x++;
                }
                y++;
            }
            mapBounds[0] = y;
            mapBounds[1] = x;

            in.close();
        } catch (IOException e) {
            // Unreachable
        }
    }

    public static boolean move(int[] mapBounds, int[] guardInfo, List<int[]> obstructionsCoords, List<int[]> visitedCoords) {
        boolean outOfBounds = false;
        int[] newCoords = new int[2];

        newCoords[0] = guardInfo[0] + guardInfo[2];
        newCoords[1] = guardInfo[1] + guardInfo[3];

        if (newCoords[0] == -1 || newCoords[0] == mapBounds[0] || newCoords[1] == -1 || newCoords[1] == mapBounds[1]) {
            outOfBounds = true;

        } else {
            if (obstructionsCoords.stream().anyMatch(obsCoords -> Arrays.equals(obsCoords, newCoords))) {
                rotate(guardInfo);

            } else {
                if (visitedCoords.stream().noneMatch(vistCoords -> Arrays.equals(vistCoords, newCoords))) {
                    visitedCoords.add(newCoords);
                }

                guardInfo[0] = newCoords[0];
                guardInfo[1] = newCoords[1];
            }
        }

        return outOfBounds;
    }

    public static void rotate(int[] guardInfo) {
        int y = guardInfo[2];
        int x = guardInfo[3];
        /*
        [y, x] . [ 0 1]
                 [-1 0]
         */
        guardInfo[2] = x;
        guardInfo[3] = y*-1;
    }

}
