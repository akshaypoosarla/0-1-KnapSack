
// Branch-and-Bound solver
public class KnapsackBBSolver1 extends KnapsackBFSolver
{
protected UPPER_BOUND ub;
int [] weightArray ;
int [] valueArray ;
public KnapsackBBSolver1(UPPER_BOUND ub_)
	{
		ub = ub_;

	}
	public void close()
	{
    
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		bestSoln = soln_;
    	crntSoln = new KnapsackSolution(inst);
    	FindSolns(1,0,0);
	}
	public void FindSolns(int itemNum,int TakenWeight,int TakenValue){
        int remainingCapacity=0;
        int ubvalue =0;
		int itemCnt = inst.GetItemCnt();
		if(TakenWeight > inst.GetCapacity()){
			return;
		}
		if (itemNum == itemCnt + 1)
		{
			CheckCrntSoln();
			return;		
		}
		remainingCapacity = inst.GetCapacity()- TakenWeight;
		//System.out.println("don takeitemno "+itemNum);
		crntSoln.DontTakeItem(itemNum);
		FindSolns(itemNum + 1,TakenWeight,TakenValue);
		for(int i = itemNum;i <=inst.GetItemCnt();i++)
		{
			if( inst.GetItemWeight(i) <= remainingCapacity )
			{
				ubvalue = ubvalue+ inst.GetItemValue(i);
			}
		}
		if(TakenWeight <= inst.GetCapacity() && TakenValue+ubvalue >=bestSoln.GetValue()){
			//System.out.println("takeitemno "+itemNum);
			crntSoln.TakeItem(itemNum);		
		FindSolns(itemNum + 1,TakenWeight+inst.GetItemWeight(itemNum),TakenValue+inst.GetItemValue(itemNum));
		}
	}
}