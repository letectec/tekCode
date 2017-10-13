package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.FunctionParser;
import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  F2 -- Functions should follow the snake_case convention
 */
public class FunctionNaming {

    private final static Logger LOGGER = LoggerFactory.getLogger(FunctionNaming.class);

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
        List<String> prototypeList = FunctionParser.getFunctionPrototypes(SourceFileReader.readFile(file));

        if (!prototypeList.isEmpty()) {
            for (String prototype : prototypeList) {
                if (!FileNaming.isSnakeCase(getFunctionName(prototype)))
                    MistakePrinter.major("F2 -- function " + getFunctionName(prototype) + " is not in snake_case.", file.getName());
            }
        }

    }
}
