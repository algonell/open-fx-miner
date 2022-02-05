package com.fxminer.open.model.candlestickpattern.bullish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend. 2. First candle red and long. 3. Second candle's real body
 * tiny. 4. Third candle green, closed at 50% or more of real body of first candle.
 *
 * @author Andrew Kreimer
 */
public class MorningStar extends BullishPattern {

  private static final String MORNING_STAR = "MorningStar";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);
    Quote q2 = symbol.getHistory().get(2);
    double q0Body = q0.getAdjClose() - q0.getOpen();
    double q1Body = q1.getAdjClose() - q1.getOpen();
    double q2Body = q1.getOpen() - q1.getAdjClose();

    return q2.getOpen() - q2.getAdjClose() > 0
        && // first red
        q0Body > 0
        && q1Body > 0
        && // second and third are green
        q2Body > q1Body
        && q2Body > q0Body
        && q1Body < q0Body
        && q1Body < q2Body
        && // second candle
        // body is tiny
        q0.getAdjClose() >= q2.getAdjClose() + q2Body / 2; // 4.
  }

  @Override
  public String getName() {
    return MORNING_STAR;
  }
}
