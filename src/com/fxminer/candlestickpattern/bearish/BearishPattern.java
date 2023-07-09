package com.fxminer.candlestickpattern.bearish;

import com.fxminer.candlestickpattern.CandlestickPattern;
import com.fxminer.candlestickpattern.PatternDirection;

/**
 * Bearish candlestick patterns.
 *
 * @author Andrew Kreimer
 */
public abstract class BearishPattern implements CandlestickPattern {

  @Override
  public PatternDirection getDirection() {
    return PatternDirection.BEARISH;
  }
}
