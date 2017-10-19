package com.myujinn.tekcode;

import com.myujinn.tekcode.checker.FileAnalyzer;
import com.myujinn.tekcode.parsing.SourceFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class TekCode {

    static final Logger LOGGER = LoggerFactory.getLogger(TekCode.class);

    void checkArgs(String[] args) {
        if (args.length == 0) {
            LOGGER.error("No directory specified.");
            throw new IllegalArgumentException();
        }

        if (!SourceFinder.isDirectory(args[0])) {
            LOGGER.error(args[0] + " is not a valid directory.");
            throw new IllegalArgumentException();
        }
    }

    void disclaimer() {
        System.out.println("-------------");
        System.out.println(" Disclaimer: ");
        System.out.println(" tekCode is just a code checker. It CAN make mistakes. (and it will)");
        System.out.println(" You can contribute to it instead of complaining.");
        System.out.println(" https://github.com/Myuujinn/tekCode");
        System.out.println("-------------");
    }

    void launch(String[] args) {
        checkArgs(args);

        disclaimer();

        List<Path> sourceFileList = SourceFinder.findSourceFiles(Paths.get(args[0]));

        for (Path filePath : sourceFileList) {
            FileAnalyzer fileAnalyzer = new FileAnalyzer(filePath.toFile());

            fileAnalyzer.analyze();
        }
    }
}
