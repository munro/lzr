package org.trevyan.signals;

import java.util.Observable;
import java.util.Observer;

public abstract class Slot_v5<G1, G2, G3, G4, G5> implements Observer {
  public void connect(Signal_v5<G1, G2, G3, G4, G5> signal) {
    signal.addObserver(this);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void update(Observable o, java.lang.Object obj) {
    Object[] args = (Object[]) obj;
    this.callback((G1) args[0], (G2) args[1], (G3) args[2], (G4) args[3],
        (G5) args[4]);
  }

  public abstract void callback(G1 arg1, G2 arg2, G3 arg3, G4 arg4, G5 args5);
}
