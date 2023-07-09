package com.fxminer.candlestickpattern.bullish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend. 2. Little/no real body. 3. Little/no upper shadow. 4. Long
 * lower shadow.
 *
 * @author Andrew Kreimer
 */
public final class DragonflyDoji extends BullishPattern {

  private static final String DRAGONFLY_DOJI = "DragonflyDoji";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q = symbol.getHistory().get(last);

    return q.getOpen() == q.getHigh() && q.getOpen() == q.getClose() && q.getOpen() > q.getLow();
  }

  @Override
  public String getName() {
    return DRAGONFLY_DOJI;
  }
}
