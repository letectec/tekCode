package com.myujinn.tekcode.checker;

import com.myujinn.tekcode.checker.major.EightyColumns;
import com.myujinn.tekcode.checker.major.FileHeader;
import com.myujinn.tekcode.checker.major.FileNaming;
import com.myujinn.tekcode.checker.major.FunctionNaming;
import com.myujinn.tekcode.checker.minor.LineFunctionSeparation;

import java.io.File;

public class FileAnalyzer {

    private File file;

    public FileAnalyzer(File file) {
        this.file = file;
    }

    private void analyzeMinorMistakes() {
        LineFunctionSeparation.check(file);
    }

    private void analyzeMajorMistakes() {
        FileNaming.check(file);
        FileHeader.check(file);
        FunctionNaming.check(file);
        EightyColumns.check(file);
    }

    private void analyzeInfoMistakes() {

    }

    public void analyze() {
        analyzeMajorMistakes();
        analyzeMinorMistakes();
        analyzeInfoMistakes();
    }

}
