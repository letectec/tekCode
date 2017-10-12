package com.myujinn.tekcode.checker;

import com.myujinn.tekcode.checker.major.FileNaming;

import java.io.File;

public class FileAnalyzer {

    private File file;

    public FileAnalyzer(File file) {
        this.file = file;
    }

    private void analyzeMinorMistakes() {

    }

    private void analyzeMajorMistakes() {
        FileNaming.check(file);
    }

    private void analyzeInfoMistakes() {

    }

    public void analyze() {
        analyzeMajorMistakes();
        analyzeMinorMistakes();
        analyzeInfoMistakes();
    }

}
