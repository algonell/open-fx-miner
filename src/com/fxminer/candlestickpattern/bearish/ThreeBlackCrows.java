package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Quote;
import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. 3 consecutive long red candles. 3. Each candle closed
 * successively lower.
 *
 * @author Andrew Kreimer
 */
public class ThreeBlackCrows extends BearishPattern {

  private static final String THREE_BLACK_CROWS = "ThreeBlackCrows";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);
    Quote q2 = symbol.getHistory().get(2);

    return q0.getOpen() - q0.getAdjClose() > 0
        && // three reds
        q1.getOpen() - q1.getAdjClose() > 0
        && q2.getOpen() - q2.getAdjClose() > 0
        && q0.getAdjClose() < q1.getAdjClose()
        && // lower close
        q1.getAdjClose() < q2.getAdjClose()
        && q1.getAdjClose() < q0.getOpen()
        && q2.getAdjClose() < q1.getOpen()
        && q0.getOpen() < q1.getOpen()
        && // lower open
        q1.getOpen() < q2.getOpen();
  }

  @Override
  public String getName() {
    return THREE_BLACK_CROWS;
  }
}
