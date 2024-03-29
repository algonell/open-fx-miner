package com.fxminer.candlestickpattern;

import com.fxminer.Symbol;

/**
 * Abstract representation of all possible candlestick patterns.
 *
 * @author Andrew Kreimer
 */
public interface CandlestickPattern {

  /** Maximum threshold between equal prices comparison. */
  double EPSILON = 0.01;

  /** Checks if this pattern exists for a given Symbol. */
  boolean isPresent(Symbol symbol);

  /** Gets the trade direction. */
  PatternDirection getDirection();

  /** Gets the pattern name. */
  String getName();
}
