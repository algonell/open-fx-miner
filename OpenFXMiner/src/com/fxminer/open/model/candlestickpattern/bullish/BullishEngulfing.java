package com.fxminer.open.model.candlestickpattern.bullish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend. 2. First candle red, second candle green. 3. First candle
 * shorter, second candle longer. 4. First candle entirely contained in real body of second candle.
 * 
 * @author Andrew Kreimer
 *
 */
public class BullishEngulfing extends BullishPattern {

  private static final String BULLISH_ENGULFING = "BullishEngulfing";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);

    return q1.getOpen() - q1.getAdjClose() > 0 && q0.getAdjClose() - q0.getOpen() > 0
        && q1.getHigh() - q1.getLow() < q0.getHigh() - q0.getLow()
        && q1.getOpen() < q0.getAdjClose() && q1.getAdjClose() > q0.getOpen();
  }

  @Override
  public String getName() {
    return BULLISH_ENGULFING;
  }

}
