import java.io.IOException;


public class PpKSoGiC
{

	public static void main(String[] args) throws IOException
	{
		int vSize = 25;
		int keySize = 500;
		int radius = 2;
	
		long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��  
		//creatTestfiles.creatTest(vSize, keySize);
		translateFile.creatKey(vSize, keySize);
		long endTime=System.currentTimeMillis(); //��ȡ����ʱ�� 
		System.out.println("���ɲ����ļ�ʱ�䣺 "+(endTime-startTime)+"ms");
		
		startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��  
		createPrime.makePrime();
		
		matrix.readMtrix("testfile/matrix.txt", vSize);
		matrix.matrixPower(radius);

		preProcess.getRradiusGraph(matrix.poweredMatrix, matrix.matrix,0); // 1,showlist
		preProcess.makePrimeMark();
		preProcess.encode();
		endTime=System.currentTimeMillis(); //��ȡ����ʱ��  
		System.out.println("Ԥ����ʱ�䣺 "+(endTime-startTime)+"ms"); 
		
		startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��  
		client.doSearch("testfile/search.txt");
		cloud.cloudSearch(0);
		client.showResult();
		endTime=System.currentTimeMillis(); //��ȡ����ʱ��  
		System.out.println("��ѯʱ�䣺 "+(endTime-startTime)+"ms"); 
		
	
	
	}

}
