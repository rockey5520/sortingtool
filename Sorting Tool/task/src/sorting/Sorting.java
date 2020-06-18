package sorting;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Sorting {

  private final List<String> stringList;
  private final Map<String, Integer> maxCount;
  private SortingOrder sortingOrder;
  private InputType inputType;

  private Type type;
  private String inputName;
  private OutputType outputType;
  private String outputName;

  public Sorting() {
    this.type = Type.WORD;
    this.sortingOrder = SortingOrder.NATURAL;
    this.inputType = InputType.SCANNER;
    this.outputType = OutputType.SCANNER;
    this.stringList = new ArrayList<>();
    this.maxCount = new TreeMap<>();
  }

  public void setType(String type) {
    try {
      this.type = Type.valueOf(type.toUpperCase());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setSortingOrder(String sosortingOrder) {
    try {
      this.sortingOrder = SortingOrder.valueOf(sosortingOrder.toUpperCase());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setInputType(InputType inputType) {
    this.inputType = inputType;
  }

  public void setOutputType(OutputType outputType) {
    this.outputType = outputType;
  }

  public void setInputName(String inputName) {
    this.inputName = inputName;
  }

  public void setOutputName(String outputName) {
    this.outputName = outputName;
  }

  public void execute() {
    Scanner scannerInput = null;
    switch (inputType) {
      case SCANNER:
        scannerInput = new Scanner(System.in);
        break;
      case INPUTFILE:
        scannerInput = new Scanner(inputName);
        break;
    }
    try {
      StringBuilder stringBuilder = new StringBuilder();
      while (scannerInput.hasNextLine()) {
        stringBuilder.append(scannerInput.nextLine()).append("\n");
      }
      include(stringBuilder);
      scannerInput.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    switch (outputType) {
      case SCANNER:
        System.setOut(System.out);
        break;
      case OUTPUTFILE:
        try {
          System.setOut(new PrintStream(new FileOutputStream(outputName)));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        break;
    }
  }

  private void include(StringBuilder stringBuilder) {
    switch (type) {
      case LINE:
        for (String item : stringBuilder.toString().split("\n")) {
          include(item);
        }
        break;
      case WORD:
        for (String item : stringBuilder.toString().split("\\s+")) {
          include(item);
        }
        break;
      case LONG:
        for (String item : stringBuilder.toString().split("\\s+")) {
          if (item.matches("-?\\d+")) {
            include(item);
          } else {
            System.out.println("\"" + item + "\" isn't a long. It's skipped.");
          }
        }
        break;
    }
  }

  private void include(String value) {
    stringList.add(value);
    if (maxCount.containsKey(value)) {
      maxCount.replace(value, maxCount.get(value) + 1);
    } else {
      maxCount.put(value, 1);
    }
  }

  public void sort() {
    String string = "";
    int size = stringList.size();
    switch (type) {
      case LINE:
        Collections.sort(stringList);
        string = "Total lines: %d.\n";
        break;
      case WORD:
        Collections.sort(stringList);
        string = "Total words: %d.\n";
        break;
      case LONG:
        List<Long> array = new ArrayList<>();
        for (String item : stringList) {
          array.add(Long.parseLong(item));
        }
        Collections.sort(array);
        stringList.clear();
        for (Long item : array) {
          stringList.add(String.valueOf(item));
        }
        string = "Total numbers: %d.\n";
        break;
    }
    switch (sortingOrder) {
      case NATURAL:
        System.out.format(string + "Sorted data:", size);
        for (String item : stringList) {
          System.out.print(" " + item);
        }
        break;
      case BYCOUNT:
        Map<String, Integer> mapSort = new LinkedHashMap<>();
        for (String item : stringList) {
          mapSort.put(item, maxCount.get(item));
        }

        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        mapSort.entrySet().stream().sorted(Map.Entry.comparingByValue())
            .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        System.out.format(string, size);
        for (var item : reverseSortedMap.entrySet()) {
          int weight = 100 * item.getValue() / size;
          System.out.println(item.getKey() + ": " + item.getValue() + " time(s), " + weight + "%");
        }
        break;
    }
  }

  public enum Type {
    LINE,
    WORD,
    LONG
  }

  public enum SortingOrder {
    NATURAL,
    BYCOUNT
  }

  public enum InputType {
    SCANNER,
    INPUTFILE
  }

  public enum OutputType {
    SCANNER,
    OUTPUTFILE
  }
}