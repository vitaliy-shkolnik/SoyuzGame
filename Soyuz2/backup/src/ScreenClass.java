import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenClass {
  protected final Map<ShapeAttribute, List<Event>> events = new HashMap<>();

  ScreenClass() {

  }

  public SimpleAnimation createScreenOne() {
    SimpleAnimation shapeCollection = new SimpleAnimationImpl();


    Shape borderLeft = new Rectangle(0, 0, 255, 0, 0, 5, 600);
    Shape borderTop = new Rectangle(0, 0, 255, 0, 0, 600, 5);

    Shape rectangle = new Rectangle(125,125,255,255,0, 125, 125);
    Event event0 = new Event(ShapeAttribute.POSITION_X, 10, 30, 125,
            325, true, 1, 0, ShapeAttribute.DIRECTION_NA);
    Event event1 = new Event(ShapeAttribute.POSITION_Y, 30, 60, 125,
            325, true, 0, 0, ShapeAttribute.DIRECTION_NA);
    Event event2 = new Event(ShapeAttribute.POSITION_Y, 60, 90, 325,
            125, true, 0, 0, ShapeAttribute.DIRECTION_NA);
    Event event3 = new Event(ShapeAttribute.POSITION_X, 90, 120, 325,
            125, true, 0, 1, ShapeAttribute.DIRECTION_NA);

    rectangle.addEvent(event0);
    rectangle.addEvent(event1);
    rectangle.addEvent(event2);
    rectangle.addEvent(event3);
    Shape oval = new Oval(500,500,0,255,0, 125, 125);
    Event event4 = new Event(ShapeAttribute.POSITION_X, 0, 50, 500,
            325, false, 1, 1, ShapeAttribute.DIRECTION_NA);
    Event event5 = new Event(ShapeAttribute.POSITION_Y, 0, 50, 500,
            325, false, 1, 1, ShapeAttribute.DIRECTION_NA);
    oval.addEvent(event4);
    oval.addEvent(event5);
    shapeCollection.addShape(borderLeft);
    shapeCollection.addShape(borderTop);
    shapeCollection.addShape(rectangle);
    shapeCollection.addShape(oval);

    return shapeCollection;
  }



  public SimpleAnimation createScreenTwo() {
    SimpleAnimation shapeCollection = new SimpleAnimationImpl();

    Shape borderLeft = new Rectangle(0, 0, 255, 0, 0, 5, 600);
    Shape borderBottom = new Rectangle(0, 595, 255, 0, 0, 600, 5);

    Shape rectangle = new Rectangle(125,125,0,0,255, 125, 125);

    Event event0 = new Event(ShapeAttribute.POSITION_X, 0, 50, 125,
            325, true, 1, 0, ShapeAttribute.DIRECTION_NA);
    Event event1 = new Event(ShapeAttribute.POSITION_X, 50, 100, 325,
            125, true, 0, 1, ShapeAttribute.DIRECTION_NA);

    rectangle.addEvent(event0);
    rectangle.addEvent(event1);
    shapeCollection.addShape(borderLeft);
    shapeCollection.addShape(borderBottom);
    shapeCollection.addShape(rectangle);

    return shapeCollection;
  }

  public SimpleAnimation createScreenThree() {
    SimpleAnimation shapeCollection = new SimpleAnimationImpl();

    Shape borderRight = new Rectangle(595, 0, 255, 0, 0, 5, 600);
    Shape borderTop = new Rectangle(0, 0, 255, 0, 0, 600, 5);

    Shape rectangle = new Rectangle(125,125,125,255,40, 125, 125);

    Event event0 = new Event(ShapeAttribute.POSITION_X, 0, 50, 125,
            325, true, 1, 0, ShapeAttribute.DIRECTION_NA);
    Event event1 = new Event(ShapeAttribute.POSITION_X, 50, 100, 325,
            125, true, 0, 1, ShapeAttribute.DIRECTION_NA);

    rectangle.addEvent(event0);
    rectangle.addEvent(event1);
    shapeCollection.addShape(borderRight);
    shapeCollection.addShape(borderTop);
    shapeCollection.addShape(rectangle);

    return shapeCollection;
  }

  public SimpleAnimation createScreenFour() {
    SimpleAnimation shapeCollection = new SimpleAnimationImpl();

    SpaceShip spaceShip = new SpaceShip(25, 25, 1, 1, 1, 100, 53, ShapeAttribute.DIRECTION_R);

    Shape borderRight = new Rectangle(595, 0, 255, 0, 0, 5, 600);
    Shape borderBottom = new Rectangle(0, 595, 255, 0, 0, 600, 5);


    Event event0 = new Event(ShapeAttribute.POSITION_X, 0, 50, 25,
            475, false, 1, 1, ShapeAttribute.DIRECTION_R);
    Event event1 = new Event(ShapeAttribute.POSITION_Y, 50, 100, 325,
            125, true, 0, 1, ShapeAttribute.DIRECTION_D);

    spaceShip.addEvent(event0);
    spaceShip.addEvent(event1);
    Map<ShapeAttribute, List<Event>> list = spaceShip.getEvents();






    shapeCollection.addShape(borderRight);
    shapeCollection.addShape(borderBottom);
    shapeCollection.addShape(spaceShip);

    return shapeCollection;
  }

}
