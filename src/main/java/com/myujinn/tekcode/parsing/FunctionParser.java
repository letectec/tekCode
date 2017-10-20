package com.myujinn.tekcode.parsing;

import java.util.ArrayList;
import java.util.List;

public class FunctionParser {

    public static String removeWhitespaces(String string) {
        int i = 0;
        while (i < string.length()) {
            if (string.charAt(i) != ' ' && string.charAt(i) != '\t')
                break;
            i++;
        }
        return string.substring(i);
    }

    public static List<String> getFunctionPrototypes(List<String> fileContents) {
        List<String> prototypeList = new ArrayList<>();

        if (fileContents == null)
            return new ArrayList<>();

        for (int i = 0; i < fileContents.size(); i++) {
            String line = fileContents.get(i);

            //just in case function declaration is multiple lines
            List<String> functionDeclarationList = new ArrayList<>();

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            //also it's dirty as fuck and it works. it's java what the fuck did you expect
            if (line.length() == 1 && line.charAt(0) == '{') {
                int j = i - 1;
                //try getting earlier lines to get prototype
                do {
                    if (j == 0) {
                        line = null;
                        break;
                    }
                    line = fileContents.get(j);
                    functionDeclarationList.add(line);
                    j--;
                } while (line.charAt(0) == '\t' || line.charAt(0) == ' '); //keep going if you encounter blankspace at char nb 0

                if (line != null) {
                    //reconstruct function declaration
                    String function = "";
                    int k = functionDeclarationList.size() - 1;

                    do {
                        function += removeWhitespaces(functionDeclarationList.get(k));
                        k--;
                    } while (k >= 0);

                    prototypeList.add(function);
                }
            }
        }

        return prototypeList;
    }
}
