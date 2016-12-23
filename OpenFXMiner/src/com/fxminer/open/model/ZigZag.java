package com.fxminer.open.model;

/**
 * Java implementation of MQL ZigZag indicator
 */
public class ZigZag {
	// input parameters
	private int depth; //12
	private int deviation; //5
	private int backstep; //3
	private double point; //0.0001~0.01

	// indicator buffers
	private double zigzagBuffer[]; // main buffer
	private double highMapBuffer[]; // highs
	private double lowMapBuffer[]; // lows
	private int level = 3; // recounting depth
	private double deviationInPoints; // deviation in points

	// auxiliary enumeration
	private static final int PIKE = 1; // searching for next high
	private static final int SILL = -1; // searching for next low

//	public ZigZag() {
//		// to use in cycle
//		deviationInPoints = deviation * point;
//	}

	public ZigZag(int depth, int deviation, int backstep, double point) {
		this.depth = depth;
		this.setDeviation(deviation);
		this.backstep = backstep;
		this.setPoint(point);
		// to use in cycle
		deviationInPoints = deviation * point;
	}

	/**
	 * 
	 * searching index of the highest bar
	 * 
	 */
	private int iHighest(double array[], int depth, int startPos) {
		int index = startPos;

		// --- start index validation
		if (startPos < 0) {
			System.out.println("Invalid parameter in the function iHighest, startPos = " + startPos);
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
	 * 
	 * searching index of the lowest bar
	 * 
	 */
	private int iLowest(double array[], int depth, int startPos) {
		int index = startPos;

		// --- start index validation
		if (startPos < 0) {
			System.out.println("Invalid parameter in the function iLowest, startPos = " + startPos);
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
	 * 
	 * Custom indicator iteration function
	 * 
	 */
	public void calculate(int rates_total, double high[], double low[]) {
		int i = 0;
		int limit = 0, counterZ = 0, whatlookfor = 0;
		int shift = 0, back = 0, lasthighpos = 0, lastlowpos = 0;
		double val = 0, res = 0;
		double curlow = 0, curhigh = 0, lasthigh = 0, lastlow = 0;
		int prev_calculated = 0;

		// initializing
		if (prev_calculated == 0) {
			zigzagBuffer = new double[rates_total];
			highMapBuffer = new double[rates_total];
			lowMapBuffer = new double[rates_total];
		}

		if (rates_total < 100) {
			System.out.println("ZigZag.calculate(): Not ebought bars for calculation");
			//throw new IllegalArgumentException("Not ebought bars for calculation");
		}

		// set start position for calculations
		if (prev_calculated == 0)
			limit = depth;

		// ZigZag was already counted before
		if (prev_calculated > 0) {
			i = rates_total - 1;

			// searching third extremum from the last uncompleted bar
			while (counterZ < level && i > rates_total - 100) {
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
			for (i = limit + 1; i < rates_total; i++) {
				zigzagBuffer[i] = 0.0;
				lowMapBuffer[i] = 0.0;
				highMapBuffer[i] = 0.0;
			}
		}

		// searching High and Low
		for (shift = limit; shift < rates_total; shift++) {
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

			// high
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

		// last preparation
		if (whatlookfor == 0) // uncertain quantity
		{
			lastlow = 0;
			lasthigh = 0;
		} else {
			lastlow = curlow;
			lasthigh = curhigh;
		}

		// final rejection
		for (shift = limit; shift < rates_total; shift++) {
			res = 0.0;

			switch (whatlookfor) {
			case 0: // search for peak or lawn
				if (lastlow == 0 && lasthigh == 0) {
					if (highMapBuffer[shift] != 0) {
						lasthigh = high[shift];
						lasthighpos = shift;
						whatlookfor = SILL;
						zigzagBuffer[shift] = lasthigh;
						res = 1;
					}

					if (lowMapBuffer[shift] != 0) {
						lastlow = low[shift];
						lastlowpos = shift;
						whatlookfor = PIKE;
						zigzagBuffer[shift] = lastlow;
						res = 1;
					}
				}

				break;

			case PIKE: // search for peak
				if (lowMapBuffer[shift] != 0.0 && lowMapBuffer[shift] < lastlow && highMapBuffer[shift] == 0.0) {
					zigzagBuffer[lastlowpos] = 0.0;
					lastlowpos = shift;
					lastlow = lowMapBuffer[shift];
					zigzagBuffer[shift] = lastlow;
					res = 1;
				}

				if (highMapBuffer[shift] != 0.0 && lowMapBuffer[shift] == 0.0) {
					lasthigh = highMapBuffer[shift];
					lasthighpos = shift;
					zigzagBuffer[shift] = lasthigh;
					whatlookfor = SILL;
					res = 1;
				}

				break;

			case SILL: // search for lawn
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

				break;
			}
		}
	}

	public double[] getZigzagBuffer() {
		return zigzagBuffer;
	}

	public double[] getHighMapBuffer() {
		return highMapBuffer;
	}

	public double[] getLowMapBuffer() {
		return lowMapBuffer;
	}

	public String calculateTrend(int bars, int i) {
		Trend ZZTrend = Trend.Uncertainty;

		do {
			if (highMapBuffer[i] != 0)
				ZZTrend = Trend.Down;
			else if (lowMapBuffer[i] != 0)
				ZZTrend = Trend.Up;

			i++;
		} while (ZZTrend == Trend.Uncertainty && i < bars - 1);

		return ZZTrend.name();
	}

	public int getDeviation() {
		return deviation;
	}

	public void setDeviation(int deviation) {
		this.deviation = deviation;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}
}
