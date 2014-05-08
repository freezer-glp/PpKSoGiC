import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;


class creatTestfiles
{
	//参数，顶点数，关键词种类数，假定每个顶点最多含100个关键词
	public static void creatTest(int vSize,int keySize) throws IOException
	{
		creatMatrix(vSize);
		creatKey(vSize, keySize);//参数，顶点数，关键词种类数，假定每个顶点最多含100个关键词
		creatSearch(keySize);
	}
	public static void creatMatrix(int size) throws IOException
	{
		int a[][] = new int [size][size];
		Random r = new Random();
		File file = new File("testfile/matrix.txt");
		if(file.exists())
			file.delete();
		RandomAccessFile raf = new RandomAccessFile("testfile/matrix.txt", "rw");
		for(int i =  0;i <= size-1;i++)
			for(int j = i;j <= size-1;j++)
			{
				if(j == i)
					a[i][j] = 1;
				else
				{
					a[i][j] = Math.abs(r.nextInt()%2);
					a[j][i] = a[i][j];
				}
						
			}
		//raf.seek(0);
//		for(int i =  0;i <= size-1;i++)
//			for(int j = 0;j <= size-1;j++)
//			{
//				if(j == size -1)
//				{
//					System.out.println(a[i][j]);
//				//raf.writeChars("\r\n");
//				}
//				else
//				{
//					System.out.print(a[i][j]);
//					System.out.print(" ");
//				}
//				
//			}
		
		for(int i =  0;i <= size-1;i++)
			for(int j = 0;j <= size-1;j++)
			{
				if(j == size -1)
				{
					raf.write(a[i][j]+'0');
					raf.write(13);
					raf.write(10);
				}
				else
				{
					raf.write(a[i][j]+'0');
					raf.write(32);
				}
				
			}
		//System.out.println(a);
	raf.close();

		
	}	
	public static void creatKey(int vSize,int keySize) throws IOException
	{//假定每个顶点最多含100个关键词，关键词种类为keysize，定点数为vsize

		//int key[][] = new int [vSize][keySize];
		int maxKeyNum = keySize;
		Stack<Integer>s = new Stack<Integer>();
		Stack<Integer>s1 = new Stack<Integer>();
		int vkNum;
		Integer buffer;
		String bufferString;
		Random r = new Random();
		File file = new File("testfile//key.txt");
		if(file.exists())
		{
			//System.out.println("exist");
			if(file.delete())
			{
				//System.out.println("sdelet");
			}
			else
			{
				//System.out.println("faildelete");
			}
		}
		String outFilePath = "file//key.txt";
//		BufferedWriter raf = new BufferedWriter(new FileWriter(outFilePath));
		RandomAccessFile raf = new RandomAccessFile("file/key.txt","rw");
//		for(int i = 0;i <= vSize-1;i++)
//			for(int j = 0;j <= keySize-1;j++)
//				key[i][j] =  -1;
//		
		for(int i = 0;i <= vSize-1;i++)
		{
			vkNum = Math.abs(r.nextInt()%100);
			s.clear();
			for(int j = 0 ;j <= vkNum;j++)
			{
				
				buffer = Math.abs(r.nextInt()%maxKeyNum);
				//System.out.println("j="+j+"s size= "+s.size()+"buffer="+buffer);
				if(!s.contains(buffer))
					s.push(buffer);
				else
					j--;
			}
			while(s.size() != 100)
			{
				s.push(-1);
			}
			while(s.size() != 0)
			{
				s1.push(s.pop());
			}
			for(int j = 0 ;j <= 99;j++)
			{
				buffer = s1.pop();
				//System.out.println("buffer="+buffer);
//				bufferString = String.valueOf(buffer);
				
//				bufferString = buffer.toString();
//				
//				if(j == 99)
//				{
//					raf.write(bufferString+"\r\n");
//					//raf.write(buffer);
//					//raf.write(13);
//					//raf.write(10);
//				}
//				else
//				{
//					//raf.write(buffer);
//					raf.write(bufferString);
//					
//					raf.write(" ");
//				}
				raf.writeInt(buffer);
				
			}
		}
			
		raf.close();
		
	}
	public static void creatSearch(int keySize) throws IOException
	{
		int maxKeyNum = keySize;
		int vkNum;
		Integer buffer;
		String bufferString;
		Random r = new Random();
		File file = new File("testfile/search.txt");
		if(file.exists())
			file.delete();
		String outFilePath = "testfile//search.txt";
		BufferedWriter raf = new BufferedWriter(new FileWriter(outFilePath));
		
		vkNum = Math.abs(r.nextInt()%5)+1;
		for(int j = 0 ;j <= vkNum -1;j++)
		{
			buffer = Math.abs(r.nextInt()%maxKeyNum);
			bufferString = buffer.toString();
			
			raf.write(bufferString);
			raf.write(" ");
					
			
		}
		raf.close();
//		raf.write(13);
//		raf.write(10);
		
			
	}
}
