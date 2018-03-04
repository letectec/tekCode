package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;
import com.myujinn.tekcode.rule.Rule;

import java.io.File;
import java.util.List;

/**
 *  C3 -- No goto keyword
 */
public class GotoSpaghettiCode extends Rule {

    public GotoSpaghettiCode() {
        ruleName = this.getClass().getName();
    }

    @Override
    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            if (line.contains("goto"))
                MistakePrinter.minor("C3 -- goto keyword.", file.getName(), i + 1);
        }
    }
}
