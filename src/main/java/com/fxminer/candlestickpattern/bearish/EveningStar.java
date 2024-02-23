package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. First candle green and long. 3. Second candle's real body
 * tiny. 4. Third candle red, closed at 50% or more of real body of first candle.
 *
 * @author Andrew Kreimer
 */
public final class EveningStar extends BearishPattern {

  private static final String EVENING_STAR = "EveningStar";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q0 = symbol.getHistory().get(last);
    var q1 = symbol.getHistory().get(last - 1);
    var q2 = symbol.getHistory().get(last - 2);
    var q0Body = q0.getOpen() - q0.getClose();
    var q1Body = q1.getOpen() - q1.getClose();
    var q2Body = q1.getClose() - q1.getOpen();

    return q2.getClose() - q2.getOpen() > 0
        && // first green
        q0Body > 0
        && q1Body > 0
        && // second and third are red
        q2Body > q1Body
        && q2Body > q0Body
        && q1Body < q0Body
        && q1Body < q2Body
        && // second candle
        // body is tiny
        q0.getClose() <= q2.getClose() - q2Body / 2; // 4.
  }

  @Override
  public String getName() {
    return EVENING_STAR;
  }
}
