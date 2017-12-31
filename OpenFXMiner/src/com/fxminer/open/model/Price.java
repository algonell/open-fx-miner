package com.fxminer.open.model;

/**
 * Holds data for real-time price quote
 * 
 * @author Andrew Kreimer
 *
 */
public class Price {
	private double bid;
	private double ask;
	private long volume;

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}
	
	public double getMid() {
		return weka.core.Utils.roundDouble((ask + bid) / 2, 2);
	}
}
