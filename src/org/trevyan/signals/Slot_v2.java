package org.trevyan.signals;

import java.util.Observable;
import java.util.Observer;

public abstract class Slot_v2<G1, G2> implements Observer {
  public void connect(Signal_v2<G1, G2> signal) {
    signal.addObserver(this);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void update(Observable o, java.lang.Object obj) {
    Object[] args = (Object[]) obj;
    this.callback((G1) args[0], (G2) args[1]);
  }

  public abstract void callback(G1 arg1, G2 arg2);
}
