package day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {

        List<int[]> obstructionsCords = new ArrayList<>();
        List<int[]> visitedCords = new ArrayList<>();

        // 0 and 1 = cords, 2 and 3 = direction vector
        int[] guardInfo = new int[4];




    }

    public static void gatherData(List<int[]> obstructionsCords, int[] guardInfo) {
        Scanner in = new Scanner("src/day6/input6.txt");

        int y = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();

            int x = 0;
            for (char c : line.toCharArray()) {
                if (c == '#') {
                    obstructionsCords.add(new int[]{y, x});

                } else if (c != '.') {
                    guardInfo[0] = y;
                    guardInfo[1] = x;

                    switch(c) {
                        case '^':
                            guardInfo[2] = 0;
                            guardInfo[3] = 0;
                            break;
                        case '>':
                            guardInfo[2] = 1;
                            guardInfo[3] = 0;
                            break;
                        case 'v':
                            guardInfo[2] = 2;
                            guardInfo[3] = 0;
                            break;
                        case '<':
                            guardInfo[2] = 3;
                            guardInfo[3] = 0;
                            break;
                    }
                }
                x++;
            }
            y++;
        }
        in.close();
    }

    public static void move(int[] guardInfo) {


    }

}