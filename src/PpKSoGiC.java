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
	
	
	}

}
