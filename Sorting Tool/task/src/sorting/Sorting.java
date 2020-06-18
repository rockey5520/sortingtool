package sorting;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sorting {

  private Type type;
  private List<String> list;
  private Deque<String> maxValue;
  private Map<String, Integer> maxCount;

  public Sorting() {
    this.type = Type.WORD;
    this.list = new ArrayList<>();
    this.maxValue = new ArrayDeque<>();
    this.maxCount = new HashMap<>();
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public void setType(String type) {
    try {
      this.type = Type.valueOf(type);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void create(StringBuilder stringBuilder) {
    switch (type) {
      case LINE:
        for (String item : stringBuilder.toString().split("\n")) {
          create(item);
        }
        break;
      case WORD:
      case LONG:
        for (String item : stringBuilder.toString().split("\\s+")) {
          create(item);
        }
        break;
    }
  }

  public void create(String value) {
    list.add(value);
    if (maxValue.size() > 0) {
      switch (type) {
        case WORD:
        case LINE:
          if (value.length() > maxValue.peek().length()) {
            maxValue.push(value);
          }
          break;
        case LONG:
          assert maxValue.peek() != null;
          if (Long.parseLong(value) > Long.parseLong(maxValue.peek())) {
            maxValue.push(value);
          }
          break;
      }
    } else {
      maxValue.push(value);
    }
    if (maxCount.containsKey(value)) {
      maxCount.replace(value, maxCount.get(value) + 1);
    } else {
      maxCount.put(value, 1);
    }
  }

  public void display() {
    String string = "";
    int size = list.size();
    int time = maxCount.get(maxValue.peek());
    int weight = 100 * time / size;
    switch (type) {
      case LINE:
        string = "Total lines: %d\nThe longest line:\n%s\n(%d + time(s), %d%%).";
        break;
      case WORD:
        string = "Total words: %d\nThe longest word: %s (%d + time(s), %d%%).";
        break;
      case LONG:
        string = "Total numbers: %d\nThe greatest number: %s (%d + time(s), %d%%).";
        break;
    }
    System.out.format(string, size, maxValue.peek(), time, weight);
  }
}