package com.myujinn.tekcode.checker.major;

import com.myujinn.tekcode.MistakePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 *  G1 -- Source files should always start with the header of the school.
 */
public class FileHeader {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileHeader.class);

    public static void check(File file) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();

            if (line == null || line.length() != 2 ||line.charAt(0) != '/' || line.charAt(1) != '*') {
                MistakePrinter.major("G1 -- Doesn't have the standard header of the school.", file.getName(),1);
                bufferedReader.close();
                return;
            }

            line = bufferedReader.readLine();

            if (line == null || line.length() < 20 || !line.contains("** EPITECH PROJECT")) {
                MistakePrinter.major("G1 -- Doesn't have the standard header of the school.", file.getName(), 2);
                bufferedReader.close();
                return;
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("Couldn't find file " + file.getName());
        } catch (IOException e) {
            LOGGER.error("Couldn't read " + file.getName());
        }
    }
}
