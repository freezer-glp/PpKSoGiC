import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

class preProcess
{
	public static int copyMatrix[][];
	public static ArrayList<rRadiusGraph> list;// 所有r半径图的list
	public static Stack<Integer> s;
	public static HashMap<Integer, ArrayList<Integer>> vGraphMap;// 每个顶点出现在哪些r半径图中
	static rRadiusGraph rg, c1, c2, show;
	public static long endTime, startTime;
	public static int p,q;

	public static void getRradiusGraph(int pm[][], int m[][], int debug)
			throws IOException// 提取出搜有r半径子图，存于list中，参数pm为r次幂矩阵，m为r-1次幂矩阵
	{
		startTime = System.currentTimeMillis(); // 获取开始时间

		int find, p, num;
		copyMatrix = new int[matrix.mSize][matrix.mSize];// 原始矩阵的copy
		s = new Stack<Integer>();
		list = new ArrayList<rRadiusGraph>();

		for (int i = 0; i <= matrix.mSize - 1; i++)
		{
			s.clear();
			// find==1 表示找到非子集的元素，该图i是r半径图。
			for (int j = 0; j <= matrix.mSize - 1; j++)
			{
				if (pm[i][j] == 1)
					s.push(j);

			}

			find = 0;
			num = s.size();

			while (!s.empty())
			{

				p = s.pop();
				// System.out.println("p = "+p+" "+s.size());
				for (int j = 0; j <= matrix.mSize - 1; j++)
				{
					if (pm[i][j] == 1 && m[p][j] != 1)// 如果不是子集
					{
						find++;
						break;
					}
				}

			}

			if (find == num)
			{
				if (debug == 1)
					System.out.println("i= " + i);

				for (int k = 0; k <= matrix.mSize - 1; k++)
					for (int j = 0; j <= matrix.mSize - 1; j++)
						copyMatrix[k][j] = m[k][j];

				for (int j = 0; j <= matrix.mSize - 1; j++)
					if (pm[i][j] == 1)
					{
						// System.out.println("j= "+j);
						s.push(j);
					}

				while (!s.empty()) // 选出该r半径子图
				{
					p = s.pop();
					// System.out.println("p= "+p);
					copyMatrix[p][p] = 2;
				}

				for (int j = 0; j <= matrix.mSize - 1; j++)
					// 从原图中去除非子图的点
					if (copyMatrix[j][j] != 2)
					{
						for (int t = 0; t <= matrix.mSize - 1; t++)
							copyMatrix[j][t] = 0;
						for (int t = 0; t <= matrix.mSize - 1; t++)
							copyMatrix[t][j] = 0;

					}

				for (int j = 0; j <= matrix.mSize - 1; j++)
					if (copyMatrix[j][j] == 2)
						copyMatrix[j][j] = 1;
				rg = null;
				rg = new rRadiusGraph(2, i, copyMatrix);
				rg.setVector();
				// System.out.println("vector is:");
				// for (int ss = 0; ss <= 499; ss++)
				// System.out.print(rg.keyVector[ss]);
				// System.out.println();
				list.add(rg); // 将该子图加入list中
				// System.out.println("size= "+list.size());

			}

		}
		// showList();
		
		removeSame(debug);// 将list中拓扑相同的子图去重
		if (debug == 1)
		{
			
			showList();
			System.out.println("size= " + list.size());
		}
		endTime = System.currentTimeMillis(); // 获取结束时间
		System.out
				.println("getRradiusGraph时间： " + (endTime - startTime) + "ms");

	}

	public static void removeSame(int debug)
	{
		if (debug == 1)
			System.out.println("befor remove size =" + list.size());
		for (int i = 0; i <= list.size() - 1; i++)
		{
			c1 = list.get(i);
			for (int j = i + 1; j <= list.size() - 1; j++)
			{
				c2 = list.get(j);
				if (debug == 1)
					System.out.println("i=" + i + ",id=" + c1.id + " j=" + j
							+ ",id=" + c2.id);
				if (isSame(c1.M, c2.M, debug) == 1) // 如果两个子图的邻接矩阵完全相同，则为重复的
				{
					if (debug == 1)
						System.out.println("same!");
					list.remove(j);
					j--;
				}

			}
		}
	}

	public static int isSame(BigInteger m1[][], BigInteger m2[][], int debug)
	{
		int same = 1;
		for (int i = 0; i <= matrix.mSize - 1; i++)
			for (int j = 0; j <= matrix.mSize - 1; j++)
			{
				if (m1[i][j] != m2[i][j])
				{
					if (debug == 1)
						System.out.println(i + "-" + j);
					same = 0;
					return same;
				}
			}
		return same;
	}

	public static void showList() // 显示所有的r半径子图
	{
		for (Iterator<rRadiusGraph> it = list.iterator(); it.hasNext();)
		{
			show = it.next();
			System.out.println("id= " + show.id);
			for (int i = 0; i <= matrix.mSize - 1; i++)
				for (int j = 0; j <= matrix.mSize - 1; j++)
				{
					if (j != matrix.mSize - 1)
						System.out.print(show.M[i][j] + " ");
					else
						System.out.println(show.M[i][j]);
				}
			System.out.println("vector is:");
			for (int j = 0; j <= 499; j++)
			{
				System.out.print(show.keyVector[j] + " ");
			}
			System.out.println();
		}
	}

	public static void makePrimeMark()
	{
		rRadiusGraph rg = null;
		BigInteger exist = BigInteger.valueOf(1);
		p = 1;q = 1;
		int count = -1, primeValue;
		for (int i = 0; i <= createPrime.primeList.size() - 1; i++)
		{

			count++; // start from 0
			primeValue = createPrime.primeList.get(i);
			p *= primeValue;
			if (count <= list.size() - 1)
			{
				//System.out.println("primevalue = " + primeValue);
				rg = list.get(count);
				for (int j = 0; j <= matrix.mSize - 1; j++)
					for (int k = 0; k <= matrix.mSize - 1; k++)
					{
						if (rg.M[j][k].equals(exist))
							rg.M[j][k] = BigInteger.valueOf(primeValue);
						else
							rg.M[j][k] = BigInteger.valueOf(1);
					}

				list.remove(count);
				list.add(count, rg);

			}
			else
				break;

		}
		
		q = p * createPrime.primeList.get(count);

	}

	public static void encode()
	{
		rRadiusGraph rg = null;
		BigInteger n;
		//System.out.println("in encode:");

		for (int i = 0; i <= list.size() - 1; i++)
		{
			rg = list.get(i);
			for (int j = 0; j <= matrix.mSize - 1; j++)
				for (int k = 0; k <= matrix.mSize - 1; k++)
				{
					// System.out.println("in encode:"+rg.M[j][k]);
					n = HEncryption.encode(rg.M[j][k],BigInteger.valueOf(p),BigInteger.valueOf(q));
					rg.M[j][k] = n;
				}

			ASPE(rg.keyVector);

			list.remove(i);
			list.add(i, rg);

		}
	}

	public static void ASPE(double m[])
	{
		double buffer[] = new double[500];
		double sum;
		for (int i = 0; i <= 499; i++)
		{
			buffer[i] = m[i];
			// System.out.print("m"+m[i]+' ');
		}

		for (int i = 0; i <= 499; i++)
		{
			sum = 0;
			for (int j = 0; j <= 499; j++)
			{
				sum += buffer[j] * matrix.transMatrix[j][i];
			}
			// System.out.println("sum:"+sum);
			m[i] = sum;
			// m[i] = 100;

		}

	}

}
