package day5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws IOException {
        HashMap<String, List<String>> dependenciesMap = new HashMap<>();
        List<List<String>> updates = new ArrayList<>();
        List<List<String>> invalidUpdates = new ArrayList<>();

        getData(dependenciesMap, updates);

        getInvalidUpdates(updates, invalidUpdates, dependenciesMap);

        filterValidUpdates(updates, invalidUpdates);

        System.out.println(getSumMiddle(updates));
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

            List<String> pagesToUpdate = new ArrayList<>(Arrays.asList(l.split(",")));

            updates.add(pagesToUpdate);
        }
    }

    //I'm sorry for this
    public static void getInvalidUpdates(List<List<String>> updates, List<List<String>> invalidUpdates, HashMap<String, List<String>> dependenciesMap) {
        for (List<String> pagesToUpdate : updates) {

            boolean valid = true;
            List<String> dependenciesKeys = new ArrayList<>(dependenciesMap.keySet());
            for (int j = 0; j < dependenciesKeys.size() && valid; j++) {
                String key = dependenciesKeys.get(j);

                int keyIndex = pagesToUpdate.indexOf(key);
                if (keyIndex != -1) {

                    List<String> dependants = dependenciesMap.get(key);
                    int dependantIndex;
                    for (int i = 0; i < dependants.size() && valid; i++) {

                        dependantIndex = pagesToUpdate.indexOf(dependants.get(i));

                        if (keyIndex > dependantIndex && dependantIndex != -1) {
                            valid = false;
                            invalidUpdates.add(pagesToUpdate);
                        }
                    }
                }
            }
        }
    }

    public static void filterValidUpdates(List<List<String>> updates, List<List<String>> invalidUpdates) {
        for (List<String> invalidPahesToUpdate : invalidUpdates) {
            updates.remove(invalidPahesToUpdate);
        }
    }

    public static int getSumMiddle(List<List<String>> updates) {
        int sumMiddle = 0;
        for (List<String> s : updates) {
            //I hope every list of pages to update has an odd number of total pages :/
            int middleIndex = s.size()/2;

            sumMiddle += Integer.parseInt(s.get(middleIndex));
        }

        return sumMiddle;
    }

}
