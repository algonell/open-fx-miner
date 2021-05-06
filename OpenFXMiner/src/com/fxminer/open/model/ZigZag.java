package com.fxminer.open.model;

import java.util.logging.Logger;

/**
 * Java implementation of MQL ZigZag indicator.
 * 
 * @author Andrew Kreimer
 *
 */
public class ZigZag {
	
	private static final Logger LOGGER = Logger.getLogger(ZigZag.class.getName());
	
	// auxiliary enumeration
	private static final int PIKE = 1; // searching for next high
	private static final int SILL = -1; // searching for next low
	
	// input parameters
	private int depth; //12
	private int deviation; //5
	private int backstep; //3
	private double point; //0.0001~0.01

	// indicator buffers
	private double[] zigzagBuffer; // main buffer
	private double[] highMapBuffer; // highs
	private double[] lowMapBuffer; // lows
	private int level = 3; // recounting depth
	private double deviationInPoints; // deviation in points

	// calculation fields
	private int limit;
	private int counterZ;
	private int whatlookfor;
	private int shift;
	private int back;
	private int lasthighpos;
	private int lastlowpos;
	private double val;
	private double res;
	private double curlow;
	private double curhigh;
	private double lasthigh;
	private double lastlow;
	private int prevCalculated;	
	
	public ZigZag(int depth, int deviation, int backstep, double point) {
		this.depth = depth;
		this.setDeviation(deviation);
		this.backstep = backstep;
		this.setPoint(point);
		// to use in cycle
		deviationInPoints = deviation * point;
	}

	/**
	 * Searches for index of the highest bar.
	 * 
	 * @param array
	 * @param depth
	 * @param startPos
	 */
	private int iHighest(double[] array, int depth, int startPos) {
		int index = startPos;

		// --- start index validation
		if (startPos < 0) {
			LOGGER.warning(() -> String.format("Invalid parameter in the function iHighest, startPos = %s", startPos));
			return 0;
		}

		// --- depth correction if need
		if (startPos - depth < 0)
			depth = startPos;

		double max = array[startPos];

		// --- start searching
		for (int i = startPos; i > startPos - depth; i--) {
			if (array[i] > max) {
				index = i;
				max = array[i];
			}
		}

		// --- return index of the highest bar
		return (index);
	}

	/**
	 * Searches for index of the lowest bar
	 * 
	 * @param array
	 * @param depth
	 * @param startPos
	 */
	private int iLowest(double[] array, int depth, int startPos) {
		int index = startPos;

		// --- start index validation
		if (startPos < 0) {
			LOGGER.warning(() -> String.format("Invalid parameter in the function iLowest, startPos = %s", startPos));
			return 0;
		}

		// --- depth correction if need
		if (startPos - depth < 0)
			depth = startPos;

		double min = array[startPos];

		// --- start searching
		for (int i = startPos; i > startPos - depth; i--) {
			if (array[i] < min) {
				index = i;
				min = array[i];
			}
		}

		// --- return index of the lowest bar
		return (index);
	}

	/**
	 * Performs custom calculation.
	 * 
	 * @param ratesTotal
	 * @param high
	 * @param low
	 */
	public void calculate(int ratesTotal, double[] high, double[] low) {
		init(ratesTotal);

		// set start position for calculations
		if (prevCalculated == 0) limit = depth;

		// ZigZag was already counted before
		calculateIfAlreadyCountedBefore(ratesTotal);

		// searching High and Low
		searchForHighAndLow(ratesTotal, high, low);

		// last preparation
		if (whatlookfor == 0) { // uncertain quantity
			lastlow = 0;
			lasthigh = 0;
		} else {
			lastlow = curlow;
			lasthigh = curhigh;
		}

		// final rejection
		for (shift = limit; shift < ratesTotal; shift++) {
			switch (whatlookfor) {
				case 0: // search for peak or lawn
					searchForPeakOrLawn(high, low);
					break;
	
				case PIKE: // search for peak
					searchForPeak();
					break;
	
				case SILL: // search for lawn
					searchForLawn();
					break;
					
				default:
					throw new UnsupportedOperationException();
			}
		}
	}

	/**
	 * Searches for lawn.
	 */
	private void searchForLawn() {
		if (highMapBuffer[shift] != 0.0 && highMapBuffer[shift] > lasthigh && lowMapBuffer[shift] == 0.0) {
			zigzagBuffer[lasthighpos] = 0.0;
			lasthighpos = shift;
			lasthigh = highMapBuffer[shift];
			zigzagBuffer[shift] = lasthigh;
		}

		if (lowMapBuffer[shift] != 0.0 && highMapBuffer[shift] == 0.0) {
			lastlow = lowMapBuffer[shift];
			lastlowpos = shift;
			zigzagBuffer[shift] = lastlow;
			whatlookfor = PIKE;
		}
	}

	/**
	 * Searches for peaks.
	 */
	private void searchForPeak() {
		if (lowMapBuffer[shift] != 0.0 && lowMapBuffer[shift] < lastlow && highMapBuffer[shift] == 0.0) {
			zigzagBuffer[lastlowpos] = 0.0;
			lastlowpos = shift;
			lastlow = lowMapBuffer[shift];
			zigzagBuffer[shift] = lastlow;
		}

		if (highMapBuffer[shift] != 0.0 && lowMapBuffer[shift] == 0.0) {
			lasthigh = highMapBuffer[shift];
			lasthighpos = shift;
			zigzagBuffer[shift] = lasthigh;
			whatlookfor = SILL;
		}
	}

	/**
	 * Searches for peak or lawn.
	 * 
	 * @param high
	 * @param low
	 */
	private void searchForPeakOrLawn(double[] high, double[] low) {
		if (lastlow == 0 && lasthigh == 0) {
			if (highMapBuffer[shift] != 0) {
				lasthigh = high[shift];
				lasthighpos = shift;
				whatlookfor = SILL;
				zigzagBuffer[shift] = lasthigh;
			}

			if (lowMapBuffer[shift] != 0) {
				lastlow = low[shift];
				lastlowpos = shift;
				whatlookfor = PIKE;
				zigzagBuffer[shift] = lastlow;
			}
		}
	}

	/**
	 * Searches for high and low.
	 * 
	 * @param ratesTotal
	 * @param high
	 * @param low
	 */
	private void searchForHighAndLow(int ratesTotal, double[] high, double[] low) {
		for (shift = limit; shift < ratesTotal; shift++) {
			handleLowFound(low);

			// high
			handleHighFound(high);
		}
	}

	/**
	 * Handles low found.
	 * 
	 * @param low
	 */
	private void handleLowFound(double[] low) {
		val = low[iLowest(low, depth, shift)];

		if (val == lastlow)
			val = 0.0;
		else {
			lastlow = val;

			if ((low[shift] - val) > deviationInPoints)
				val = 0.0;
			else {
				for (back = 1; back <= backstep; back++) {
					res = lowMapBuffer[shift - back];

					if ((res != 0) && (res > val))
						lowMapBuffer[shift - back] = 0.0;
				}
			}
		}

		if (low[shift] == val)
			lowMapBuffer[shift] = val;
		else
			lowMapBuffer[shift] = 0.0;
	}

	/**
	 * Handles found high.
	 * 
	 * @param high
	 */
	private void handleHighFound(double[] high) {
		val = high[iHighest(high, depth, shift)];

		if (val == lasthigh)
			val = 0.0;
		else {
			lasthigh = val;

			if ((val - high[shift]) > deviationInPoints)
				val = 0.0;
			else {
				for (back = 1; back <= backstep; back++) {
					res = highMapBuffer[shift - back];

					if ((res != 0) && (res < val))
						highMapBuffer[shift - back] = 0.0;
				}
			}
		}

		if (high[shift] == val)
			highMapBuffer[shift] = val;
		else
			highMapBuffer[shift] = 0.0;
	}

	/**
	 * Performs calculations if previously done.
	 * 
	 * @param ratesTotal
	 */
	private void calculateIfAlreadyCountedBefore(int ratesTotal) {
		int i;
		if (prevCalculated > 0) {
			i = ratesTotal - 1;

			// searching third extremum from the last uncompleted bar
			while (counterZ < level && i > ratesTotal - 100) {
				res = zigzagBuffer[i];

				if (res != 0)
					counterZ++;

				i--;
			}

			i++;
			limit = i;

			// what type of exremum we are going to find
			if (lowMapBuffer[i] != 0) {
				curlow = lowMapBuffer[i];
				whatlookfor = PIKE;
			} else {
				curhigh = highMapBuffer[i];
				whatlookfor = SILL;
			}

			// chipping
			for (i = limit + 1; i < ratesTotal; i++) {
				zigzagBuffer[i] = 0.0;
				lowMapBuffer[i] = 0.0;
				highMapBuffer[i] = 0.0;
			}
		}
	}

	/**
	 * Initializes variables.
	 * 
	 * @param ratesTotal
	 */
	private void init(int ratesTotal) {
		limit = 0;
		counterZ = 0;
		whatlookfor = 0;
		shift = 0;
		back = 0;
		lasthighpos = 0;
		lastlowpos = 0;
		val = 0;
		res = 0;
		curlow = 0;
		curhigh = 0;
		lasthigh = 0;
		lastlow = 0;
		prevCalculated = 0;	

		zigzagBuffer = new double[ratesTotal];
		highMapBuffer = new double[ratesTotal];
		lowMapBuffer = new double[ratesTotal];

		if (ratesTotal < 100) LOGGER.warning("Not ebought bars for calculation");
	}

	public double[] getZigzagBuffer() { return zigzagBuffer; }
	public double[] getHighMapBuffer() { return highMapBuffer; }
	public double[] getLowMapBuffer() { return lowMapBuffer; }

	public String calculateTrend(int bars, int i) {
		var zzTrend = Trend.UNCERTAINTY;

		do {
			if (highMapBuffer[i] != 0)
				zzTrend = Trend.DOWN;
			else if (lowMapBuffer[i] != 0)
				zzTrend = Trend.UP;

			i++;
		} while (zzTrend == Trend.UNCERTAINTY && i < bars - 1);

		return zzTrend.name();
	}

	public int getDeviation() { return deviation; }
	public void setDeviation(int deviation) { this.deviation = deviation; }
	public double getPoint() { return point; }
	public void setPoint(double point) { this.point = point; }
	
}