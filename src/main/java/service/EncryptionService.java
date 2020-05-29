package service;

import java.util.Base64;

public class EncryptionService {
    private static final EncryptionService encryptionServiceInstance = new EncryptionService();

    private EncryptionService() {}

    public static EncryptionService getInstance() {
        return encryptionServiceInstance;
    }

    public String encrypt(String str) {
        return Base64.getEncoder().withoutPadding().encodeToString(str.getBytes());
    }

    public String decrypt(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);

        return new String(decodedBytes);
    }
}
