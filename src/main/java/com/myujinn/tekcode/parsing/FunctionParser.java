package com.myujinn.tekcode.parsing;

import java.util.ArrayList;
import java.util.List;

public class FunctionParser {

    //probably the worst method in this code.
    //i'm not proud of that.
    //but the thing that i learned in my internship is that if it works for now, we good.
    //feel free to improve it, i just don't have the time to make it good.
    public static List<String> getFunctionPrototypes(List<String> fileContents) {
        List<String> prototypeList = new ArrayList<>();

        if (fileContents == null)
            return new ArrayList<>();

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            //just in case function declaration is multiple lines
            List<String> functionDeclarationList = new ArrayList<>();

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            //also it's dirty as fuck and it works. it's java what the fuck did you expect
            if (line.contains("{")) {
                functionDeclarationList.add(line);

                //try getting earlier lines to get prototype if line is only a curly boi
                if (!line.contains("(")) {
                    int j = i - 1;

                    do {
                        if (j == 0) {
                            line = null;
                            break;
                        }
                        line = fileContents.get(j);
                        functionDeclarationList.add(line);
                        j--;
                    }
                    while (!line.isEmpty() && (line.charAt(0) == '\t' || line.charAt(0) == ' ')); //keep going if you encounter blankspace at char nb 0

                    //go to end of function to not trip function detector
                    while (i < fileContents.size()) {
                        if (line != null && !line.isEmpty() && line.charAt(0) == '}')
                            break;
                        line = fileContents.get(i++);
                    }
                }
                //reconstruct function declaration
                String function = "";
                int k = functionDeclarationList.size() - 1;

                do {
                    function += SourcePurifier.removeWhitespaces(functionDeclarationList.get(k)) + "\n";
                    k--;
                } while (k >= 0);

                //to avoid global declarations of arrays
                if (function.contains("(") && function.contains(")"))
                    prototypeList.add(function);
            }
        }

        return prototypeList;
    }
}
