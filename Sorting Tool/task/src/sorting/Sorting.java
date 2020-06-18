package sorting;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Sorting {

  private DataSortingType dataSortingType;
  private List<String> stringList;
  private Type type;
  private Map<String, Integer> maxcount;

  public Sorting() {
    this.type = Type.WORD;
    this.dataSortingType = DataSortingType.NATURAL;
    this.stringList = new ArrayList<>();
    this.maxcount = new TreeMap<>();
  }

  public void setType(String type) {
//        if (type == null) {
//            this.type = DataType.WORD;
//        } else {
    try {
      this.type = Type.valueOf(type.toUpperCase());
    } catch (Exception e) {
      e.printStackTrace();
    }
//        }
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public DataSortingType getDataSortingType() {
    return dataSortingType;
  }

  public void setDataSortingType(DataSortingType dataSortingType) {
    this.dataSortingType = dataSortingType;
  }

  public void setSorting(String sorting) {
//        if (sorting == null) {
//            this.sorting = DataSorting.NATURAL;
//        } else {
    try {
      this.dataSortingType = DataSortingType.valueOf(sorting.toUpperCase());
    } catch (Exception e) {
      e.printStackTrace();
    }
//        }
  }

  public void inclusive(StringBuilder stringBuilder) {
    switch (type) {
      case LINE:
        for (String item : stringBuilder.toString().split("\n")) {
          inclusive(item);
        }
        break;
      case WORD:
        for (String item : stringBuilder.toString().split("\\s+")) {
          inclusive(item);
        }
        break;
      case LONG:
        for (String item : stringBuilder.toString().split("\\s+")) {
          if (item.matches("-?\\d+")) {
            inclusive(item);
          } else {
            System.out.println("\"" + item + "\" isn't a long. It's skipped.");
          }
        }
        break;
    }
  }

  public void inclusive(String value) {
    stringList.add(value);
    if (maxcount.containsKey(value)) {
      maxcount.replace(value, maxcount.get(value) + 1);
    } else {
      maxcount.put(value, 1);
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
    switch (dataSortingType) {
      case NATURAL:
        System.out.format(string + "Sorted data:", size);
        for (String item : stringList) {
          System.out.print(" " + item);
        }
        break;
      case BYCOUNT:
        Map<String, Integer> mapSort = new LinkedHashMap<>();
        for (String item : stringList) {
          mapSort.put(item, maxcount.get(item));
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
    LINE("LINE"),
    WORD("WORD"),
    LONG("LONG");

    private String type;

    Type(String type) {
      this.type = type;
    }
  }

  public enum DataSortingType {
    NATURAL,
    BYCOUNT
  }
}