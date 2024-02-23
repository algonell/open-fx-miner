package com.fxminer.candlestickpattern.bullish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend 2. 3 consecutive long green candles 3. Each candle closed
 * successively higher
 *
 * @author Andrew Kreimer
 */
public final class ThreeWhiteSoldiers extends BullishPattern {

  private static final String THREE_WHITE_SOLDIERS = "ThreeWhiteSoldiers";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);
    var q2 = symbol.getHistory().get(last - 2);

    return q0.getClose() - q0.getOpen() > 0
        && // three greens
        q1.getClose() - q1.getOpen() > 0
        && q2.getClose() - q2.getOpen() > 0
        && q0.getClose() > q1.getClose()
        && // higher close
        q1.getClose() > q2.getClose()
        && q1.getClose() > q0.getOpen()
        && q2.getClose() > q1.getOpen()
        && q0.getOpen() > q1.getOpen()
        && // higher open
        q1.getOpen() > q2.getOpen();
  }

  @Override
  public String getName() {
    return THREE_WHITE_SOLDIERS;
  }
}
