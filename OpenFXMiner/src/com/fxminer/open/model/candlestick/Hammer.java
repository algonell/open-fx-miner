package com.fxminer.open.model.candlestick;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites:
 * 1. Preceding downtrend
 * 2. Little/no upper shadow
 * 3. Lower shadow at least 2x longer than real body
 * 
 * @author Andrew Kreimer
 *
 */
public class Hammer extends BullishPattern {

	@Override
	public boolean isPresent(Symbol symbol) {
		Quote q = symbol.getHistory().get(0);
		double body = q.getAdjClose() - q.getOpen();
		
		return q.getOpen() < q.getAdjClose() && 
				q.getAdjClose() == q.getHigh() &&
				q.getOpen() > q.getLow() &&
				body * 2 <= (q.getOpen() - q.getLow());
	}

	@Override
	public String getName() {
		return "Hammer";
	}

}
