package com.fxminer.open.model.candlestickpattern.bullish;

import com.fxminer.open.model.candlestickpattern.CandlestickPattern;
import com.fxminer.open.model.candlestickpattern.PatternDirection;

/**
 * Bullish candlestick patterns.
 * 
 * @author Andrew Kreimer
 *
 */
public abstract class BullishPattern implements CandlestickPattern {

	@Override
	public PatternDirection getDirection() {
		return PatternDirection.BULLISH;
	}

}