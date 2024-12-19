package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/day1/input1.txt"));

        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();
        Set<Integer> nums1Unique = new HashSet<>();

        while (in.hasNext()) {
            int num1 = in.nextInt();
            int num2 = in.nextInt();

            nums1.add(num1);
            nums1Unique.add(num1);

            nums2.add(num2);
        }

        Collections.sort(nums1);
        Collections.sort(nums2);

        int similarityScore = 0;
        for (int value : nums1Unique) {
            int totalApperances1 = countValueInOrderedList(nums1, value);
            int totalApperances2 = countValueInOrderedList(nums2, value);

            similarityScore += (value * totalApperances2) * totalApperances1;
        }

        System.out.println(similarityScore);
    }

    private static int countValueInOrderedList(List<Integer> orderedList, int value) {
        if (orderedList.contains(value)) {
            int firstIndex = orderedList.indexOf(value);
            int lastIndex = orderedList.lastIndexOf(value);

            return (lastIndex - firstIndex) + 1;

        } else {
            return 0;
        }
    }

}