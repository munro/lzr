class Wall {
  void update_texture(Packet p) {
    int time = p.getInt();
    fast.get(time<=time).delete();
    texture.render(fast);
    texture.render(packet);
    texture.commit();
  }
  void draw() {
    glDrawRect(this.x1, this.y1, this.z1, this.w, this.h, this.d);
  }
}

