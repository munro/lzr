package org.trevyan.lzr;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector2f;

public class Laser implements Object {
  public static enum DIRECTION {FORWARD, BACKWARD, LEFT, RIGHT};
  private Vector2f position;
  private float yaw, pitch;
  
  /*void move(Pointf dpoint, dyaw, dpitch, upd = false) {
    // Draw
    wall_start = world.find_intersecting_wall(this.point, this.yaw, this.pitch);
    wall_end = world.find_intersecting_wall(this.point + dpoint, this.yaw + dyaw, this.pitch + dpitch);
    
    point_a = wall_start.get_intersecting_2d_point(this.point, this.yaw, this.pitch);
    if(wall_start == wall_end) {
      point_b = wall_start.get_intersecting_2d_point(this.point + dpoint, this.yaw + dyaw, this.pitch + dpitch);
      wall_start.draw_line(point_a, point_b, upd);
    }
    
    list(wall_next, point_intersect) = wall_start.find_next_intersecting_wall(
      this.point, this.yaw, this.pitch,
      this.point + dpoint, this.yaw + dyaw, this.pitch + dpitch);
    point_b = wall_start.get_intersecting_2d_point(point_intersect);
    wall_next.draw_line(point_a, point_b, upd);

    if(wall_next != wall_end)
      do_this_again(wall_next, wall_end);
  }*/
  public Laser() {
    position = new Vector2f();
  }
  @Override
  public boolean update(float delta) {
    // TODO Auto-generated method stub
    return true;
  }
  @Override
  public void render() {
    glBegin(GL_LINES);
      glColor3f(1.0f, 0.0f, 1.0f);
      glVertex3f(position.x, Wall.FLOOR,   position.y);
      glVertex3f(position.x, Wall.CEILING, position.y);
    glEnd();
  }
  public float getX() {
    return position.x;
  }
  public float getZ() {
    return position.y;
  }
  public void move(float distance, DIRECTION direction) {
    Vector2f new_position = new Vector2f(position);
    if(direction == DIRECTION.FORWARD || direction == DIRECTION.BACKWARD) {
      double rad = Math.toRadians(yaw);
      float sin = (float)Math.sin(rad) * distance;
      float cos = (float)Math.cos(rad) * distance;
      
      if(direction == DIRECTION.FORWARD) {
        new_position.x -= cos;
        new_position.y -= sin;
      }
      else
      {
        new_position.x += cos;
        new_position.y += sin;
      }
    }
    else
    {
      double rad = Math.toRadians(yaw + 90.0f);
      float sin = (float)Math.sin(rad) * distance;
      float cos = (float)Math.cos(rad) * distance;
      
      if(direction == DIRECTION.LEFT) {
        new_position.x += cos;
        new_position.y += sin;
      }
      else
      {
        new_position.x -= cos;
        new_position.y -= sin;
      }
    }
    System.out.println(World.intersectsLine(
        new Vector2f(0.0f, 0.0f), new Vector2f(2.0f, 0.0f),
        new Vector2f(1.0f, -1.0f), new Vector2f(1.0f, -0.0f)));
    if(!World.getInst().intersectsWall(position, new_position))
      position = new_position;
  }
  public float getYaw() {
    return yaw;
  }
  public void setYaw(float yaw) {
    this.yaw = yaw;
  }
}
