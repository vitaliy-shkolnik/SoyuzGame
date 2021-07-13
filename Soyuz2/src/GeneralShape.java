import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class for cs5004.animator.model.shapes.GeneralShape.
 */
public abstract class GeneralShape implements Shape {
  protected final State initialState;
  protected final Map<ShapeAttribute, List<Event>> events = new HashMap<>();
  private final String name;
  private BufferedImage image;
  private double direction;
  protected double width;
  protected double height;
  protected int groupStartTime = 0;
  protected int groupEndTime = 0;


  protected GeneralShape(String name) {
    this.name = name;
    this.initialState = new State(0, 0, 0, 0, 0, 0, 0, 3);
  }

  protected GeneralShape(State s) {
    this.name = "";
    this.initialState = clone(s);
  }

  /**
   * Constructor for cs5004.animator.model.shapes.GeneralShape abstract class.
   *
   * @param initX     initial X value.
   * @param initY     initial Y value.
   * @param initRed   initial red color value.
   * @param initGreen initial green color value.
   * @param initBlue  initial blue color value.
   * @param width     width value.
   * @param height    width value.
   * @throws IllegalArgumentException exception for invalid values.
   */
  protected GeneralShape(double initX, double initY, double initRed, double initGreen,
                         double initBlue, double width,
                         double height, double direction) throws IllegalArgumentException {
    if (width < 0 || height < 0
            || initRed < 0 || initRed > 255 || initGreen < 0 || initGreen > 255 || initBlue < 0
            || initBlue > 255) {
      throw new IllegalArgumentException("Shape - Invalid input parameters");
    }
    this.name = "";
    this.initialState = new State(initX, initY, initRed, initGreen, initBlue,
            width, height, direction);
    this.width = width;
    this.height = height;
    this.direction = direction;
    //loadImage(ShapeAttribute.DIRECTION_R);
  }

  /**
   * Method to add an Event object to the HashMap events.
   *
   * @param newEvent an Event object.
   */
  @Override
  public void addEvent(Event newEvent) {
    //if the key doesn't exist already. Create key and new list.
    if (!events.containsKey(newEvent.attribute)) {
      events.put(newEvent.attribute, new ArrayList<>());
    }

    //get the list associated with the key.
    //list = list of interest.
    //if nothing inside list. Skip loop. to end. Add event.
    //because size of list is zero.
    List<Event> list = events.get(newEvent.attribute);
    // validate whether it's okay to add e into the list
    // iterate through list and insert at the right place
    for (int i = 0; i < list.size(); i++) {
      Event event = list.get(i);

      //remove comments to turn on overlap event handling.
      if (event.overlaps(newEvent)) {
        throw new IllegalArgumentException("This event collides with an existing event");
      }

      if (event.startTime > newEvent.startTime) {
        //inserts event at index i. Same index as event.
        list.add(i, newEvent);
        return;
      }
    }
    // newEvent is the latest event in the timeline, append to end
    list.add(newEvent);
  }

  /**
   * Method to get all the Events related to the shape.
   *
   * @return a Map containing all the shape's Events.
   */
  @Override
  public Map<ShapeAttribute, List<Event>> getEvents() {
    return events;
  }

  /**
   * Method to return the state of an object at a given time tick.
   *
   * @param time represents a unit of time. an Int.
   * @return State; a new State object containing the variables of that object at a given tick.
   */
  @Override
  public State atState(double time) {


    //List<Event> list = events.get(ShapeAttribute.POSITION_X);


    //calculate the values based on time instance.
    double x = calculateAttributeFor(time, ShapeAttribute.POSITION_X);
    double y = calculateAttributeFor(time, ShapeAttribute.POSITION_Y);
    double red = calculateAttributeFor(time, ShapeAttribute.RED);
    double green = calculateAttributeFor(time, ShapeAttribute.GREEN);
    double blue = calculateAttributeFor(time, ShapeAttribute.BLUE);
    double width = calculateAttributeFor(time, ShapeAttribute.WIDTH);
    double height = calculateAttributeFor(time, ShapeAttribute.HEIGHT);
    double direction = calculateAttributeFor(time, ShapeAttribute.DIRECTION);
    return new State(x, y, red, green, blue, width, height, direction);
  }

  /**
   * Method to clone a State object.
   *
   * @param s represents cs5004.animator.model.State. Of class cs5004.animator.model.State.
   * @return State; a new State object with the appropriate variables.
   */
  @Override
  public State clone(State s) {
    //s.valueFor(cs5004.animator.model.shapes.ShapeAttribute.POSITION_Y);
    //calculate the values based on time instance.
    double x = s.valueFor(ShapeAttribute.POSITION_X);
    double y = s.valueFor(ShapeAttribute.POSITION_Y);
    double red = s.valueFor(ShapeAttribute.RED);
    double green = s.valueFor(ShapeAttribute.GREEN);
    double blue = s.valueFor(ShapeAttribute.BLUE);
    double width = s.valueFor(ShapeAttribute.WIDTH);
    double height = s.valueFor(ShapeAttribute.HEIGHT);
    double direction = s.valueFor(ShapeAttribute.DIRECTION);
    return new State(x, y, red, green, blue, width, height, direction);
  }

  /**
   * Method to calculate the Attribute for a given attribute at a given tick time.
   *
   * @param time      an int; represents a time Tick.
   * @param attribute an enum; an enum of ShapeAttribute.
   * @return the current value of the given enum ShapeAttribute at the given time tick.
   */
  private double calculateAttributeFor(double time, ShapeAttribute attribute) {
    //grab the list.
    //you need to calculate even if events are empty
    //Perhaps there are no events. Return initialState values.
    List<Event> list = events.getOrDefault(attribute, Collections.emptyList());

    //iterate through list, which should be sorted by time
    // keep track of the "last" (temporal) value we saw for this attribute
    // imagine Red as init state. CurrentValue = Red.

    double currentValue = initialState.valueFor(attribute);

    for (Event event : list) {



      if (event.endLoop == 1) {
        groupEndTime = event.endTime;
      }
      if (event.startLoop == 1) {
        groupStartTime = event.startTime;
      }
    }

    for (Event event : list) {

      if (event.getLoop()) {
        //time = time % (modTime + 1);
        time = (time - groupStartTime)% (groupEndTime-groupStartTime) + groupStartTime;
        //(time - startTime)%timeDelta + startTime   double timeDelta = endTime - startTime;

      }


      if (event.startTime > time) {
        return currentValue;
      }
      if (event.contains(time)) {
        return event.valueAt(time);
      }
      //As you pass an event. Set it to endValue.
      currentValue = event.endValue;
    }
    return currentValue;
  }


  /**
   * Getter method for width of general shapes class.
   *
   * @return a double; represents the width values.
   */
  public double getWidth() {
    return width;
  }

  /**
   * Getter method for height of general shapes class.
   *
   * @return a double; represents the height values.
   */
  public double getHeight() {
    return height;
  }

  /**
   * Getter method for getting name.
   *
   * @return name. A string.
   */
  @Override
  public String getName() {
    return name;
  }

  @Override
  public int endTime() {
    // All event lists have the same length
    List<Event> events = this.events.get(ShapeAttribute.WIDTH);
    return events.get(events.size() - 1).endTime;
  }


  public void loadImage(ShapeAttribute direction) {


  }

  public BufferedImage getImage() {
    return this.image;
  }

  public void changeDirection(double direction) {
    this.direction = direction;
  }

}
