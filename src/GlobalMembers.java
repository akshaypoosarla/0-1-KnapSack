import java.util.Calendar;

public class GlobalMembers
{

	public static int gRefTime = 0;
/********************************************************************/
/********************************************************************/

	public static void SetTime()
	{
		gRefTime = GetCurrentTime();
	}
/********************************************************************/

	public static int GetTime()
	{
		int crntTime = GetCurrentTime();

		return (crntTime - gRefTime);
	}

	public static void main (String[] args)
	{
		long BFTime;
		long DPTime;
		long BTTime;
		long BB_UB1_Time;
		long BB_UB2_Time;
		long BB_UB3_Time;
		long BB_UB2En_Time;
		float speedup;
		int itemCnt;
		KnapsackInstance inst; //a Knapsack instance object
		KnapsackDPSolver DPSolver = new KnapsackDPSolver(); //dynamic programming solver
		KnapsackBFSolver BFSolver = new KnapsackBFSolver(); //brute-force solver
		KnapsackBTSolver BTSolver = new KnapsackBTSolver(); //backtracking solver
	    KnapsackBBSolver BBSolver1 = new KnapsackBBSolver(UPPER_BOUND.UB1); //branch-and-bound solver with UB1
	    KnapsackBBSolver1 BBSolver2 = new KnapsackBBSolver1(UPPER_BOUND.UB2); //branch-and-bound solver with UB2
        KnapsackBBSolver2 BBSolver3 = new KnapsackBBSolver2(UPPER_BOUND.UB3); //branch-and-bound solver with UB3
        KnapsackBBSolver1Enhanced BBSolver2En = new KnapsackBBSolver1Enhanced(UPPER_BOUND.UB3); //branch-and-bound solver with UB3

        KnapsackSolution DPSoln;
		KnapsackSolution BFSoln;
		KnapsackSolution BTSoln;
		KnapsackSolution BBSoln1;
		KnapsackSolution BBSoln2;
		KnapsackSolution BBSoln3;
		KnapsackSolution BBSoln2En;

		if (args.length != 1)
		{
			System.out.print("Invalid Number of command-line arguments\n");
			System.exit(1);
		}
		itemCnt = Integer.parseInt(args[0]);
		if (itemCnt < 1)
		{
			System.out.print("Invalid number of items\n");
			System.exit(1);
		}

		inst = new KnapsackInstance(itemCnt);
		DPSoln = new KnapsackSolution(inst);
		BFSoln = new KnapsackSolution(inst);
		BTSoln = new KnapsackSolution(inst);
		BBSoln1 = new KnapsackSolution(inst);
		BBSoln2 = new KnapsackSolution(inst);
		BBSoln3 = new KnapsackSolution(inst);
        BBSoln2En = new KnapsackSolution(inst);
		inst.Generate();
		inst.Print();

		//SetTime();
		long  startTime = System.nanoTime();
		DPSolver.Solve(inst, DPSoln);
		long elapsed = System.nanoTime()-startTime;
		DPTime =  (Long)(elapsed/1000000);
		System.out.println();
		System.out.printf("\n\nSolved using dynamic programming (DP) in "+DPTime+"ms Optimal value = "+DPSoln.GetValue());
		if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
		{
		DPSoln.Print("Dynamic Programming Solution");
		}

		//SetTime();
		startTime = System.nanoTime();
		BFSolver.Solve(inst,BFSoln);
		elapsed = System.nanoTime()-startTime;
		BFTime =  (Long)(elapsed/1000000);
        System.out.println();
		System.out.printf("\n\nSolved using brute-force enumeration (BF) in "+BFTime+"ms Optimal value = "+ BFSoln.GetValue());
		if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
		{
			BFSoln.Print("Brute-Force Solution");
		}
		if (DPSoln.equalsTo(BFSoln))
		{
			System.out.print("\nSUCCESS: DP and BF solutions match");
		}
		else
		{
			System.out.print("\nERROR: DP and BF solutions mismatch");
		}

	//SetTime();
		startTime = System.nanoTime();
		BTSolver.Solve(inst,BTSoln);
		elapsed = System.nanoTime()-startTime;
		BTTime =  (Long)(elapsed/1000000);
        System.out.println();
	   System.out.printf("\n\nSolved using Back Tracking enumeration (Bt) in "+BTTime+"ms Optimal value = "+ BTSoln.GetValue());
     if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
	{
		BTSoln.Print("Backtracking Solution");
	}
	if (BTSoln.equalsTo(BFSoln))
	{
		System.out.print("\nSUCCESS: BF and BT solutions match");
	}
	else
	{
		System.out.print("\nERROR: BF and BT solutions mismatch");
		}
	speedup = (float) (BTTime == 0? 0 : 100.0 * (BFTime - BTTime) / (float)BFTime);
	System.out.printf("\nSpeedup of BT relative to BF is"+speedup+"percent");

	
	startTime = System.nanoTime();
	BBSolver1.Solve(inst,BBSoln1);	
	elapsed = System.nanoTime()-startTime;
	BB_UB1_Time =  (Long)(elapsed/1000000);
	System.out.printf("\n\nSolved using Branch and Bound enumeration in "+BB_UB1_Time +"ms Optimal value = "+ BBSoln1.GetValue());
		if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
		{
			BBSoln1.Print("BB-UB1 Solution");
		}
		if (BFSoln.equalsTo(BBSoln1))
		{
			System.out.print("\nSUCCESS: BF and BB-UB1 solutions match");
		}
		else
		{
			System.out.print("\nERROR: BF and BB-UB1 solutions mismatch");
		}
		speedup = (float) (BB_UB1_Time == 0? 0 : 100.0 * (BFTime - BB_UB1_Time) / (float)BFTime);
		System.out.printf("\nSpeedup of BB 1 relative to BF is"+speedup+"percent");
		
		
		startTime = System.nanoTime();
		BBSolver2.Solve(inst,BBSoln2);	
		elapsed = System.nanoTime()-startTime;
		BB_UB2_Time =  (Long)(elapsed/1000000);
		System.out.printf("\n\nSolved using Branch and Bound enumeration2 in "+BB_UB2_Time +"ms Optimal value = "+ BBSoln1.GetValue());
			if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
			{
				BBSoln1.Print("BB-UB2 Solution");
			}
			if (BFSoln.equalsTo(BBSoln2))
			{
				System.out.print("\nSUCCESS: BF and BB-UB2 solutions match");
			}
			else
			{
				System.out.print("\nERROR: BF and BB-UB2 solutions mismatch");
			}
			speedup = (float) (BB_UB1_Time == 0? 0 : 100.0 * (BFTime - BB_UB2_Time) / (float)BFTime);
			System.out.printf("\nSpeedup of BB 2 relative to BF is"+speedup+"percent");
		
		
		
		 startTime = System.nanoTime();
		    BBSolver3.Solve(inst,BBSoln3);	
			elapsed = System.nanoTime()-startTime;
			BB_UB3_Time =  (Long)(elapsed/1000000);
			System.out.printf("\n\nSolved using Branch and Bound enumeration  3 in "+BB_UB3_Time +"ms Optimal value = "+ BBSoln3.GetValue());
			if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
			{
				BBSoln3.Print("BB-UB3 Solution");
			}
			if (BFSoln.equalsTo(BBSoln3))
			{
				System.out.print("\nSUCCESS: BF and BB-UB3 solutions match");
			}
			else
			{
				System.out.print("\nERROR: BF and BB-UB3 solutions mismatch");
			}
		    speedup = (float) (BB_UB3_Time == 0? 0 : 100.0 * (BFTime - BB_UB3_Time) / (float)BFTime);
  
	System.out.printf("\nSpeedup of BB 3 with fractional Bound relative to BF is"+speedup+"percent");
	startTime = System.nanoTime();
	BBSolver2En.Solve(inst,BBSoln2En);	
	elapsed = System.nanoTime()-startTime;
	BB_UB2En_Time=  (Long)(elapsed/1000000);
	System.out.printf("\n\nSolved using Branch and Bound 2 with  enhancment in "+BB_UB2En_Time +"ms Optimal value = "+ BBSoln2En.GetValue());
	if (itemCnt <= DefineConstants.MAX_SIZE_TO_PRINT)
	{
	   BBSoln2En.Print("BB-UB2EN Solution");
	}
	if (BFSoln.equalsTo(BBSoln2En))
	{
		System.out.print("\nSUCCESS: BF and BB-UB2En solutions match");
	}
	else
	{
	   System.out.print("\nERROR: BF and BB-UB2En solutions mismatch");
	}
	speedup = (float) (BB_UB2En_Time == 0? 0 : 100.0 * (BFTime - BB_UB2En_Time) / (float)BFTime);
	System.out.printf("\nSpeedup of BB2 withenhancement relative to BF is"+speedup+"percent");
		
	
	
	
	
	inst = null;
		DPSoln = null;
		BFSoln = null;
		BTSoln = null;
		BBSoln1 = null;
		BBSoln2 = null;
		BBSoln3 = null;

		System.out.print("\n\nProgram Completed Successfully\n");

	}
	/********************************************************************/

	/*****************************************************************************/

	public static int GetCurrentTime()
	{
		int crntTime = 0;

		crntTime = Calendar.getInstance().get(Calendar.MILLISECOND);

		return crntTime;
	}
}