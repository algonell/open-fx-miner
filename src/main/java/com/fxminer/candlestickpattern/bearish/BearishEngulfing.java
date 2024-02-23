package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. First candle green, second candle red. 3. First candle
 * shorter, second candle longer. 4. First candle entirely contained in real body of second candle.
 *
 * @author Andrew Kreimer
 */
public final class BearishEngulfing extends BearishPattern {

  private static final String BEARISH_ENGULFING = "BearishEngulfing";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);

    return q1.getClose() - q1.getOpen() > 0
        && q0.getOpen() - q0.getClose() > 0
        && q1.getHigh() - q1.getLow() < q0.getHigh() - q0.getLow()
        && q1.getOpen() > q0.getClose()
        && q1.getClose() < q0.getOpen();
  }

  @Override
  public String getName() {
    return BEARISH_ENGULFING;
  }
}
