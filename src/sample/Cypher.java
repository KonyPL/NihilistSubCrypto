package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cypher {
    private List<Character> alphabet; //= new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
    private ArrayList<ArrayList<Character>> matrix; // = new ArrayList<ArrayList<Character>>(5);
    private ArrayList<ArrayList<Integer>> encryptedMatrix; // = new ArrayList<ArrayList<Integer>>();

    private String formatText(String text) {
        text = text.replaceAll("\\p{Punct}", "");
        text = text.toUpperCase();
        text = text.replace('J','I');
        text = text.replace('Ą', 'A');
        text = text.replace('Ć', 'C');
        text = text.replace('Ę', 'E');
        text = text.replace('Ł', 'L');
        text = text.replace('Ń', 'N');
        text = text.replace('Ó', 'O');
        text = text.replace('Ś', 'S');
        text = text.replace('Ż', 'Z');
        text = text.replace('Ź', 'Z');
        text = text.replace(" ","");
        return text;
    }

    private Integer getNumberOfLetter(Character letter) {
        Integer number = 0;
        for(ArrayList<Character> row : matrix) {
            int index = row.indexOf(letter);
            if(index != -1) {
                number = 10 * (matrix.indexOf(row) + 1) + index + 1;
                return number;
            }
        }
        return -1;
    }

    private Character getCharacterOfNumber(Integer number) {
        Character character = 0;
        character = matrix.get(number/10 - 1).get(number%10 - 1);
        return character;
    }

    private void fillMatrix(String key1) {
        //Initialize lists and Arrays
        alphabet = new ArrayList<>(Arrays.asList('A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
        matrix =  new ArrayList<ArrayList<Character>>(5);

        //Change J -> I and all non english symbols to their counterparts
        key1 = formatText(key1);

        String strippedkey = "";

        for(int i=0; i<key1.length(); i++) {
            if(strippedkey.indexOf(key1.charAt(i)) == -1) {
                strippedkey+=key1.charAt(i);
            }
        }

        Character forRemoval = 0;
        for(int i=0; i < strippedkey.length(); i++) {
            forRemoval = strippedkey.charAt(i);
            alphabet.remove(forRemoval);
        }

        for(int i = 0; i < 5; i++) {
            ArrayList<Character> arr = new ArrayList<>(5);
            for(int j = 0; j < 5; j++) {
                if(i * 5 + j < strippedkey.length()) {
                    arr.add(strippedkey.charAt(i * 5 + j));
                } else {
                    arr.add(alphabet.get((i * 5 + j)-strippedkey.length()));
                }
            }

            matrix.add(arr);
            System.out.println(arr);
        }
    }

    public String encrypt(String key1, String key2, String text) {
        fillMatrix(key1);

        if(key2.length() < 1)
            key2 = "DEFAULT";

        //Change J -> I and all non english symbols to their counterparts
        key2 = formatText(key2);
        text = formatText(text);
        //Prevent errors by forcing key2 to have at least 2 letters
        if(key2.length() == 1)
            key2+=key2;

        String encryptedText = "";
        int widthOfEncryptedMatrix = key2.length();
        int heightOfEncryptedMatrix = text.length() / key2.length() + 1;
        int lastRow = text.length() % key2.length();
        int number = 0;

        if ( lastRow != 0)
            heightOfEncryptedMatrix++;
        else
            lastRow = -1;
        encryptedMatrix = new ArrayList<ArrayList<Integer>>(heightOfEncryptedMatrix);
        for (int i = 0; i < heightOfEncryptedMatrix; i++) {
            ArrayList<Integer> arr = new ArrayList<>(widthOfEncryptedMatrix);
            for(int j = 0; j < widthOfEncryptedMatrix; j++) {
                if(text.length() != key2.length())
                    if(i == heightOfEncryptedMatrix-1 && j == lastRow)
                        break;

                if(i == 0) {
                    arr.add(getNumberOfLetter(key2.charAt(j)));
                } else {
                    number = encryptedMatrix.get(0).get(j) + getNumberOfLetter(text.charAt((i - 1) * widthOfEncryptedMatrix + j));
                    if(number > 99)
                        number -= 100;
                    arr.add(number);
                }
            }
            System.out.println(arr);
            encryptedMatrix.add(arr);

            for(int j = 0; j < widthOfEncryptedMatrix; j++) {
                if(text.length() != key2.length())
                    if(i == heightOfEncryptedMatrix-1 && j == lastRow)
                        break;
                if(i == 0) {
                    break;
                } else {
                    number = encryptedMatrix.get(i).get(j);
                    if(number < 10) {
                        encryptedText += "0" + number;
                    } else {
                        encryptedText += number;
                    }
                }
            }
        }
        return encryptedText;
    }

    public String decrypt(String key1, String key2, String encryptedText) {
        fillMatrix(key1);

        if(key2.length() < 1)
            key2 = "DEFAULT";

        //Change J -> I and all non english symbols to their counterparts
        key2 = formatText(key2);
        //Prevent errors by forcing key2 to have at least 2 letters
        if(key2.length() == 1)
            key2+=key2;

        String decryptedText = "";
        int widthOfEncryptedMatrix = key2.length();
        int heightOfEncryptedMatrix = (encryptedText.length()/2) / key2.length() + 1;
        int lastRow = (encryptedText.length()/2) % key2.length();
        int number = 0;

        if ( lastRow != 0)
            heightOfEncryptedMatrix++;
        else
            lastRow = -1;
        encryptedMatrix = new ArrayList<ArrayList<Integer>>(heightOfEncryptedMatrix);
        for (int i = 0; i < heightOfEncryptedMatrix; i++) {
            ArrayList<Integer> arr = new ArrayList<>(widthOfEncryptedMatrix);
            for(int j = 0; j < widthOfEncryptedMatrix; j++) {
                if(encryptedText.length() != key2.length()*2)
                    if(i == heightOfEncryptedMatrix-1 && j == lastRow)
                        break;

                if(i == 0) {
                    arr.add(getNumberOfLetter(key2.charAt(j)));
                } else {
                    number = Integer.parseInt(("" + encryptedText.charAt((i - 1) * widthOfEncryptedMatrix * 2 + 2 * j) + encryptedText.charAt((i - 1) * widthOfEncryptedMatrix * 2 + 2 * j + 1)));
                    if(number < 11)
                        number += 100;
                    arr.add(number);
                }
            }
            System.out.println(arr);
            encryptedMatrix.add(arr);

            for(int j = 0; j < widthOfEncryptedMatrix; j++) {
                if(encryptedText.length() != key2.length()*2)
                    if(i == heightOfEncryptedMatrix-1 && j == lastRow)
                        break;
                if(i == 0) {
                    break;
                } else {
                    number = encryptedMatrix.get(i).get(j) - encryptedMatrix.get(0).get(j);
                    decryptedText += getCharacterOfNumber(number);
                }
            }
        }
        return decryptedText;
    }
}
