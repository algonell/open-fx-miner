package com.fxminer.open.model.candlestickpattern.bearish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites:
 * 1. Preceding uptrend
 * 2. Little/no real body
 * 3. Little/no lower shadow
 * 4. Long upper shadow
 * 
 * @author Andrew Kreimer
 *
 */
public class GravestoneDoji extends BearishPattern {

	@Override
	public boolean isPresent(Symbol symbol) {
		Quote q = symbol.getHistory().get(0);
		
		return q.getOpen() == q.getLow() && 
				q.getOpen() == q.getAdjClose() &&
				q.getOpen() < q.getHigh();
	}

	@Override
	public String getName() {
		return "GravestoneDoji";
	}

}
