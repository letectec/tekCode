package com.myujinn.tekcode.rule;

import com.myujinn.tekcode.checker.info.ChainedTernaries;
import com.myujinn.tekcode.checker.major.*;
import com.myujinn.tekcode.checker.minor.*;

import java.util.ArrayList;
import java.util.List;

public class RuleFactory {

    private List<Rule> ruleListSource = new ArrayList<>();
    private List<Rule> ruleListHeader = new ArrayList<>();

    public List<Rule> getRulesSource() {
        if (areRulesEmpty())
            generateRules();

        return ruleListSource;
    }

    public List<Rule> getRulesHeader() {
        if (areRulesEmpty())
            generateRules();

        return ruleListHeader;
    }

    private boolean areRulesEmpty() {
        return ruleListSource.isEmpty() || ruleListHeader.isEmpty();
    }

    private void generateRules() {
        generateGeneralRules();
        generateSourceRules();
        generateHeaderRules();
    }

    private void generateHeaderRules() {
        ruleListHeader.add(new DoubleInclusion());
        ruleListHeader.add(new NamingIdentifiers());
    }

    private void generateSourceRules() {
        ruleListSource.add(new FunctionNaming());
        ruleListSource.add(new ArgumentsPolicy());
        ruleListSource.add(new OneStatement());
        ruleListSource.add(new TwentyLines());
        ruleListSource.add(new CurlyBrackets());
        ruleListSource.add(new LineFunctionSeparation());
        ruleListSource.add(new NoCommentsInFunctions());
        ruleListSource.add(new SpaceAfterKeyword());
        ruleListSource.add(new ChainedTernaries());
        ruleListSource.add(new ConstGlobal());
    }

    private void generateGeneralRules() {
        EightyColumns eightyColumns = new EightyColumns();
        ruleListSource.add(eightyColumns);
        ruleListHeader.add(eightyColumns);
        FileHeader fileHeader = new FileHeader();
        ruleListSource.add(fileHeader);
        ruleListHeader.add(fileHeader);
        FileNaming fileNaming = new FileNaming();
        ruleListSource.add(fileNaming);
        ruleListHeader.add(fileNaming);
        IndentationStyle indentationStyle = new IndentationStyle();
        ruleListSource.add(indentationStyle);
        ruleListHeader.add(indentationStyle);
    }
}
