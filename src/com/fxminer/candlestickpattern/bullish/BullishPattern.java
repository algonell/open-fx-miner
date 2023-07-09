package com.fxminer.candlestickpattern.bullish;

import com.fxminer.candlestickpattern.CandlestickPattern;
import com.fxminer.candlestickpattern.PatternDirection;

/**
 * Bullish candlestick patterns.
 *
 * @author Andrew Kreimer
 */
public abstract class BullishPattern implements CandlestickPattern {

  @Override
  public PatternDirection getDirection() {
    return PatternDirection.BULLISH;
  }
}
