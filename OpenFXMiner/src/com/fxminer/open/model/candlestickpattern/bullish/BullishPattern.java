package com.fxminer.open.model.candlestick;

/**
 * Bullish candlestick patterns
 * 
 * @author Andrew Kreimer
 *
 */
public abstract class BullishPattern implements CandlestickPattern {

	@Override
	public PatternDirection getDirection() {
		return PatternDirection.Bullish;
	}

}
