package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;
import com.myujinn.tekcode.rule.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 *  G2 -- One and only one empty line should seperate declaration of functions
 */
public class LineFunctionSeparation extends Rule {

    private final static Logger LOGGER = LoggerFactory.getLogger(LineFunctionSeparation.class);

    public LineFunctionSeparation() {
        ruleName = this.getClass().getSimpleName();
    }

    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        boolean inFunction = false;
        boolean checkNextLineIfFunction = false;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            //we need to check this line if true, if line isn't empty then error
            if (checkNextLineIfFunction && line.length() != 0) {
                MistakePrinter.minor("G2 -- No empty line after a function", file.getName(), i + 1);
                checkNextLineIfFunction = false;
            } else if (checkNextLineIfFunction) {
                checkNextLineIfFunction = false;
            }

            //if at the end of function, check next line
            if (inFunction && line.length() == 1 && line.charAt(0) == '}') {
                checkNextLineIfFunction = true;
                inFunction = false;
            }

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            if (line.length() == 1 && line.charAt(0) == '{') {
                inFunction = true;
            }
        }
    }
}
