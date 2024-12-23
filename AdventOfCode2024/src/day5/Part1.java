package day5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws IOException {
        HashMap<String, List<String>> dependenciesMap = new HashMap<>();
        List<List<String>> updates = new ArrayList<>();

        getData(dependenciesMap, updates);

        //Oh boy here it comes
        List<List<String>> validUpdates = new ArrayList<>();
        for (List<String> pagesToUpdate : updates) {
            for (String pageToUpdate : pagesToUpdate) {
                int indexOfPage = pageToUpdate.indexOf(pageToUpdate);
                List<String> dependantsOfPage = dependenciesMap.get(pageToUpdate);

                for (String dependant : dependantsOfPage) {
                    if ()
                }

            }
        }
    }

    public static void getData(HashMap<String, List<String>> dependenciesMap, List<List<String>> updates) {
        try {
            Scanner in = new Scanner(Paths.get("src/day5/input5.txt"));

            getDependencies(in, dependenciesMap);
            getUpdates(in, updates);

            in.close();
        } catch (IOException e) {
            //Just let me make mistakes java, im human ok?
        }
    }

    public static void getDependencies(Scanner in, HashMap<String, List<String>> dependenciesMap) {
        String line;
        while ((line = in.nextLine()) != "") {;
            String[] data = line.split("\\|");

            String num1 = data[0];
            String num2 = data[1];

            List<String> dependants = dependenciesMap.get(num1);

            if (dependants == null) {
                dependants = new ArrayList<>();
                dependenciesMap.put(num1, dependants);
            }

            dependants.add(num2);
        }
    }

    public static void getUpdates(Scanner in, List<List<String>> updates) {
        while (in.hasNextLine()) {
            String l = in.nextLine();

            List<String> pagesToUpdate = new ArrayList<>();
            for (String s : l.split(",")) {
                pagesToUpdate.add(s);
            }

            updates.add(pagesToUpdate);
        }
    }

}
