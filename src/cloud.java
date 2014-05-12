import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class cloud
{
	public static ArrayList<rRadiusGraph> cloudList;
	public static double searchVector[];
	public static BigInteger returnM[][];

	static Comparator<rRadiusGraph> cmp = new Comparator<rRadiusGraph>()
	{

		@Override
		public int compare(rRadiusGraph o1, rRadiusGraph o2)
		{
			if (Double.compare(o1.score, o2.score) <= 0)
				return 1;
			else
				return -1;
		}

	};

	// 暂定返回top5的结果
	public static void cloudSearch(int debug)
	{
		double score;
		returnM = new BigInteger[matrix.mSize][matrix.mSize];

		for (int j = 0; j <= matrix.mSize - 1; j++)
			for (int k = 0; k <= matrix.mSize - 1; k++)
				returnM[j][k] = BigInteger.valueOf(1);

		searchVector = new double[500];
		for (int i = 0; i <= 499; i++)
			searchVector[i] = client.sVector[i];
		
		cloudList = new ArrayList<>();
		cloudList = preProcess.list;	//cloudlist里存着所有的子图

		showList();
		
		
		
		
		
		
		for (int i = 0; i <= cloudList.size() - 1; i++)
		{
			score = innerProduct(cloudList.get(i).keyVector, searchVector);
			cloudList.get(i).score = score;
		}

		Collections.sort(cloudList, cmp);// 已经子图的得分进行排序
		
		
		for (int i = 0; i <= cloudList.size() - 1; i++)
			System.out.println(cloudList.get(i).score);

		for (int i = 0; i <= 4; i++)
		{
			for (int j = 0; j <= matrix.mSize - 1; j++)
				for (int k = 0; k <= matrix.mSize - 1; k++)
					returnM[j][k] = returnM[j][k].multiply(cloudList.get(i).M[j][k]);
		}
		
		
		if (1==6)
			for (int i = 0; i <= matrix.mSize - 1; i++)
				for (int j = 0; j <= matrix.mSize - 1; j++)
				{
					if (j != matrix.mSize - 1)
						System.out.print(returnM[i][j] + " ");
					else
						System.out.println(returnM[i][j]);
				}

	}

	private static double innerProduct(double[] keyVector, double[] searchVector)
	{
		double result = 0;
		for (int i = 0; i <= 499; i++)
			result += keyVector[i] * searchVector[i];
		return result;
	}

	public static void showList() // 显示所有的r半径子图
	{
		rRadiusGraph show;
		for (Iterator<rRadiusGraph> it = cloudList.iterator(); it.hasNext();)
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

}
