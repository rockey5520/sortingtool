package sorting;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {

        //stage 2
        if (args.length == 0) {
            return;
        }
        Sorting sorting = new Sorting();
        if (args.length == 2 && args[0].contains("-dataType")) {
            sorting.setType(args[1].toUpperCase());
        }

        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        sorting.create(stringBuilder);
        sorting.display();

        /*
        // stage 1
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
        System.out.println("The greatest number: " + list.get(0) + " (" + frequency + " time(s)).");*/
    }
}
