package com.myujinn.tekcode;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class MistakePrinter {

    public static void major(String mistake, int line) {
        AnsiConsole.out.println(ansi().fg(RED).a("[ Major ] at line " + line + " : " + mistake).reset());
    }

    public static void major(String mistake) {
        AnsiConsole.out.println(ansi().fg(RED).a("[ Major ] " + mistake).reset());
    }

    public static void minor(String mistake, int line) {
        AnsiConsole.out.println(ansi().fg(GREEN).a("[ Minor ] at line " + line + " : " + mistake).reset());
    }

    public static void minor(String mistake) {
        AnsiConsole.out.println(ansi().fg(RED).a("[ Minor ] " + mistake).reset());
    }

    public static void info(String mistake, int line) {
        AnsiConsole.out.println(ansi().fg(BLUE).a("[ Info ] at line " + line + " : " + mistake).reset());
    }

    public static void info(String mistake) {
        AnsiConsole.out.println(ansi().fg(BLUE).a("[ Info ] " + mistake).reset());
    }
}
