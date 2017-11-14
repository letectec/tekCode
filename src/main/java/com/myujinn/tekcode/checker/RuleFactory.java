package com.myujinn.tekcode.checker;

import com.myujinn.tekcode.checker.major.*;
import com.myujinn.tekcode.checker.minor.*;

public class RuleFactory {

    public Rule getRule(String ruleName) throws BadRuleException {
        switch (ruleName) {
            case ("ArgumentsPolicy") :
                return new ArgumentsPolicy();
            case ("EightyColumns") :
                return new EightyColumns();
            case ("FileHeader") :
                return new FileHeader();
            case ("FileNaming") :
                return new FileNaming();
            case ("FunctionNaming") :
                return new FunctionNaming();
            case ("OneStatement") :
                return new OneStatement();
            case ("TwentyLines") :
                return new TwentyLines();
            case ("CurlyBrackets") :
                return new CurlyBrackets();
            case ("LineFunctionSeparation") :
                return new LineFunctionSeparation();
            case ("NoCommentsInFunctions") :
                return new NoCommentsInFunctions();
            case ("SpaceAfterKeyword") :
                return new SpaceAfterKeyword();
            default :
                throw new BadRuleException();
        }
    }
}
