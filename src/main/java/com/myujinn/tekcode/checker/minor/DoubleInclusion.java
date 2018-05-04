package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;
import com.myujinn.tekcode.rule.Rule;

import java.io.File;
import java.util.List;

/**
 *  H2 -- Headers should be protected from double inclusion
 */
public class DoubleInclusion extends Rule {

    @Override
    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        boolean isIfNDef = false;

        for (String fileContent : fileContents) {
            String line = SourcePurifier.purify(fileContent);

            if (line.contains("#ifndef"))
                isIfNDef = true;
            else if (isIfNDef && line.contains("#") && line.contains("define"))
                return;
            else
                isIfNDef = false;
            if (line.contains("#pragma") && line.contains("once"))
                return;
        }
        MistakePrinter.minor("H2 -- Header file should be protected from double inclusion.", file.getName());
    }
}
