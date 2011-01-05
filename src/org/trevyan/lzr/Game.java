package org.trevyan.lzr;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;

public class Game {
  public static final String GAME_TITLE = "Lazors";
  private static boolean finished;

  public static void main(String[] args) {
    try {
      // Init
      Display.setTitle(GAME_TITLE);
      Display.setDisplayMode(new DisplayMode(640, 480));
      Display.setVSyncEnabled(true);
      Display.create();
      Display.setLocation(100, 100);
      glMatrixMode(GL_PROJECTION);

      // Run
      while (!finished) {
        Display.update();

        // Check for close requests
        if (Display.isCloseRequested()
            || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
          finished = true;
        }
        else
        {
          // Draw yo
          glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
          glLoadIdentity();
          //glTranslatef(-1.5f,0.0f,-6.0f);
          glBegin(GL_TRIANGLES);
            glColor3f(1.0f,0.0f,0.0f);
            glVertex3f( 0.0f, 1.0f, 0.0f);
            glColor3f(0.0f,1.0f,0.0f);
            glVertex3f(-1.0f,-1.0f, 0.0f);
            glColor3f(0.0f,0.0f,1.0f);
            glVertex3f( 1.0f,-1.0f, 0.0f);
          glEnd();
          Display.sync(30);
        }
      }
    // Exception
    } catch (Exception e) {
      e.printStackTrace(System.err);
      Sys.alert(GAME_TITLE, "An error occured and the game will exit.");
    // Cleanup
    } finally {
      Display.destroy();
    }
    System.exit(0);
  }
}