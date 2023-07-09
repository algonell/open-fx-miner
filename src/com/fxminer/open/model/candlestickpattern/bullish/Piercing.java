package com.fxminer.open.model.candlestickpattern.bullish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend. 2. First candle red, second candle green. 3. Second candle
 * opened below/at closing of first candle. 4. Second candle closed at 50% or more of real body of
 * first candle.
 *
 * @author Andrew Kreimer
 */
public class Piercing extends BullishPattern {

  private static final String STR_PIERCING = "Piercing";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);
    double q1Body = q1.getOpen() - q1.getAdjClose();

    return q1.getOpen() - q1.getAdjClose() > 0
        && q0.getAdjClose() - q0.getOpen() > 0
        && q0.getOpen() <= q1.getAdjClose()
        && q0.getAdjClose() >= q1.getAdjClose() + q1Body / 2
        && q0.getAdjClose() < q1.getOpen();
  }

  @Override
  public String getName() {
    return STR_PIERCING;
  }
}
