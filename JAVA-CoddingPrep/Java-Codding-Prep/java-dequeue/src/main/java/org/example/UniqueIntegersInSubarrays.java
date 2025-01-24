package org.example;

import java.util.*;


public class UniqueIntegersInSubarrays  {
    public static void main(String[] args) {
        // Use Scanner for dynamic input
        Scanner scanner = new Scanner(System.in);

        // Read array size and subarray length
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        // Read array elements
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // Calculate and print maximum unique integers
       // int maxUnique = maxUniqueIntegers(array, k);

        //Alternative solution
        int maxUnique = maxUniqueIntegersUsingLoop(array, k);

        System.out.println("Input Array :"+ Arrays.toString(array));
        System.out.println("Max unique count :"+maxUnique);

        scanner.close();
    }

    public static int maxUniqueIntegers(int[] arr, int k) {
        // HashSet to track unique integers
        HashSet<Integer> uniqueIntegers = new HashSet<>();

        // Queue to manage sliding window
        Queue<Integer> slidingWindow = new LinkedList<>();

        // Variable to store maximum unique count
        int maxUniqueCount = 0;

        // Iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // Add current element to the sliding window
            slidingWindow.add(arr[i]);
            uniqueIntegers.add(arr[i]);

            // Maintain the size of the sliding window
            if (slidingWindow.size() > k) {
                int removedElement = slidingWindow.poll();

                // Remove from unique set if not in window anymore
                if (!slidingWindow.contains(removedElement)) {
                    uniqueIntegers.remove(removedElement);
                }
            }

            // Update max unique count when window size is k
            if (slidingWindow.size() == k) {
                maxUniqueCount = Math.max(maxUniqueCount, uniqueIntegers.size());
            }
        }

        return maxUniqueCount;
    }


    /*
        Alternative solution using loops
        This simplified version:
        - Uses a nested loop approach
        - Directly creates a set for each subarray
        - Tracks the maximum number of unique integers
        - Has O(n*k) time complexity
        - Maintains readability and straightforward logic
     */
    public static int maxUniqueIntegersUsingLoop(int[] arr, int k) {
        int maxUnique = 0;

        for (int i = 0; i <= arr.length - k; i++) { // break the array into sub-array
            Set<Integer> uniqueSet = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                // for each sub array add the elements into a set
                uniqueSet.add(arr[j]);
            }
            // count max unique value
            maxUnique = Math.max(maxUnique, uniqueSet.size());
        }

        return maxUnique;
    }
}