package superbad.idea.string;

import java.util.ArrayList;
import java.util.List;

public class StringSplit {

    public static List<String> splitString(String input, int splitLenght){
        String[] splits = input.split("\\b");

        StringBuilder sb = new StringBuilder();
        List<String> resultlist = new ArrayList<>();
        boolean addLastChunk = true;
        for (String str : splits){
            sb.append(str);
            addLastChunk = true;
            if (sb.length() >= splitLenght){
                resultlist.add(sb.toString().trim());
                sb = new StringBuilder();
                addLastChunk = false;
            }
        }
        if (addLastChunk){
            resultlist.add(sb.toString().trim());
        }
        return resultlist;
    }

    public static void main(String args[]){
        splitString("ceci est une très longue chaine que j'aimerai bien voir découpée correctement", 18)
        .stream().forEach(System.out::println);
    }
}
