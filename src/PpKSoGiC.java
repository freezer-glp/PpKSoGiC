import java.io.IOException;


public class PpKSoGiC
{

	public static void main(String[] args) throws IOException
	{
		int vSize = 12;
		int keySize = 500;
		int radius = 2;
		//System.out.print("sdf");
		int ex,dx;
		client c1;
//		ex=HEncryption.encode(100);
//		dx = HEncryption.decode(ex);
//		System.out.println(dx);
//		creatTestfiles.creatKey(12, 500);
		createPrime.makePrime();
		matrix.readMtrix("file/matrix.txt", vSize);
		matrix.matrixPower(radius);
//		matrix.showMatrix('O');
		preProcess.getRradiusGraph(matrix.poweredMatrix, matrix.matrix,0); // 1,showlist
		//preProcess.showList();
		preProcess.makePrimeMark();
		//preProcess.showList();
		preProcess.encode();
		//preProcess.showList();
		client.doSearch("file/search.txt");
		//client.showList();
		cloud.cloudSearch(0);
		//cloud.showList();
		client.showResult();
		
		
//		int a=11,b=11;
//		int c;
//		a = HEncryption.encode(a);
//		b = HEncryption.encode(b);
//		c = a*b;
//		System.out.println(c);
//		c = HEncryption.decode(c);
//		System.out.println(c);
		
	
	
	}

}
