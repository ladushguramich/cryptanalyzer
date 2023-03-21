package com.javarush.cryptanalyzer.maisuradze;

public class Cryptanalyzer {
    public static final char[] RusAlphabet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж',
            'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т',
            'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '.', ',','”', '”', '-', ':', '!', '?', ' '};

    public static String encrypt(String text, int key) {
        char[] str = text.toCharArray();
        char[] result = new char[str.length];

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < RusAlphabet.length; j++) {
                if (str[i] == RusAlphabet[j]) {
                    int newAlphabetPosition = (j + key);
                    if (newAlphabetPosition >= RusAlphabet.length)
                        newAlphabetPosition %= RusAlphabet.length;
                    result[i] = RusAlphabet[newAlphabetPosition];
                }
            }
        }
        return String.valueOf(result);
    }

    public static String decrypt(String text, int key) {
        char[] str = text.toCharArray();
        char[] result = new char[str.length];

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; RusAlphabet.length > j; j++) {
                if (str[i] == RusAlphabet[j]) {
                    int newAlphabetPosition = (j - key);
                    newAlphabetPosition = (newAlphabetPosition + RusAlphabet.length) % RusAlphabet.length;
                    result[i] = Character.toLowerCase(RusAlphabet[newAlphabetPosition]);
                }
            }
        }
        return String.valueOf(result);
    }


    public static String decrypt(String text, String checkString) {
        int maxSize = 9;
        for (int i = 1; i < maxSize; i++) {
            String decrypted = Cryptanalyzer.decrypt(text, i);
            if (decrypted.contains(checkString.toLowerCase()))
                return decrypted;
        }
        return "ERROR";
    }
}
