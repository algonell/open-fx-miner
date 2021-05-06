package com.fxminer.open.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Holds data for symbol.
 * Name examples: GOOG, FB, EURUSD etc.
 * Period examples: M5, H1, D1 etc.
 * Class name examples: Up or Down etc.
 * 
 * @author Andrew Kreimer
 *
 */
public class Symbol implements Serializable {
	
	private static final long serialVersionUID = 7595668717527149119L;
	
	private String name;
	private String period;
	private String className;
	
	/** 
	 * [0] is the most recent tick, [n] is the last in history (oldest).
	 */
	private List<Quote> history = new ArrayList<>();
	
	public Symbol(String period, String name) {
		this.name = name;
		this.period = period;
	}
	
	public Symbol(Symbol copy) {
		this.name = copy.name;
		this.period = copy.period;
		this.className = copy.className;
		this.history = copy.history;
	}
	
	public String getClassName() { return className; }
	public void setClassName(String className) { this.className = className; }
	public String getPeriod() { return period; }
	public void setPeriod(String period) { this.period = period; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public List<Quote> getHistory() { return history; }
	public void setHistory(List<Quote> history) { this.history = history; }
	
	/**
	 * Calculates avergae volume.
	 * Similar to Yahoo Avg Vol: 63 days mean volume.
	 * 
	 * @return 3 month avg vol
	 */
	public long calculateAvgVol() {
		var stats = new DescriptiveStatistics();
		
		//min data points is 63
		if(history.size() < 63){
			return 0;
		}
		
		//calc mean
		for (var i = 0; i < 63; i++) {
			stats.addValue(history.get(i).getVolume());
		}
		
		return (long)stats.getMean();
	}
	
}