

// Branch-and-Bound solver
public class KnapsackBBSolver2 extends KnapsackBFSolver
{
protected UPPER_BOUND ub;
	int [] weightArray ;
	int [] valueArray ;
	int[] valuetoWeight;

public KnapsackBBSolver2(UPPER_BOUND ub_)
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
    	crntSoln = new KnapsackSolution(inst);;
    	InsertionSort1( 1,inst.GetItemCnt());
    	

    FindSolns(1,0,0);
	}
	public void FindSolns(int itemNum, int weight,int value){
		int itemCnt = inst.GetItemCnt();
		if(weight > inst.GetCapacity()){
			return;
		}
		if (itemNum == itemCnt + 1)
		{
			CheckCrntSoln();
			return;		
		}
		float FractionalBoundValue =0;
		   float crLoad= 0;
		float reaminingCapacity = inst.GetCapacity()-weight;
		//System.out.println("don takeitemno "+itemNum);
		crntSoln.DontTakeItem(itemNum);
		FindSolns(itemNum + 1,weight,value);
		for(int i = itemNum; i <= itemCnt;i++)
		{   
			if( inst.GetItemWeight(i) < (reaminingCapacity- crLoad))
			{
				FractionalBoundValue = FractionalBoundValue +inst.GetItemValue(i);
				crLoad = crLoad+inst.GetItemWeight(i);
			}
			else
			{
				
				FractionalBoundValue = FractionalBoundValue+(((reaminingCapacity-crLoad)*inst.GetItemValue(i))/inst.GetItemWeight(i));
				break;
			}
		}
		if(weight <= inst.GetCapacity()&& value+FractionalBoundValue >=bestSoln.GetValue()){
			//System.out.println("takeitemno "+itemNum);
			crntSoln.TakeItem(itemNum);		
		FindSolns(itemNum + 1,(weight+inst.GetItemWeight(itemNum)),(value+inst.GetItemValue(itemNum)));
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
    
				while (j >= lo && (inst.GetItemValuePerWeight(j) < key3))
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