/*
 * Copyright © 1996-2009 Bart Massey
 * ALL RIGHTS RESERVED
 * [This program is licensed under the "MIT License"]
 * Please see the file COPYING in the source
 * distribution of this software for license terms.
 */

package DES;

public class DESBlock {
    private DESAlgorithm DES = null;
    private int[] schedule = new int[32];

    public DESBlock(byte key[], boolean checkParity) {
	DES = new DESAlgorithm(checkParity);
	if (!checkParity)
	    DES.des_set_odd_parity(key);
	DES.des_set_key(key, schedule);
    }

    public DESBlock(byte key[]) {
	DES = new DESAlgorithm(true);
	DES.des_set_key(key, schedule);
    }

    public DESBlock(long keyblock) {
	byte key[] = new byte[8];
	key[0] = (byte) ((keyblock << 1) & 0xf7);
	for (int i = 1; i < 8; i++)
	    key[i] = (byte) ((keyblock >>> (i * 7 - 1)) & 0xf7);
	DES = new DESAlgorithm(false);
	DES.des_set_odd_parity(key);
	DES.des_set_key(key, schedule);
    }

    public DESBlock(String key) {
	DES = new DESAlgorithm(false);
	DES.des_set_strkey(key, schedule);
    }

    public void ecb_encrypt(byte plain[], byte cipher[]) {
	DES.des_ecb_encrypt(plain, cipher, schedule, true);
    }

    public byte [] ecb_encrypt(byte plain[]) {
	byte cipher[] = new byte[8];
	ecb_encrypt(plain, cipher);
	return cipher;
    }

    public long ecb_encrypt(long plainblock) {
	byte plain[] = new byte[8];
	for(int i = 0; i < 8; i++)
	    plain[i] = (byte) ((plainblock >>> (i * 8)) & 0xff);
	byte cipher[] = ecb_encrypt(plain);
	long cipherblock = 0;
	for(int i = 0; i < 8; i++)
	    cipherblock |= ((((long) cipher[i]) & 0xff) << (i * 8));
	return cipherblock;
    }

    public void ecb_decrypt(byte cipher[], byte plain[]) {
	DES.des_ecb_encrypt(cipher, plain, schedule, false);
    }

    public byte [] ecb_decrypt(byte cipher[]) {
	byte plain[] = new byte[8];
	ecb_decrypt(cipher, plain);
	return plain;
    }

    public long ecb_decrypt(long cipherblock) {
	byte cipher[] = new byte[8];
	for(int i = 0; i < 8; i++)
	    cipher[i] = (byte) ((cipherblock >>> (i * 8)) & 0xff);
	byte plain[] = ecb_decrypt(cipher);
	long plainblock = 0;
	for(int i = 0; i < 8; i++)
	    plainblock |= ((((long) plain[i]) & 0xff) << (i * 8));
	return plainblock;
    }

}
