import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class matrix
{
	public static int matrix[][];                    //ԭʼͼ���ڽӾ���
	public static int poweredMatrix[][];			 //��radius�ݺ�ľ���
	public static int radius;						//��ͼ�İ뾶
	public static int mSize;						// ����Ľ���

	
	static void readMtrix(String filepath, int matrixSize) //���ļ��ж�ȡԭʼͼ���ڽӾ���
	{
		mSize = matrixSize;
		matrix = new int[mSize][mSize];
		Scanner fr = null;
		try
		{
			fr = new Scanner(new File(filepath));
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
	}

	static void matrixPower(int r)			//���о����r������
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

		for (int i = 0; i <= mSize - 1; i++)             //�����з�0ֵ��1����
			for (int j = 0; j <= mSize - 1; j++)
			{
				if (poweredMatrix[i][j] != 0)
					poweredMatrix[i][j] = 1;
			}
	}

	public static void showMatrix(char string)//��ʾ�����OΪԭʼ�ڽӾ���PΪr���ݾ���
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
