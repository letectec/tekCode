package com.myujinn.tekcode.checker;

import java.util.Arrays;
import java.util.List;

public class Rules {

    private static String[] ruleClasses = {
            "ArgumentsPolicy",
            "EightyColumns",
            "FileHeader",
            "FileNaming",
            "FunctionNaming",
            "OneStatement",
            "TwentyLines",
            "CurlyBrackets",
            "LineFunctionSeparation",
            "NoCommentsInFunctions",
            "SpaceAfterKeyword"
    };

    public static List<String> getRules() {
        return Arrays.asList(ruleClasses);
    }
}
