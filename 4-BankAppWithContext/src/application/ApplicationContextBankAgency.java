package application;

import bank.BankAgency;

import java.io.PrintStream;
import java.util.Scanner;

public class ApplicationContextBankAgency {
    private static ApplicationContextBankAgency INSTANCE = null;

    public static ApplicationContextBankAgency getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationContextBankAgency();
        }
        return INSTANCE;
    }

    private ApplicationContextBankAgency() {
        bankAgency = AccesBankAgency.getBankAgency();
        scanner = new Scanner(System.in);
        printStream = System.out;
    }

    public BankAgency bankAgency;
    public Scanner scanner;
    public PrintStream printStream;
}
