package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.rule.Rule;
import com.myujinn.tekcode.parsing.SourceFileReader;

import java.io.File;
import java.util.List;

/**
 *  F3 -- Length of a line shouldn't exceed 80 columns
 */
public class EightyColumns extends Rule {

    private static int charactersToColumns(String string) {
        int columns = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '\t')
                columns += 7;
            columns++;
        }

        return columns;
    }

    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null)
            return;

        boolean inFunction = false;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = fileContents.get(i);

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            if (inFunction && line.length() == 1 && line.charAt(0) == '}') {
                inFunction = false;
            } else if (line.length() == 1 && line.charAt(0) == '{') {
                inFunction = true;
            } else if (inFunction && charactersToColumns(line) > 80) {
                MistakePrinter.major("F3 -- Line is exceeding 80 columns.", file.getName(), i + 1);
            }

        }
    }
}