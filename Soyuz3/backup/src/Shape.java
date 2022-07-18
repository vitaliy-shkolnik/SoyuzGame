import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public interface Shape {

  /**
   * Method that gets a value of a specific Shape Attribute at a specific time.
   * @param time represents a unit of time. an Int.
   * @return a new State object containing values of a Shape object at a time stamp.
   */
  State atState(double time);

  /**
   * Method that add Events to a hashmap.
   * Events are sorted by time.
   * @param e represents an event. Class cs5004.animator.model.Event.
   * @throws IllegalArgumentException exception for event collision.
   */
  void addEvent(Event e);

  /**
   * Method that gets Events from a shape.
   * Events are sorted by time.
   *
   */
  Map<ShapeAttribute, List<Event>> getEvents();

  /**
   * Method to clone state.
   * @param s represents cs5004.animator.model.State. Of class cs5004.animator.model.State.
   * @return new cs5004.animator.model.State object.
   */
  State clone(State s);

  /**
   * Method to return the area of a shape.
   * @return a double; represents the area of a shape.
   */
  double areaShape();

  /**
   * Method to return the perimeter of a shape.
   * @return a double; represents the perimeter of a shape.
   */
  double perimeterShape();

  /**
   * Gets the name of the Shape.
   * @return name of shape.
   */
  String getName();

  /**
   * Last time that this Shape is defined on screen.
   * @return the time
   */
  int endTime();

  /**
   * Gets loaded sprite image.
   * @return bufferedImage.
   */
  BufferedImage getImage(double direction);

  /**
   * Loads image.
   */
  void loadImage(double direction);


  void changeDirection(double direction);
}


