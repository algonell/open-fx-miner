package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Quote;
import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. First candle green and long. 3. Second candle's real body
 * tiny. 4. Third candle red, closed at 50% or more of real body of first candle.
 *
 * @author Andrew Kreimer
 */
public class EveningStar extends BearishPattern {

  private static final String EVENING_STAR = "EveningStar";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q0 = symbol.getHistory().get(0);
    Quote q1 = symbol.getHistory().get(1);
    Quote q2 = symbol.getHistory().get(2);
    double q0Body = q0.getOpen() - q0.getAdjClose();
    double q1Body = q1.getOpen() - q1.getAdjClose();
    double q2Body = q1.getAdjClose() - q1.getOpen();

    return q2.getAdjClose() - q2.getOpen() > 0
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
        q0.getAdjClose() <= q2.getAdjClose() - q2Body / 2; // 4.
  }

  @Override
  public String getName() {
    return EVENING_STAR;
  }
}
