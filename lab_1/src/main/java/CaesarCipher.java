class CaesarCipher {
    String encrypt(String text, int s) {
        var result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);

                int negatedChar = flipBits((int) ch);
                ch = (char) negatedChar;
                result.append(ch);
            } else if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);
                int negatedChar = flipBits((int) ch);
                ch = (char) negatedChar;
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i)));
                result.append(ch);
            }
        }
        return result.toString();
    }

    String decrypt(String text, int key) {
        var result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            //char charToBeChecked = (int) text.charAt(i)- 65;
            char negatedChar = (char) flipBits((int) text.charAt(i));
            if (Character.isUpperCase(negatedChar)) {
                int chara = negatedChar - key;
                if (chara < 65) {
                    chara += 26;
                } else if (chara > 90) {
                    chara -= 27;
                }
                //char ch = (char) (((int) negatedChar - key - 65) % 26 + 65);
                result.append((char) chara);
            } else if (Character.isLowerCase(negatedChar)) {
                int chara = negatedChar - key;
                if (chara < 97) {
                    chara += 26;
                } else if (chara > 122) {
                    chara -= 27;
                }
                //char ch = (char) (((int) negatedChar - key - 97) % 26 + 97);
                result.append((char) chara);
            } else {
                char ch = (char) (((int) text.charAt(i)));
                result.append(ch);
            }
        }
        return result.toString();

    }

    private int flipBits(int n) {
        return ~n;
    }
} 