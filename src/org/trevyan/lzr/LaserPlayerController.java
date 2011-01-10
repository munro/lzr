package org.trevyan.lzr;

import org.lwjgl.input.Keyboard;
import org.trevyan.lzr.Laser.DIRECTION;

public class LaserPlayerController {
  private Laser laser;
  
  public LaserPlayerController(Laser laser) {
    this.laser = laser;
  }
  public float getX() {
    return laser.getX();
  }
  public float getZ() {
    return laser.getZ();
  }
  public float getYaw() {
    return laser.getYaw();
  }
  public void update(float delta) {
    // Looking
    if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
      laser.setYaw(laser.getYaw() - delta * 80.0f);
    else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
      laser.setYaw(laser.getYaw() + delta * 80.0f);
    // Moving
    if(Keyboard.isKeyDown(Keyboard.KEY_W))
      laser.move(delta * 7.0f, DIRECTION.FORWARD);
    else if(Keyboard.isKeyDown(Keyboard.KEY_S))
      laser.move(delta * 7.0f, DIRECTION.BACKWARD);
    if(Keyboard.isKeyDown(Keyboard.KEY_A))
      laser.move(delta * 7.0f, DIRECTION.LEFT);
    else if(Keyboard.isKeyDown(Keyboard.KEY_D))
      laser.move(delta * 7.0f, DIRECTION.RIGHT);
  }
}
