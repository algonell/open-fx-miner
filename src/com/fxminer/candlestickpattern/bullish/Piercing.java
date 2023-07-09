package com.fxminer.candlestickpattern.bullish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend 2. First candle red, second candle green 3. Second candle
 * opened below/at closing of first candle 4. Second candle closed at 50% or more of real body of
 * first candle
 *
 * @author Andrew Kreimer
 */
public final class Piercing extends BullishPattern {

  private static final String STR_PIERCING = "Piercing";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);
    var q1Body = q1.getOpen() - q1.getClose();

    return q1.getOpen() - q1.getClose() > 0
        && q0.getClose() - q0.getOpen() > 0
        && q0.getOpen() <= q1.getClose()
        && q0.getClose() >= q1.getClose() + q1Body / 2
        && q0.getClose() < q1.getOpen();
  }

  @Override
  public String getName() {
    return STR_PIERCING;
  }
}
