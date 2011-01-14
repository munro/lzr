package org.trevyan.signals;

import java.util.Observable;
import java.util.Observer;

public abstract class Slot_v0 implements Observer {
  public void connect(Signal_v0 signal) {
    signal.addObserver(this);
  }

  @Override
  public void update(Observable o, java.lang.Object arg1) {
    this.callback();
  }

  public abstract void callback();
}
