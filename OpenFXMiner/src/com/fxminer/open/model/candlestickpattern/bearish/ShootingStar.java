package com.fxminer.open.model.candlestickpattern.bearish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites: 1. Preceding uptrend. 2. Little/no lower shadow. 3. Upper shadow at least 2x
 * longer than real body.
 *
 * @author Andrew Kreimer
 */
public class ShootingStar extends BearishPattern {

  private static final String SHOOTING_STAR = "ShootingStar";

  @Override
  public boolean isPresent(Symbol symbol) {
    Quote q = symbol.getHistory().get(0);
    double body = q.getOpen() - q.getAdjClose();

    return q.getOpen() > q.getAdjClose()
        && q.getAdjClose() == q.getLow()
        && q.getOpen() < q.getHigh()
        && body * 2 <= (q.getHigh() - q.getOpen());
  }

  @Override
  public String getName() {
    return SHOOTING_STAR;
  }
}
