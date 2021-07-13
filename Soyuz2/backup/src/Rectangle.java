import java.awt.image.BufferedImage;

/**
 * Class for rectangle shape.
 */
public class Rectangle extends GeneralShape {

  public Rectangle(String name) {
    super(name);
  }

  /**
   * constructor for the rectangle class.
   * @param initX initial X value.
   * @param initY initial Y value.
   * @param initRed initial red value.
   * @param initGreen initial green value.
   * @param initBlue initial blue value.
   * @param width width value.
   * @param height height value.
   */
  public Rectangle(double initX, double initY, double initRed,
                   double initGreen, double initBlue, double width, double height) {
    super(initX, initY, initRed, initGreen, initBlue, width, height);
  }

  /**
   * Method to compute for area of a rectangle.
   * @return computed area of rectangle.
   */
  @Override
  public double areaShape() {
    return getWidth() * getHeight();
  }

  /**
   * Method to compute for perimeter of a rectangle.
   * @return computed perimeter of rectangle.
   */
  @Override
  public double perimeterShape() {
    return 2 * (getWidth() + getHeight());
  }

  @Override
  public BufferedImage getImage(ShapeAttribute direction) {
    return null;
  }

  @Override
  public void loadImage(ShapeAttribute direction) {

  }

}






