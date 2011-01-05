

class LaserNetworkController {
  LaserNetworkController() {
    net.connect("player_on_move", this.on_move);
  }
  void on_move(Packet packet) {
    int x = packet.readInt();
    int y = packet.readInt();
  }
}

class Laser extends Object {
  private double x, y, z;
  private double yaw, pitch;
  boolean update(double delta) {
    
  }
  void move(Pointf dpoint, dyaw, dpitch, upd = false) {
    /* Draw */
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
  }
  void draw() {
    glMoveTo(this.x, this.y, 1.0f);
    glLineTo(this.x, this.y, 0.0f);
    glDraw();
  }
}
