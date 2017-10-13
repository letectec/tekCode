package com.myujinn.tekcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class SourceFileReader {

    private final static Logger LOGGER = LoggerFactory.getLogger(SourceFileReader.class);

    public static List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            LOGGER.error("Couldn't read file " + file.getName());
        }
        return null;
    }
}
