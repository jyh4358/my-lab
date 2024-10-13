package com.jddng.programers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class Level1SolutionTest {

  /**
   * @see <a
   * href="https://school.programmers.co.kr/learn/courses/30/lessons/131128?language=java">숫자
   * 짝궁</a>
   */
  @Test
  void 숫자_짝궁() {
    String X = "12321";
    String Y = "42531";
    String result = "";
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
      result = "0";
    }
    if (sb.toString().equals("")) {
      result = "-1";
    }

    result = sb.toString();

    System.out.println("result = " + result);
  }

  @Test
  void 콜라_문제() {
    int a = 2;
    int b = 1;
    int n = 20;
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

    System.out.println("givenCnt = " + givenCnt);
  }

  @Test
  void 옹알이_2() {
    String[] babbling = new String[]{"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
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

    System.out.println("answer = " + answer);
  }
}