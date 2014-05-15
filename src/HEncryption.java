import java.math.BigInteger;
import java.util.Random;


public class HEncryption		//realization of the Homomorphic Encryption on integer
{
//	public static BigInteger 
//	p = new BigInteger("510827")
//	,q =  new BigInteger("511787");
	//public static int p = 17,q = 13;
	
	public static BigInteger encode(BigInteger x,BigInteger p,BigInteger q)
	{
		//System.out.println("x is:"+x);
		BigInteger n = q,ran, a = null,b,c = null,ex;
		
		n = n.multiply(p);
		
		Random rand = new Random();
		int r = rand.nextInt();
		r =  Math.abs(r)%50 +1;  			// r is between 1~50
		
		ran = BigInteger.valueOf(r);
		
		//System.out.println("r is:"+ran);

		a = ran.multiply(p);
		b = x.add(a);
		ex = b.mod(n);
		//System.out.println("r is:"+ex.mod(p));
		return ex;
	}
	
	public static int decode(BigInteger ex,BigInteger p,BigInteger q)
	{	
		BigInteger dx,p1,x1,x2,x3;
		//x1 = new BigInteger("26929039382848046154495490036531200");
		//x2 =  new BigInteger("224068");
		//p1 = BigInteger.valueOf(p);
		//System.out.println(ex);
		//System.out.println(p1);
//		dx = ex.divide(p);
		//x3 = p.add();
		dx = ex.mod(p);
		//dx = x1.divide(x2);
		//System.out.println(dx);
		return dx.intValue();
	}
}
