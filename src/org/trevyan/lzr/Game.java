package org.trevyan.lzr;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.util.glu.GLU.*;

public class Game {
  private boolean done = false;
  public float rot = 0.0f;
  public World world = new World();

  public Game() {
    init();

    while (!done) {
      if (Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        done = true;
      render();
      Display.update();
    }
    

    Display.destroy();
  }

  private void render() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();

    world.render();
    
    glTranslatef(-1.5f, 0.0f, -6.0f);
    glRotatef(rot += 0.75f, 0.0f, 1.0f, 0.0f);
    
    glLineWidth(5.0f);
    glBegin(GL_LINES);
      glColor3f(1.0f, 0.0f, 0.0f);
      glVertex3f(3.0f, 1.0f, 0.0f);
      glColor3f(0.0f, 1.0f, 0.0f);
      glVertex3f(2.0f, 2.0f, -2.0f);
      glVertex3f(2.0f, 2.0f, -2.0f);
      glColor3f(1.0f, 0.0f, 0.0f);
      glVertex3f(1.0f, 1.0f, 0.0f);
    glEnd();
    
    glBegin(GL_TRIANGLES);
      glColor3f(1.0f, 0.0f, 0.0f);
      glVertex3f(0.0f, 1.0f, 0.0f);
      glColor3f(0.0f, 1.0f, 0.0f);
      glVertex3f(-1.0f, -1.0f, 0.0f);
      glColor3f(0.0f, 0.0f, 1.0f);
      glVertex3f(1.0f, -1.0f, 0.0f);
    glEnd();
  }

  private void init() {
    int w = 640;
    int h = 480;

    try {
      Display.setDisplayMode(new DisplayMode(w, h));
      Display.setVSyncEnabled(true);
      Display.setTitle("Shader Setup");
      Display.create();
    } catch (Exception e) {
      System.out.println("Error setting up display");
      System.exit(0);
    }

    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(45.0f, ((float) w / (float) h), 0.1f, 100.0f);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glShadeModel(GL_SMOOTH);
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    glClearDepth(1.0f);
    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LEQUAL);
    glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
  }

  public static void main(String[] args) {
    new Game();
  }
}

