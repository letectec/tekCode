package com.myujinn.tekcode.checker.minor;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;
import com.myujinn.tekcode.rule.Rule;

import java.io.File;
import java.util.List;

/**
 *  G4 -- Global variable must be const.
 *
 *  Developer note:
 *  This rule is stupid. I know. But it's checked.
 *  You should not put constants in variables because it pollutes the stack.
 *  Some compilers optimize that, but a macro is always 100% better.
 *  Use a good old macro for that instead in C.
 *  Except in C++ where you should ALWAYS use constexpr.
 */
public class ConstGlobal extends Rule {

    public ConstGlobal() {
        ruleName = this.getClass().getName();
    }

    @Override
    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        boolean inFunction = false;
        boolean isMaybeGlobal = false;
        String globalDeclaration = "";

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.purify(fileContents.get(i));

            //entered a function because first character is a curly boi; a bit lazy but it works since other rules says to
            //put the curly boi on a new line so it will eventually be detected
            if (inFunction && line.length() >= 1 && line.charAt(0) == '}') {
                inFunction = false;
            } else if (line.length() >= 1 && line.charAt(0) == '{') {
                inFunction = true;
            } else if (!inFunction && line.length() > 3 && line.contains(";")) {
                isMaybeGlobal = true;
                globalDeclaration = line;
            } else if (isMaybeGlobal && !(line.length() >= 1 && line.charAt(0) == '{')) {
                if (!globalDeclaration.contains("const"))
                    MistakePrinter.minor("G4 -- global variable must be const.", file.getName(), i);
                isMaybeGlobal = false;
                globalDeclaration = "";
            } else
                isMaybeGlobal = false;
        }
    }
}
