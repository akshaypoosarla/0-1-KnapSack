import java.util.*;

///#define KNAPSACK_DEBUG


public enum UPPER_BOUND
{
	UB1,
	UB2,
	UB3;

	public static final int SIZE = java.lang.Integer.SIZE;

	public int getValue()
	{
		return this.ordinal();
	}

	public static UPPER_BOUND forValue(int value)
	{
		return values()[value];
	}
}