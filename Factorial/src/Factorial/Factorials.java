package Factorial;

class Factorials {
	public long factorial( long n )
	{
		long result=0;
			if(n<=1)
			{
				return n;
			}
			else
			{
				result = n*factorial(n-1); 
				return result;
			}					
	}
}
