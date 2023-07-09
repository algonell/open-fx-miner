package com.fxminer.candlestickpattern.bullish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend. 2. First candle red, second candle green. 3. Both candles
 * sharing same/almost same low.
 *
 * @author Andrew Kreimer
 */
public final class TweezerBottom extends BullishPattern {

  private static final String TWEEZER_BOTTOM = "TweezerBottom";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);

    return q1.getOpen() - q1.getClose() > 0
        && // second green
        q0.getClose() - q0.getOpen() > 0
        && // first red
        q1.getOpen() == q0.getClose()
        && // same body
        q1.getClose() == q0.getOpen()
        && q1.getClose() > q1.getLow()
        && q0.getLow() == q1.getLow(); // same low
  }

  @Override
  public String getName() {
    return TWEEZER_BOTTOM;
  }
}
