package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.rule.Rule;
import com.myujinn.tekcode.parsing.FunctionParser;
import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;

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

    private boolean isSpacesInDeclaration(String functionPrototype) {
        String functionName = functionPrototype.substring(0, functionPrototype.indexOf('('));
        return functionName.charAt(functionName.length() - 1) == ' '
                || functionName.charAt(functionName.length() - 1) == '\t';
    }

    private String getReturnType(String functionPrototype) {
        //removing args
        functionPrototype = functionPrototype.substring(0, functionPrototype.indexOf('('));

        //removing function return by splitting with tabs and spaces and getting latest token
        String[] tokens = functionPrototype.split("\t");
        tokens = tokens[0].split(" ");

        int i = 0;
        while ("static".equals(tokens[i]) || "inline".equals(tokens[i]))
            i++;
        return tokens[i];
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
                if (isSpacesInDeclaration(prototype))
                    MistakePrinter.major("F2 -- function declaration is not compliant.", file.getName());
                if (!FileNaming.isSnakeCase(getReturnType(prototype)))
                    MistakePrinter.major("F2 -- return value is not in snake_case.", file.getName());
                String functionName = getFunctionName(prototype);
                if (!FileNaming.isSnakeCase(functionName))
                    MistakePrinter.major("F2 -- function " + functionName + " is not in snake_case.", file.getName());
            }
        }

    }
}
