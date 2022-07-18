import java.awt.*;
import java.awt.event.*;


import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {


  static final int SCREEN_WIDTH = 600;
  static final int SCREEN_HEIGHT = 600;
  static final int MAP_WIDTH = 2425;
  static final int MAP_HEIGHT = 1500;


  static final int UNIT_SIZE = 25;
  static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
  static final int DELAY = 50;

  int applesEaten;
  int appleX;
  int appleY;
  int nikoX = 0;
  int nikoY = 0;


  char direction = 'R';
  boolean running = false;
  boolean saveState = true;
  int edgeX = 0;
  int edgeY = 0;
  int moveImageX = 25;
  int moveImageY = 25;
  int nikoXAdjusted;
  int nikoYAdjusted;
  int tick = 0;
  int spaceTime = 0;
  double screen1_1SaveTime = 0;
  Timer timer;
  Random random;
  private BufferedImage image;
  private BufferedImage image2;


  private final ScreenClass screens = new ScreenClass();
  private final SimpleAnimation screenOne = screens.createScreenOne(moveImageX, moveImageY);
  private final SimpleAnimation screenTwo = screens.createScreenTwo();
  private final SimpleAnimation screenThree = screens.createScreenThree();
  private final SimpleAnimation screenFour = screens.createScreenFour(screen1_1SaveTime);
  private final SimpleAnimation screenFive = screens.createScreenFive();

  private final Point2D nikoLocation = new Point2D(0, 0);


  GamePanel() {
    random = new Random();
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.BLACK);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());

    try {

      File file = new File("./src/resources/images/MapDemo2_3000_2071.png");
      this.image2 = ImageIO.read(file);


    } catch (IOException var6) {
      var6.printStackTrace();
    }


    startGame();
  }


  public void startGame() {
    newApple();
    running = true;
    timer = new Timer(DELAY, this);

    timer.start();

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    try {
      draw(g);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void drawGrid(Graphics g) {
    for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {

      g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
      g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
    }
  }

  private void drawScreenName(Graphics g, String screenName) {
    g.setColor(Color.WHITE);
    g.setFont(new Font("Times", Font.BOLD, 20));
    FontMetrics metrics = getFontMetrics(g.getFont());
    g.drawString(screenName, (SCREEN_WIDTH - metrics.stringWidth(screenName)) / 2, 22);

    g.setColor(Color.WHITE);
    g.setFont(new Font("Tick: " + tick, Font.BOLD, 20));
    FontMetrics metrics2 = getFontMetrics(g.getFont());
    g.drawString("Tick: " + tick, (SCREEN_WIDTH - metrics2.stringWidth(screenName) - 20), 22);

    g.setColor(Color.WHITE);
    g.setFont(new Font("spaceTime: " + spaceTime, Font.BOLD, 20));
    FontMetrics metrics3 = getFontMetrics(g.getFont());
    g.drawString("spaceTime: " + spaceTime, (SCREEN_WIDTH - metrics3.stringWidth(screenName) - 82), 52);

    g.setColor(Color.WHITE);
    g.setFont(new Font("screen1_1SaveTime: " + screen1_1SaveTime, Font.BOLD, 20));
    FontMetrics metrics4 = getFontMetrics(g.getFont());
    g.drawString("screen1_1SaveTime: " + screen1_1SaveTime, (SCREEN_WIDTH - metrics4.stringWidth(screenName) - 167), 82);
  }


  private void drawShapes(Graphics g, State state, Shape shape) {

    Point2D shipCoordinates = new Point2D();

    int x = (int) state.valueFor(ShapeAttribute.POSITION_X) - (moveImageX);
    int y = (int) state.valueFor(ShapeAttribute.POSITION_Y) - (moveImageY);



    g.setColor(new Color((int) state.valueFor(ShapeAttribute.RED),
            (int) state.valueFor(ShapeAttribute.GREEN),
            (int) state.valueFor(ShapeAttribute.BLUE)));



    if (shape.getClass().getSimpleName().equals("Rectangle")) {

      g.fillRect(x,
              y,
              (int) state.valueFor(ShapeAttribute.WIDTH),
              (int) state.valueFor(ShapeAttribute.HEIGHT));
    }



    if (shape.getClass().getSimpleName().equals("Oval")) {

      int left = (int) (state.valueFor(ShapeAttribute.POSITION_X)- moveImageX
              - state.valueFor(ShapeAttribute.WIDTH) / 2);
      int top = (int) (state.valueFor(ShapeAttribute.POSITION_Y)- moveImageY
              - state.valueFor(ShapeAttribute.HEIGHT) / 2);

      g.fillOval(left, top, (int) state.valueFor(ShapeAttribute.WIDTH),
              (int) state.valueFor(ShapeAttribute.HEIGHT));
    }


  }


  public void draw(Graphics g) throws IOException {

    //System.out.printf("nikoAdjusted coords: %d, %d%n", nikoXAdjusted, nikoYAdjusted);


    List<Shape> shapeList = screenOne.listShapes();

    g.drawImage(image2, -moveImageX, -moveImageY, this);


    if (running) {
      drawGrid(g);
    }

    for (Shape shape : shapeList) {



      State state = shape.atState(tick);
      drawShapes(g, state, shape);
    }


    if ((nikoXAdjusted >= 2300  && nikoXAdjusted <= 2400) && nikoYAdjusted >= 400 && nikoYAdjusted <= 500) {

      Shape test = screenOne.getShape(4);
      State testChangeColorState = test.atState(tick);
      g.setColor(new Color(0, 0,255));
      int x2 = (int) (testChangeColorState.valueFor(ShapeAttribute.POSITION_X)-moveImageX);
      int y2 = (int) (testChangeColorState.valueFor(ShapeAttribute.POSITION_Y)-moveImageY);
      //System.out.println(x2);
      //System.out.println(y2);
      g.fillRect(x2, y2, (int) testChangeColorState.valueFor(ShapeAttribute.WIDTH), (int) testChangeColorState.valueFor(ShapeAttribute.HEIGHT));

    }

    g.setColor(Color.ORANGE);
    g.fillOval(nikoX, nikoY, UNIT_SIZE, UNIT_SIZE);


  }


  public void newApple() {
    appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
    appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
  }

  public void move() {


    if (moveImageX == 0 || moveImageX == MAP_WIDTH) {
      this.edgeX = 1;
    }

    if (moveImageY == 0 || moveImageY == MAP_HEIGHT) {
      this.edgeY = 1;
    }

    switch (direction) {
      case 'U':
        if (nikoY > 300 || edgeY == 1) {
          nikoY = nikoY - UNIT_SIZE;
          if (nikoY == 300) {
            this.edgeY = 0;
          }
        }

        if (edgeY == 0) {
          moveImageY = moveImageY - UNIT_SIZE;
          nikoYAdjusted = nikoY + moveImageY;

        }
        break;


      case 'D':
        if (nikoY < 300 || edgeY == 1) {
          nikoY = nikoY + UNIT_SIZE;
          if (nikoY == 300) {
            this.edgeY = 0;
          }
        }

        if (edgeY == 0) {
          moveImageY = moveImageY + UNIT_SIZE;
          nikoYAdjusted = nikoY + moveImageY;
        }
        break;


      case 'L':

        if (nikoX > 300 || edgeX == 1) {
          nikoX = nikoX - UNIT_SIZE;
          if (nikoX == 300) {
            this.edgeX = 0;
          }
        }

        if (edgeX == 0) {
          moveImageX = moveImageX - UNIT_SIZE;
          nikoXAdjusted = nikoX + moveImageX;
        }

        break;


      case 'R':

        if (nikoX < 300 || edgeX == 1) {
          nikoX = nikoX + UNIT_SIZE;
          if (nikoX == 300) {
            this.edgeX = 0;
          }
        }
        if (edgeX == 0) {
          moveImageX = moveImageX + UNIT_SIZE;
          nikoXAdjusted = nikoX + moveImageX;
        }

        break;
    }
  }

  public void checkApple() {
    if ((nikoX == appleX) && (nikoY == appleY)) {
      applesEaten++;
      newApple();
    }
  }

  public void checkBorder() {


    //checks left border
    if (moveImageX < 0) {
      moveImageX = 0;
    }
    if (nikoX < 0) {
      nikoX = 0;
    }


    //checks top border.
    if (moveImageY < 0) {
      moveImageY = 0;
    }

    if (nikoY < 0) {
      nikoY = 0;
    }


    //checks bottom border
    if (moveImageY > MAP_HEIGHT-25) {
      this.moveImageY = MAP_HEIGHT-25;
    }

    if (nikoY > SCREEN_HEIGHT-25) {
      nikoY = SCREEN_HEIGHT-25;
    }


    //checks right border
    if (moveImageX > MAP_WIDTH-25) {
      this.moveImageX = MAP_WIDTH - 25;
    }

    if (nikoX > SCREEN_WIDTH-25) {
      nikoX = SCREEN_WIDTH-25;
    }

  }


  public void gameOver(Graphics g) {
    //Game text
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 75));
    FontMetrics metrics = getFontMetrics(g.getFont());
    g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 40));
    FontMetrics metrics2 = getFontMetrics(g.getFont());
    g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth
            ("Score: " + applesEaten)) / 2, g.getFont().getSize());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (running) {

      checkApple();
      checkBorder();
      tick++;
      spaceTime++;

      //System.out.println(tick);
    }
    repaint();
  }

  public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {

        case KeyEvent.VK_LEFT:
          direction = 'L';
          move();
          break;


        case KeyEvent.VK_RIGHT:

          direction = 'R';
          move();
          break;

        case KeyEvent.VK_UP:

          direction = 'U';
          move();
          break;

        case KeyEvent.VK_DOWN:

          direction = 'D';
          move();
          break;

        case KeyEvent.VK_E:

          //enterDoor = 1;
          break;

      }
    }
  }


}
