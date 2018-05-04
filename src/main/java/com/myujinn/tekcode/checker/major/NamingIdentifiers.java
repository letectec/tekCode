package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;
import com.myujinn.tekcode.parsing.SourceFileReader;
import com.myujinn.tekcode.parsing.SourcePurifier;
import com.myujinn.tekcode.rule.Rule;

import java.io.File;
import java.util.List;

/**
 *  V1 -- All identifiers should be snake_case. Typedefs should end with "_t". Macros/constants should be UPPER_CASE.
 */
public class NamingIdentifiers extends Rule {

    private final String ruleErrorTypedef = "V1 -- Typedefs should end with \"_t\".";
    private final String ruleErrorMacro = "V1 -- Macros should be in screaming SNAKE_CASE.";

    public void check(File file) {
        List<String> fileContents = SourceFileReader.readFile(file);

        if (fileContents == null || fileContents.isEmpty())
            return;

        for (int i = 0; i < fileContents.size(); i++) {
            String line = SourcePurifier.removeWhitespaces(SourcePurifier.purify(fileContents.get(i)));

            if (line.contains("typedef") && !doesTypedefComplyWithRule(getTypedefName(fileContents, i)))
                MistakePrinter.major(ruleErrorTypedef, file.getName(), i + 1);
            if (line.contains("#define") && FileNaming.isScreamingSnakeCase(getMacroName(line)))
                MistakePrinter.major(ruleErrorMacro, file.getName(), i + 1);
        }
    }

    private String getMacroName(String line) {
        int i = line.indexOf("#define") + "#define".length();
        while (i < line.length() && (line.charAt(i) == ' ' || line.charAt(i) == '\t'))
            i++;

        int j = i;
        while (j < line.length() && line.charAt(j) != ' ' && line.charAt(j) != '\t')
            j++;

        return line.substring(i, j);
    }

    private boolean doesTypedefComplyWithRule(String typedefName) {
        return typedefName.length() >= 3 && FileNaming.isSnakeCase(typedefName) &&
                typedefName.charAt(typedefName.length() - 1) == 't'
                        && typedefName.charAt(typedefName.length() - 2) == '_';

    }

    private String extractNameFromDef(String typedef) {
        int i = typedef.length() - 1;

        while (i > 0 && typedef.charAt(i) != '\t' && typedef.charAt(i) != ' ') {
            i--;
        }

        return typedef.substring(i + 1, typedef.length() - 1);
    }

    private String getTypedefName(List<String> fileContents, int i) {
        String typedefDeclaration = fileContents.get(i);
        boolean isInStructTypedef = false;

        while (i < fileContents.size() &&
                ((!isInStructTypedef && !typedefDeclaration.contains(";")) || isInStructTypedef)) {
            typedefDeclaration = fileContents.get(i);
            if (typedefDeclaration.contains("{"))
                isInStructTypedef = true;
            if (typedefDeclaration.contains("}"))
                isInStructTypedef = false;
            i++;
        }

        if (i == fileContents.size())
            return "";

        return extractNameFromDef(typedefDeclaration);
    }
}
