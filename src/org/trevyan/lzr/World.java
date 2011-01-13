package org.trevyan.lzr;

import java.awt.geom.Line2D;
import java.text.*;
import java.util.*;

import org.lwjgl.util.vector.Vector2f;

public class World {
  private static World singleton = null;
  public float wall_bottom = 0.0f, wall_top = 1.0f; 
  private String foobar =
      "+-----------------+\n" +
      "|                 |\n" +
      "|     +--+        |\n" +
      "|     |  |        |\n" +
      "|                 |\n" +
      "|                 |\n" +
      "|                 |\n" +
      "|                 |\n" +
      "|                 |\n" +
      "+-----------------+";
  ArrayList<Wall>walls = new ArrayList<Wall>();

  public static World getInst() {
    if(singleton == null)
      singleton = new World();
    return singleton;
  }
  public World() {
    // Parse foobar
    float x = 0, y = 0;
    CharacterIterator it = new StringCharacterIterator(foobar);
    for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next(), x++) {
      switch(ch) {
        case '+':
          walls.add(new Wall(new Vector2f(x + 0.5f, y),
              new Vector2f(x + 0.5f, y + 1.0f)));
        case '-':
          walls.add(new Wall(new Vector2f(x, y + 0.5f),
              new Vector2f(x + 1.0f, y + 0.5f)));
          break;
        case '|':
          walls.add(new Wall(new Vector2f(x + 0.5f, y),
              new Vector2f(x + 0.5f, y + 1.0f)));
          break;
        case '\n':
          x = -1;
          y++;
          break;
      }
    }
    singleton = this;
  }
  
  public void render() {
    ListIterator<Wall>it;
    Wall w;
    for(it = walls.listIterator(); it.hasNext(); ) {
      w = it.next();
      w.render();
    }
  }
  
  boolean intersectsWall(Vector2f a, Vector2f b) {
    ListIterator<Wall>it;
    Wall w;
    //int x = 0;
    for(it = walls.listIterator(); it.hasNext(); ) {
      w = it.next();
      //System.out.println("HEY " + (x++));
      if(w.intersectsLine(a, b))
        return true;
    }
    return false;
  }
  
  public static boolean intersectsLine(Vector2f a, Vector2f b, Vector2f c,
      Vector2f d) {
    Line2D l1 = new Line2D.Float(a.x, a.y, b.x, b.y);
    Line2D l2 = new Line2D.Float(c.x, c.y, d.x, d.y);
    return l1.intersectsLine(l2);
  }
}
