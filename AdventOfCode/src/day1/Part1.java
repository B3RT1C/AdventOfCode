package day1;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("src/day1/input1.txt"));

        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();

        while (in.hasNext()) {
            nums1.add(in.nextInt());
            nums2.add(in.nextInt());
        }

        Collections.sort(nums1);
        Collections.sort(nums2);

        int total = 0;
        for (int i = 0; i < nums1.size(); i++) {
            int preTotal = nums1.get(i) - nums2.get(i);
            preTotal *= preTotal < 0? -1 : 1;

            total += preTotal;
        }

        System.out.println(total);
    }
}