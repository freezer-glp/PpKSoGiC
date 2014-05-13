import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class client
{
	public static double sVector[];
	public static int rMatrix[][];
	public static ArrayList<Integer[][]> resultList;

	public static void doSearch(String sfPath) throws IOException
	{
		BufferedReader input = new BufferedReader(new FileReader(sfPath));
		String a, abuffer[] = null;
		sVector = new double[500];
		a = input.readLine();
		abuffer = a.split(" ");
		input.close();
		for (int i = 0; i <= 499; i++)
			sVector[i] = 0;

		for (int i = 0; i <= abuffer.length - 1; i++)
		{
			sVector[Integer.parseInt(abuffer[i])] = 1;
		}

		sASPE(sVector);
		// showList();

	}

	public static void sASPE(double m[])
	{

		// System.out.print("aaaaaaaaaaaa");
		double buffer[] = new double[500];
		double sum;
		for (int i = 0; i <= 499; i++)
		{
			buffer[i] = m[i];
			// System.out.print("m"+m[i]+' ');
		}

		// for (int i = 0; i <= matrix.mSize - 1; i++)
		// for (int j = 0; j <= matrix.mSize - 1; j++)
		// {
		// if (j != matrix.mSize - 1)
		// System.out.print(matrix.inverMatrix[i][j] + " ");
		// else
		// System.out.println(matrix.inverMatrix[i][j]);
		// }
		for (int i = 0; i <= 499; i++)
		{
			sum = 0;
			for (int j = 0; j <= 499; j++)
			{
				sum += buffer[j] * matrix.inverMatrix[j][i];
			}
			// System.out.println("sum:"+sum);
			m[i] = sum;
			// System.out.println("m["+i+"]="+m[i]+"\n");
			// m[i] = 100;

		}

	}

	// decode the matrix returned by the cloud and split the returned matrix
	public static void showResult()
	{
		rMatrix = new int[matrix.mSize][matrix.mSize];

		for (int i = 0; i <= matrix.mSize - 1; i++)
			for (int j = 0; j <= matrix.mSize - 1; j++)
			{
				rMatrix[i][j] = HEncryption.decode(cloud.returnM[i][j]);
				// System.out.println(rMatrix[i][j]);
			}

		if (1 == 1)
			for (int i = 0; i <= matrix.mSize - 1; i++)
				for (int j = 0; j <= matrix.mSize - 1; j++)
				{
					if (j != matrix.mSize - 1)
						System.out.print(rMatrix[i][j] + " ");
					else
						System.out.println(rMatrix[i][j]);
				}

		primeSplit();

	}

	public static void primeSplit()
	{
		resultList = new ArrayList<>();
		int primeFlag = 0, done = 0;
		int primenum;
		Integer[][] M1 = new Integer[matrix.mSize][matrix.mSize];
		Integer[][] M2 = new Integer[matrix.mSize][matrix.mSize];

		while (done != 1)
		{

			primenum = createPrime.primeList.get(primeFlag);
			for (int i = 0; i <= matrix.mSize - 1; i++)
				for (int j = 0; j <= matrix.mSize - 1; j++)
					M2[i][j] = rMatrix[i][j];

			for (int i = 0; i <= matrix.mSize - 1; i++)
				for (int j = 0; j <= matrix.mSize - 1; j++)
				{
					if (M2[i][j] % primenum == 0)
					{
						M1[i][j] = primenum;
						M2[i][j] = M2[i][j] / primenum;
					}
					else
					{
						M1[i][j] = 1;

					}
				}

			// System.out.println("M1 is:");
			// int OKNUM = splitOk(M1);
			// System.out.println("OKNUM is:"+OKNUM);
			// for (int k = 0; k <= matrix.mSize - 1; k++)
			// for (int j = 0; j <= matrix.mSize - 1; j++)
			// {
			// if (j != matrix.mSize - 1)
			// System.out.print(M1[k][j] + " ");
			// else
			// System.out.println(M1[k][j]);
			// }

			if (splitOk(M1) == 1) // current prime is the factor,M1 is not all 1
			{
				// add one dimension to store the score
				Integer buffer[][] = new Integer[matrix.mSize + 1][matrix.mSize + 1];
				for (int i = 0; i <= matrix.mSize - 1; i++)
					for (int j = 0; j <= matrix.mSize - 1; j++)
						buffer[i][j] = M1[i][j];

				removePrimeMark(buffer);

				resultList.add(buffer);
				primeFlag++;

				for (int i = 0; i <= matrix.mSize - 1; i++)
					for (int j = 0; j <= matrix.mSize - 1; j++)
						rMatrix[i][j] = M2[i][j];
			}
			else
			{
				primeFlag++;
			}

			if (splitOk(M2) == 0) // M2 is all 1
				done = 1;

		}
		
		Collections.sort(resultList, cmp);
		
		if (1 == 1)
		{
			Integer re[][] = new Integer[matrix.mSize][matrix.mSize];
			for (int i = 0; i <= resultList.size() - 1; i++)
			{
				System.out.println("re is =" + i);
				re = resultList.get(i);
				for (int k = 0; k <= matrix.mSize - 1; k++)
					for (int j = 0; j <= matrix.mSize - 1; j++)
					{
						if (j != matrix.mSize - 1)
							System.out.print(re[k][j] + " ");
						else
							System.out.println(re[k][j]);
					}
			}
		}

	}

	private static void removePrimeMark(Integer[][] buffer)
	{
		int score = 0;
		for (int k = 0; k <= matrix.mSize - 1; k++)
			for (int j = 0; j <= matrix.mSize - 1; j++)
			{
				if (buffer[k][j] != 1)
				{
					buffer[k][j] = 1;
					score ++;
				}
				else
					buffer[k][j] = 0;
			}
		
		buffer[matrix.mSize][matrix.mSize] = score;

	}

	// private static int issame(Integer[][] m1, int[][] m2)
	// {
	// int same = 1;
	// for (int i = 0; i <= matrix.mSize - 1; i++)
	// for (int j = 0; j <= matrix.mSize - 1; j++)
	// {
	// if (m1[i][j] != m2[i][j])
	// {
	// // if (debug == 1)
	// // System.out.println(i + "-" + j);
	// same = 0;
	// return same;
	// }
	// }
	// return same;
	// }

	private static int splitOk(Integer[][] m1) // return 0 if matrix is all 1
	{
		int ok = 0;
		for (int i = 0; i <= matrix.mSize - 1; i++)
			for (int j = 0; j <= matrix.mSize - 1; j++)
				if (m1[i][j] != 1)
				{
					ok = 1;
					return ok;
				}
		return ok;
	}

	
	static Comparator<Integer[][]> cmp  = new Comparator<Integer[][]>()
	{

		@Override
		public int compare(Integer[][] o1, Integer[][] o2)
		{
			if(o1[matrix.mSize][matrix.mSize] <= o2[matrix.mSize][matrix.mSize])
				return 1;
			else
				return -1;
		}
		
	};
		
	public static void showList()
	{
		// System.out.print("aaaaaaaaaaaa");
		for (int i = 0; i <= sVector.length - 1; i++)
			System.out.println(sVector[i]);

	}
}
