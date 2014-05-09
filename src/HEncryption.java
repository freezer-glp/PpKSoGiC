import java.util.Random;


public class HEncryption		//realization of the Homomorphic Encryption on integer
{
	public static int p = 101,q = 71;
	
	public static int encode(int x)
	{
		int n = p*q;
		Random rand = new Random();
		int r = rand.nextInt();
		r =  Math.abs(r)%50 +1;  			// r is between 1~50
		//System.out.println("r is:"+r);
		int ex = (x + r*p) % n;
		
		return ex;
	}
	
	public static int decode(int x)
	{
		int dx;
		dx = x % p;
		return dx;
	}
}
