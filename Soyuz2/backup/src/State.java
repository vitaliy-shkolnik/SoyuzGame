import java.util.HashMap;
import java.util.Map;

public class State {
  private final Map<ShapeAttribute, Double> attributes = new HashMap<>();

  /**
   * Constructor class for the cs5004.animator.model.State().
   * @param posX position X.
   * @param posY position Y.
   * @param red red color value.
   * @param green green color value.
   * @param blue blue color value.
   * @param width width value.
   * @param height height value.
   */
  public State(double posX, double posY, double red,
               double green, double blue, double width, double height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("cs5004.animator.model.State - Invalid input values");
    }
    this.attributes.put(ShapeAttribute.POSITION_X, posX);
    this.attributes.put(ShapeAttribute.POSITION_Y, posY);
    this.attributes.put(ShapeAttribute.RED, red);
    this.attributes.put(ShapeAttribute.GREEN, green);
    this.attributes.put(ShapeAttribute.BLUE, blue);
    this.attributes.put(ShapeAttribute.WIDTH, width);
    this.attributes.put(ShapeAttribute.HEIGHT, height);
  }

  /**
   * Returns specific attributes necessary to render a shape at a particular time.
   * @param attribute attribute parameter.
   * @return return specific attribute.
   */
  public double valueFor(ShapeAttribute attribute) {
    return this.attributes.get(attribute);
  }

  /**
   * The toString method use for cs5004.animator.model.State() behaviour.
   * @return string value.
   */
  @Override
  public String toString() {
    return String.format("POSITION_X: %.1f, POSITION_Y: %.1f, RED: %.1f, GREEN: %.1f, "
                    + "BLUE: %.1f, WIDTH: %.1f, HEIGHT: %.1f",
            this.attributes.get(ShapeAttribute.POSITION_X),
            this.attributes.get(ShapeAttribute.POSITION_Y),
            this.attributes.get(ShapeAttribute.RED),
            this.attributes.get(ShapeAttribute.GREEN),
            this.attributes.get(ShapeAttribute.BLUE),
            this.attributes.get(ShapeAttribute.WIDTH),
            this.attributes.get(ShapeAttribute.HEIGHT));
  }
}
