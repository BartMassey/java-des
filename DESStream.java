package DES;

import java.io.*;

public class DESStream {
    private DESAlgorithm DES = null;
    private int[] schedule = new int[32];

    public DESStream(byte key[], boolean checkParity) {
	DES = new DESAlgorithm(checkParity);
	if (!checkParity)
	    DES.des_set_odd_parity(key);
	DES.des_set_key(key, schedule);
    }

    public DESStream(byte key[]) {
	DES = new DESAlgorithm(true);
	DES.des_set_key(key, schedule);
    }

    public DESStream(String key) {
	DES = new DESAlgorithm(false);
	DES.des_set_strkey(key, schedule);
    }


    private static int readn(InputStream in, byte buf[], int c)
	throws IOException
    {
	int i = 0;
	while (i < c) {
	    int n = in.read(buf, i, c - i);
	    if (n == -1)
		return i;
	    i += n;
	}
	return c;
    }

    public void encrypt(InputStream in, OutputStream out)
	throws IOException
    {
	byte inbuf[] = new byte[8];
	byte outbuf[] = new byte[8];
	boolean done = false;
	while (!done) {
	    int n = readn(in, inbuf, 8);
	    if (n < 8) {
		done = true;
		int m = n;
		while (n < 7)
		    inbuf[n++] = 0;
		inbuf[n++] = (byte) (m & 0xff);
	    }
	    DES.des_ecb_encrypt(inbuf, outbuf, schedule, true);
	    out.write(outbuf, 0, 8);
	}
    }

    public void decrypt(InputStream in, OutputStream out)
	throws IOException
    {
	byte inbuf[] = new byte[8];
	byte outbuf[] = new byte[8];
	byte tmpbuf[] = new byte[8];
	byte tmp[];
	boolean done = false;
	int n = readn(in, inbuf, 8);
	while (n == 8) {
	    tmp = tmpbuf;
	    tmpbuf = outbuf;
	    outbuf = tmp;
	    n = readn(in, inbuf, 8);
	    if (n < 8)
		break;
	    DES.des_ecb_encrypt(inbuf, tmpbuf, schedule, false);
	    out.write(outbuf, 0, 8);
	}
	if (n > 0)
	    throw new IOException("input file length wrong");
	int m = outbuf[7];
        if (m > 0 && m <= 7)
            out.write(outbuf, 0, outbuf[7]);
        if (m < 0 || m > 7)
	    throw new IOException("input file format error");
    }
}
