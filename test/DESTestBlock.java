import DES.*;

public class DESTestBlock {
    public static void main(String argv[]) {
	int n = new Integer(argv[0]).intValue();
	DESBlock prng = new DESBlock(System.currentTimeMillis());
	for (int i = 0; i < n; i++)
	    System.out.println(Long.toString(prng.ecb_encrypt(i), 16));
    }
}
