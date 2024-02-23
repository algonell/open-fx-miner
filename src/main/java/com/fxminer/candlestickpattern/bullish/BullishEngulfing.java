package com.fxminer.candlestickpattern.bullish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend. 2. First candle red, second candle green. 3. First candle
 * shorter, second candle longer. 4. First candle entirely contained in real body of second candle.
 *
 * @author Andrew Kreimer
 */
public final class BullishEngulfing extends BullishPattern {

  private static final String BULLISH_ENGULFING = "BullishEngulfing";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);

    return q1.getOpen() - q1.getClose() > 0
        && q0.getClose() - q0.getOpen() > 0
        && q1.getHigh() - q1.getLow() < q0.getHigh() - q0.getLow()
        && q1.getOpen() < q0.getClose()
        && q1.getClose() > q0.getOpen();
  }

  @Override
  public String getName() {
    return BULLISH_ENGULFING;
  }
}
