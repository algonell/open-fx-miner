package com.fxminer.candlestickpattern;

/**
 * Trade direction for pattern.
 *
 * @author Andrew Kreimer
 */
public enum PatternDirection {
  BULLISH("Bullish"),

  BEARISH("Bearish");

  private final String name;

  PatternDirection(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
