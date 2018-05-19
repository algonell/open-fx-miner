package com.fxminer.open.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Container for basic asset data (FX currency pair, Stock and etc.)
 *
 */
public class Quote implements QuoteValidator, Serializable {
	private static final long serialVersionUID = -2885651778065986388L;
	
	/**
	 * OHLC
	 */
	protected double open;
	protected double high;
	protected double low;
	protected double close;
	protected double adjClose;
	protected long volume;
	protected Date timestamp;

	/**
	 * Trend
	 */
	protected String classifiedTrend;

	public Quote(double open, double high, double low, double close, double adjClose, long volume, Date timestamp) {
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjClose = adjClose;
		this.volume = volume;
		this.timestamp = timestamp;
	}

	public double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public String getClassifiedTrend() {
		return classifiedTrend;
	}

	public void setClassifiedTrend(String classifiedTrend) {
		this.classifiedTrend = classifiedTrend;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}
	
	/**
	 * Checks if the data is clean and ready for calculations
	 * @return
	 */
	public boolean isCleanAttribute(){
		return open != 0 && 
				high != 0 && 
				low != 0 && 
				close != 0;
	}

	/**
	 * Checks if the data is clean and ready for calculations
	 * @return
	 */
	public boolean isCleanClass() {
		return !classifiedTrend.equals(Trend.Uncertainty.name()) && !classifiedTrend.equals(Trend.Ranging.name());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(timestamp.getTime()).append(",");

		// OHLC
		sb.append(open).append(",").append(high).append(",").append(low).append(",").append(close).append(",").append(adjClose).append(",").append(volume);
		
		return sb.toString();
	}
	
}
