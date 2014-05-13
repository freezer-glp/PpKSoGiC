import java.util.ArrayList;
import java.util.Stack;

public class createPrime
{
	Stack<Integer> primeStack = new Stack<>();
	static ArrayList<Integer> primeList = new ArrayList<>();
	//primelist size is 9592
	static int stackSize, count;

	public static void makePrime()
	{
		primeList.clear();
		boolean prime[] = new boolean[100001];
		stackSize = matrix.mSize;
		count = stackSize;

		prime[2] = true;
		for (int i = 3; i <= 100000; i++)
			if (i % 2 == 0)
				prime[i] = false;
			else
				prime[i] = true;

		for (int i = 3; i <= Math.sqrt(100001); i += 2)
		{
			if (prime[i])
				for (int j = i + i; j <= 100000; j += i)
					prime[j] = false;
		}
		for (int i = 2; i <= 100000; i++)
		{
			if(prime[i] == true)
				primeList.add(i);
		}
		System.out.println("primesize:"+primeList.size());
		// for(int i = 2; i <= 100; i++)
		// {
		// if(prime[i])
		// System.out.print(i+" ");
		// }

	}

}
