package com.fxminer.open.model.candlestickpattern.bullish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites:
 * 1. Preceding downtrend.
 * 2. Little/no real body.
 * 3. Little/no upper shadow.
 * 4. Long lower shadow.
 * 
 * @author Andrew Kreimer
 *
 */
public class DragonflyDoji extends BullishPattern {

	private static final String DRAGONFLY_DOJI = "DragonflyDoji";

	@Override
	public boolean isPresent(Symbol symbol) {
		Quote q = symbol.getHistory().get(0);
		
		return q.getOpen() == q.getHigh() && 
				q.getOpen() == q.getAdjClose() &&
				q.getOpen() > q.getLow();
	}

	@Override
	public String getName() {
		return DRAGONFLY_DOJI;
	}

}