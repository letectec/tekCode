package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class CurlyBrackets {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurlyBrackets.class);

    public static void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        boolean inFunction = false;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            if (inFunction && line.length() == 1 && line.charAt(0) == '}') {
                inFunction = false;
            } else if (line.length() == 1 && line.charAt(0) == '{') {
                inFunction = true;
            } else if (!inFunction && )
            //dirty as fuck but hey who the hell is going to put "//" in a fucking C string,
            //like you have to be retarded, don't do it
        }
    }
}
