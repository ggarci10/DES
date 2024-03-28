class TripleDes extends Des {
    // public static void main(String[] args) {

    // byte rawkey[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    // byte rawkey2[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 };
    // byte plaintext[] = { 1, 0, 1, 0, 1, 0, 1, 0 };

    // byte ciphertext[];
    // ciphertext = tencryption(plaintext, rawkey, rawkey2);
    // for (int i = 0; i < ciphertext.length; i++) {
    // System.out.print(ciphertext[i]);
    // }
    // }

    public byte[] tencryption(byte[] key1, byte[] key2, byte[] plain) {
        byte[] converting = new byte[10];
        converting = Encrypt(key1, plain);
        converting = Decrypt(key2, converting);
        converting = Encrypt(key1, converting);
        return converting;

    }

    public byte[] tdencryption(byte[] key1, byte[] key2, byte[] plain) {
        byte[] converting = new byte[10];
        converting = Decrypt(key1, plain);
        converting = Encrypt(key2, converting);
        converting = Decrypt(key1, converting);
        return converting;

    }
}