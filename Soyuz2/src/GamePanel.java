import java.awt.*;
import java.awt.event.*;


import javax.swing.JPanel;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

  static final int SCREEN_WIDTH = 600;
  static final int SCREEN_HEIGHT = 600;
  static final int UNIT_SIZE = 25;
  static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
  static final int DELAY = 50;

  int applesEaten;
  int appleX;
  int appleY;
  int nikoX = 75;
  int nikoY = 75;
  char direction = 'R';
  boolean running = false;
  boolean saveState = true;
  int tick = 0;
  int spaceTime = 0;
  double screen1_1SaveTime = 0;
  Timer timer;
  Random random;
  private BufferedImage image;


  private final ScreenClass screens = new ScreenClass();
  private final SimpleAnimation screenOne = screens.createScreenOne();
  private final SimpleAnimation screenTwo = screens.createScreenTwo();
  private final SimpleAnimation screenThree = screens.createScreenThree();
  private final SimpleAnimation screenFour = screens.createScreenFour(screen1_1SaveTime);

  private final Point2D nikoLocation = new Point2D(0, 0);


  GamePanel() {
    random = new Random();
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.black);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());


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
    g.setColor(new Color((int) state.valueFor(ShapeAttribute.RED),
            (int) state.valueFor(ShapeAttribute.GREEN),
            (int) state.valueFor(ShapeAttribute.BLUE)));
    if (shape.getClass().getSimpleName().equals("Rectangle")) {
      g.fillRect((int) state.valueFor(ShapeAttribute.POSITION_X),
              (int) state.valueFor(ShapeAttribute.POSITION_Y),
              (int) state.valueFor(ShapeAttribute.WIDTH),
              (int) state.valueFor(ShapeAttribute.HEIGHT));
    }

    if (shape.getClass().getSimpleName().equals("Oval")) {
      int left = (int) (state.valueFor(ShapeAttribute.POSITION_X)
              - state.valueFor(ShapeAttribute.WIDTH) / 2);
      int top = (int) (state.valueFor(ShapeAttribute.POSITION_Y)
              - state.valueFor(ShapeAttribute.HEIGHT) / 2);

      g.fillOval(left, top, (int) state.valueFor(ShapeAttribute.WIDTH),
              (int) state.valueFor(ShapeAttribute.HEIGHT));
    }

    if (shape.getClass().getSimpleName().equals("SpaceShip")) {

      int x = (int) (state.valueFor(ShapeAttribute.POSITION_X));
      int y = (int) (state.valueFor(ShapeAttribute.POSITION_Y));

      if (saveState) {
        State newState = shape.atState(screen1_1SaveTime++);
        int x2 = (int) (newState.valueFor(ShapeAttribute.POSITION_X));
        int y2 = (int) (newState.valueFor(ShapeAttribute.POSITION_Y));
        BufferedImage image = shape.getImage(newState.valueFor(ShapeAttribute.DIRECTION));
        g.drawImage(image, x2, y2, this);
      } else {

        BufferedImage image = shape.getImage(state.valueFor(ShapeAttribute.DIRECTION));
        g.drawImage(image, x, y, this);

      }
    }
  }


  public void draw(Graphics g) throws IOException {

    List<Shape> shapeList = new ArrayList<>();

    if (running) {
      drawGrid(g);

      if (nikoLocation.getX() == 0 && nikoLocation.getY() == 0) {
        drawScreenName(g, "Screen 0, 0");
        shapeList = screenOne.listShapes();
      }

      if (nikoLocation.getX() == 0 && nikoLocation.getY() == 1) {
        drawScreenName(g, "Screen 0, 1");
        shapeList = screenTwo.listShapes();
      }

      if (nikoLocation.getX() == 1 && nikoLocation.getY() == 0) {
        drawScreenName(g, "Screen 1, 0");
        shapeList = screenThree.listShapes();
      }

      if (nikoLocation.getX() == 1 && nikoLocation.getY() == 1) {
        drawScreenName(g, "Screen 1, 1");
        shapeList = screenFour.listShapes();
      }

      for (Shape shape : shapeList) {

          State state = shape.atState(tick);
          drawShapes(g, state, shape);
        }

        g.setColor(Color.WHITE);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        g.setColor(Color.ORANGE);
        g.fillOval(nikoX, nikoY, UNIT_SIZE, UNIT_SIZE);

      }
    }



  public void newApple() {
    appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
    appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
  }

  public void move() {

    switch (direction) {
      case 'U':
        nikoY = nikoY - UNIT_SIZE;

        break;
      case 'D':
        nikoY = nikoY + UNIT_SIZE;
        break;
      case 'L':
        nikoX = nikoX - UNIT_SIZE;
        break;
      case 'R':
        nikoX = nikoX + UNIT_SIZE;

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

    if (nikoLocation.getX() == 0 && nikoLocation.getY() == 0) {
      //checks left border
      if (nikoX < 0) {
        nikoX = 0;
      }
      //checks top border.
      if (nikoY < 0) {
        nikoY = 0;
      }
      //checks bottom border
      if (nikoY > SCREEN_HEIGHT - 25) {
        nikoLocation.changeXY(0, 1);
        this.nikoY = 0;
        this.tick = -2;
      }
      //checks right border
      if (nikoX > SCREEN_WIDTH - 25) {
        nikoLocation.changeXY(1, 0);
        this.nikoX = 0;
        this.tick = -2;
      }
    }

    if (nikoLocation.getX() == 0 && nikoLocation.getY() == 1) {
      //checks left border
      if (nikoX < 0) {
        nikoX = 0;
      }
      //checks bot border.
      if (nikoY > SCREEN_HEIGHT - 25) {
        nikoY = SCREEN_HEIGHT - 25;
      }
      //checks top border
      if (nikoY < 0) {
        nikoLocation.changeXY(0, 0);
        this.nikoY = SCREEN_HEIGHT - 25;
        this.tick = -2;
      }
      //checks right border
      if (nikoX > SCREEN_WIDTH - 25) {
        nikoLocation.changeXY(1, 1);
        this.nikoX = 0;
        this.tick = -2;
      }
    }

    if (nikoLocation.getX() == 1 && nikoLocation.getY() == 0) {
      //checks right border
      if (nikoX > SCREEN_WIDTH - 25) {
        nikoX = SCREEN_WIDTH - 25;
      }
      //checks top border.
      if (nikoY < 0) {
        nikoY = 0;
      }
      //checks bot border
      if (nikoY > SCREEN_HEIGHT - 25) {
        nikoLocation.changeXY(1, 1);
        this.nikoY = 0;
        this.tick = -2;
      }
      //checks left border
      if (nikoX < 0) {
        nikoLocation.changeXY(0, 0);
        this.nikoX = SCREEN_WIDTH - 25;
        this.tick = -2;
      }
    }

    if (nikoLocation.getX() == 1 && nikoLocation.getY() == 1) {
      screen1_1SaveTime = screen1_1SaveTime + .5;


      if (nikoX > SCREEN_WIDTH - 25) {
        nikoX = SCREEN_WIDTH - 25;
      }
      //checks bot border.
      if (nikoY > SCREEN_HEIGHT - 25) {
        nikoY = SCREEN_HEIGHT - 25;
      }
      //checks top border
      if (nikoY < 0) {
        nikoLocation.changeXY(1, 0);
        this.nikoY = SCREEN_HEIGHT - 25;
        this.tick = -2;
      }
      //checks left border
      if (nikoX < 0) {
        nikoLocation.changeXY(0, 1);
        this.nikoX = SCREEN_WIDTH - 25;
        this.tick = -2;
      }
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

      }
    }
  }


}
