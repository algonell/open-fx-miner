package com.fxminer.candlestickpattern.bullish;

import com.fxminer.Symbol;

/**
 * Prerequisites: 1. Preceding downtrend. 2. Little/no upper shadow. 3. Lower shadow at least 2x
 * longer than real body.
 *
 * @author Andrew Kreimer
 */
public final class Hammer extends BullishPattern {

  private static final String NAME = "Hammer";

  @Override
  public boolean isPresent(Symbol symbol) {
    var last = symbol.getHistory().size() - 1;
    var q = symbol.getHistory().get(last);
    var body = q.getClose() - q.getOpen();

    return q.getOpen() < q.getClose()
        && q.getClose() == q.getHigh()
        && q.getOpen() > q.getLow()
        && body * 2 <= (q.getOpen() - q.getLow());
  }

  @Override
  public String getName() {
    return NAME;
  }
}
