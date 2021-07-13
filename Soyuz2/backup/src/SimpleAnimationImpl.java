import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;




/**
 * cs5004.animator.model.SimpleAnimationImpl class to represent animation. List stores all Shapes.
 * Each Shape object contains events SimpleAnimation Impl
 * can list Shapes, addShape, removeShape.
 */
public class SimpleAnimationImpl implements SimpleAnimation {
  // private final maybe the XY resolution for this screen
  protected final List<Shape> shapes = new ArrayList<>();


  /**
   * SimpleAnimationImpl constructor.
   */
  public SimpleAnimationImpl() {
  }




  /**
   * Method to return the List of shapes.
   * @return shapes; an ArrayList containing Shape objects.
   */
  @Override
  public List<Shape> listShapes() {
    return shapes;
  }

  /**
   * Method to add shapes to the ArrayList.
   *
   * @param shape represents a shape. Of cs5004.animator.model.shapes.Shape class.
   */
  @Override
  public void addShape(Shape shape) {
    shapes.add(shape);
  }

  /**
   * Method to insert a shape into the ArrayList.
   * @param index represents index. An int.
   * @param shape represents shape. Of Shape class.
   * @throws IllegalArgumentException if the index is out of range.
   */
  @Override
  public void insertShape(int index, Shape shape) throws IllegalArgumentException {
    if (index < 0 || index > shapes.size()) {
      throw new IllegalArgumentException("your index is out of range.");
    }
    shapes.add(index, shape);
  }

  /**
   * Method to remove a shape from the list.
   * @param shape represents a shape. Of cs5004.animator.model.shapes.Shape class.
   * @return boolean; returns true if the size of the list is greater than shapes.size.
   */
  @Override
  public boolean removeShape(Shape shape) {
    int sizeList = shapes.size();
    shapes.remove(shape);
    return sizeList > shapes.size();
  }

  /**
   * Method to return the frame at a time Tick.
   * @param time an int; represents the time currently being viewed.
   * @return states; a list of State objects.
   * @throws IllegalArgumentException if the entered time is less than 0.
   */
  @Override
  public List<State> frameAtTic(int time) throws IllegalArgumentException {
    if (time < 0) {
      throw new IllegalArgumentException("time cannot be less than 0");
    }

    List<State> states = new ArrayList<>();
    for (Shape shape : shapes) {
      states.add(shape.atState(time));
    }
    return states;
  }

  /**
   * Method to get the shape at a given index in the list.
   * @param index an int; represents the index of a list.
   * @return Shape; returns a Shape object.
   * @throws IllegalArgumentException if the entered int is out of index.
   */
  @Override
  public Shape getShape(int index) throws IllegalArgumentException {
    //TODO: Add throw here. Out of index.
    if (index < 0 || index > shapes.size()) {
      throw new IllegalArgumentException("your index is out of range.");
    }
    return shapes.get(index);
  }

  /**
   * Method to get a Shape equal to the inputted String.
   * @param name represents the name of the object. A string.
   * @return a Shape object.
   */
  @Override
  public Shape getShape(String name) throws IllegalArgumentException {

    for (Shape shape : shapes) {
      if (shape.getName().equals(name)) {
        return shape;
      }
    }
    return null;
  }


  /**
   * Method to get the end Time of a shape.
   * @return the end time. an int.
   */
  @Override
  public int endTime() {
    int latestTick = 0;
    for (Shape shape : shapes) {
      latestTick = Math.max(shape.endTime(), latestTick);
    }
    return latestTick;
  }
}