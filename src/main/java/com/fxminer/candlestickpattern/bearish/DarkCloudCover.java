package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. First candle green, second candle red. 3. Second candle
 * opened above/at closing of first candle. 4. Second candle closed at 50% or more of real body of
 * first candle.
 *
 * @author Andrew Kreimer
 */
public final class DarkCloudCover extends BearishPattern {

  private static final String DARK_CLOUD_COVER = "DarkCloudCover";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);
    var q1Body = q1.getClose() - q1.getOpen();

    return q1.getClose() - q1.getOpen() > 0
        && q0.getOpen() - q0.getClose() > 0
        && q0.getOpen() >= q1.getClose()
        && q0.getClose() <= q1.getClose() - q1Body / 2
        && q0.getClose() > q1.getOpen();
  }

  @Override
  public String getName() {
    return DARK_CLOUD_COVER;
  }
}
