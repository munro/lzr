package org.trevyan.signals;

import java.util.Observable;

public class Signal_v6<G1, G2, G3, G4, G5, G6> extends Observable {
  public void emit(G1 arg1, G2 arg2, G3 arg3, G4 arg4, G5 arg5, G6 arg6) {
    setChanged();
    notifyObservers(new Object[] { arg1, arg2, arg3, arg4, arg5, arg6 });
  }
}
