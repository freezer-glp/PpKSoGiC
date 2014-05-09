import java.util.Stack;

public class createPrime
{
	Stack<Integer> primeStack = new Stack<>();
	static boolean prime[] = new boolean[10001];
	static int stackSize, count;

	public static void makePrime()
	{
		stackSize = matrix.mSize;
		count = stackSize;

		
		prime[2] = true;
		for (int i = 3; i <= 10000; i++)
			if (i % 2 == 0)
				prime[i] = false;
			else
				prime[i] = true;
		
		
		for (int i = 3; i <= Math.sqrt(10001); i += 2)
		{
			if (prime[i])
				for (int j = i + i; j <= 10000; j += i)
					prime[j] = false;
		}
		
//		for(int i = 2; i <= 100; i++)
//		{
//			if(prime[i])
//			System.out.print(i+" ");
//		}
		
		
	}
	
	
}
