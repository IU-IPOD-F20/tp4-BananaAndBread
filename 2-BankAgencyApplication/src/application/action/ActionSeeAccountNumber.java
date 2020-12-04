package application.action;

import bank.Account;
import bank.BankAgency;

import java.util.Scanner;

public class ActionSeeAccountNumber implements Action {
    private String message = "";
    private String code = "";

    public ActionSeeAccountNumber(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String actionMessage() {
        return message;
    }

    @Override
    public String actionCode() {
        return code;
    }

    @Override
    public void execute(BankAgency ag) throws Exception {
        System.out.print("Account Number -> ");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        Account c = ag.getAccount(number);
        if (c == null) {
            System.out.println("Account non existing ...");
        } else {
            c.print();
        }
    }
}
