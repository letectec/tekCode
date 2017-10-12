package com.myujinn.tekcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class SourceFinder {

    final static Logger LOGGER = LoggerFactory.getLogger(SourceFinder.class);

    static boolean isDirectory(String path) {
        return new File(path).isDirectory();
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        return "";
    }

    static List<Path> findSourceFiles(Path sourcePath) {
        List<Path> pathList = new ArrayList<>();

        final File folder = sourcePath.toFile();
        final File[] filesInFolder = folder.listFiles();

        if (filesInFolder == null) {
            LOGGER.error("Couldn't list folder " + sourcePath.toString());
        }

        for (File file : filesInFolder) {
            if (file.isDirectory())
                pathList.addAll(findSourceFiles(file.toPath()));
            else if (file.isFile() && ("c".equals(getFileExtension(file)) || "h".equals(getFileExtension(file))))
                pathList.add(file.toPath());
        }

        return pathList;
    }
}
