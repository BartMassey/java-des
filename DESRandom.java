/*
 * Copyright © 1996-2009 Bart Massey
 * ALL RIGHTS RESERVED
 * [This program is licensed under the "MIT License"]
 * Please see the file COPYING in the source
 * distribution of this software for license terms.
 */

package DES;

public class DESRandom {
    private DESBlock DES;
    private long state = 0;

    public DESRandom(long seed) {
	DES = new DESBlock(seed);
    }

    public DESRandom() {
	this(System.currentTimeMillis());
    }

    public long nextLong() {
	long result = DES.ecb_encrypt(state);
	state = state + 1;
	return result;
    }

    public long nextUnsignedLong() {
	return nextLong() & 0x7fffffffffffffffL;
    }

    public int nextInt() {
	return (int) (nextLong() & 0xffffffffL);
    }
    
    public int nextUnsignedInt() {
	return (int) (nextLong() & 0x7fffffffL);
    }

    public int randomMod(int k) {
	if (k < 0)
	    throw new IllegalArgumentException("negative modulus");
	return (int) (nextUnsignedLong() % k);
    }

    private static final void exchange(Object a[], int i, int j) {
	if (i == j)
	    return;
	Object tmp = a[i];
	a[i] = a[j];
	a[j] = tmp;
    }
    
    public void shuffle(Object a[]) {
	int n = a.length;
	for (int i = 0; i < n - 1; i++)
	    exchange(a, i, i + randomMod(n - i));
    }
}
