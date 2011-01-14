package org.trevyan.signals;

import java.util.Observable;

public class Signal_v1<G1> extends Observable {
  public void emit(G1 arg1) {
    setChanged();
    notifyObservers(arg1);
  }
}
