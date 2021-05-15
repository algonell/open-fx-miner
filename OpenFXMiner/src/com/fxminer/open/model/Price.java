package com.fxminer.open.model;

import java.io.Serializable;

/**
 * Holds data for real-time price quote.
 * 
 * @author Andrew Kreimer
 *
 */
public class Price implements Serializable {

  private static final long serialVersionUID = -85127596414383276L;

  private double bid;
  private double ask;
  private long volume;
  private double close;

  public double getBid() {
    return bid;
  }

  public void setBid(double bid) {
    this.bid = bid;
  }

  public double getAsk() {
    return ask;
  }

  public void setAsk(double ask) {
    this.ask = ask;
  }

  public long getVolume() {
    return volume;
  }

  public void setVolume(long volume) {
    this.volume = volume;
  }

  public double getClose() {
    return close;
  }

  public void setClose(double close) {
    this.close = close;
  }

  public double getMid() {
    return weka.core.Utils.roundDouble((ask + bid) / 2, 2);
  }

  public double getSpread() {
    return weka.core.Utils.roundDouble(ask - bid, 2);
  }

  public double getChange() {
    return weka.core.Utils.roundDouble(getMid() - close, 2);
  }

  public double getChangePercent() {
    return weka.core.Utils.roundDouble(getChange() / close * 100, 1);
  }

  @Override
  public String toString() {
    return "[" + getMid() + ", " + getChange() + ", " + getChangePercent() + "%, " + getSpread()
        + ", " + volume + "]";
  }

}
