package model.imageclasses;

/**
 * Enumeration of the different Image types that are currently supported.
 */
public enum ImageTypes {
  JPG(".JPG"), PNG(".PNG"), PPM(".PPM");
  private String type;

  ImageTypes(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
