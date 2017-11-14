package com.myujinn.tekcode.checker;

import java.io.File;

public abstract class Rule {

    private String name;

    public abstract void check(File file);

    public String getName() {
        return name;
    }
}
