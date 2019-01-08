package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * The type Highscore manager.
 */
public class HighscoreManager {

    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String key = "%Tnxd}Mc<@n#Z/=.!mGR0N#T90Og$w";
    private final String filePath;
    private int highScore;

    /**
     * Instantiates a new Highscore manager.
     *
     * @param filePath the file path
     */
    public HighscoreManager(String filePath) {
        this.filePath = filePath;
        this.highScore = parseHighScore();
    }

    private int parseHighScore() {
        try {
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            SecretKey secretKey = initializeKey();
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            Path fileLocation = Paths.get(filePath);
            byte[] data = Files.readAllBytes(fileLocation);
            byte[] decodedBytes = Base64.getDecoder().decode(data);
            byte[] scoreBytes = cipher.doFinal(decodedBytes);
            String scoreText = new String(scoreBytes);
            return Integer.parseInt(scoreText);

        } catch (IOException e) {
            return 0;
        } catch (IllegalArgumentException e) {
            File file = new File(filePath);
            boolean success = file.delete();
            return 0;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private SecretKey initializeKey() {
        try {
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            KeySpec keySpec = new DESedeKeySpec(keyBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
            return skf.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets high score.
     *
     * @return the high score
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Sets high score.
     *
     * @param highScore the high score
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
        saveHighScore();
    }

    private void saveHighScore() {
        try {
            FileOutputStream stream = new FileOutputStream(filePath);
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            SecretKey secretKey = initializeKey();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] scoreBytes = Integer.toString(highScore).getBytes(StandardCharsets.UTF_8);
            byte[] encryptedBytes = cipher.doFinal(scoreBytes);
            byte[] encodedBaseBytes = Base64.getEncoder().encode(encryptedBytes);
            stream.write(encodedBaseBytes);
        } catch (NoSuchPaddingException | IOException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
