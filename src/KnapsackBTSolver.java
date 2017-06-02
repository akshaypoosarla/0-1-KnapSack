 import java.util.*;

// Backtracking solver
public class KnapsackBTSolver extends KnapsackBFSolver
{
	/*****************************************************************************/

	public KnapsackBTSolver()
	{
		crntSoln = null;
	}
	/********************************************************************/

	public void dispose()
	{

		super.dispose();
	}
	/********************************************************************/

	public final void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		
		inst = inst_;
		bestSoln = soln_;
		crntSoln = new KnapsackSolution(inst);
		FindSolns(1,0);
	}
	public void FindSolns(int itemNum, int weight){

		int itemCnt = inst.GetItemCnt();
		
		if(weight > inst.GetCapacity()){
			System.out.println("backtrackat"+itemNum);
			return;
		}

		if (itemNum == itemCnt + 1)
		{
			CheckCrntSoln();
			return;		
		}
		crntSoln.DontTakeItem(itemNum);
		FindSolns(itemNum + 1,weight);
		if(weight <= inst.GetCapacity()){
			crntSoln.TakeItem(itemNum);		
		FindSolns(itemNum + 1,(weight+inst.GetItemWeight(itemNum)));
		}
	}
}