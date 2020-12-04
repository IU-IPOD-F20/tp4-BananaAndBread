package application.actionlist;

import application.action.Action;
import application.action.ActionList;
import bank.BankAgency;

import java.util.ArrayList;
import java.util.Scanner;

public class GenericActionList<E> implements ActionList<E> {

    String message;
    String code;
    String title;
    ArrayList<Action<E>> actionList;

    public GenericActionList(String message, String code, String title) {
        this.message = message;
        this.code = code;
        this.title = title;
        actionList = new ArrayList<>();
    }

    @Override
    public String listTitle() {
        return title;
    }

    @Override
    public int size() {
        return actionList.size();
    }

    @Override
    public boolean addAction(Action ac) {
        actionList.add(ac);
        return true;
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
    public void execute(E ag) throws Exception {
        while (true) {
            System.out.println("--");
            System.out.println(title);
            System.out.println("--");

            for (Action action : actionList) {
                System.out.println(String.format("    %s - %s", action.actionCode(), action.actionMessage()));
            }
            System.out.println("\n0 - To quit this menu");
            System.out.println();
            System.out.print("Choice ?");
            Scanner scanner = new Scanner(System.in);
            String code = scanner.next();
            if (code.equals("0")) {
                return;
            }
            for (Action action : actionList) {
                if (action.actionCode().equals(code)) {
                    action.execute(ag);
                }
            }
        }
    }
}
