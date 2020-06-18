package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        var list = new ArrayList();
        int counter = 0;
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            list.add(number);
            counter++;
        }
        list.sort(Collections.reverseOrder());
        int frequency = Collections.frequency(list, list.get(0));
        System.out.println("Total numbers: " + counter);
        System.out.println("The greatest number: " + list.get(0) + " (" + frequency + " time(s)).");
    }
}
