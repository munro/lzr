package org.trevyan.lzr;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Point;
import java.text.*;
import java.util.*;

public class World {
  private String foobar =
      "+--------------+\n" +
      "|              |\n" +
      "|              |\n" +
      "+--------------+";
  ArrayList<Point>
      wcorner = new ArrayList<Point>(),
      wvertical = new ArrayList<Point>(),
      whorizontal = new ArrayList<Point>();

  public World() {
    // Parse foobar
    int x = 0, y = 0;
    CharacterIterator it = new StringCharacterIterator(foobar);
    for (char ch = it.first(); ch != CharacterIterator.DONE; ch = it.next(), x++) {
      if (ch == '\n') {
        x = -1;
        y++;
      } else if (ch == '+')
        wcorner.add(new Point(x, y));
      else if (ch == '-')
        whorizontal.add(new Point(x, y));
      else if (ch == '|')
        wvertical.add(new Point(x, y));
    }
  }
  
  void render() {
    ListIterator<Point>it;
    Point p;
    glLineWidth(1.0f);
    glColor3f(1.0f, 1.0f, 1.0f);
    glBegin(GL_LINES);
    for(it = wcorner.listIterator(); it.hasNext(); ) {
      p = it.next();
      glVertex3f((float)p.x + 0.5f, (float)p.y, -50.0f);
      glVertex3f((float)p.x + 0.5f, (float)p.y + 1.0f, -50.0f);
      glVertex3f((float)p.x, (float)p.y + 0.5f, -50.0f);
      glVertex3f((float)p.x + 1.0f, (float)p.y + 0.5f, -50.0f);
    }
    for(it = whorizontal.listIterator(); it.hasNext(); ) {
      p = it.next();
      glVertex3f((float)p.x, (float)p.y + 0.5f, -50.0f);
      glVertex3f((float)p.x + 1.0f, (float)p.y + 0.5f, -50.0f);
    }
    for(it = wvertical.listIterator(); it.hasNext(); ) {
      p = it.next();
      glVertex3f((float)p.x + 0.5f, (float)p.y, -50.0f);
      glVertex3f((float)p.x + 0.5f, (float)p.y + 1.0f, -50.0f);
    }
    glEnd();
  }
}
