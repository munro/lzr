package org.trevyan.signals;

import java.util.Observable;

public class Signal_v4<G1, G2, G3, G4> extends Observable {
  public void emit(G1 arg1, G2 arg2, G3 arg3, G4 arg4) {
    setChanged();
    notifyObservers(new Object[] { arg1, arg2, arg3, arg4 });
  }
}
