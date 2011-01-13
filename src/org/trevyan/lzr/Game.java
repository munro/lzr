package org.trevyan.lzr;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.util.glu.GLU.*;

public class Game {
  private float delta, ticks;
  private boolean done = false;
  public float rot = 0.0f;
  public World world;
  public LaserPlayerController player;

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

  private void init() {
    int w = 1024;
    int h = 768;
    
    // Set up game
    world = new World();
    player = new LaserPlayerController(new Laser());

    // Initiate window
    try {
      Display.setDisplayMode(new DisplayMode(w, h));
      Display.setVSyncEnabled(false);
      Display.setTitle("Lazors");
      Display.create();
    } catch (Exception e) {
      System.out.println("Error setting up display");
      System.exit(0);
    }

    // Set up view port
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
    
    // Lighting
    /*FloatBuffer lightAmbient = BufferUtils.createFloatBuffer(4).put(
        new float[] {0.2f, 0.2f, 0.2f, 1.0f });*/
    FloatBuffer buffer = BufferUtils.createFloatBuffer(4);
    glLight(GL_LIGHT1, GL_AMBIENT, (FloatBuffer) buffer.put(
        new float[] {1.0f, 1.0f, 0.0f, 1.0f}).flip());
    glLight(GL_LIGHT1, GL_DIFFUSE, (FloatBuffer) buffer.put(
        new float[] {1.0f, 1.0f, 1.0f, 1.0f}).flip());
    glLight(GL_LIGHT1, GL_POSITION, (FloatBuffer) buffer.put(
        new float[] {-5.0f, -6.0f, -12.0f, 3.0f}).flip());
    glEnable(GL_LIGHT1);
    glEnable(GL_LIGHTING);

    // Set timer
    ticks = (float)System.nanoTime() / 1.0E9f;
  }

  private void render() {
    // Calculate delta
    delta = ticks;
    ticks = (float)System.nanoTime() / 1000000000.0f;
    delta = ticks - delta;
    if(delta > 1.0f)
      delta = 1.0f;

    // Clear screen
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();

    // Set an initial position
    glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
    // Set up player's view
    glRotatef(player.getYaw(), 0.0f, 1.0f, 0.0f);
    glTranslatef(player.getX(), 0.0f, player.getZ());
    // Set an initial position
    glTranslatef(-2.0f, -1.6f, -2.0f);
    
    // Update objects
    player.update(delta);
    
    // Render world
    world.render();
  }

  public static void main(String[] args) {
    new Game();
  }
}

