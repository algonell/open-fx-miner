package com.fxminer.open.model.candlestick;

import com.fxminer.open.model.Symbol;

/**
 * Abstract representation of all possible candlestick patterns
 * 
 * @author Andrew Kreimer
 *
 */
public interface CandlestickPattern {
	
	/**
	 * Check if this pattern exists for a given Symbol
	 * 
	 * @param symbol
	 * @return
	 */
	public boolean isPresent(Symbol symbol);
	
	/**
	 * Get the trade direction
	 * 
	 * @return
	 */
	public PatternDirection getDirection();
}
