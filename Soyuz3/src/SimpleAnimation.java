import java.util.List;


/**
 * Interface for Simple Animation.
 */
public interface SimpleAnimation {

  /**
   * Method to return a list of shapes.
   * @return a list of Shapes.
   */
  List<Shape> listShapes();

  /**
   * Adds Shape to list shapes.
   * @param shape represents a shape. Of cs5004.animator.model.shapes.Shape class.
   */
  void addShape(Shape shape);

  /**
   * Inserts Shape at specific index of list shapes.
   * @param index represents index. An int.
   * @param shape represents shape. Of Shape class.
   */
  void insertShape(int index, Shape shape);

  /**
   * Removes shape from list. return true if shape success.
   * @param shape represents a shape. Of cs5004.animator.model.shapes.Shape class.
   */
  boolean removeShape(Shape shape);

  /**
   * Method to extrapolate values of the animation based on tick.
   * @param time represents time. an int.
   * @return State object containing a frame of the entire animation.
   */
  List<State> frameAtTic(int time);

  /**
   * Gets the shape at at an index.
   * @param index represents the index of a list.
   * @return Shape. Of class Shape.
   */
  Shape getShape(int index);

  /**
   * Method to get shape using it's name.
   * @param name represents the name of the object. A string.
   * @return Shape object. A shape.
   */
  Shape getShape(String name);

  int endTime();

}