import DES.*;

public class DESTestShuffle {
    public static void main(String argv[]) {
	int n = new Integer(argv[0]).intValue();
	int s = new Integer(argv[1]).intValue();
	int counts[] = new int[s];
	Integer deck[] = new Integer[s];
	DESRandom prng = new DESRandom();
	for (int k = 0; k < s; k++) {
	    for (int j = 0; j < s; j++)
		counts[j] = 0;
	    for (int i = 0; i < n; i++) {
		for (int j = 0; j < s; j++)
		    deck[j] = new Integer(j);
		prng.shuffle(deck);
		int j;
		for (j = 0; j < s; j++)
		    if (deck[j].intValue() == k)
			break;
		counts[j]++;
	    }
	    for (int j = 0; j < s; j++)
		System.out.println(counts[j]);
	    System.out.println();
	}
    }
}
