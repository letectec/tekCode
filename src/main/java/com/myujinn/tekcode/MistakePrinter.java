package com.myujinn.tekcode;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class MistakePrinter {

    public static void major(String mistake, String fileName, int line) {
        AnsiConsole.out.println(ansi().fg(RED).a("[ Major ] " + fileName + " at line " + line + " : " + mistake).reset());
    }

    public static void major(String mistake, String fileName) {
        AnsiConsole.out.println(ansi().fg(RED).a("[ Major ] " + fileName + " : " + mistake).reset());
    }

    public static void minor(String mistake, String fileName, int line) {
        AnsiConsole.out.println(ansi().fg(GREEN).a("[ Minor ] " + fileName + " at line " + line + " : " + mistake).reset());
    }

    public static void minor(String mistake, String fileName) {
        AnsiConsole.out.println(ansi().fg(GREEN).a("[ Minor ] " + fileName + " : " + mistake).reset());
    }

    public static void info(String mistake, String fileName, int line) {
        AnsiConsole.out.println(ansi().fg(BLUE).a("[ Info ] " + fileName + " at line " + line + " : " + mistake).reset());
    }

    public static void info(String mistake, String fileName) {
        AnsiConsole.out.println(ansi().fg(BLUE).a("[ Info ] " + fileName + " : " + mistake).reset());
    }
}
