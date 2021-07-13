import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenClass {
  protected final Map<ShapeAttribute, List<Event>> events = new HashMap<>();

  ScreenClass() {

  }

  public SimpleAnimation createScreenOne() {
    SimpleAnimation shapeCollection = new SimpleAnimationImpl();


    Shape borderLeft = new Rectangle(0, 0, 255, 0, 0, 5, 600,0);
    Shape borderTop = new Rectangle(0, 0, 255, 0, 0, 600, 5,0);

    Shape rectangle = new Rectangle(125,125,255,255,0, 125, 125,0);
    Event event0 = new Event(ShapeAttribute.POSITION_X, 10, 30, 125,
            325, true, 1, 0, 0);
    Event event1 = new Event(ShapeAttribute.POSITION_Y, 30, 60, 125,
            325, true, 0, 0, 0);
    Event event2 = new Event(ShapeAttribute.POSITION_Y, 60, 90, 325,
            125, true, 0, 0, 0);
    Event event3 = new Event(ShapeAttribute.POSITION_X, 90, 120, 325,
            125, true, 0, 1, 0);

    rectangle.addEvent(event0);
    rectangle.addEvent(event1);
    rectangle.addEvent(event2);
    rectangle.addEvent(event3);
    Shape oval = new Oval(500,500,0,255,0, 125, 125,0);
    Event event4 = new Event(ShapeAttribute.POSITION_X, 0, 50, 500,
            325, false, 1, 1, 0);
    Event event5 = new Event(ShapeAttribute.POSITION_Y, 0, 50, 500,
            325, false, 1, 1, 0);
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

    Shape borderLeft = new Rectangle(0, 0, 255, 0, 0, 5, 600,0);
    Shape borderBottom = new Rectangle(0, 595, 255, 0, 0, 600, 5,0);

    Shape rectangle = new Rectangle(125,125,0,0,255, 125, 125,0);

    Event event0 = new Event(ShapeAttribute.POSITION_X, 0, 50, 125,
            325, true, 1, 0, 0);
    Event event1 = new Event(ShapeAttribute.POSITION_X, 50, 100, 325,
            125, true, 0, 1, 0);

    rectangle.addEvent(event0);
    rectangle.addEvent(event1);
    shapeCollection.addShape(borderLeft);
    shapeCollection.addShape(borderBottom);
    shapeCollection.addShape(rectangle);

    return shapeCollection;
  }

  public SimpleAnimation createScreenThree() {
    SimpleAnimation shapeCollection = new SimpleAnimationImpl();

    Shape borderRight = new Rectangle(595, 0, 255, 0, 0, 5, 600,0);
    Shape borderTop = new Rectangle(0, 0, 255, 0, 0, 600, 5,0);

    Shape rectangle = new Rectangle(125,125,125,255,40, 125, 125,0);

    Event event0 = new Event(ShapeAttribute.POSITION_X, 0, 50, 125,
            325, true, 1, 0, 0);
    Event event1 = new Event(ShapeAttribute.POSITION_X, 50, 100, 325,
            125, true, 0, 1, 0);

    rectangle.addEvent(event0);
    rectangle.addEvent(event1);
    shapeCollection.addShape(borderRight);
    shapeCollection.addShape(borderTop);
    shapeCollection.addShape(rectangle);

    return shapeCollection;
  }

  public SimpleAnimation createScreenFour(double screen1_1SaveTime) {
    SimpleAnimation shapeCollection = new SimpleAnimationImpl();

    SpaceShip spaceShip = new SpaceShip(25, 25, 1, 1, 1, 100, 53, 3.0);

    Shape borderRight = new Rectangle(595, 0, 255, 0, 0, 5, 600,0);
    Shape borderBottom = new Rectangle(0, 595, 255, 0, 0, 600, 5,0);

    //going 3 o'clock
    Event event0 = new Event(ShapeAttribute.POSITION_X, 0, 50, 25,
            475, true, 1, 0, 3.0);

    //aim spaceship 6 o'clock
    Event event1 = new Event(ShapeAttribute.DIRECTION, 50, 50, 3.0, 6.0,
            true, 0, 0, 6.0);

    //going 6 0'clock
    Event event2 = new Event(ShapeAttribute.POSITION_Y, 50, 100, 25,
            475, true, 0, 0, 6.0);

    //aim spaceship 12 o'clock
    Event event3 = new Event(ShapeAttribute.DIRECTION, 100, 100, 6.0, 12.0,
            true, 0, 0, 12.0);

    //going 12 o'clock
    Event event4 = new Event(ShapeAttribute.POSITION_Y, 100, 150, 475,
            25, true, 0, 0, 12.0);

    //aim spaceship 9 o'clock
    Event event5 = new Event(ShapeAttribute.DIRECTION, 150, 150, 12.0, 9.0,
            true, 0, 0, 9.0);

    //going 9 o'clock
    Event event6 = new Event(ShapeAttribute.POSITION_X, 150, 200, 475,
            25, true, 0, 1, 9.0);

    //aim spaceship 3 o'clock
    Event event7 = new Event(ShapeAttribute.DIRECTION, 200, 200, 9.0, 12.0,
            true, 0, 0, 9.0);



    spaceShip.addEvent(event0);
    spaceShip.addEvent(event1);
    spaceShip.addEvent(event2);
    spaceShip.addEvent(event3);
    spaceShip.addEvent(event4);
    spaceShip.addEvent(event5);
    spaceShip.addEvent(event6);
    spaceShip.addEvent(event7);

    Map<ShapeAttribute, List<Event>> list = spaceShip.getEvents();






    shapeCollection.addShape(borderRight);
    shapeCollection.addShape(borderBottom);
    shapeCollection.addShape(spaceShip);

    return shapeCollection;
  }

}
