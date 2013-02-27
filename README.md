# java-DES

This source is definitely a past thing--there's better
solutions out there now, and everybody has moved to AES
anyhow. I provide it because some of my libraries depend on
it, and I'm too lazy to fix them.

---

The source file DESAlgorithm.java in this directory is a
very nice port to Java by Frank O'Dwyer <fod@brd.ie> and
Rainbow Diamond Limited of a DES implementation by Eric
Young <eay@mincom.oz.au>.  It's copyright all of these
entities, but has been generously made available under an
open source license with some restrictions.  Please see the
end of the file DESAlgorithm.java for details.

My "contribution" to all of this was to fix some minor bugs
(in weak-key testing and parity checking), maybe change the
interface slightly, remove some dead and broken code, and
provide some utility classes.  The file
`DESAlgorithm.java.patch` contains compressed context diffs
from the original to what I did, in case you need to get the
original back.  My code is licensed under terms described by
the file `COPYING` in this distribution.

`DESBlock.java` provides a more conventional OOP interface
to the algorithm.

`DESStream.java` encrypts and decrypts a Java Stream in a
format compatible with UNIX "des -b".  (`jdes.java` provides
a command-line interface to this code as an example of how
to use it.  I was actually pleasantly surprised by this
code's performance of around 8KB/sec interpreted on my UNIX
box!)

`DESRandom.java` generates pseudo-random numbers by
encrypting successive integers using DES.  I have no idea
whether this is actually secure, but it seems to work pretty
well for my purposes.

*TO DO:*

  * Have somebody re-implement DES from scratch in Java
    and place it in the public domain.
  * Add CBC mode, 3DES, etc.
  * Add other algorithms, and try to design a general purpose
    superclass for all of them.  Replace DESStream and DESRandom
    with classes which take objects of this class in the
    constructor.

All my modifications are placed under the "MIT License", as
per the `COPYING` file with this distribution.  Have fun!

> Bart Massey  
> bart@cirl.uoregon.edu  
> 12/1/97
