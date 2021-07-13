import java.awt.image.BufferedImage;

/**
 * Class for rectangle shape.
 */
public class Oval extends GeneralShape {

  public Oval(String name) {
    super(name);
  }

  /**
   * Constructor for the oval class.
   * @param initX initial X value.
   * @param initY initial Y value.
   * @param initRed initial red value.
   * @param initGreen initial green value.
   * @param initBlue initial blue value.
   * @param rad1 1st radius value.
   * @param rad2 2nd radius value.
   */
  public Oval(double initX, double initY, double initRed, double initGreen,
              double initBlue, double rad1, double rad2) {
    super(initX, initY, initRed, initGreen, initBlue, rad1, rad2);
  }

  /**
   * Method to compute for area of an oval.
   * @return computed area of oval.
   */
  public double areaShape() {
    return Math.PI * getWidth() * getHeight();
  }

  /**
   * Method to compute for perimeter of an oval.
   * @return computed perimeter of oval.
   */
  public double perimeterShape() {
    return 2 * Math.PI * Math.sqrt((Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2)) / 2);
  }

  @Override
  public BufferedImage getImage(ShapeAttribute direction) {
    return null;
  }

  @Override
  public void loadImage(ShapeAttribute direction) {

  }
}
