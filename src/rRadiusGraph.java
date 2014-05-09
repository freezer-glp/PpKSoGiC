import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.xml.crypto.dsig.keyinfo.KeyValue;


class rRadiusGraph
{
	static int num = 0;				//r半径子图计数器
	double score;					//子图的得分
	int r;							//子图的半径
	int id;							//子图的编号		
	int M[][];						//子图的邻接矩阵
	double keyVector[];				//该子图所含关键词的向量
	public static RandomAccessFile finput = null;

	public rRadiusGraph(int R, int Id, int m[][])
	{
		this.M = new int[matrix.mSize][matrix.mSize];
		rRadiusGraph.num++;
		this.r = R;
		this.id = Id;
		for (int i = 0; i <= matrix.mSize - 1; i++)
			for (int j = 0; j <= matrix.mSize - 1; j++)
				this.M[i][j] = m[i][j];

		
	}
	
	public void setVector() throws IOException
	{
		finput = new RandomAccessFile("file/key.txt", "rw");
		int seekNum = 0,keyNum;
		keyVector =  new double[500];
		for(int i = 0; i <= 499; i++)
			keyVector[i] = 0;
		for(int i = 0; i <= matrix.mSize - 1; i++)
		{
			if(M[i][i] == 1)		//对于子图中每个存在点
			{
				seekNum = i*100*4;
				finput.seek(seekNum);
				keyNum = finput.readInt();
				while(keyNum != -1)
				{
					//System.out.println("i="+i+" keyNum="+keyNum);
					keyVector[keyNum] = 1;
					keyNum = finput.readInt();
				}
			}
		}
		
		System.out.println("id="+id);
		for(int i = 0; i <= 499; i++)
			System.out.print(keyVector[i]);
		finput.close();
		
	}
	
	
	
	
	
}
