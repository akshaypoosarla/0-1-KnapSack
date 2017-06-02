import java.util.Random;

//----------------------------------------------------------------------------------------
//	Copyright © 2006 - 2017 Tangible Software Solutions Inc.
//	This class can be used by anyone provided that the copyright notice remains intact.
//
//	This class provides the ability to simulate the behavior of the C/C++ functions for 
//	generating random numbers.
//	'rand' converts to the parameterless overload of NextNumber
//	'random' converts to the single-parameter overload of NextNumber
//	'randomize' converts to the parameterless overload of Seed
//	'srand' converts to the single-parameter overload of Seed
//----------------------------------------------------------------------------------------
public final class RandomNumbers
{
	private static Random r;

	public static int nextNumber()
	{
		if (r == null)
			seed();

		return r.nextInt();
	}

	public static int nextNumber(int ceiling)
	{
		if (r == null)
			seed();

		return r.nextInt(ceiling);
	}

	public static void seed()
	{
		r = new Random();
	}

	public static void seed(int seed)
	{
		r = new Random(seed);
	}
}