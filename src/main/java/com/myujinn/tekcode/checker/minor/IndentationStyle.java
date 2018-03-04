package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;
import com.myujinn.tekcode.rule.Rule;

import java.io.File;
import java.util.List;

/**
 *  L2 -- Indent style should be tabulations only
 */
public class IndentationStyle extends Rule {

    public IndentationStyle() {
        ruleName = this.getClass().getName();
    }

    private boolean hasBadIndentation(String line) {
        int i = 0;
        while (i < line.length() && line.charAt(i) == '\t')
            i++;
        return i < line.length() && line.charAt(i) == ' ';
    }

    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            if (!line.isEmpty() && hasBadIndentation(line))
                MistakePrinter.minor("L2 -- Indentation should only be tabs.", file.getName(), i + 1);
        }
    }
}

