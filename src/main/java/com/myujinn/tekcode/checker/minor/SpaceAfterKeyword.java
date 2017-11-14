package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.checker.Rule;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 *  L3 -- Space after a keyword or operator
 */
public class SpaceAfterKeyword extends Rule {

    /*
    * Yes there are missing operators and keywords.
    * I know. You don't need to report it, but this is just to warn people.
    * There can be problems with those I didn't include.
    * For example, the keyword "/" can be flagged for
    * "#include <sys/linux.h>" and it's not wrong.
    * The other operators shouldn't be that common as variable names and stuff.
    * So it's okay to keep them as to warn the programmer that this is fault.
    * End of PSA.
    */
    private final static List<String> keywordsOperators = Arrays.asList(
            "return", "while", "if", "for", "else", "goto", "switch", "break", "case", "do",  // keywords except sizeof
            "\\+", "-", "\\*", "\\%",  // arithmetic operators
            "&", "\\^", "\\|", "~", ">>", "<<",  // binary and unary
            "=", "\\+=", "-=", "\\*=", "/=", "%=", "<<=", ">>=", "&=", "\\^=", "\\|=",  // assignement operators
            "==", "!=", ">=", "<=", "<", ">", // relational operators
            "&&", "\\|\\|",  //logical operators
            "\\?", ":",  // ternary operators
            "\\," // it's an operator trust me
    );

    private static boolean hasSpaceAfterPattern(String line, String pattern, int index) {
        return index + pattern.length() < line.length() && line.charAt(index + pattern.length()) != ' ';
    }

    private static void analyzeLine(String source, String pattern, int lineNumber, File file) {
        String line = source;

        while (true) {
            int i = SourcePurifier.indexOfRegex(line, "\\b(" + pattern + ")\\b");
            if (i == -1)
                break;
            if (hasSpaceAfterPattern(line, pattern, i))
                MistakePrinter.minor("L3 -- Should have a space after " + pattern +  " keyword/operator", file.getName(), lineNumber + 1);
            line = line.substring(i + pattern.length());
        }
    }

    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            for (String keywordsOrOperator : keywordsOperators) {
                analyzeLine(line, keywordsOrOperator, i, file);
            }
        }
    }
}
