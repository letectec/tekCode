package com.myujinn.tekcode.checker;

import java.io.File;
import java.util.List;

public class FileAnalyzer {

    private File file;

    private RuleFactory ruleFactory = new RuleFactory();

    public FileAnalyzer(File file) {
        this.file = file;
    }

    public void analyze() {
        List<String> rulesNameList = Rules.getRules();

        for (String ruleName : rulesNameList) {
            try {
                ruleFactory.getRule(ruleName).check(file);
            } catch (BadRuleException e) {
                e.printStackTrace();
            }
        }
    }

}
