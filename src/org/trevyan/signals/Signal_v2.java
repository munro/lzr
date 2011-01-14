package org.trevyan.signals;

import java.util.Observable;

public class Signal_v2<G1, G2> extends Observable {
  public void emit(G1 arg1, G2 arg2) {
    setChanged();
    notifyObservers(new Object[] { arg1, arg2 });
  }
}
