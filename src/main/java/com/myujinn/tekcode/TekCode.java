package com.myujinn.tekcode;

import com.myujinn.tekcode.checker.FileAnalyzer;
import com.myujinn.tekcode.parsing.SourceFinder;
import com.myujinn.tekcode.rule.RuleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class TekCode {

    private static final Logger LOGGER = LoggerFactory.getLogger(TekCode.class);

    boolean checkArgs(String[] args) {
        if (args.length == 0) {
            LOGGER.error("No directory specified.");
            return false;
        }

        if (!SourceFinder.isDirectory(args[0])) {
            LOGGER.error(args[0] + " is not a valid directory.");
            return false;
        }
        return true;
    }

    void disclaimer() {
        System.out.println("-------------");
        System.out.println(" Disclaimer: ");
        System.out.println(" tekCode 1.1.2 is just an Epitech coding style checker.");
        System.out.println(" It CAN make mistakes. (and it will)");
        System.out.println(" You can contribute on my GitHub if it does that too often.");
        System.out.println(" https://github.com/Myuujinn/tekCode");
        System.out.println("-------------");
    }

    void launch(String[] args) {
        if (!checkArgs(args))
            return;

        disclaimer();

        List<Path> sourceFileList = SourceFinder.findSourceFiles(Paths.get(args[0]));

        Thread[] threads = new Thread[sourceFileList.size()];

        for (int i = 0; i < sourceFileList.size(); i++) {
            FileAnalyzer fileAnalyzer = new FileAnalyzer(sourceFileList.get(i).toFile(), new RuleFactory());
            threads[i] = new Thread(fileAnalyzer);
            threads[i].start();
        }

        // just to avoid error and properly exit the program
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
