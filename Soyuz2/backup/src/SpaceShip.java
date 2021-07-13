import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;

/**
 * Class for rectangle shape.
 */
public class SpaceShip extends GeneralShape {
  private BufferedImage image;
  private ShapeAttribute direction;
  String shipPath;


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
  public SpaceShip(double initX, double initY, double initRed,
                   double initGreen, double initBlue, double width, double height, ShapeAttribute direction) {
    super(initX, initY, initRed, initGreen, initBlue, width, height);
    loadImage(direction);
    this.direction = direction;

  }
  @Override
  public void changeDirection(ShapeAttribute direction) {
    this.direction = direction;
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
    this.direction = direction;
    loadImage(direction);
    return image;
  }

  @Override
  public void loadImage(ShapeAttribute direction) {

    String shipRightImagePath = "./src/resources/images/goingRight.png";
    String shipDownImagePath = "./src/resources/images/goingDown.png";
    String shipLeftImagePath = "./src/resources/images/goingLeft.png";
    String shipUpImagePath = "./src/resources/images/goingUp.png";


    if (direction == ShapeAttribute.DIRECTION_R) {
      shipPath = shipRightImagePath;
    }
    if (direction == ShapeAttribute.DIRECTION_D) {
      shipPath = shipDownImagePath;
    }
    if (direction == ShapeAttribute.DIRECTION_L) {
      shipPath = shipLeftImagePath;
    }
    if (direction == ShapeAttribute.DIRECTION_U) {
      shipPath = shipUpImagePath;
    }

    try {

        File file = new File(shipPath);
        this.image = ImageIO.read(file);


    } catch (IOException var6) {
      var6.printStackTrace();
    }
  }

}






