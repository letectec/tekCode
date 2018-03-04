package com.myujinn.tekcode.checker;

import com.myujinn.tekcode.rule.Rule;
import com.myujinn.tekcode.rule.RuleFactory;

import java.io.File;
import java.util.List;

public class FileAnalyzer implements Runnable {

    private File file;
    private RuleFactory ruleFactory;

    public FileAnalyzer(File file, RuleFactory ruleFactory) {
        this.file = file;
        this.ruleFactory = ruleFactory;
    }

    private boolean isSource() {
        String name = file.getName();
        return name.charAt(name.length() - 1) == 'c';
    }

    public void run() {
        List<Rule> ruleList;

        if (isSource())
            ruleList = ruleFactory.getRulesSource();
        else
            ruleList = ruleFactory.getRulesHeader();

        for (Rule rule : ruleList) {
            rule.check(file);
        }
    }

}
