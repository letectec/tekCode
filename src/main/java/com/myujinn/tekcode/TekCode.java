package com.myujinn.tekcode;

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

    void launch(String[] args) {
        checkArgs(args);

        List<Path> sourceFileList = SourceFinder.findSourceFiles(Paths.get(args[0]));
        System.out.println(sourceFileList);
    }
}
