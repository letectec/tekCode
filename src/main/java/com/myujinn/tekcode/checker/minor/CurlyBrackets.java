package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourcePurifier;
import com.myujinn.tekcode.rule.Rule;
import com.myujinn.tekcode.parsing.FunctionParser;
import com.myujinn.tekcode.parsing.SourceFileReader;

import java.io.File;
import java.util.List;

/**
 *  L4 -- Curly brackets must be at the end of their line except for functions where they must be alone on their line.
 *  Closing curly brackets should always be alone.
 */
public class CurlyBrackets extends Rule {

    public CurlyBrackets() {
        ruleName = this.getClass().getSimpleName();
    }

    private static void checkFunctionDeclaration(List<String> fileContents, File file) {
        List<String> functionPrototypes = FunctionParser.getFunctionPrototypes(fileContents);

        for (String functionDeclaration : functionPrototypes) {
            if (!functionDeclaration.contains("{\n")
                    || functionDeclaration.charAt(functionDeclaration.lastIndexOf("{") - 1) != '\n') {
                MistakePrinter.minor("L4 -- Opening curly bracket should be alone on their line after function declaration", file.getName());
            }

        }
    }

    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        checkFunctionDeclaration(fileContents, file);

        boolean inFunction = false;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.removeWhitespaces(SourcePurifier.purify(fileContents.get(i)));

            //closing brackets should always always be alone on their line.
            if (inFunction && line.contains("}") && !line.contains("else") && !line.contains("while") && line.length() != 1)
                MistakePrinter.minor("L4 -- Closing curly bracket should always be alone on their line", file.getName(), i + 1);

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            if (inFunction && line.length() == 1 && line.charAt(0) == '}') {
                inFunction = false;
            } else if (line.length() == 1 && line.charAt(0) == '{') {
                inFunction = true;
            }
        }
    }
}
