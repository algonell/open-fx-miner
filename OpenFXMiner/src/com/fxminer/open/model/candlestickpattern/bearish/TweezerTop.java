package com.fxminer.open.model.candlestickpattern.bearish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. First candle green, second candle red. 3. Both candles
 * sharing same/almost same high.
 * 
 * @author Andrew Kreimer
 *
 */
public class TweezerTop extends BearishPattern {

  private static final String TWEEZER_BOTTOM = "TweezerBottom";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);

    return q1.getAdjClose() - q1.getOpen() > 0 && // first green
        q0.getOpen() - q0.getAdjClose() > 0 && // second red
        q1.getOpen() == q0.getAdjClose() && // same body
        q1.getAdjClose() == q0.getOpen() && q1.getAdjClose() < q1.getHigh()
        && q0.getHigh() == q1.getHigh(); // same high
  }

  @Override
  public String getName() {
    return TWEEZER_BOTTOM;
  }

}
