/*
 * Copyright © 1996-2009 Bart Massey
 * ALL RIGHTS RESERVED
 * [This program is licensed under the "MIT License"]
 * Please see the file ../COPYING in the source
 * distribution of this software for license terms.
 */

import DES.*;

public class DESTestRL {
    public static void main(String argv[]) {
	int n = new Integer(argv[0]).intValue();
	DESRandom prng = new DESRandom();
	for (int i = 0; i < n; i++)
	    System.out.println(Long.toString(prng.nextLong(), 16));
    }
}
