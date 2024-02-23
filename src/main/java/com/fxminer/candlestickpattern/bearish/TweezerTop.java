package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. First candle green, second candle red. 3. Both candles
 * sharing same/almost same high.
 *
 * @author Andrew Kreimer
 */
public final class TweezerTop extends BearishPattern {

  private static final String TWEEZER_BOTTOM = "TweezerBottom";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);

    return q1.getClose() - q1.getOpen() > 0
        && // first green
        q0.getOpen() - q0.getClose() > 0
        && // second red
        q1.getOpen() == q0.getClose()
        && // same body
        q1.getClose() == q0.getOpen()
        && q1.getClose() < q1.getHigh()
        && q0.getHigh() == q1.getHigh(); // same high
  }

  @Override
  public String getName() {
    return TWEEZER_BOTTOM;
  }
}
