package com.fxminer.open.model.candlestickpattern.bullish;

import com.fxminer.open.model.Quote;
import com.fxminer.open.model.Symbol;

/**
 * Prerequisites:
 * 1. Preceding downtrend
 * 2. 3 consecutive long green candles
 * 3. Each candle closed successively higher
 * 
 * @author Andrew Kreimer
 *
 */
public class ThreeWhiteSoldiers extends BullishPattern {

	@Override
	public boolean isPresent(Symbol symbol) {
		Quote q0 = symbol.getHistory().get(0);
		Quote q1 = symbol.getHistory().get(1);
		Quote q2 = symbol.getHistory().get(2);
		
		return q0.getAdjClose() - q0.getOpen() > 0 && //three greens
				q1.getAdjClose() - q1.getOpen() > 0 &&
				q2.getAdjClose() - q2.getOpen() > 0 &&
				q0.getAdjClose() > q1.getAdjClose() && //higher close
				q1.getAdjClose() > q2.getAdjClose() &&
				q1.getAdjClose() > q0.getOpen() &&
				q2.getAdjClose() > q1.getOpen() &&
				q0.getOpen() > q1.getOpen() && //higher open
				q1.getOpen() > q2.getOpen();
	}

	@Override
	public String getName() {
		return "ThreeWhiteSoldiers";
	}

}
