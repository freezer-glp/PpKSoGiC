import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Stack;


public class translateFile
{
	public static void creatKey(int vSize,int keySize) throws IOException
	{//假定每个顶点最多含100个关键词，关键词种类为keysize，定点数为vsize

		//int key[][] = new int [vSize][keySize];
		int maxKeyNum = keySize;
		Stack<Integer>s = new Stack<Integer>();
		Stack<Integer>s1 = new Stack<Integer>();
		int writeBuffer;
		String buffer[];
		String bufferString;
		//Random r = new Random();
		File file = new File("file//key.txt");
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
		
		RandomAccessFile output = new RandomAccessFile("testfile/key.txt","rw");
		RandomAccessFile input = new RandomAccessFile("testfile/key1.txt","rw");
//		for(int i = 0;i <= vSize-1;i++)
//			for(int j = 0;j <= keySize-1;j++)
//				key[i][j] =  -1;
//		
		
		for(int i = 0;i <= vSize-1;i++)
		{
			//vkNum = Math.abs(r.nextInt()%100);
			s.clear();
			bufferString = input.readLine();
			buffer = bufferString.split(" ");
			for(int j = 0 ;j <= buffer.length-1;j++)
			{
				
				//buffer = Math.abs(r.nextInt()%maxKeyNum);
				//System.out.println("j="+j+"s size= "+s.size()+"buffer="+buffer);
				if(!s.contains(Integer.parseInt(buffer[j])))
					s.push(Integer.parseInt(buffer[j]));
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
				writeBuffer = s1.pop();
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
				output.writeInt(writeBuffer);
				
			}
		}
			
		output.close();
		input.close();
		
	}
}
