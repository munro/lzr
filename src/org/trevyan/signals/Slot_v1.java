package org.trevyan.signals;

import java.util.Observable;
import java.util.Observer;

public abstract class Slot_v1<G1> implements Observer {
  public void connect(Signal_v1<G1> signal) {
    signal.addObserver(this);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void update(Observable o, java.lang.Object arg1) {
    this.callback((G1) arg1);
  }

  public abstract void callback(G1 arg1);
}
