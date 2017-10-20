package com.myujinn.tekcode.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourcePurifier {

    public static String purify(String line) {
        return purifyComments(purifyStrings(line));
    }

    public static String purifyComments(String line) {
        line = line.replaceAll("(\\/\\/.*)|\"(?:\\\\\"|.)*?\"", "");
        line = line.replaceAll("(\\/\\*.*)|\"(?:\\\\\"|.)*?\"", "");
        line = line.replaceAll("(\\*\\*.*)|\"(?:\\\\\"|.)*?\"", "");
        line = line.replaceAll("(\\*\\/.*)|\"(?:\\\\\"|.)*?\"", "");
        return line;
    }

    public static int indexOfRegex(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    public static String purifyStrings(String line) {
        return line.replaceAll("\"[^\"]*\"", "");
    }

}
