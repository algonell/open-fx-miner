package com.fxminer.open.model.candlestickpattern.bearish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. First candle green, second candle red. 3. First candle
 * shorter, second candle longer. 4. First candle entirely contained in real body of second candle.
 * 
 * @author Andrew Kreimer
 *
 */
public class BearishEngulfing extends BearishPattern {

  private static final String BEARISH_ENGULFING = "BearishEngulfing";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);

    return q1.getAdjClose() - q1.getOpen() > 0 && q0.getOpen() - q0.getAdjClose() > 0
        && q1.getHigh() - q1.getLow() < q0.getHigh() - q0.getLow()
        && q1.getOpen() > q0.getAdjClose() && q1.getAdjClose() < q0.getOpen();
  }

  @Override
  public String getName() {
    return BEARISH_ENGULFING;
  }

}
