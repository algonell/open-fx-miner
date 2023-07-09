package com.fxminer.candlestickpattern.bullish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend 2. First candle red and long 3. Second candle's real body
 * tiny 4. Third candle green, closed at 50% or more of real body of first candle
 *
 * @author Andrew Kreimer
 */
public final class MorningStar extends BullishPattern {

  private static final String MORNING_STAR = "MorningStar";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);
    var q2 = symbol.getHistory().get(last - 2);
    var q0Body = q0.getClose() - q0.getOpen();
    var q1Body = q1.getClose() - q1.getOpen();
    var q2Body = q1.getOpen() - q1.getClose();

    return q2.getOpen() - q2.getClose() > 0
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
        q0.getClose() >= q2.getClose() + q2Body / 2; // 4.
  }

  @Override
  public String getName() {
    return MORNING_STAR;
  }
}
