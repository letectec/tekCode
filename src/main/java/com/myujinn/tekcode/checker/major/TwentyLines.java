package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;

import java.io.File;
import java.util.List;

/**
 *  F4 -- A function should not exceed 20 lines
 */
public class TwentyLines {

    public static void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null)
            return;

        boolean inFunction = false;
        int functionSize = 0;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = fileContents.get(i);

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            if (inFunction && line.length() == 1 && line.charAt(0) == '}') {
                inFunction = false;
                functionSize = 0;
            } else if (line.length() == 1 && line.charAt(0) == '{') {
                inFunction = true;
            } else if (inFunction) {
                functionSize++;
            }

            if (functionSize == 21)
                MistakePrinter.major("F4 -- Function exceeding 20 lines.", file.getName(), i + 1);

        }

    }
}
