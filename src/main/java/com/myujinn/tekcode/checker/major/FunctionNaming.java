package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.checker.Rule;
import com.myujinn.tekcode.parsing.FunctionParser;
import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 *  F2 -- Functions should follow the snake_case convention
 */
public class FunctionNaming extends Rule {

    private static String purifyLineSeparator(String string) {
        return string.replaceAll("\n","");
    }

    private static String pointerRemoval(String string) {
        int i = 0;

        while (string.charAt(i) == '*')
            i++;

        return string.substring(i);
    }

    public static String getFunctionName(String function) {
        //removing args
        function = function.substring(0, function.indexOf('('));

        //removing function return by splitting with tabs and spaces and getting latest token
        String[] tokens = function.split("\t");
        tokens = tokens[tokens.length - 1].split(" ");

        //removing pointer if there are
        return pointerRemoval(tokens[tokens.length - 1]);
    }

    public void check(File file) {
        List<String> prototypeList = FunctionParser.getFunctionPrototypes(SourceFileReader.readFile(file));

        if (!prototypeList.isEmpty()) {
            for (String prototype : prototypeList) {
                prototype = purifyLineSeparator(prototype);
                if (!FileNaming.isSnakeCase(getFunctionName(prototype)))
                    MistakePrinter.major("F2 -- function " + getFunctionName(prototype) + " is not in snake_case.", file.getName());
            }
        }

    }
}
