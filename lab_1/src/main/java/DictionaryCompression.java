import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class DictionaryCompression {

    String compression(String text) {
        List<Character> chars = text.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        var distinctCharsList = chars.stream().distinct().collect(Collectors.toList());
        var convertedText = new StringBuilder();
        int x = distinctCharsList.size();
        int n = BigDecimal.valueOf(Math.log(x) / Math.log(2)).setScale(0, RoundingMode.UP).intValue();
        int overBits = (8 - (3 + text.length() * n) % 8) % 8;
        var dictionary = createDictionary(distinctCharsList, n);
        String firstBits = Integer.toBinaryString(overBits);
        var tempBuilder = new StringBuilder();
        tempBuilder.append(firstBits);
        convertedText.append((char) x);
        convertedText.append(distinctCharsList.toString().substring(1, 3 * distinctCharsList.size() - 1).replaceAll(", ", ""));
        chars.forEach(character -> {
            var characterInBits = dictionary.get(character);
            tempBuilder.append(characterInBits);
            if (tempBuilder.length() >= 8) {
                Character encodedCharacter = (char) Integer.parseInt(tempBuilder.substring(0, 8), 2);
                System.out.println(encodedCharacter);
                tempBuilder.delete(0, 8);
                convertedText.append(encodedCharacter);
            }
        });
        if (tempBuilder.length() > 8) {
            do {
                //Character encodedCharacter = (char) Integer.parseInt(tempBuilder.substring(0, 8), 2);
                int bitsIntoInteger = Integer.parseInt(tempBuilder.substring(0, 8), 2);
                Character c = (char) bitsIntoInteger;
                tempBuilder.delete(0, 8);
                convertedText.append(c);
            } while (tempBuilder.length() >= 8);
        }
        if (tempBuilder.length() > 0) {
            String paddedString = StringUtils.rightPad(tempBuilder.toString(), 8, "1");
            System.out.println(paddedString);
            int bitsIntoInteger = Integer.parseInt(paddedString, 2);
            Character encodedCharacter = (char) bitsIntoInteger;
            convertedText.append(encodedCharacter);
        }
        return convertedText.toString();
    }

    private Map<Character, String> createDictionary(List<Character> characters, int n) {
        var wrapper = new Object() {
            int inc = 0;
        };
        Map<Character, String> mapOfCharacters = new HashMap<>();
        characters.forEach(character -> {
            String key = Integer.toBinaryString(wrapper.inc);
            String paddedString = StringUtils.leftPad(key, n, "0");
            mapOfCharacters.put(character, paddedString);
            wrapper.inc++;

        });
        return mapOfCharacters;
    }

}
