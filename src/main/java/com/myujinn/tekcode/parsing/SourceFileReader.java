package com.myujinn.tekcode.parsing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class SourceFileReader {

    private final static Logger LOGGER = LoggerFactory.getLogger(SourceFileReader.class);

    private static List<String> fileContents = null;
    private static File fileReference = null;

    public static List<String> readFile(File file) {
        //simple buffer system, to not read the same file again
        if (fileReference == file)
            return fileContents;

        //read file
        try {
            fileContents = Files.readAllLines(file.toPath());
            fileReference = file;
            return fileContents;
        } catch (IOException e) {
            LOGGER.error("Couldn't read file " + file.getName());
        }
        return null;
    }
}
