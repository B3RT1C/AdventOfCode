package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day2/input2.txt"));

        int totalSafeReports = 0;

        while (in.hasNextLine()) {
            List<String> report = List.of(in.nextLine().split(" "));

            if (help(report)) {
                totalSafeReports++;

            } else {
                boolean isTolerable = false;

                for (int i = 0; i < report.size() && !isTolerable; i++) {
                    List<String> subReport = new ArrayList<>(report);
                    subReport.remove(i);

                    if (help(subReport)) {
                        isTolerable = true;
                        totalSafeReports++;
                    }
                }
            }
        }

        System.out.println(totalSafeReports);
    }

    private static boolean help(List<String> report) {
        int ascending = 0;
        int descending = 0;

        for (int i = 1; i < report.size(); i++) {
            int numA = Integer.parseInt(report.get(i - 1));
            int numB = Integer.parseInt(report.get(i));

            if (numA == numB ||
                    Math.absExact(numA - numB) > 3) {
                return false;
            }

            if (numA < numB) {
               ascending++;

            } else {
                descending++;
            }

            if (ascending > 0 && descending > 0) {
                return false;
            }
        }

        return true;
    }

}