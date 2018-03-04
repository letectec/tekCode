package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.rule.Rule;
import com.myujinn.tekcode.parsing.SourceFileReader;

import java.io.*;
import java.util.List;

/**
 *  G1 -- Source files should always start with the header of the school.
 */
public class FileHeader extends Rule {

    public FileHeader() {
        ruleName = this.getClass().getSimpleName();
    }

    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        if (fileContents.size() < 4) {
            MistakePrinter.major("G1 -- Doesn't have the standard header of the school.", file.getName(), 1);
            return;
        }
        String line = fileContents.get(0);

        if (line == null || line.length() != 2 ||line.charAt(0) != '/' || line.charAt(1) != '*') {
            MistakePrinter.major("G1 -- Doesn't have the standard header of the school.", file.getName(),1);
            return;
        }

        line = fileContents.get(1);

        if (line == null || line.length() < 20 || !line.contains("** EPITECH PROJECT")) {
            MistakePrinter.major("G1 -- Doesn't have the standard header of the school.", file.getName(), 2);
        }

    }
}
