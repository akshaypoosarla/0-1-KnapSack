import java.util.*;

// Dynamic programming solver
public class KnapsackDPSolver implements java.io.Closeable
{
	private KnapsackInstance inst;
	private KnapsackSolution soln;

	public KnapsackDPSolver()
	{
    
	}
	public void close()
	{
    
	}
	public void Solve(KnapsackInstance inst_, KnapsackSolution soln_)
	{
		inst = inst_;
		soln = soln_;
		int [][] matrix  = new int[inst_.GetItemCnt()+1][inst_.GetCapacity()+1];
		int n = inst.GetItemCnt();
		int c = inst.GetCapacity();
		for(int i=0;i<=c ;i++)
		{
			matrix[0][i]=0;
		}
		for(int i =1;i <=n ;i++)
		{
			matrix[i][0]= 0;
			for (int j =1; j<=c;j++)
			{ 
				if (j < inst.GetItemWeight(i)) //item doesnot fit
					matrix[i][j]= matrix[i-1][j];//Take Previous value
				else
					matrix[i][j]=Math.max(inst.GetItemValue(i)+matrix[i-1][j-inst.GetItemWeight(i)], matrix[i-1][j]);
			}
		}
		int i = n ;
		int j = c;
		for( i = n ; i >=1;i--)
		{
			if(matrix[i][j] != matrix[i-1][j])
			{
				soln.TakeItem(i);
				j = j - inst.GetItemWeight(i);	
				}
		}
		soln.ComputeValue();
	}

	
}