package org.trevyan.signals;

import java.util.Observable;

public class Signal_v0 extends Observable {
  public void emit() {
    setChanged();
    notifyObservers();
  }
}
