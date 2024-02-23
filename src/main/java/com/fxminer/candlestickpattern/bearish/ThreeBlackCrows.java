package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. 3 consecutive long red candles. 3. Each candle closed
 * successively lower.
 *
 * @author Andrew Kreimer
 */
public final class ThreeBlackCrows extends BearishPattern {

  private static final String THREE_BLACK_CROWS = "ThreeBlackCrows";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);
    var q2 = symbol.getHistory().get(last - 2);

    return q0.getOpen() - q0.getClose() > 0
        && // three reds
        q1.getOpen() - q1.getClose() > 0
        && q2.getOpen() - q2.getClose() > 0
        && q0.getClose() < q1.getClose()
        && // lower close
        q1.getClose() < q2.getClose()
        && q1.getClose() < q0.getOpen()
        && q2.getClose() < q1.getOpen()
        && q0.getOpen() < q1.getOpen()
        && // lower open
        q1.getOpen() < q2.getOpen();
  }

  @Override
  public String getName() {
    return THREE_BLACK_CROWS;
  }
}
