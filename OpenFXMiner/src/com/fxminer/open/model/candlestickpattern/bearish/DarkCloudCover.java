package com.fxminer.open.model.candlestickpattern.bearish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. First candle green, second candle red. 3. Second candle
 * opened above/at closing of first candle. 4. Second candle closed at 50% or more of real body of
 * first candle.
 *
 * @author Andrew Kreimer
 */
public class DarkCloudCover extends BearishPattern {

  private static final String DARK_CLOUD_COVER = "DarkCloudCover";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);
    double q1Body = q1.getAdjClose() - q1.getOpen();

    return q1.getAdjClose() - q1.getOpen() > 0
        && q0.getOpen() - q0.getAdjClose() > 0
        && q0.getOpen() >= q1.getAdjClose()
        && q0.getAdjClose() <= q1.getAdjClose() - q1Body / 2
        && q0.getAdjClose() > q1.getOpen();
  }

  @Override
  public String getName() {
    return DARK_CLOUD_COVER;
  }
}
