package com.myujinn.tekcode.rule;

import java.io.File;

public abstract class Rule {

    protected String ruleName;

    public abstract void check(File file);

    public String getName() {
        return ruleName;
    }
}
