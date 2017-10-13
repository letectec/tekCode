package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.SourceFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Source;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *  F2 -- Functions should follow the snake_case convention
 */
public class FunctionNaming {

    private final static Logger LOGGER = LoggerFactory.getLogger(FunctionNaming.class);

    private static List<String> getFunctionPrototypes(List<String> fileContents) {
        List<String> prototypeList = new ArrayList<>();

        if (fileContents == null)
            return new ArrayList<>();

        for (int i = 0; i < fileContents.size(); i++) {
            String line = fileContents.get(i);

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
                    j--;
                } while (line.charAt(0) == '\t' || line.charAt(0) == ' '); //keep going if you encounter blankspace at char nb 0

                if (line != null) {
                    prototypeList.add(line);
                }
            }
        }

        return prototypeList;
    }

    private static String pointerRemoval(String string) {
        int i = 0;
        while (string.charAt(i) == '*')
            i++;
        return string.substring(i);
    }

    private static String getFunctionName(String function) {
        //removing args
        function = function.substring(0, function.indexOf('('));

        //removing function return by splitting with tabs and spaces and getting latest token
        String[] tokens = function.split("\t");
        tokens = tokens[tokens.length - 1].split(" ");

        //removing pointer if there are

        return pointerRemoval(tokens[tokens.length - 1]);
    }

    public static void check(File file) {
        List<String> prototypeList = getFunctionPrototypes(SourceFileReader.readFile(file));

        if (!prototypeList.isEmpty()) {
            for (String prototype : prototypeList) {
                if (!FileNaming.isSnakeCase(getFunctionName(prototype)))
                    MistakePrinter.major("F2 -- function " + getFunctionName(prototype) + " is not in snake_case.", file.getName());
            }
        }

    }
}
