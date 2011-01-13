/*
 * This will render the world in first person mode!
 */
package org.trevyan.lzr.gfx;

import org.lwjgl.util.vector.Vector3f;
import org.trevyan.lzr.Laser;
import org.trevyan.lzr.World;

public class FirstPersonRenderer {
  Vector3f position;
  boolean death_animation = false;
  float death_timer = 0.0f;
  Laser player;
  World world;
  
  public FirstPersonRenderer(Laser player, World world) {
    this.player = player;
    this.world = world;
  }
  
  public boolean update(float delta) {
    // This is a little animation to pan away from where you died
    if(death_animation) {
      if(death_timer < 3.0f) {
        // TODO: this isn't right
        position.translate(delta, 0, delta);
        death_timer += delta;
      }
      else
        return false;
    }
    return true;
  }
  
  public void render() {
    /*if(!death_animation)
      world.renderFromPerspective(player);
    else
      world.renderFromPerspective(position);*/
  }
  
  public void on_player_death() {
    death_animation = true;
  }
}
