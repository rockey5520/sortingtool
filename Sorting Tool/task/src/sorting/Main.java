package sorting;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(final String[] args) {
    //stage 5
    if (args.length == 0) {
      return;
    }

    Map<String, String> arguments = new HashMap<>();
    for (int i = 0; i < args.length; ++i) {
      if (i + 1 < args.length) {
        arguments.put(args[i], args[i + 1]);
        ++i;
      } else {
        arguments.put(args[i], null);
      }
    }
    Sorting genericsSorting = new Sorting();
    for (var entry : arguments.entrySet()) {
      switch (entry.getKey()) {
        case "-dataType":
          if (entry.getValue() == null) {
            System.out.println("No data type defined!");
            return;
          }
          genericsSorting.setType(entry.getValue());
          break;
        case "-sortingType":
          if (entry.getValue() == null) {
            System.out.println("No sorting type defined!");
            return;
          }
          genericsSorting.setSorting(entry.getValue());
          break;
        default:
          System.out.println("\"" + entry.getKey() + "\" isn't a valid parameter. It's skipped.");
      }
    }

    Scanner scanner = new Scanner(System.in);
    StringBuilder stringBuilder = new StringBuilder();
    while (scanner.hasNextLine()) {
      stringBuilder.append(scanner.nextLine()).append("\n");
    }
    genericsSorting.inclusive(stringBuilder);
    genericsSorting.sort();
    /* //stage 4
    if (args.length == 0) {
      return;
    }

    Map<String, String> arguments = new HashMap<>();
    for (int i = 0; i < args.length; ++i) {
      if (i + 1 < args.length) {
        arguments.put(args[i], args[i + 1]);
        ++i;
      } else {
        arguments.put(args[i], null);
      }
    }

    Sorting genericsSorting = new Sorting();
    Scanner scanner = new Scanner(System.in);
    StringBuilder stringBuilder = new StringBuilder();
    while (scanner.hasNextLine()) {
      stringBuilder.append(scanner.nextLine()).append("\n");
    }

    genericsSorting.setSorting(Nature.NATURAL);
    genericsSorting.setType(Type.WORD);

    for (var entry : arguments.entrySet()) {
      switch (entry.getKey()) {
        case "-dataType":
          genericsSorting.setType(entry.getValue());
          break;
        case "-sortingType":
          genericsSorting.setSorting(entry.getValue());
          break;
      }
    }
    genericsSorting.inlcusive(stringBuilder);
    genericsSorting.sort();
  }*/
      /*  //stage 3
        if (args.length == 0) {
            return;
        }
        Map<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "-dataType":
                    if (i + 1 < args.length) {
                        arguments.put(args[i], args[i + 1]);
                    }
                    break;
                case "-sortIntegers":
                    arguments.put(args[i], "");
                    break;
            }
        }

        Sorting Sorting = new Sorting();
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }

        for (var entry : arguments.entrySet()) {
            switch (entry.getKey()) {
                case "-dataType":
                    Sorting.setType(Type.valueOf(entry.getValue().toUpperCase()));
                    break;
                case "-sortIntegers":
                    Sorting.setType(Type.LONG);
                    break;
            }
        }

        Sorting.include(stringBuilder);

        if (arguments.containsKey("-dataType")
            && arguments.containsKey("-sortIntegers")) {
            Sorting.sort();
        } else {
            for (var entry : arguments.entrySet()) {
                switch (entry.getKey()) {
                    case "-dataType":
                        Sorting.show();
                        break;
                    case "-sortIntegers":
                        Sorting.sort();
                        break;
                }
            }
        }*/

    /*    //stage 2
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
        sorting.display();*/

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