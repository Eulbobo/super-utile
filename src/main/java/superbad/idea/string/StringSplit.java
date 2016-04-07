package superbad.idea.string;

import java.util.ArrayList;
import java.util.List;

public class StringSplit {

    public static List<String> splitString(String input, int splitLenght){
        String[] splits = input.split("\\b");

        StringBuilder sb = new StringBuilder();
        List<String> resultlist = new ArrayList<>();
        for (String str : splits){
            if (sb.length() + str.length() >= splitLenght){
                resultlist.add(sb.toString().trim());
                sb = new StringBuilder();
            }
            sb.append(str);
        }
        resultlist.add(sb.toString().trim());
        return resultlist;
    }

    public static void main(String args[]){
        splitString("ceci est une tr�s longue chaine que j'aimerai bien voir d�coup�e correctement", 18)
        .stream().forEach(System.out::println);
    }
}
