package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.FunctionParser;
import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;

import java.io.File;
import java.util.List;

/**
 *  F5 -- Not more than 4 arguments, should be declared in ANSI C syntax
 */
public class ArgumentsPolicy {

    public static void check(File file) {
        List<String> functionList = FunctionParser.getFunctionPrototypes(SourceFileReader.readFile(file));

        if (functionList == null || functionList.isEmpty())
            return;

        for (String functionDeclaration : functionList) {
            String arguments = functionDeclaration.substring(functionDeclaration.indexOf("("), functionDeclaration.lastIndexOf(")") + 1);
            if (arguments.contains("()"))
                MistakePrinter.major("F5 -- Function " + functionDeclaration + " is not declared in the ISO/ANSI C syntax.", file.getName());
            System.out.println(functionDeclaration);
        }
    }

}
