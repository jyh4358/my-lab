package com.jddng;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LeanStream {

  /**
   * <pre>
   *   1. grouping
   *   2. Function.identity
   *     - 자기 자신 반환
   *   3. Map sort
   *   4. LinkedHashMap 생성
   * </pre>
   * groupBing, toMap
   */
  private void 그룹핑(String str) {
    Arrays.stream(str.split(""))
        .map(Integer::parseInt)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)))
        .entrySet()
        .stream()
        // Map sorting
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        // LinkedHashMap 컬렉션 생성
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new
        ));
  }



}
