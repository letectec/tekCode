package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.rule.Rule;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;

import java.io.File;
import java.util.List;

/**
 *  L1 --  A line should correspond to one statement.
 */
public class OneStatement extends Rule {

    public static int patternCounter(String string, String pattern) {
        int counter = 0;
        String newString = string;

        while (newString.contains(pattern)) {
            if (newString.indexOf(pattern) + pattern.length() < newString.length()) {
                newString = newString.substring(newString.indexOf(pattern) + pattern.length());
                counter++;
            }
        }
        return counter;
    }

    public static int countCharOccurrences(String string, char c) {
        int counter = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == c)
                counter++;
        }
        return counter;
    }

    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            //checking for all statement errors
            if ((!line.contains("for") && patternCounter(line, " = ") > 1)
            || (line.contains("malloc") && line.contains("!= NULL")) //we all did it, now it's forbidden :^)
            || (countCharOccurrences(line, ';') > 1 && !line.contains("for")) //#dirtyfixnumber20323
            || (countCharOccurrences(line, ';') > 3 && line.contains("for")) //#dirtyfixnumber20324
            || (line.contains("if (") && line.contains("return ")))
                MistakePrinter.major("L1 -- A line should correspond to only one statement.", file.getName(), i + 1);
        }
    }
}
