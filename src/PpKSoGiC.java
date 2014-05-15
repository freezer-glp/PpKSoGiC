import java.io.IOException;


public class PpKSoGiC
{

	public static void main(String[] args) throws IOException
	{
		int vSize = 25;
		int keySize = 500;
		int radius = 2;
	
		long startTime=System.currentTimeMillis();   //获取开始时间  
		//creatTestfiles.creatTest(vSize, keySize);
		translateFile.creatKey(vSize, keySize);
		long endTime=System.currentTimeMillis(); //获取结束时间 
		System.out.println("生成测试文件时间： "+(endTime-startTime)+"ms");
		
		startTime=System.currentTimeMillis();   //获取开始时间  
		createPrime.makePrime();
		
		matrix.readMtrix("testfile/matrix.txt", vSize);
		matrix.matrixPower(radius);

		preProcess.getRradiusGraph(matrix.poweredMatrix, matrix.matrix,0); // 1,showlist
		preProcess.makePrimeMark();
		preProcess.encode();
		endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("预处理时间： "+(endTime-startTime)+"ms"); 
		
		startTime=System.currentTimeMillis();   //获取开始时间  
		client.doSearch("testfile/search.txt");
		cloud.cloudSearch(0);
		client.showResult();
		endTime=System.currentTimeMillis(); //获取结束时间  
		System.out.println("查询时间： "+(endTime-startTime)+"ms"); 
		
	
	
	}

}
