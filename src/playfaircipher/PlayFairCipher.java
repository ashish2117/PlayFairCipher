/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playfaircipher;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author user
 */
public class PlayFairCipher {

    final char key[][];

    /**
     * @param args the command line arguments
     */
    public PlayFairCipher(String s) {
        key = this.fillKey(removeDuplicates(s));
    }

    public static void main(String[] args) {
        PlayFairCipher p = new PlayFairCipher("CRYPTOGRAPHY");
        System.out.println((p.encode("MYNAMEISJROHIT")));
    }

    private char[][] fillKey(String keyword) {
        char key[][] = new char[5][5];
        int n = keyword.length();
        int k = 0, alpha = 65;
        for (int i = 1; i <= 5;) {
            for (int j = 1; j <= 5;) {
                if (k < n) {
                    key[i - 1][j - 1] = keyword.charAt(k);
                    System.out.print(key[i - 1][j - 1] + " ");
                    j++;
                    k++;
                } else {
                    if (keyword.indexOf((char) alpha) == -1) {
                        key[i - 1][j - 1] = (char) alpha;
                        System.out.print(key[i - 1][j - 1] + " ");
                        j++;
                        if ((char) alpha == 'I') {
                            alpha++;
                        }
                    }
                    alpha++;
                }

            }
            i++;
            System.out.println();
        }
        return key;
    }

    private static String removeDuplicates(String string) {

        char[] chars = string.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

    public String decode(String cipher) {

        StringBuilder res = new StringBuilder();
        int n = cipher.length();
        int i = 0;
        while (i < n) {
            if (i != n - 1) {
                res.append(findMatchInKeyDecode(cipher.substring(i, i + 2)));
            }
            i += 2;
        }
        return res.toString();

    }

    public String encode(String plaintext) {
        StringBuilder res = new StringBuilder();
        int n = plaintext.length();
        int i = 0;
        while (i < n) {

            if (i != n - 1) {
                res.append(findMatchInKeyEncode(plaintext.substring(i, i + 2)));
            } else {
                res.append(findMatchInKeyEncode(plaintext.charAt(i) + "X"));
            }
            i += 2;
        }
        return res.toString();
    }

    private String findMatchInKeyDecode(String input) {
        StringBuilder s = new StringBuilder();
        int found = 0;
        char a = input.charAt(0);
        char b = input.charAt(1);
        int p[] = new int[2], q[] = new int[2];
        label:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (key[i][j] == a) {
                    p[0] = i;
                    p[1] = j;
                    found++;
                } else if (key[i][j] == b) {
                    q[0] = i;
                    q[1] = j;
                    found++;
                }
                if (found == 2) {
                    break label;
                }
            }
        }
        if (p[0] == q[0]) {
            s.append(key[p[0]][(p[1] + 4) % 5]);
            s.append(key[q[0]][(q[1] + 4) % 5]);

        } else if (p[1] == q[1]) {
            s.append(key[(p[0] + 4) % 5][p[1]]);
            s.append(key[(q[0] + 4) % 5][q[1]]);
        } else {
            s.append(key[p[0]][q[1]]);
            s.append(key[q[0]][p[1]]);

        }
        return s.toString();
    }

    private String findMatchInKeyEncode(String input) {
        StringBuilder s = new StringBuilder();
        int found = 0;
        char a = input.charAt(0);
        char b = input.charAt(1);
        int p[] = new int[2], q[] = new int[2];
        label:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (key[i][j] == a) {
                    p[0] = i;
                    p[1] = j;
                    found++;
                } else if (key[i][j] == b) {
                    q[0] = i;
                    q[1] = j;
                    found++;
                }
                if (found == 2) {
                    break label;
                }
            }
        }
        if (p[0] == q[0]) {
            s.append(key[p[0]][(p[1] + 1) % 5]);
            s.append(key[q[0]][(q[1] + 1) % 5]);

        } else if (p[1] == q[1]) {
            s.append(key[(p[0] + 1) % 5][p[1]]);
            s.append(key[(q[0] + 1) % 5][q[1]]);
        } else {
            s.append(key[p[0]][q[1]]);
            s.append(key[q[0]][p[1]]);

        }
        return s.toString();
    }

}
