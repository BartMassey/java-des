/*
 * Copyright © 1996-2009 Bart Massey
 * ALL RIGHTS RESERVED
 * [This program is licensed under the "MIT License"]
 * Please see the file COPYING in the source
 * distribution of this software for license terms.
 */

import DES.DESStream;
import java.lang.*;
import java.io.*;

public class jdes {
    static public void main(String args[])
	    throws FileNotFoundException, IOException
    {
	boolean ok = false;
	boolean encrypt = true;
	String key = null;
	String name_in = null;
	String name_out = null;
	String mode = null;
	if (args.length == 4) {
	    mode = args[0];
	    key = args[1];
	    name_in = args[2];
	    name_out = args[3];
	    if (mode.equals("-e")) {
	        ok = true;
	        encrypt = true;
	    }
	    if (mode.equals("-d")) {
	        ok = true;
	        encrypt = false;
	    }
	}
	if (!ok)
	    throw new IllegalArgumentException("usage: jdes [-ed] key infile outfile");
	FileInputStream file_in = new FileInputStream(name_in);
	FileOutputStream file_out = new FileOutputStream(name_out);
	DESStream DES = new DESStream(key);
	if (encrypt)
	    DES.encrypt(file_in, file_out);
	else
	    DES.decrypt(file_in, file_out);
	file_in.close();
	file_out.close();
    }
}
