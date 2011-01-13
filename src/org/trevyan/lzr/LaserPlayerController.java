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
    float rotate_speed = 80.0f;
    float move_speed =  5.5f;
    if((Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_S))
        && (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D)))
      move_speed /= 2;
    // Looking
    if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
      laser.setYaw(laser.getYaw() - delta * rotate_speed);
    else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
      laser.setYaw(laser.getYaw() + delta * rotate_speed);
    // Moving
    if(Keyboard.isKeyDown(Keyboard.KEY_W))
      laser.move(delta * move_speed, DIRECTION.FORWARD);
    else if(Keyboard.isKeyDown(Keyboard.KEY_S))
      laser.move(delta * move_speed, DIRECTION.BACKWARD);
    if(Keyboard.isKeyDown(Keyboard.KEY_A))
      laser.move(delta * move_speed, DIRECTION.LEFT);
    else if(Keyboard.isKeyDown(Keyboard.KEY_D))
      laser.move(delta * move_speed, DIRECTION.RIGHT);
  }
}
