package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;

import java.io.File;
import java.util.List;

/**
 *  F6 -- No comments inside a function
 */
public class NoCommentsInFunctions {

    public static void check(File file) {
        List<String> fileContent = SourceFileReader.readFile(file);

        if (fileContent == null || fileContent.isEmpty())
            return;

        boolean inFunction = false;

        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            if (inFunction && line.length() == 1 && line.charAt(0) == '}') {
                inFunction = false;
            } else if (line.length() == 1 && line.charAt(0) == '{') {
                inFunction = true;
            } else if (inFunction && (line.contains("/*") || line.contains("//"))) {
                MistakePrinter.minor("F6 -- No comments inside a function body.", file.getName(), i + 1);
            }
            //dirty as fuck but hey who the hell is going to put "//" in a fucking C string,
            //like you have to be retarded, don't do it
        }
    }
}
