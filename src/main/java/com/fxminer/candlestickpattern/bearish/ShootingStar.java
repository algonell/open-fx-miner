package com.fxminer.candlestickpattern.bearish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. Little/no lower shadow. 3. Upper shadow at least 2x
 * longer than real body.
 *
 * @author Andrew Kreimer
 */
public final class ShootingStar extends BearishPattern {

  private static final String SHOOTING_STAR = "ShootingStar";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q = symbol.getHistory().get(last);
    var body = q.getOpen() - q.getClose();

    return q.getOpen() > q.getClose()
        && q.getClose() == q.getLow()
        && q.getOpen() < q.getHigh()
        && body * 2 <= (q.getHigh() - q.getOpen());
  }

  @Override
  public String getName() {
    return SHOOTING_STAR;
  }
}
