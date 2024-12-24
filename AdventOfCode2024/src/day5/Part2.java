package day5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws IOException {
        HashMap<String, List<String>> dependenciesMap = new HashMap<>();
        List<List<String>> updates = new ArrayList<>();
        List<List<String>> invalidUpdates = new ArrayList<>();
        List<List<String>> orderedUpdates = new ArrayList<>();

        getData(dependenciesMap, updates);

        getInvalidUpdates(updates, invalidUpdates, dependenciesMap);

        iDontKnowIfImReadyForDaySix(invalidUpdates, orderedUpdates, dependenciesMap);

        System.out.println(getSumMiddle(orderedUpdates));
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

    public static int getSumMiddle(List<List<String>> updates) {
        int sumMiddle = 0;
        for (List<String> s : updates) {
            //I hope every list of pages to update has an odd number of total pages :/
            int middleIndex = s.size()/2;

            sumMiddle += Integer.parseInt(s.get(middleIndex));
        }

        return sumMiddle;
    }

    public static void iDontKnowIfImReadyForDaySix(List<List<String>> invalidUpdates, List<List<String>> orderedUpdates, HashMap<String, List<String>> dependenciesMap) {
        for (List<String> pagesToOrder : invalidUpdates) {

            List<String> orderedPages = new ArrayList<>();
            for (String pageToOrder : pagesToOrder) {

                if (orderedPages.isEmpty()) {
                    orderedPages.add(pageToOrder);

                } else {

                    List<String> dependants = dependenciesMap.get(pageToOrder);
                    int lowestDependantIndex = -2;
                    if (dependants != null) {
                        for (int i = 0; i < dependants.size() && lowestDependantIndex != 0; i++) {

                            int dependantIndex = orderedPages.indexOf(dependants.get(i));
                            if ((lowestDependantIndex > dependantIndex || lowestDependantIndex == -2) && dependantIndex != -1) {
                                lowestDependantIndex = dependantIndex;
                            }
                        }
                    }

                    //lowestDependantIndex == -1 || == -2
                    if (lowestDependantIndex < 0) {
                        orderedPages.add(pageToOrder);
                    } else {
                        orderedPages.add(lowestDependantIndex, pageToOrder);
                    }

                }
            }

            orderedUpdates.add(orderedPages);
        }
    }

}
