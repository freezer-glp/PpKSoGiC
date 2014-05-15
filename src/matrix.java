import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

class matrix
{
	public static int matrix[][]; // 原始图的邻接矩阵
	public static int poweredMatrix[][]; // 求radius幂后的矩阵
	public static int radius; // 子图的半径
	public static int mSize; // 矩阵的阶数
	public static double transMatrix[][];
	public static double inverMatrix[][];

	static void readMtrix(String filepath, int matrixSize) throws IOException // 从文件中读取原始图的邻接矩阵
	{
		mSize = matrixSize;
		matrix = new int[mSize][mSize];
		transMatrix = new double[500][500];
		inverMatrix = new double[500][500];
		int a, b;

		Scanner fr = null;
		// Scanner tm = null;
		// Scanner im = null;
		BufferedReader tm = null;
		BufferedReader im = null;
		String inFilet = "file/T500.txt";
		String inFilei = "file/_500.txt";
		String tnum[] = null;
		String inum[] = null;
		try
		{
			fr = new Scanner(new File(filepath));
			// tm = new Scanner(new File("file/T500.txt"));
			// im = new Scanner(new File("file/_500.txt"));

			tm = new BufferedReader(new FileReader(inFilet));
			im = new BufferedReader(new FileReader(inFilei));
			String linet = tm.readLine();
			String linei = im.readLine();
			tnum = linet.split(",");
			inum = linei.split(",");

			// System.out.println("----->" + num_elem.length);
		}
		catch (FileNotFoundException e)
		{

			e.printStackTrace();
		}

		for (int i = 0; i <= mSize - 1; i++)
			for (int j = 0; j <= mSize - 1; j++)
			{
				matrix[i][j] = fr.nextInt();
				// System.out.println(matrix[i][j]);
			}
		if (1 == 1)
			for (int i = 0; i <= mSize - 1; i++)
				for (int j = 0; j <= mSize - 1; j++)
				{
					if(matrix[i][j] != matrix[j][i])
					{
						System.out.println("("+i+","+j+")");
					}
					// System.out.println(matrix[i][j]);
				}
		int flag = 0;
		for (int i = 0; i <= 499; i++)
			for (int j = 0; j <= 499; j++)
			{

				transMatrix[i][j] = Double.parseDouble(tnum[flag]);
				inverMatrix[i][j] = Double.parseDouble(inum[flag]);
				flag++;
				// System.out.println(matrix[i][j]);
			}
		// for (int i = 0; i <= 499; i++)
		// for (int j = 0; j <= 499; j++)
		// {
		//
		// System.out.println(inverMatrix[i][j]+" ");
		//
		//
		// // System.out.println(matrix[i][j]);
		// }

		fr.close();
		tm.close();
		im.close();

	}

	static void matrixPower(int r) // 进行矩阵的r次求幂
	{
		poweredMatrix = new int[mSize][mSize];
		radius = r;
		int a = 0;
		int temp[][] = new int[mSize][mSize];
		for (int i = 0; i <= mSize - 1; i++)
			for (int j = 0; j <= mSize - 1; j++)
			{
				temp[i][j] = matrix[i][j];
				poweredMatrix[i][j] = matrix[i][j];
			}

		while (r-- != 1)
		{
			for (int i = 0; i <= mSize - 1; i++)
				for (int j = 0; j <= mSize - 1; j++)
				{
					a = 0;
					for (int k = 0; k <= mSize - 1; k++)
						a += temp[i][k] * matrix[k][j];
					poweredMatrix[i][j] = a;
				}

			for (int i = 0; i <= mSize - 1; i++)
				for (int j = 0; j <= mSize - 1; j++)
				{
					temp[i][j] = poweredMatrix[i][j];

				}
		}

		for (int i = 0; i <= mSize - 1; i++)
			// 将所有非0值用1代替
			for (int j = 0; j <= mSize - 1; j++)
			{
				if (poweredMatrix[i][j] != 0)
					poweredMatrix[i][j] = 1;
			}
	}

	public static void showMatrix(char string)// 显示结果，O为原始邻接矩阵，P为r次幂矩阵
	{
		if (string == 'O')
		{
			for (int i = 0; i <= mSize - 1; i++)
				for (int j = 0; j <= mSize - 1; j++)
				{
					if (j != mSize - 1)
						System.out.print(matrix[i][j] + " ");
					else
						System.out.println(matrix[i][j]);
				}
		}
		else if (string == 'P')
		{
			for (int i = 0; i <= mSize - 1; i++)
				for (int j = 0; j <= mSize - 1; j++)
				{
					if (j != mSize - 1)
						System.out.print(poweredMatrix[i][j] + " ");
					else
						System.out.println(poweredMatrix[i][j]);
				}
		}
	}
}
