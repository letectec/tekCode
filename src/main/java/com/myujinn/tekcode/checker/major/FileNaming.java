package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;

import java.io.File;

/**
 *  O4 - snake_case convention on file names.
 */
public class FileNaming {

    private static String removeExtension(File file) {
        String fileName = file.getName();
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            return fileName.substring(0, pos);
        }
        return fileName;
    }

    public static boolean isSnakeCase(String string) {
        // checks uppercase letters
        if (!string.equals(string.toLowerCase()))
            return false;

        // checks special chars, didn't use regex cuz too slow and not understandable code, might be
        // dirty code but at least it's readable and reliable. feel free to improve here
        final char[] chars = string.toCharArray();
        for (char c : chars) {
            if (!Character.isLetterOrDigit(c) && c != '_')
                return false;
        }
        return true;
    }

    public static void check(File file) {
        if (!isSnakeCase(removeExtension(file))) {
            MistakePrinter.major("O4 -- File name is not following the snake_case convention.", file.getName());
        }
    }
}
