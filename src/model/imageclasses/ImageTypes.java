package model.imageclasses;

public enum ImageTypes {
    JPG(".JPG"), PNG(".PNG"), PPM(".PPM");
    private String type;
    private ImageTypes(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
