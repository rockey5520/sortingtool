import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

  static int counter = 0;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    List<String> ints = new ArrayList<>();
    List<Integer> updatedInts = new ArrayList<>();
    while (scanner.hasNext()) {
      ints.add(scanner.nextLine());
    }
    for (String s : ints
    ) {
      String[] s1 = s.split(" ");
      Integer[] s2 = new Integer[s1.length];
      for (int i = 0; i < s1.length; i++) {
        s2[i] = Integer.valueOf(s1[i]);
      }
      updatedInts.addAll(Arrays.asList(s2).subList(1, s2.length));
    }
    updatedInts.sort(Collections.reverseOrder());
    for (Integer integer : updatedInts
    ) {
      System.out.print(integer + " ");
    }

  }

}