package app.helper;

import java.util.StringTokenizer;

/**
 * Created by Justin on 20/05/2017.
 */
public class MazeHelper {

    public static char[][] getCharArrayFromString(String baseString){
        StringTokenizer tokenizer = new StringTokenizer(baseString,"|");
        char[][] charArray = new char[tokenizer.countTokens()][];
        int rowCOunter = 0;
        while (tokenizer.hasMoreTokens()){
            String arr = tokenizer.nextToken();
            StringTokenizer innerTokenizer = new StringTokenizer(arr.substring(1,arr.length()),",");
            char[] innerArray = new char[innerTokenizer.countTokens()];
            int columnCounter = 0;
            while (innerTokenizer.hasMoreTokens()){
                String add = innerTokenizer.nextToken();
                innerArray[columnCounter] = add.charAt(0);
                columnCounter ++;
            }
            charArray[rowCOunter] = innerArray;
            rowCOunter ++;
        }
        return charArray;
    }
}
