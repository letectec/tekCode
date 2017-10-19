package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  L3 -- Space after a keyword or operator
 */
public class SpaceAfterKeyword {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpaceAfterKeyword.class);

    /*
    * Yes there are missing operators and keywords.
    * I know. You don't need to report it, but this is just to warn people.
    * There can be problems with those I didn't include.
    * For example, the keyword "do" can be flagged for
    * "list->doshit = 0;" and it's not wrong.
    * The other operators shouldn't be that common as variable names and stuff.
    * So it's okay to keep them as to warn the programmer that this is fault.
    * End of PSA.
    */
    private final static List<String> keywordsOperators = Arrays.asList(
            "return", "if", "while", "for", "else", "goto", "switch",  // keywords except sizeof
            "|", ">>", "<<",  // binary and unary
            "=", "+=", "-=", "*=", "/=", "%=", "<<=", ">>=", "&=", "^=", "|=",  // assignement operators
            "==", "!=", ">=", "<=",  // relational operators
            "&&", "||"  //logical operators
    );

    private static void analyzeLine(String source, String pattern, int lineNumber, File file) {
        String line = source;

        while (line.contains(pattern)) {
            int i = line.indexOf(pattern);
            if (i + pattern.length() < line.length() && i - 1 > 0 && (line.charAt(i + pattern.length()) != ' ' && line.charAt(i - 1) != ' '))
                MistakePrinter.minor("L3 -- Should have a space after " + pattern +  " keyword/operator", file.getName(), lineNumber + 1);
            line = line.substring(i + pattern.length());
        }
    }

    //TODO
    public static void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourceFileReader.purifyCStrings(fileContents.get(i));

            for (String keywordsOrOperator : keywordsOperators) {
                analyzeLine(line, keywordsOrOperator, i, file);
            }
        }
    }
}
