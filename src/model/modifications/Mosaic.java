package model.modifications;

import model.imageclasses.Image;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

/**
 * Class used to turn a image into a mosaic like version of itself. The image will appear to
 * have a "stained glass" effect, which is heavily based on the number of seeds.
 */
public class Mosaic implements IModifyImage {
  private final int numSeeds;

  /**
   * Creates a new Mosaic object with the given number of seeds.
   * @param numSeeds The given number of seeds
   */
  public Mosaic(int numSeeds) {
    this.numSeeds = numSeeds;
  }

  @Override
  public void apply(Image img) {
    System.out.println("Generating unique random seeds");
    Point2D[] seedArr = this.generateSeeds(numSeeds, img.getWidth(), img.getHeight());
    System.out.println("Mapping pixels to their closest seeds");
    Map<MyPoint, Point2D> pixelToClosestSeed = this.mapPixelsToSeeds(img, seedArr);
    System.out.println("Determining the average color values of pixels with the same seed");
    Map<MyPoint, Color> pixelToAverage = this.mapPixelsToAverage(img, seedArr, pixelToClosestSeed);

    System.out.println("Applying changes");
    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        img.setColorAt(x, y, pixelToAverage.get(new MyPoint(x, y)));
      }
    }
  }

  /**
   * Generates an array of unique, random Point2D objects that represent the seeds to use in the
   * mosaic.
   * @param numSeeds The number of seeds to generate
   * @param width The width of the picture
   * @param height The height of the picture
   * @return A Point2D[] where each Point2D is a unique seed
   */
  private Point2D[] generateSeeds(int numSeeds, int width, int height) {
    Point2D[] pointArray = new Point2D[numSeeds];
    Set<Point2D> uniqueSet = new HashSet<Point2D>();
    int sizeBefore;
    int sizeAfter;
    Random rand = new Random();
    for (int i = 0; i < numSeeds; i++) {
      sizeBefore = uniqueSet.size();
      int xCord = rand.nextInt(width);
      int yCord = rand.nextInt(height);
      Point2D pointToAdd = new Point(xCord, yCord);
      uniqueSet.add(pointToAdd);
      sizeAfter = uniqueSet.size();
      if (sizeBefore == sizeAfter - 1) {
        //The point was unique, can add it to the array
        pointArray[i] = pointToAdd;
      } else {
        //Point was not unique, start over
        i--;
      }
    }
    return pointArray;
  }

  /**
   * Maps the pixels in the given image (which are represented as MyPoint) to the seed that
   * is closest.
   * @param img The image which pixels will be used to map to Point2D objects
   * @return A HashMap from MyPoint to Point2D
   */
  private Map<MyPoint, Point2D> mapPixelsToSeeds(Image img, Point2D[] seeds) {
    Map<MyPoint, Point2D> pixelToSeed = new HashMap<MyPoint, Point2D>();
    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        double closestSoFar = img.getHeight() * img.getWidth();
        Point2D toReturn = new Point(-1, -1);
        for (Point2D seed : seeds) {
          double closestCurrent = seed.distance(x, y);
          if (closestCurrent < closestSoFar) {
            closestSoFar = closestCurrent;
            toReturn = seed;
          }
        }
        //System.out.println("adding point " + new MyPoint(x,y) + "with seed " + toReturn);
        pixelToSeed.put(new MyPoint(x, y), toReturn);
      }
    }
    return pixelToSeed;
  }

  /**
   * Maps each pixel in the given image to a color, which represents the average of that pixel
   * and every other pixel that is closest to the same seed.
   * @param img The given image
   * @param seedArr The array of seeds that are used to determine which pixels should be grouped
   * @param pixelToSeed A mapping from each pixel to its closest seed
   * @return A Map from MyPoint to Color which represents how each pixel needs to be changed
   */
  private Map<MyPoint, Color> mapPixelsToAverage(Image img, Point2D[] seedArr,
                                                 Map<MyPoint, Point2D> pixelToSeed) {
    Map<MyPoint, Color> pointToAverage = new HashMap<MyPoint, Color>();
    MyTempAverage[] averageArr = new MyTempAverage[seedArr.length];
    for (int i = 0; i < averageArr.length; i++) {
      averageArr[i] = new MyTempAverage(seedArr[i]);
    }

    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        Point2D mappedSeed = pixelToSeed.get(new MyPoint(x,y));
        for (MyTempAverage avg : averageArr) {
          if (avg.matchSeed(mappedSeed)) {
            Color c = img.getColorAt(x, y);
            avg.addToTotal(c.getRed(), c.getBlue(), c.getGreen());
          }
        }
      }
    }

    Map<Point2D, Color> seedToAverage = new HashMap<Point2D, Color>();
    for (MyTempAverage avg : averageArr) {
      seedToAverage.put(avg.getSeed(),
          new Color((int)avg.rAvg(), (int)avg.gAvg(), (int)avg.bAvg()));
    }

    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        Point2D closestSeed = pixelToSeed.get(new MyPoint(x,y));
        pointToAverage.put(new MyPoint(x,y), seedToAverage.get(closestSeed));
      }
    }
    return pointToAverage;
  }

  /**
   * Simple class used to represent the coordinates of a pixel.
   */
  private static class MyPoint {
    private final int x;
    private final int y;

    /**
     * Creates a MyPoint object and sets the x and y fields.
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public MyPoint(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof MyPoint)) {
        return false;
      }
      MyPoint other = (MyPoint) o;
      return other.x == this.x && other.y == this.y;
    }

    @Override
    public int hashCode() {
      return this.x * 1000 + this.y * 1000;
    }
  }

  /**
   * Stores information about the Colors of pixels and the seed that are closest to them.
   * Provides an easy way to calculate the average Color of all the pixels that have been added
   * to the object.
   */
  private static class MyTempAverage {
    private double rTotal;
    private double gTotal;
    private double bTotal;
    private int count;
    private final Point2D seed;

    /**
     * Creates a MyTempAverage object with the given seed. Sets the count, and all the r g b values
     * to 0.
     * @param seed The seed associated with this object
     */
    public MyTempAverage(Point2D seed) {
      this.rTotal = 0;
      this.gTotal = 0;
      this.bTotal = 0;
      this.count = 0;
      this.seed = seed;
    }

    /**
     * Adds the given r g b values to their respective fields. Increments count as information about
     * a new pixel has been added.
     * @param r The red value to be added
     * @param g The green value to be added
     * @param b The blue value to be added
     */
    public void addToTotal(double r, double g, double b) {
      this.rTotal += r;
      this.gTotal += g;
      this.bTotal += b;
      count++;
    }

    /**
     * Determines if the given seed matches this one.
     * @param seed The given seed
     * @return True if the seed has the same x and y value as this one, false otherwise
     */
    public boolean matchSeed(Point2D seed) {
      return this.seed.equals(seed);
    }

    /**
     * Computes the average of the red component of the color that this object represents.
     * @return Total red value divided by the count
     */
    public double rAvg() {
      return this.rTotal / count;
    }

    /**
     * Computes the average of the green component of the color that this object represents.
     * @return Total green value divided by the count
     */
    public double gAvg() {
      return this.gTotal / count;
    }

    /**
     * Computes the average of the blue component of the color that this object represents.
     * @return Total blue value divided by the count
     */
    public double bAvg() {
      return this.bTotal / count;
    }

    /**
     * Creates a copy of this seed and returns it.
     * @return A seed with the same x and y value as this one
     */
    public Point2D getSeed() {
      return new Point((int)seed.getX(), (int)seed.getY());
    }
  }
}
