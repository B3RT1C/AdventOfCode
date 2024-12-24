package day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        Scanner in = new Scanner("src/day6/input6.txt");

        List<int[]> obstructuionsCords = new ArrayList<>();
        List<int[]> visitedCords = new ArrayList<>();
        int[] guardCords = new int[2];

        int y = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();

            int x = 0;
            for (char c : line.toCharArray()) {
                if (c == '#') {
                    obstructuionsCords.add(new int[]{y, x});

                } else if (c == '^' || c == '>' || c == 'v' || c == '<') {
                    guardCords[0] = y;
                    guardCords[1] = x;
                }

                x++;
            }


            y++;
        }
    }
}
