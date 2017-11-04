package com.myujinn.tekcode.checker;

import com.myujinn.tekcode.checker.major.*;
import com.myujinn.tekcode.checker.minor.CurlyBrackets;
import com.myujinn.tekcode.checker.minor.LineFunctionSeparation;
import com.myujinn.tekcode.checker.minor.NoCommentsInFunctions;
import com.myujinn.tekcode.checker.minor.SpaceAfterKeyword;

import java.io.File;

public class FileAnalyzer {

    private File file;

    public FileAnalyzer(File file) {
        this.file = file;
    }

    private void analyzeMinorMistakes() {
        LineFunctionSeparation.check(file);
        NoCommentsInFunctions.check(file);
        SpaceAfterKeyword.check(file);
        CurlyBrackets.check(file);
    }

    private void analyzeMajorMistakes() {
        FileNaming.check(file);
        FileHeader.check(file);
        FunctionNaming.check(file);
        EightyColumns.check(file);
        TwentyLines.check(file);
        ArgumentsPolicy.check(file);
        OneStatement.check(file);
    }

    private void analyzeInfoMistakes() {

    }

    public void analyze() {
        analyzeMajorMistakes();
        analyzeMinorMistakes();
        analyzeInfoMistakes();
    }

}
