import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

class Demo {
    public static void main(String[] args) {
        // part1sdes();
        // part2triplesdes();
        // try {
        // part3number1();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // try {
        // cracking();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        try {
            cracking2();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // try {
        // part3number2();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    public static void part1sdes() {

        Des object = new Des();
        byte rawkey[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        byte plaintext[] = new byte[8];
        byte ciphertext[] = { 0, 0, 0, 1, 0, 0, 0, 1 };
        // ciphertext = object.Encrypt(rawkey, plaintext);

        plaintext = object.Decrypt(rawkey, ciphertext);
        for (int i = 0; i < plaintext.length; i++) {
            System.out.print(plaintext[i]);
        }
        System.out.println(" ");

    }

    public static void part2triplesdes() {

        TripleDes tdes = new TripleDes();
        byte rawkey1[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        byte rawkey2[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        byte plaintext[] = new byte[8];
        byte ciphertext[] = { 1, 0, 0, 1, 0, 0, 1, 0 };
        // ciphertext = object.Encrypt(rawkey, plaintext);

        plaintext = tdes.tencryption(rawkey1, rawkey2, ciphertext);
        for (int i = 0; i < plaintext.length; i++) {
            System.out.print(plaintext[i]);
        }
        System.out.println(" ");

    }

    public static void part3number1() throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\chro\\DES\\DESproject\\src\\test.txt");

        CASCII cascii = new CASCII();
        Des object = new Des();
        byte key[] = { 0, 1, 1, 1, 0, 0, 1, 1, 0, 1 };
        String converthis = "CRYPTOGRAPHY";
        byte con[];
        int count = 0;
        con = CASCII.Convert(converthis);
        // for (int a = 0; a < con.length; a++) {
        // // System.out.print(con[a]);

        // }
        // System.out.println(con.length);
        // this shows i have 64 characters
        byte values[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
        count = 0;
        byte translate[] = new byte[8];
        System.out.println("the legnth of con is : " + con.length);
        for (int i = 0; i < con.length; i += 8) {

            // values[i % 8] = con[i];
            // System.out.println(values[i % 8]);
            // System.out.println(con[i]);
            for (int j = 0; j < values.length; j++) {
                values[j] = con[count];
                // System.out.print(values[j] + " ");

                count++;
            }
            // System.out.println();
            translate = object.Encrypt(key, values);
            // System.out.println(count);
            for (int k = 0; k < 8; k++) {
                // System.out.print();
                // fw.write();
                byte check1 = 1;
                byte check2 = 0;
                if (check1 == translate[k]) {
                    fw.write("1");
                } else if (check2 == translate[k]) {
                    fw.write("0");
                }
            }

            // System.out.print(CASCII.toString(translate));
            // System.out.println(count);
            // System.out.println();
        }
        fw.close();
        Path fileName = Path.of("C:\\Users\\chro\\DES\\DESproject\\src\\test.txt");
        String content = Files.readString(fileName);
        // System.out.println(content);
        char at;
        byte cb[] = new byte[content.length()];
        // System.out.println(cb.length());
        for (int i = 0; i < content.length(); i++) {
            at = content.charAt(i);
            // System.out.println(at);
            if (at == '0') {
                cb[i] = 0;
            } else if (at == '1') {
                cb[i] = 1;
            }

        }
        // System.out.print(cb.length);
        System.out.println(CASCII.toString(cb));

    }

    public static void cracking() throws IOException {
        Path fileName = Path.of("C:\\Users\\chro\\DES\\DESproject\\src\\msg1.txt");
        String content = Files.readString(fileName);
        Des object = new Des();
        char at;
        byte cb[] = new byte[content.length()];
        // System.out.println(cb.length());
        System.out.println("cracking starting");
        System.out.println("the legnth of con is : " + cb.length);
        for (int i = 0; i < content.length(); i++) {
            at = content.charAt(i);
            // System.out.println(at);
            if (at == '0') {
                cb[i] = 0;
            } else if (at == '1') {
                cb[i] = 1;
            }

        }

        // System.out.println(CASCII.toString(cb));
        // The message in the file msg1.txt Download msg1.txtwas encoded using SDES.
        // Decrypt it, and find the 10-bit raw key used for its encryption.

        byte key[] = new byte[10];
        byte values[] = new byte[8];
        int count = 0;
        byte translate[] = new byte[8];
        String keynum;
        // System.out.println(" about to go into a loop");

        // for (int j = 0; j < cb.length; j++) {
        // System.out.print(cb[j]);
        // }
        FileWriter fw = new FileWriter("C:\\Users\\chro\\DES\\DESproject\\src\\file.txt");

        for (int keygen = 0; keygen < 1024; keygen++) {
            keynum = Integer.toBinaryString(keygen);
            // System.out.println(keynum);
            key = recombine(keynum);
            // // System.out.println(cb.length);
            count = 0;
            for (int i = 0; i < cb.length - 1; i += 8) {

                for (int j = 0; j < values.length; j++) {
                    values[j] = cb[count];
                    // System.out.print(values[j] + " ");
                    // System.out.println(count);
                    count++;

                }
                translate = object.Decrypt(key, values);
                for (int k = 0; k < 8; k++) {
                    // System.out.print();
                    // fw.write();
                    byte check1 = 1;
                    byte check2 = 0;
                    if (check1 == translate[k]) {
                        fw.write("1");
                    } else if (check2 == translate[k]) {
                        fw.write("0");
                    }
                }

            }
            // System.out.println();

            // // System.out.println(count);
            // for (int k = 0; k < 8; k++) {
            // // // System.out.print();
            // // // fw.write();
            // byte check1 = 1;
            // byte check2 = 0;
            // if (check1 == translate[k]) {
            // fw.write("1");
            // } else if (check2 == translate[k]) {
            // fw.write("0");
            // }
            // }
            // fw.write(" ");
            // fw.write(keynum);
            fw.write("\n");
            // }
            // }

        }

        fw.close();
        Scanner scanner = new Scanner(new File("C:\\Users\\chro\\DES\\DESproject\\src\\file.txt"));
        FileWriter fa = new FileWriter("C:\\Users\\chro\\DES\\DESproject\\src\\file22.txt");
        count = 0;
        while (scanner.hasNextLine()) {

            String c = scanner.nextLine();
            System.out.println(c);
            System.out.println("\n");

            char aw;
            byte cw[] = new byte[c.length()];
            // System.out.println(cb.length());
            for (int i = 0; i < c.length(); i++) {
                aw = c.charAt(i);
                // System.out.println(at);
                if (aw == '0') {
                    cw[i] = 0;
                } else if (aw == '1') {
                    cw[i] = 1;
                }

            }
            // System.out.print(cb.length);
            fa.write(CASCII.toString(cw));
            fa.write("  ");
            fa.write(String.valueOf(count));
            fa.write("\n");

            // System.out.println(CASCII.toString(cw));
            count++;
            // System.out.println(String.valueOf(count));
        }
        scanner.close();
        fa.close();

    }

    public static void cracking2() throws IOException {
        Path fileName = Path.of("C:\\Users\\chro\\DES\\DESproject\\src\\msg2.txt");
        String content = Files.readString(fileName);
        TripleDes tdes = new TripleDes();
        char at;
        byte cb[] = new byte[content.length()];
        // System.out.println(cb.length());
        System.out.println("cracking starting");
        System.out.println("the legnth of con is : " + cb.length);
        for (int i = 0; i < content.length(); i++) {
            at = content.charAt(i);
            // System.out.println(at);
            if (at == '0') {
                cb[i] = 0;
            } else if (at == '1') {
                cb[i] = 1;
            }

        }

        // System.out.println(CASCII.toString(cb));
        // The message in the file msg1.txt Download msg1.txtwas encoded using SDES.
        // Decrypt it, and find the 10-bit raw key used for its encryption.

        byte key1[] = new byte[10];
        byte key2[] = new byte[10];
        byte values[] = new byte[8];
        int count = 0;
        byte translate[] = new byte[8];
        String keynum1;
        String keynum2;
        // System.out.println(" about to go into a loop");

        // for (int j = 0; j < cb.length; j++) {
        // System.out.print(cb[j]);
        // }
        FileWriter fw = new FileWriter("C:\\Users\\chro\\DES\\DESproject\\src\\cracking.txt");

        for (int keygen1 = 0; keygen1 < 1024; keygen1++) {
            keynum1 = Integer.toBinaryString(keygen1);
            // System.out.println(keynum);
            key1 = recombine(keynum1);
            count = 0;
            for (int keygen2 = 0; keygen2 < 1024; keygen2++) {
                keynum2 = Integer.toBinaryString(keygen2);
                // System.out.println(keynum);
                key2 = recombine(keynum2);
                // // System.out.println(cb.length);
                count = 0;
                for (int i = 0; i < cb.length - 1; i += 8) {

                    for (int j = 0; j < values.length; j++) {
                        values[j] = cb[count];
                        // System.out.print(values[j] + " ");
                        // System.out.println(count);
                        count++;

                    }
                    translate = tdes.tdencryption(key1, key2, values);
                    for (int k = 0; k < 8; k++) {
                        // System.out.print();
                        // fw.write();
                        byte check1 = 1;
                        byte check2 = 0;
                        if (check1 == translate[k]) {
                            fw.write("1");
                        } else if (check2 == translate[k]) {
                            fw.write("0");
                        }
                    }

                }
                // System.out.println();

                // // System.out.println(count);
                // for (int k = 0; k < 8; k++) {
                // // // System.out.print();
                // // // fw.write();
                // byte check1 = 1;
                // byte check2 = 0;
                // if (check1 == translate[k]) {
                // fw.write("1");
                // } else if (check2 == translate[k]) {
                // fw.write("0");
                // }
                // }
                // fw.write(" ");
                // fw.write(keynum);
                fw.write("\n");
                // }
                // }

            }
        }

        fw.close();
        Scanner scanner = new Scanner(new File("C:\\Users\\chro\\DES\\DESproject\\src\\cracking.txt"));
        FileWriter fa = new FileWriter("C:\\Users\\chro\\DES\\DESproject\\src\\crackdecrypt.txt");
        int count2 = 0;
        count = -1;
        while (scanner.hasNextLine()) {

            String c = scanner.nextLine();
            // System.out.println(c);
            // System.out.println("\n");

            char aw;
            byte cw[] = new byte[c.length()];
            // System.out.println(cb.length());
            for (int i = 0; i < c.length(); i++) {
                aw = c.charAt(i);
                // System.out.println(at);
                if (aw == '0') {
                    cw[i] = 0;
                } else if (aw == '1') {
                    cw[i] = 1;
                }

            }
            // System.out.print(cb.length);
            fa.write(CASCII.toString(cw));
            fa.write("  ");
            if (count2 % 1024 == 0) {
                count++;
            }
            fa.write(String.valueOf(count % 1024));
            fa.write("   ");
            fa.write(String.valueOf(count2 % 1024));
            fa.write("\n");

            // System.out.println(CASCII.toString(cw));
            count2++;
            // System.out.println(String.valueOf(count));
        }
        scanner.close();
        fa.close();

    }

    public static byte[] recombine(String a) {
        byte binary[] = new byte[10];
        int raylegnth = 10;
        int count = 0;
        char at;
        raylegnth = 10 - a.length();
        for (int i = 0; i < 10; i++)
            if (i < raylegnth) {
                binary[i] = 0;
            } else if (i >= raylegnth) {
                at = a.charAt(count);
                if (at == '0') {
                    binary[i] = 0;
                } else if (at == '1') {
                    binary[i] = 1;
                }
                count++;
            }
        return binary;
    }

}
