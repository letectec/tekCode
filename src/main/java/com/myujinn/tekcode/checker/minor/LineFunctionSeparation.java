package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.checker.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 *  G2 -- One and only one empty line should seperate declaration of functions
 */
public class LineFunctionSeparation extends Rule {

    private final static Logger LOGGER = LoggerFactory.getLogger(LineFunctionSeparation.class);

    public void check(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            boolean inFunction = false;
            boolean checkNextLineIfFunction = false;
            int lineNumber = 1;

            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {

                //we need to check this line if true, if line isn't empty then error
                if (checkNextLineIfFunction && line.length() != 0) {
                    MistakePrinter.minor("G2 -- No empty line after a function", file.getName(), lineNumber);
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

                lineNumber++;
            }

            bufferedReader.close();

        } catch (FileNotFoundException e) {
            LOGGER.error("Couldn't find file " + file.getName());
        } catch (IOException e) {
            LOGGER.error("Couldn't read " + file.getName());
        }
    }
}
