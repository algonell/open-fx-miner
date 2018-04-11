package com.fxminer.open.model.candlestickpattern.bearish;

import com.fxminer.open.model.candlestickpattern.CandlestickPattern;
import com.fxminer.open.model.candlestickpattern.PatternDirection;

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
