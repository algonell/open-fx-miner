package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. Little/no real body. 3. Little/no lower shadow. 4. Long
 * upper shadow.
 *
 * @author Andrew Kreimer
 */
public final class GravestoneDoji extends BearishPattern {

  private static final String GRAVESTONE_DOJI = "GravestoneDoji";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q = symbol.getHistory().get(last);

    return q.getOpen() == q.getLow() && q.getOpen() == q.getClose() && q.getOpen() < q.getHigh();
  }

  @Override
  public String getName() {
    return GRAVESTONE_DOJI;
  }
}
