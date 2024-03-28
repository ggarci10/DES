import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class Des {
    public static void main(String[] args) {
        byte rawkey[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        byte plaintext[] = new byte[8];

        byte ciphertext[] = { 0, 0, 0, 0, 0, 1, 0, 0 };
        // ciphertext = Encrypt(rawkey, plaintext);

        // plaintext = Decrypt(rawkey, ciphertext);
        // for (int i = 0; i < plaintext.length; i++) {
        // System.out.print(plaintext[i]);
        // }
        // part3number1();
        part3number2();
    }

    public static void part3number1() {
        CASCII cascii = new CASCII();
        byte key[] = { 0, 1, 1, 1, 0, 0, 1, 1, 0, 1 };
        String what = "CRYPTOGRAPHY";
        byte con[];
        System.out.println(what);
        int count = 0;
        con = CASCII.Convert(what);
        byte values[] = new byte[8];
        count = 0;
        byte translate[] = new byte[8];
        for (int i = 0; i < 64; i++) {

            values[i % 7] = con[i];

            if (i % 7 == 0 && count != 0) {
                translate = Encrypt(key, values);
                // for(int j=0;j<8;j++){
                // System.out.print(translate[j]);
                // }
                System.out.print(CASCII.toString(translate));
                // System.out.println(count);
            }

            count++;
        }
    }

    public static void part3number2() {
        String c = "10110110011110010010111011111100001111101000000000011101110100011110111111"
                + "01101100010011000000101101011010101000101111100011101011010111100011101001"
                + "01011110110010111000001001010111000111011101111101010101010000110001100001"
                + "10101011110111110100111101110010010111001011010010000110111110110000100100"
                + "01011101100011011110000000110010111111010000011100011111111000010111010100"
                + "00110000101001100101010101000011010110111111101001011000100100000111100000"
                + "00111100000111101100100101010101000010000110100001000110101011000000101110"
                + "00000010101110100001000111010010010101110111010010111100011111010101111011"
                + "10111100010100101000110110010110011100111011100110010110001111100110000011"
                + "01000010011000100001000111000000000010010100111010111001010001110111000100"
                + "01111101011111100000010111110101010000000100110110111111000000111110111010"
                + "10011000001011000011101000111100010101111110101110110101001010001011110001"
                + "1100000001010101110111111101101100101010011100111011110101011011";

        String content = stringBuilder.toString();
        System.out.println(content);

        char at;
        byte cb[] = new byte[c.length()];
        // System.out.println(c.length());
        for (int i = 0; i < c.length(); i++) {
            at = c.charAt(i);
            // System.out.println(at);
            if (at == '0') {
                cb[i] = 0;
            } else if (at == '1') {
                cb[i] = 1;
            }

        }

        // for (int j = 0; j < cb.length; j++) {
        // System.out.print(cb[j]);
        // }

        // System.out.println(CASCII.toString(cb));

    }

    public static byte[] concat(byte[] left, byte[] right) {
        byte[] temp = new byte[8];
        temp[0] = left[0];
        temp[1] = left[1];
        temp[2] = left[2];
        temp[3] = left[3];
        temp[4] = right[0];
        temp[5] = right[1];
        temp[6] = right[2];
        temp[7] = right[3];
        return temp;
    }

    public static byte[] sbox1(byte[] perm) {
        int row = 0;
        int col = 0;
        byte number;
        byte[] temp = new byte[2];
        byte[][] sbox = { { 1, 0, 3, 2 }, { 3, 2, 1, 0 }, { 0, 2, 1, 3 }, { 3, 1, 3, 2 } };
        if (perm[0] == 0 && perm[3] == 0) {
            row = 0;
        } else if (perm[0] == 0 && perm[3] == 1) {
            row = 1;
        } else if (perm[0] == 1 && perm[3] == 0) {
            row = 2;
        } else if (perm[0] == 1 && perm[3] == 1) {
            row = 3;
        }
        if (perm[1] == 0 && perm[2] == 0) {
            col = 0;
        } else if (perm[1] == 0 && perm[2] == 1) {
            col = 1;
        } else if (perm[1] == 1 && perm[2] == 0) {
            col = 2;
        } else if (perm[1] == 1 && perm[2] == 1) {
            col = 3;
        }
        // System.out.println(row);
        // System.out.println(col);

        number = sbox[row][col];
        if (number == 0) {
            temp[0] = 0;
            temp[1] = 0;
        } else if (number == 1) {
            temp[0] = 0;
            temp[1] = 1;
        } else if (number == 2) {
            temp[0] = 1;
            temp[1] = 0;
        } else if (number == 3) {
            temp[0] = 1;
            temp[1] = 1;
        }
        // System.out.println(sbox[row][col]);
        // System.out.println(temp[0]);
        // System.out.println(temp[1]);
        return temp;
    }

    public static byte[] sbox2(byte[] perm) {

        int row = 0;
        int col = 0;
        byte number;
        byte[] temp = new byte[2];
        byte[][] sbox = { { 0, 1, 2, 3 }, { 2, 0, 1, 3 }, { 3, 0, 1, 0 }, { 2, 1, 0, 3 } };
        if (perm[0] == 0 && perm[3] == 0) {
            row = 0;
        } else if (perm[0] == 0 && perm[3] == 1) {
            row = 1;
        } else if (perm[0] == 1 && perm[3] == 0) {
            row = 2;
        } else if (perm[0] == 1 && perm[3] == 1) {
            row = 3;
        }
        if (perm[1] == 0 && perm[2] == 0) {
            col = 0;
        } else if (perm[1] == 0 && perm[2] == 1) {
            col = 1;
        } else if (perm[1] == 1 && perm[2] == 0) {
            col = 2;
        } else if (perm[1] == 1 && perm[2] == 1) {
            col = 3;
        }
        number = sbox[row][col];
        if (number == 0) {
            temp[0] = 0;
            temp[1] = 0;
        } else if (number == 1) {
            temp[0] = 0;
            temp[1] = 1;
        } else if (number == 2) {
            temp[0] = 1;
            temp[1] = 0;
        } else if (number == 3) {
            temp[0] = 1;
            temp[1] = 1;
        }
        // System.out.println(sbox[row][col]);
        // System.out.println(temp[0]);
        // System.out.println(temp[1]);
        return temp;
    }

    public static byte[] xor(byte[] key, byte[] perm) {

        byte[] temp = new byte[key.length];
        for (int i = 0; i < key.length; i++) {
            if (key[i] == 0 && perm[i] == 0) {
                temp[i] = 0;
            } else if (key[i] == 0 && perm[i] == 1) {
                temp[i] = 1;
            } else if (key[i] == 1 && perm[i] == 1) {
                temp[i] = 0;
            } else if (key[i] == 1 && perm[i] == 0) {
                temp[i] = 1;
            }
        }
        // for (int i = 0; i < temp.length; i++) {
        // System.out.println(temp[i]);

        // }
        return temp;
    }

    public static byte[] EP(byte[] key) {
        byte[] temp = new byte[8];
        temp[0] = key[3];
        temp[1] = key[0];
        temp[2] = key[1];
        temp[3] = key[2];
        temp[4] = key[1];
        temp[5] = key[2];
        temp[6] = key[3];
        temp[7] = key[0];
        // for (int i = 0; i < temp.length; i++) {
        // System.out.println(temp[i]);

        // }

        return temp;

    }

    public static byte[] IP(byte[] plain) {
        byte[] temp = new byte[8];
        temp[0] = plain[1];
        temp[1] = plain[5];
        temp[2] = plain[2];
        temp[3] = plain[0];
        temp[4] = plain[3];
        temp[5] = plain[7];
        temp[6] = plain[4];
        temp[7] = plain[6];
        return temp;
    }

    public static byte[] inverseP(byte[] plain) {
        byte[] temp = new byte[8];
        temp[0] = plain[3];
        temp[1] = plain[0];
        temp[2] = plain[2];
        temp[3] = plain[4];
        temp[4] = plain[6];
        temp[5] = plain[1];
        temp[6] = plain[7];
        temp[7] = plain[5];
        return temp;
    }

    public static byte[] p4(byte[] left, byte[] right) {
        byte[] temp = new byte[4];
        // System.out.print(left[0]);
        // System.out.print(left[1]);
        // System.out.print(right[0]);
        // System.out.print(right[1]);
        temp[0] = left[1];
        temp[1] = right[1];
        temp[2] = right[0];
        temp[3] = left[0];
        // for (int i = 0; i < temp.length; i++) {
        // System.out.println(temp[i]);

        // }
        return temp;

    }

    public static byte[] p10(byte[] key) {
        byte[] temp = new byte[10];
        temp[0] = key[2];
        temp[1] = key[4];
        temp[2] = key[1];
        temp[3] = key[6];
        temp[4] = key[3];
        temp[5] = key[9];
        temp[6] = key[0];
        temp[7] = key[8];
        temp[8] = key[7];
        temp[9] = key[5];
        // for (int i = 0; i < temp.length; i++) {
        // System.out.println(temp[i]);

        // }
        return temp;

    }

    public static byte[] shiftleft(byte[] key) {
        byte[] temp = new byte[10];
        temp[0] = key[1];
        temp[1] = key[2];
        temp[2] = key[3];
        temp[3] = key[4];
        temp[4] = key[0];

        temp[5] = key[6];
        temp[6] = key[7];
        temp[7] = key[8];
        temp[8] = key[9];
        temp[9] = key[5];
        // for (int i = 0; i < temp.length; i++) {
        // System.out.println(temp[i]);

        // }

        return temp;

    }

    public static byte[] p8(byte[] key) {
        byte[] temp = new byte[8];
        temp[0] = key[5];
        temp[1] = key[2];
        temp[2] = key[6];
        temp[3] = key[3];
        temp[4] = key[7];
        temp[5] = key[4];
        temp[6] = key[9];
        temp[7] = key[8];
        // for (int i = 0; i < temp.length; i++) {
        // System.out.println(temp[i]);

        // }

        return temp;

    }

    public static byte[] lsplit(byte[] plain) {
        byte[] temp = new byte[4];
        temp[0] = plain[0];
        temp[1] = plain[1];
        temp[2] = plain[2];
        temp[3] = plain[3];
        return temp;
    }

    public static byte[] switching(byte[] left, byte[] right) {
        byte[] temp = new byte[8];
        temp[0] = right[0];
        temp[1] = right[1];
        temp[2] = right[2];
        temp[3] = right[3];
        temp[4] = left[0];
        temp[5] = left[1];
        temp[6] = left[2];
        temp[7] = left[3];
        return temp;
    }

    public static byte[] rsplit(byte[] plain) {
        byte[] temp = new byte[4];
        temp[0] = plain[4];
        temp[1] = plain[5];
        temp[2] = plain[6];
        temp[3] = plain[7];
        return temp;
    }

    public static byte[] Encrypt(byte[] rawkey, byte[] plaintext) {
        // first do p10 then slipt in 2 for left shift for bits 0-4 and 5-9
        byte[] rkey = new byte[10];
        rkey = p10(rawkey);
        byte[] shiftkey = new byte[10];
        shiftkey = shiftleft(rkey);
        byte[] key1 = new byte[8];
        key1 = p8(shiftkey);
        byte[] shift = new byte[10];
        shift = shiftleft(shiftkey);
        shift = shiftleft(shift);
        byte[] key2 = new byte[10];
        key2 = p8(shift);
        // System.out.println("what do you mean undefine");
        // for (int i = 0; i < key2.length; i++) {
        // System.out.println(key2[i]);

        // }
        /// this part here will be using IP FK SW FK inverse IP
        byte[] ip = new byte[8];
        ip = IP(plaintext);
        byte[] left = new byte[4];
        byte[] right = new byte[4];
        // split into left right
        left = lsplit(ip);
        right = rsplit(ip);
        // exanp the right
        byte[] expand = new byte[8];
        expand = EP(right);
        byte[] xor = new byte[8];
        xor = xor(key1, expand);
        byte[] lsplitfors0 = new byte[4];
        byte[] rsplitfors1 = new byte[4];
        lsplitfors0 = lsplit(xor);
        rsplitfors1 = rsplit(xor);
        byte[] bitsleft = new byte[2];
        byte[] bitsright = new byte[2];
        bitsleft = sbox1(lsplitfors0);
        // now do lsplit rsplit then s0 s1
        bitsright = sbox2(rsplitfors1);
        byte[] perm4 = new byte[4];
        perm4 = p4(bitsleft, bitsright);
        byte[] xorstuff = new byte[4];
        xorstuff = xor(left, perm4);
        byte[] sw = new byte[8];
        sw = switching(xorstuff, right);
        // for (int i = 0; i < sw.length; i++) {
        // System.out.println(sw[i]);

        // }
        /// trying this out delete if fail
        left = lsplit(sw);
        right = rsplit(sw);
        expand = EP(right);
        xor = xor(key2, expand);
        lsplitfors0 = lsplit(xor);
        rsplitfors1 = rsplit(xor);
        bitsleft = sbox1(lsplitfors0);
        // now do lsplit rsplit then s0 s1
        bitsright = sbox2(rsplitfors1);
        perm4 = p4(bitsleft, bitsright);
        xorstuff = xor(left, perm4);
        byte[] connect = new byte[8];
        connect = concat(xorstuff, right);
        byte[] ciphertext = new byte[8];
        ciphertext = inverseP(connect);

        // do lsplit rsplit again and everything from below that
        return ciphertext;
    }

    public static byte[] Decrypt(byte[] rawkey, byte[] ciphertext) {
        // create key first
        byte[] rkey = new byte[10];
        rkey = p10(rawkey);
        byte[] shiftkey = new byte[10];
        shiftkey = shiftleft(rkey);
        byte[] key1 = new byte[8];
        key1 = p8(shiftkey);
        byte[] shift = new byte[10];
        shift = shiftleft(shiftkey);
        shift = shiftleft(shift);
        byte[] key2 = new byte[10];
        key2 = p8(shift);

        // IP fkwithkey2 SW fk+k1 InverseP
        byte[] ip = new byte[8];
        ip = IP(ciphertext);
        byte[] left = new byte[4];
        byte[] right = new byte[4];
        // split into left right
        left = lsplit(ip);
        right = rsplit(ip);
        // exanp the right
        byte[] expand = new byte[8];
        expand = EP(right);
        byte[] xor = new byte[8];
        xor = xor(key2, expand);
        byte[] lsplitfors0 = new byte[4];
        byte[] rsplitfors1 = new byte[4];
        lsplitfors0 = lsplit(xor);
        rsplitfors1 = rsplit(xor);
        byte[] bitsleft = new byte[2];
        byte[] bitsright = new byte[2];
        bitsleft = sbox1(lsplitfors0);
        // now do lsplit rsplit then s0 s1
        bitsright = sbox2(rsplitfors1);
        byte[] perm4 = new byte[4];
        perm4 = p4(bitsleft, bitsright);
        byte[] xorstuff = new byte[4];
        xorstuff = xor(left, perm4);
        byte[] sw = new byte[8];
        sw = switching(xorstuff, right);
        left = lsplit(sw);
        right = rsplit(sw);
        expand = EP(right);
        xor = xor(key1, expand);
        lsplitfors0 = lsplit(xor);
        rsplitfors1 = rsplit(xor);
        bitsleft = sbox1(lsplitfors0);
        // now do lsplit rsplit then s0 s1
        bitsright = sbox2(rsplitfors1);
        perm4 = p4(bitsleft, bitsright);
        xorstuff = xor(left, perm4);
        byte[] connect = new byte[8];
        connect = concat(xorstuff, right);
        byte[] plaintext = new byte[8];
        plaintext = inverseP(connect);

        // for (int i = 0; i < plaintext.length; i++) {
        // System.out.println(plaintext[i]);

        // }

        return plaintext;
    }

}