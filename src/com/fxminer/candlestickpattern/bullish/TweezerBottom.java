package com.fxminer.candlestickpattern.bullish;

import com.fxminer.Quote;
import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend. 2. First candle red, second candle green. 3. Both candles
 * sharing same/almost same low.
 *
 * @author Andrew Kreimer
 */
public class TweezerBottom extends BullishPattern {

  private static final String TWEEZER_BOTTOM = "TweezerBottom";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);

    return q1.getOpen() - q1.getAdjClose() > 0
        && // second green
        q0.getAdjClose() - q0.getOpen() > 0
        && // first red
        q1.getOpen() == q0.getAdjClose()
        && // same body
        q1.getAdjClose() == q0.getOpen()
        && q1.getAdjClose() > q1.getLow()
        && q0.getLow() == q1.getLow(); // same low
  }

  @Override
  public String getName() {
    return TWEEZER_BOTTOM;
  }
}
