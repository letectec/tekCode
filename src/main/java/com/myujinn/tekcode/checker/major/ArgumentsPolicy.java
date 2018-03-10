package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.rule.Rule;
import com.myujinn.tekcode.parsing.FunctionParser;
import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;

import java.io.File;
import java.util.List;

/**
 *  F5 -- Not more than 4 arguments, should be declared in ANSI C syntax
 */
public class ArgumentsPolicy extends Rule {

    private String getArguments(String functionDeclaration) {
        return functionDeclaration.substring(functionDeclaration.indexOf("("),
                functionDeclaration.lastIndexOf(")") + 1);
    }

    private int countArguments(String arguments) {
        int argumentCount = 0;
        for (int i = 0; i < arguments.length(); i++) {
            if (arguments.charAt(i) == ',')
                argumentCount++;
        }
        return argumentCount;
    }

    public void check(File file) {
        List<String> functionList = FunctionParser.getFunctionPrototypes(SourceFileReader.readFile(file));

        if (functionList == null || functionList.isEmpty())
            return;

        for (String functionDeclaration : functionList) {
            String arguments = getArguments(functionDeclaration);
            if (arguments.contains("()"))
                MistakePrinter.major("F5 -- Function " + FunctionNaming.getFunctionName(functionDeclaration)
                        + " is not declared in the ISO/ANSI C syntax.", file.getName());
            if (countArguments(arguments) >= 4)
                MistakePrinter.major("F5 -- Function " + FunctionNaming.getFunctionName(functionDeclaration)
                        + " shouldn't have more than 4 arguments", file.getName());
        }
    }

}
