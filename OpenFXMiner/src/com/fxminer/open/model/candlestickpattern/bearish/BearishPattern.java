package com.fxminer.open.model.candlestick;

/**
 * Bearish candlestick patterns
 * 
 * @author Andrew Kreimer
 *
 */
public abstract class BearishPattern implements CandlestickPattern {

	@Override
	public PatternDirection getDirection() {
		return PatternDirection.Bearish;
	}

}
