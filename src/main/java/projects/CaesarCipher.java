package projects;

import datastructures.queues.MyLinkedQueue;
import datastructures.queues.Queue;

import java.io.*;

public class CaesarCipher {
    public static void main(String[] args) {
        Integer[] keys = {1, 3, 1, 4, 5, 2, 0};

        String msg = decodeFileToString(
                "C:\\Users\\sunki\\OneDrive\\Desktop\\secret-msg.txt",
                keys);
        System.out.println(msg);
    }

    /**
     * Encodes a given message my means of Caesar cipher with a set of keys
     * that determines the number of places to shift which can be both
     * positive (to the right) and negative (to the left). Only encodes the
     * letters in the message and passes everything else on as is.
     * @param msg  the {@link String} message to be encoded
     * @param keys the set of keys as an {@link Integer} array
     * @return the encoded message as a {@link String}
     */
    public static String encodeLetters(String msg, Integer[] keys) {
        Queue<Integer> keyQueue = new MyLinkedQueue<>(keys);
        StringBuilder encodedMsg = new StringBuilder();
        //
        for (char c : msg.toCharArray()) {
            Integer key = keyQueue.poll();
            keyQueue.offer(key);
            if (Character.isDigit(c)) {
                encodedMsg.append(c);
            } else if (Character.isLetter(c)) {
                int newChar = c + key;
                if (Character.isUpperCase(c)) {
                    if (newChar < 65)
                        newChar = 97 - (97 - newChar) + 1;
                    else if (newChar > 95)
                        newChar = 65 + (newChar - 65) - 1;

                } else {
                    if (newChar < 97)
                        newChar = 122 - (97 - newChar) + 1;
                    else if (newChar > 122)
                        newChar = 97 + (newChar - 122) - 1;
                }

                encodedMsg.append((char) newChar);
            } else {
                encodedMsg.append(c);
            }
        }
        return encodedMsg.toString();
    }

    /**
     * Decodes a message that has been encoded by Caesar cipher
     *
     * @param msg the encoded message
     * @param keys the set of keys for the message
     * @return a message decoded according to the keys provided as a {@link String}
     */
    public static String decodeLetter(String msg, Integer[] keys) {
        Queue<Integer> keyQueue = new MyLinkedQueue<>(keys);
        StringBuilder decodedMsg = new StringBuilder();
        for (char c : msg.toCharArray()) {
            Integer key = keyQueue.poll();
            keyQueue.offer(key);
            if (Character.isDigit(c))
                decodedMsg.append(c);
            else if (Character.isLetter(c)) {
                int newChar = c - key;
                if (Character.isUpperCase(c)) {
                    if (newChar < 65)
                        newChar = 97 - (65 - newChar) + 1;
                    else if (newChar > 95)
                        newChar = 65 + (newChar - 95) - 1;
                } else {
                    if (newChar < 97)
                        newChar = 122 - (97 - newChar) + 1;
                    else if (newChar > 122)
                        newChar = 97 + (newChar - 122) - 1;
                }
                decodedMsg.append((char) newChar);
            } else {
                decodedMsg.append(c);
            }
        }
        return decodedMsg.toString();
    }

    /**
     * Encodes a given message my means of Caesar cipher with a set of keys
     * that determines the number of places to shift which can be both
     * positive (to the right) and negative (to the left). Encodes all the
     * valid ASCII characters in the given message.
     *
     * @param msg  the message to encode
     * @param keys the keys
     * @return the encoded message
     */
    public static String encode(String msg, Integer[] keys) {
        Queue<Integer> keyQueue = new MyLinkedQueue<>(keys);
        StringBuilder encodedMsg = new StringBuilder();
        for (char c : msg.toCharArray()) {
            Integer key = keyQueue.poll();
            keyQueue.offer(key);
            int newChar = c + key;
            if (newChar < 0) newChar = 127 + newChar + 1;
            else if (newChar > 127) newChar = newChar - 127 - 1;
            encodedMsg.append((char) newChar);
        }
        return encodedMsg.toString();
    }

    /**
     * Decodes a message encoded by Caesar cipher with the keys
     * provided.
     *
     * @param encodedMsg the encoded message
     * @param keys the keys
     * @return the decoded message according to the keys provided
     */
    public static String decode(String encodedMsg, Integer[] keys) {
        Queue<Integer> keyQueue = new MyLinkedQueue<>(keys);
        StringBuilder decodedMsg = new StringBuilder();
        for (char c : encodedMsg.toCharArray()) {
            Integer key = keyQueue.poll();
            keyQueue.offer(key);
            int newChar = c - key;
            if (newChar < 0) newChar = 127 + newChar + 1;
            else if (newChar > 127) newChar = newChar + 127 - 1;
            decodedMsg.append((char) newChar);
        }
        return decodedMsg.toString();
    }

    /**
     * Encodes a string and directly writes it to a
     * designated text file and creates a new file
     * with the designated name if it does not exist.
     *
     * @param msg the message to be encoded
     * @param targetFilePath the file path to the target file
     * @param keys the keys to encode the message with
     */
    public static void encodeStringToFile(
            String msg,
            String targetFilePath,
            Integer[] keys) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(targetFilePath))) {
            writer.write(encode(msg, keys));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read an encoded message from a file and decode it using
     * Caesar cipher then outputs it as a {@link String}
     *
     * @param targetFilePath the path to the encoded file
     * @param keys the keys used to decode the file
     * @return a {@link String} containing the message decoded
     *         according to the keys provided
     */
    public static String decodeFileToString(
            String targetFilePath,
            Integer[] keys) {
        StringBuilder msg = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(targetFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                msg.append(decode(line, keys)).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg.toString();
    }

    /**
     * Encode a message read from a file and write the ecoded message
     * into another file
     *
     * @param inputFilePath the file to read the message from
     * @param outputFilePath the file to write the encoded message to
     * @param keys the keys used to decode the file
     */
    public static void encodeFileToFile(
            String inputFilePath,
            String outputFilePath,
            Integer[] keys) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(
                new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(encode(line, keys) + '\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Decode an encoded message from a file and write the
     * decoded message into another file
     *
     * @param inputFilePath the file path of the file to read the encoded message
     * @param outputFilePath the file path fo the file to write the decoded message to
     * @param keys the keys used to decode the file
     */
    public static void decodeFileToFile(
            String inputFilePath,
            String outputFilePath,
            Integer[] keys) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(decode(line, keys) + '\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
