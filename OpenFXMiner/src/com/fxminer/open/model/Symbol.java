package com.fxminer.open.model;

import java.util.ArrayList;

/**
 * Holds data for symbol
 * Name examples: GOOG, FB, EURUSD etc.
 * Period examples: M5, H1, D1 etc.
 * Class name examples: Up or Down etc.
 *  
 * @author Andrew Kreimer
 *
 */
public class Symbol{
	private String name;
	private String period;
	private String className;
	/** 
	 * [0] is the most recent tick, [n] is the last in history (oldest)
	 */
	private ArrayList<Quote> history = new ArrayList<Quote>();
	
	public Symbol(String period, String name) {
		super();
		this.name = name;
		this.period = period;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Quote> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<Quote> history) {
		this.history = history;
	}
}
