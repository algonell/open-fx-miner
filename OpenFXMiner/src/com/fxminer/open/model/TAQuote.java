package com.fxminer.open.model;

import java.util.Date;

/**
 * Container for basic asset dimensions with Technical Analysis indicators for Data Mining
 * Uses Moving Averages, CCI and RSI
 * 
 * @author Andrew Kreimer
 * 
 */
public class TAQuote extends Quote {
	// SMA
	protected double sma10;
	protected double sma20;
	protected double sma50;

	// CCI
	protected double cci14;
	protected double cci21;
	protected double cci28;

	// RSI
	protected double rsi14;
	protected double rsi21;
	protected double rsi28;

	// Trend
	protected String classifiedTrend;

	public TAQuote(double open, double high, double low, double close, Date timestamp) {
		super(open, high, low, close, timestamp);
	}

	public double getCci28() {
		return cci28;
	}

	public void setCci28(double cci28) {
		this.cci28 = cci28;
	}

	public double getRsi28() {
		return rsi28;
	}

	public void setRsi28(double rsi28) {
		this.rsi28 = rsi28;
	}

	public double getSma10() {
		return sma10;
	}

	public void setSma10(double sma10) {
		this.sma10 = sma10;
	}

	public double getSma20() {
		return sma20;
	}

	public void setSma20(double sma20) {
		this.sma20 = sma20;
	}

	public double getSma50() {
		return sma50;
	}

	public void setSma50(double sma50) {
		this.sma50 = sma50;
	}

	public double getCci14() {
		return cci14;
	}

	public void setCci14(double cci14) {
		this.cci14 = cci14;
	}

	public double getCci21() {
		return cci21;
	}

	public void setCci21(double cci21) {
		this.cci21 = cci21;
	}

	public double getRsi14() {
		return rsi14;
	}

	public void setRsi14(double rsi14) {
		this.rsi14 = rsi14;
	}

	public double getRsi21() {
		return rsi21;
	}

	public void setRsi21(double rsi21) {
		this.rsi21 = rsi21;
	}

	public String getClassifiedTrend() {
		return classifiedTrend;
	}

	public void setClassifiedTrend(String classifiedTrend) {
		this.classifiedTrend = classifiedTrend;
	}

	/**
	 * Provides CSV/ARFF style
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(timestamp.getTime()).append(",");

		// OHLC
		sb.append(open).append(",").append(high).append(",").append(low).append(",").append(close).append(",");

		// SMA
		sb.append(sma10).append(",").append(sma20).append(",").append(sma50).append(",");

		// CCI
		sb.append(cci14).append(",").append(cci21).append(",").append(cci28).append(",");

		// RSI
		sb.append(rsi14).append(",").append(rsi21).append(",").append(rsi28).append(",");

		// Class
		sb.append(classifiedTrend);
		
		return sb.toString();
	}

	/**
	 * Validate data
	 */
	public boolean isCleanAttribute() {
		return super.isCleanAttribute() &&

		// SMA
		sma10 > 0 && sma20 > 0 && sma50 > 0 &&

		// CCI
		// cci14 != 0 &&
		// cci21 != 0 &&

		// RSI
		rsi14 > 0 && rsi21 > 0 && rsi28 > 0; 	
	}

	/**
	 * Validate data
	 * @return
	 */
	public boolean isCleanClass() {
		return !classifiedTrend.equals(Trend.UNCERTAINTY) && !classifiedTrend.equals(Trend.RANGING);
	}
}
