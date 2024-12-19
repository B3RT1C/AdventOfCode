package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day3/input3.txt"));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/day3/input3.txt")));

        //mul(1,1)
        int minLength = 8;

        //mul(333,333)
        int maxLenth = 12;

        int totalMult = 0;
        boolean isEnabled = true;
        while (in.hasNextLine()) {
            String data = in.nextLine();
            Matcher matcher1 = Pattern.compile("(mul\\((\\d+),(\\d+)\\))|do\\(\\)|don't\\(\\)").matcher(data);

            while (matcher1.find()) {
                String match = matcher1.group();

                if (match.matches("don't\\(\\)")) {
                    isEnabled = false;

                } else if (match.matches("do\\(\\)")) {
                    isEnabled = true;

                } else if (isEnabled) {
                    Matcher matcher2 = Pattern.compile("\\d+").matcher(matcher1.group());

                    matcher2.find();
                    int num1 = Integer.parseInt(matcher2.group());

                    matcher2.find();
                    int num2 = Integer.parseInt(matcher2.group());

                    totalMult += num1 * num2;
                }
            }
        }

        System.out.println(totalMult);
    }
}