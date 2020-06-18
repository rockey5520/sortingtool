package sorting;

public enum Type {
  LINE("line"),
  WORD("word"),
  LONG("long");

  private final String type;

  Type(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
