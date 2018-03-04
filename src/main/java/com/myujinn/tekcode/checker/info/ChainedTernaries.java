package com.myujinn.tekcode.checker.info;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.checker.major.OneStatement;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;
import com.myujinn.tekcode.rule.Rule;

import java.io.File;
import java.util.List;

/**
 *  C2 -- No nested or chained ternaries.
 */
public class ChainedTernaries extends Rule {

    public ChainedTernaries() {
        ruleName = this.getClass().getName();
    }

    @Override
    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            if (OneStatement.countCharOccurrences(line, '?') > 1
                    && OneStatement.countCharOccurrences(line, ':') > 1)
                MistakePrinter.info("C2 -- nested or chained ternaries.", file.getName(), i);
        }
    }
}
