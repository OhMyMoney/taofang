package com.taofang.webapi.mock;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-06
 */
public class ChilkatExample {
    static {
        try {
            System.loadLibrary("chilkat");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

//    public static void main(String argv[])
//    {
//        CkCrypt2 crypt = new CkCrypt2();
//
//        boolean success = crypt.UnlockComponent("Anything for 30-day trial");
//        if (success != true) {
//            System.out.println(crypt.lastErrorText());
//            return;
//        }
//
//        String hexKey;
//        //  http://www.di-mgt.com.au/cryptoKDFs.html#examplespbkdf
//
//        String pw = "password";
//        String pwCharset = "ansi";
//        //  Hash algorithms may be: sha1, md2, md5, etc.
//        String hashAlg = "sha1";
//        //  The salt should be 8 bytes:
//        String saltHex = "78578E5A5D63CB06";
//        int iterationCount = 1000;
//        //  Derive a 128-bit key from the password.
//        int outputBitLen = 128;
//
//        //  The derived key is returned as a hex or base64 encoded string.
//        //  (Note: The salt argument must be a string that also uses
//        //  the same encoding.)
//        String enc = "hex";
//
//        hexKey = crypt.pbkdf1(pw,pwCharset,hashAlg,saltHex,iterationCount,outputBitLen,enc);
//
//        System.out.println(hexKey);
//
//        //  The output should have this value:
//        //  DC19847E05C64D2FAF10EBFB4A3D2A20
//
//    }
}
