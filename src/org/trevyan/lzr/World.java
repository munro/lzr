package org.trevyan.lzr;

import java.text.*;
import java.util.*;

import org.lwjgl.util.vector.Vector2f;

public class World {
  public float wall_bottom = 0.0f, wall_top = 1.0f; 
  private String foobar =
      "+--------------+\n" +
      "|              |\n" +
      "|     +--+     |\n" +
      "|     |  |     |\n" +
      "|              |\n" +
      "+--------------+";
  ArrayList<Wall>walls = new ArrayList<Wall>();

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
  }
  
  void render() {
    ListIterator<Wall>it;
    Wall w;
    for(it = walls.listIterator(); it.hasNext(); ) {
      w = it.next();
      w.render();
    }
  }
}
