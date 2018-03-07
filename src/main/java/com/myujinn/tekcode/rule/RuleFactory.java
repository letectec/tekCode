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
        if (ruleListSource.isEmpty())
            generateRules();

        return ruleListSource;
    }

    public List<Rule> getRulesHeader() {
        if (ruleListHeader.isEmpty())
            generateRules();

        return ruleListHeader;
    }

    private void generateRules() {
        ruleListSource.add(new ArgumentsPolicy());

        EightyColumns eightyColumns = new EightyColumns();
        ruleListSource.add(eightyColumns);
        ruleListHeader.add(eightyColumns);

        FileHeader fileHeader = new FileHeader();
        ruleListSource.add(fileHeader);
        ruleListHeader.add(fileHeader);

        FileNaming fileNaming = new FileNaming();
        ruleListSource.add(fileNaming);
        ruleListHeader.add(fileNaming);

        ruleListSource.add(new FunctionNaming());

        ruleListSource.add(new OneStatement());

        ruleListSource.add(new TwentyLines());

        ruleListSource.add(new CurlyBrackets());

        ruleListSource.add(new LineFunctionSeparation());

        ruleListSource.add(new NoCommentsInFunctions());

        ruleListSource.add(new SpaceAfterKeyword());

        IndentationStyle indentationStyle = new IndentationStyle();
        ruleListSource.add(indentationStyle);
        ruleListHeader.add(indentationStyle);

        ruleListHeader.add(new NamingIdentifiers());
        ruleListSource.add(new ChainedTernaries());

        ruleListHeader.add(new DoubleInclusion());

        ruleListSource.add(new ConstGlobal());
    }
}
