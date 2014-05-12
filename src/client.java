import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class client
{
	public static double sVector[];	
	
	public static void doSearch(String sfPath) throws IOException 
	{
		BufferedReader input = new BufferedReader(new FileReader(sfPath));
		String a,abuffer[] = null;
		sVector = new double[500];
		a = input.readLine();
		abuffer = a.split(" ");
		input.close();
		for(int i = 0; i <= 499; i++)
			sVector[i] = 0;
		
		for(int i = 0; i <= abuffer.length-1; i++)
		{
			sVector[Integer.parseInt(abuffer[i])] = 1;
		}
		
		sASPE(sVector);
		//showList();
		
	}
	
	public static void sASPE(double m[])
	{
		
		//System.out.print("aaaaaaaaaaaa");
		double buffer[] = new double[500];
		double sum;
		for (int i = 0; i <= 499; i++)
		{	
			buffer[i] = m[i];
			//System.out.print("m"+m[i]+' ');
		}
		
//		for (int i = 0; i <= matrix.mSize - 1; i++)
//			for (int j = 0; j <= matrix.mSize - 1; j++)
//			{
//				if (j != matrix.mSize - 1)
//					System.out.print(matrix.inverMatrix[i][j] + " ");
//				else
//					System.out.println(matrix.inverMatrix[i][j]);
//			}
		for (int i = 0; i <= 499; i++)
		{
			sum = 0;
			for (int j = 0; j <= 499; j++)
			{
				sum += buffer[j] * matrix.inverMatrix[j][i];
			}
			//System.out.println("sum:"+sum);
			m[i] = sum;
			//System.out.println("m["+i+"]="+m[i]+"\n");
			//m[i] = 100;

		}

	}
	
	public static void showResult()
	{
		int rMatrix[][] = new int[matrix.mSize][matrix.mSize];
		for(int i = 0; i <= matrix.mSize-1;i++)
			for(int j = 0; j <= matrix.mSize-1;j++)
			{
				rMatrix[i][j] =HEncryption.decode(cloud.returnM[i][j]);
				//System.out.println(rMatrix[i][j]);
			}
		
		if (1==1)
			for (int i = 0; i <= matrix.mSize - 1; i++)
				for (int j = 0; j <= matrix.mSize - 1; j++)
				{
					if (j != matrix.mSize - 1)
						System.out.print(rMatrix[i][j] + " ");
					else
						System.out.println(rMatrix[i][j]);
				}
	}
	
	public static void showList()
	{
		//System.out.print("aaaaaaaaaaaa");
		for(int i = 0; i <= sVector.length-1; i++)
			System.out.println(sVector[i]);
		
	}
}
