package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          if (nums[i] + nums[j] + nums[k] == 0 && i != j && j != k) {
            List<Integer> listOriginal = new ArrayList<>();
            listOriginal.add(nums[i]);
            listOriginal.add(nums[j]);
            listOriginal.add(nums[k]);
            List<Integer> listTemp = new ArrayList<>();
            listTemp.add(nums[i]);
            listTemp.add(nums[j]);
            listTemp.add(nums[k]);
            Collections.sort(listTemp);
            if (!res.contains(listTemp) && !res.contains(listOriginal) && listTemp != listOriginal) {
              List<Integer> list = new ArrayList<Integer>();
              list.add(nums[i]);
              list.add(nums[j]);
              list.add(nums[k]);
              res.add(list);
            }

          }
        }
      }

    }
    return res;
  }
}
