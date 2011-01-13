package org.trevyan.lzr;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.*;

class Wall implements Object {
  public static float FLOOR = 0.0f;
  public static float CEILING = 2.5f; 
  private Vector2f a, b;
  
  public Wall(Vector2f a, Vector2f b) {
    this.a = a;
    this.b = b;
  }
  /*
  void update_texture(Packet p) {
    int time = p.getInt();
    fast.get(time<=time).delete();
    texture.render(fast);
    texture.render(packet);
    texture.commit();
  }*/
  
  @Override
  public boolean update(float delta) {
    return true;
  }
  
  @Override
  public void render() {
    glBegin(GL_QUADS);
      glVertex3f(a.x, FLOOR,   a.y);
      glVertex3f(b.x, FLOOR,   b.y);
      glVertex3f(b.x, CEILING, b.y);
      glVertex3f(a.x, CEILING, a.y);
    glEnd();
  }
  
  public boolean intersectsLine(Vector2f c, Vector2f d) {
    return World.intersectsLine(a, b, c, d);
  }
}

