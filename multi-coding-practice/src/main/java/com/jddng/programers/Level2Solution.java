package com.jddng.programers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Level2Solution {

  /**
   * @see <a
   * href="https://school.programmers.co.kr/learn/courses/30/lessons/131128?language=java">숫자
   * 짝궁</a>
   */
  public String 숫자_짝궁(String X, String Y) {
    Map<Integer, Integer> xMap = Arrays.stream(X.split(""))
        .map(Integer::parseInt)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new
        ));

    Map<Integer, Integer> yMap = Arrays.stream(Y.split(""))
        .map(Integer::parseInt)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new
        ));
    StringBuilder sb = new StringBuilder();
    for (Entry<Integer, Integer> entry : xMap.entrySet()) {
      int maxValue = Math.min(entry.getValue(), yMap.getOrDefault(entry.getKey(), 0));
      for (int i = 0; i < maxValue; i++) {
        sb.append(entry.getKey());
      }
    }

    if (sb.toString().startsWith("0")) {
      return "0";
    }
    if (sb.toString().equals("")) {
      return "-1";
    }
    return sb.toString();
  }

  /**
   * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/132267">콜라 문제</a>
   */
  public int 콜라_문제(int a, int b, int n) {
    // a : 마트에 줘야하는 수
    // b : 마트가 주는 수
    // n : 내가 갖고있는 수
    int currentCnt = n;
    int givenCnt = 0;

    // 1. 현재 병수를 a로 나누어 몫을 마트에 가져다 준다.
    while (currentCnt >= a) {
      int quotient = currentCnt / a;
      int remainder = currentCnt % a;

      givenCnt += quotient * b;
      currentCnt = quotient * b + remainder;
    }

    return givenCnt;
  }

  /**
   * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/133499">옹알이 (2)</a>
   */
  public int 옹알이_2(String[] babbling) {
    int answer = 0;

    for (int i = 0; i < babbling.length; i++) {
      // 연속발음될 경우 예외처리
      if (babbling[i].contains("ayaaya")
          || babbling[i].contains("yeye")
          || babbling[i].contains("woowoo")
          || babbling[i].contains("mama")) {
        continue;
      }

      babbling[i] = babbling[i].replace("aya", " ");
      babbling[i] = babbling[i].replace("ye", " ");
      babbling[i] = babbling[i].replace("woo", " ");
      babbling[i] = babbling[i].replace("ma", " ");
      babbling[i] = babbling[i].replace(" ", "");

      if (babbling[i].length() == 0) {
        answer++;
      }
    }

    return answer;
  }

  /**
   * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/133502">햄버거 만들기</a>
   */
  private static final int[] RECIPE = {1, 2, 3, 1};
  private static final int RECIPE_LENGTH = RECIPE.length;
  public int 햄버거_만들기(int[] ingredient) {
    int count = 0;
    int n = ingredient.length;
    int[] stack = new int[n];
    int stackIndex = 0;

    for (int i = 0; i < n; i++) {
      stack[stackIndex++] = ingredient[i];

      if (stackIndex >= RECIPE_LENGTH && isRecipe(stack, stackIndex)) {
        stackIndex -= RECIPE_LENGTH;
        count++;
      }
    }

    return count;
  }
  private boolean isRecipe(int[] stack, int stackIndex) {
    for (int i = 0; i < RECIPE_LENGTH; i++) {
      if (stack[stackIndex - RECIPE_LENGTH + i] != RECIPE[i]) {
        return false;
      }
    }
    return true;
  }
}
