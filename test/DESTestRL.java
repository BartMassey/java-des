import DES.*;

public class DESTestRL {
    public static void main(String argv[]) {
	int n = new Integer(argv[0]).intValue();
	DESRandom prng = new DESRandom();
	for (int i = 0; i < n; i++)
	    System.out.println(Long.toString(prng.nextLong(), 16));
    }
}
