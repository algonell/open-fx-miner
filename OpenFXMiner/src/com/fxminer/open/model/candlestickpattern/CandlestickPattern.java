package com.fxminer.open.model.candlestickpattern;

import com.fxminer.open.model.Symbol;

/**
 * Abstract representation of all possible candlestick patterns
 * 
 * @author Andrew Kreimer
 *
 */
public interface CandlestickPattern {
	
	/**
	 * Maximum threshold between equal prices comparison 
	 */
	double EPSILON = 0.01;
	
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
	
	/**
	 * Get the pattern name
	 * 
	 * @return
	 */
	public String getName();
}
