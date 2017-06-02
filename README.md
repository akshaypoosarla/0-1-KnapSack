# 0-1-KnapSack
Comparison of Different algorithms for different input sizes for the 0-1 knap sack problem


In this assignment I implemented four different algorithms for solving the 0-1 Knapsack Problem and evaluate their performances using real time measurements. The algorithms are:

1. Brute-Force Enumeration.
2. Backtracking.
3. Branch-and-Bound. implemented the three upper bound techniques  
4. Dynamic Programming. 


 implemented three different branch-and-bound algorithms; each algorithm uses one of the following three upper bounds (UBs) to prune the tree:
 
1. UB1: the sum of taken item values and undecided item values

2. UB2: the sum of taken item values and the values of the undecided items that fit in the remaining capacity at each node.

3. UB3: solve the remaining sub-problem at each node as a Fractional Knapsack problem.

 
After implementation ran the program with the following input sizes:
n = 10
n = 20
n = 30
n = 40


For each algorithm report the running time for each input size as well as the largest input size that the algorithm solves within 10 seconds. Your report should include a table listing the results as follows:

	Brute Force	Backtracking 	B&B
UB1	B&B
UB2	B&B
UB3	Dynamic Programming
N = 10						
N = 20						
N = 30						
N = 40						
Largest Input 
Solved in 10s  						

Extra Work

1. A more efficient way of computing UB2. Sorting the elements in increasing order of thier weights is done before processing 
2. A more efficient way of computing UB3. Implements the look up table 

Fell free to raise a pull request if the code doesnt work or any modifications required.



