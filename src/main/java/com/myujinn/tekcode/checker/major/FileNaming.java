package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;

import java.io.File;

/**
 *  O4 - Checks the snake case convention on file names
 */
public class FileNaming {

    private static String removeExtension(File file) {
        String fileName = file.getName();
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }
        return fileName;
    }

    public static void check(File file) {
        final String fileName = removeExtension(file);

        if (!fileName.equals(fileName.toLowerCase())) {
            MistakePrinter.major(file.getName() + " is not following the snake_case convention.");
            return;
        }
    }
}
