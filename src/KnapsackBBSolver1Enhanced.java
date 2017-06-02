// Branch-and-Bound solver
public class KnapsackBBSolver1Enhanced extends KnapsackBFSolver
{
protected UPPER_BOUND ub;
int [] weightArray ;
int [] valueArray ;
public KnapsackBBSolver1Enhanced(UPPER_BOUND ub_)
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


    	//QuickSort1.QuickSort1(weightArray,valueArray,0,inst.GetItemCnt()-1);
    	InsertionSort1(1,inst.GetItemCnt()-1);
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
			else
			{
				break;
			}
		}
		if(TakenWeight <= inst.GetCapacity() && TakenValue+ubvalue >=bestSoln.GetValue()){
			//System.out.println("takeitemno "+itemNum);
			crntSoln.TakeItem(itemNum);		
		FindSolns(itemNum + 1,TakenWeight+inst.GetItemWeight(itemNum),TakenValue+inst.GetItemValue(itemNum));
		}
	}
	
	public  void InsertionSort1(int lo, int hi)
	{
		int j = lo;
			for (int i = lo + 1; i <= hi; ++i)
			{
				int key1 = inst.GetItemWeight(i);
			int key2 = inst.GetItemValue(i);
			float key3 = inst.GetItemValuePerWeight(i);
				j = i - 1;
    
				while (j >= lo && (inst.GetItemWeight(j) > key3))
				{
				inst.SetItemWeight(j + 1,inst.GetItemWeight(j));
				inst.SetItemValue(j + 1,inst.GetItemValue(j));
				inst.SetItemValuePerWeight(j + 1,inst.GetItemValuePerWeight(j));
						j = j - 1;
				}
			inst.SetItemWeight(j + 1,key1);
			inst.SetItemValue(j + 1,key2);
			inst.SetItemValuePerWeight(j + 1,key3);
			}
	}
}