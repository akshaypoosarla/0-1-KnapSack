/*import java.util.*;

///#define KNAPSACK_DEBUG


public class KnapsackInstance
{
	private int itemCnt; //Number of items
	private int cap; //The capacity
	public int[] weights; //An array of weights
	public int[] values; //An array of values
	public float [] valueToweights;

	*//********************************************************************//*

	public KnapsackInstance(int itemCnt_)
	{
		itemCnt = itemCnt_;

		weights = new int[itemCnt + 1];
		values = new int[itemCnt + 1];
		valueToweights = new float[itemCnt+1];
		cap = 0;
	}
	*//********************************************************************//*

	public void dispose()
	{
		weights = null;
		values = null;
		valueToweights = null;
	}

	*//********************************************************************//*

	public final void Generate()
	{
		int i;

		cap = itemCnt * 3;

		weights[0] = 0;
		values[0] = 0;
		valueToweights[0] = 0;

		for (i = 1; i <=itemCnt; i++)
		{
			weights[i] = Math.abs((RandomNumbers.nextNumber() % 10))+ 1;
			values[i] = Math.abs((RandomNumbers.nextNumber() % 5)) + 1;
			values[i] *= weights[i];
			if(weights[i] !=0)
	    		valueToweights[i] = values[i]/weights[i];
	    		else
	    			valueToweights[i] =0;
		}
	}

	*//********************************************************************//*

	public final int GetItemCnt()
	{
		return itemCnt;
	}
	*//********************************************************************//*

	public final int GetItemWeight(int itemNum)
	{
		return weights[itemNum];
	}
	*//********************************************************************//*

	public final int GetItemValue(int itemNum)
	{
		return values[itemNum];
	}
	*//********************************************************************//*

	public final int GetCapacity()
	{
		return cap;
	}
	*//********************************************************************//*
	public void SetItemWeight(int itemNum, int weight)
	{
		weights[itemNum] = weight;
	}

	public void SetItemValue(int itemNum, int value)
	{
		values[itemNum] = value;
	}

	public void SetItemValuePerWeight(int itemNum, float value)
	{
		valueToweights[itemNum] = value;
	}
	public final void Print()
	{
		int i;

		System.out.println("Number of items = "+itemCnt+" Capacity = "+cap);
		System.out.print("Weights: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",weights[i]);
		}
		System.out.print("\nValues: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",values[i]);
		}
		System.out.print("\n");
	}
}*/


import java.util.*;

///#define KNAPSACK_DEBUG


public class KnapsackInstance
{
	private int itemCnt; //Number of items
	private int cap; //The capacity
	public int[] weights; //An array of weights
	public int[] values; //An array of values
	public float[] valuePerWeights;
	/********************************************************************/

	public KnapsackInstance(int itemCnt_)
	{
		itemCnt = itemCnt_;

		weights = new int[itemCnt + 1];
		values = new int[itemCnt + 1];
		valuePerWeights = new float[itemCnt + 1];
		cap = 0;
	}
	/********************************************************************/

	public void dispose()
	{
		weights = null;
		values = null;
	}

	/********************************************************************/

	public final void Generate()
	{
		int i;

		cap = itemCnt * 3;

		weights[0] = 0;
		values[0] = 0;
		valuePerWeights[0] = 0;
	    

		for (i = 1; i <= itemCnt; i++)
		{
			weights[i] = (Math.abs(RandomNumbers.nextNumber() % 10) + 1);
			values[i] = (Math.abs(RandomNumbers.nextNumber() % 5) + 1);
			values[i] *= weights[i];
			if( weights[i] !=0)
                       {
                        valuePerWeights[i] = (float)(values[i] / weights[i]);
		       }
                      else
			{	
            	valuePerWeights[i] =0;
			}
                
		}	
	}

	/********************************************************************/

	public final int GetItemCnt()
	{
		return itemCnt;
	}
	/********************************************************************/

	public final int GetItemWeight(int itemNum)
	{
		return weights[itemNum];
	}
	/********************************************************************/

	public final int GetItemValue(int itemNum)
	{
		return values[itemNum];
	}
	/********************************************************************/

	public final int GetCapacity()
	{
		return cap;
	}
	/********************************************************************/
	public float GetItemValuePerWeight(int itemNum)
	{
		return valuePerWeights[itemNum];
	}


	public void SetItemWeight(int itemNum, int weight)
	{
		weights[itemNum] = weight;
	}

	public void SetItemValue(int itemNum, int value)
	{
		values[itemNum] = value;
	}

	public void SetItemValuePerWeight(int itemNum, float value)
	{
		valuePerWeights[itemNum] = value;
	}

	public final void Print()
	{
		int i;

		System.out.println("Number of items = "+itemCnt+" Capacity = "+cap);
		System.out.print("Weights: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",weights[i]);
		}
		System.out.print("\nValues: ");
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%d ",values[i]);
		}
		for (i = 1; i <= itemCnt; i++)
		{
			System.out.printf("%f ",valuePerWeights[i]);
		}
		
		System.out.print("\n");
	}
}