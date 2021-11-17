package br.com.domain.security;

import br.com.domain.exception.errors.ErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Configuration
public class CryptoUtil {

    private static Cipher cipherEncrypt;
    private static Cipher cipherDecrypt;

    CryptoUtil() throws ErrorException {
        try {
            final Properties props = new Properties();
            final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("crypto.properties");
            props.load(inputStream);

            final String secretKey = props.getProperty("crypto.secret-key");
            final String salt = props.getProperty("crypto.salt");

            final KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            final SecretKey tmp = factory.generateSecret(spec);
            final SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            final byte[] iv = {3, 10, 9, 1, 12, 6, 8, 19, 21, 4, 9, 21, 11, 7, 2, 8};
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            cipherEncrypt = getCipher();
            Objects.requireNonNull(cipherEncrypt).init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            cipherDecrypt = getCipher();
            Objects.requireNonNull(cipherDecrypt).init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao carregar o construtor CryptoAES256", e);
        }
    }

    private static Cipher getCipher() throws ErrorException {
        try {
            return Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao obter o Cipher", e);
        }
    }

    public static String encrypt(String plainText) throws ErrorException {
        try {
            return Base64.getEncoder().encodeToString(cipherEncrypt.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao encriptar a senha " + plainText, e);
        }
    }

    public static String decrypt(String hash) throws ErrorException {
        try {
            return new String(cipherDecrypt.doFinal(Base64.getDecoder().decode(hash)));
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao decriptar a senha " + hash, e);
        }
    }

    public static boolean check(String plainText, String hash) throws ErrorException {
        return plainText != null && !plainText.isEmpty() && hash != null && !hash.isEmpty() && plainText.equals(decrypt(hash));
    }

}