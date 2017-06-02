import java.util.*;

// Branch-and-Bound solver
public class KnapsackBBSolver extends KnapsackBFSolver
{
protected UPPER_BOUND ub;
int totalValue=0;

public KnapsackBBSolver(UPPER_BOUND ub_)
	{
		ub = ub_;
	}
	public void close()
	{
    super.close();
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		for(int i =1; i <= inst.GetItemCnt();i++)
		{
			totalValue = totalValue+inst.GetItemValue(i);
		}
		bestSoln = soln_;
    	crntSoln = new KnapsackSolution(inst);
    	FindSolns(1,0,0);
	}
	public void FindSolns(int itemNum,int TakenWeight,int NotTakenValue){
		int itemCnt = inst.GetItemCnt();
		if(TakenWeight > inst.GetCapacity()){
			return;
		}
		if (itemNum == itemCnt + 1)
		{
			CheckCrntSoln();
			return;		
		}
		int remainingValue =0;
		crntSoln.DontTakeItem(itemNum);
		remainingValue = totalValue-NotTakenValue;
		FindSolns(itemNum + 1,TakenWeight,NotTakenValue+inst.GetItemValue(itemNum));
		System.out.println("upperboundat"+remainingValue);
		if(TakenWeight <= inst.GetCapacity() &&(remainingValue >=bestSoln.GetValue())){
			crntSoln.TakeItem(itemNum);		
		FindSolns(itemNum + 1,TakenWeight+inst.GetItemWeight(itemNum),NotTakenValue);
		}
	}
}