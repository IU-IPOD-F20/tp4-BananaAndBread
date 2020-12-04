package application.action;

import application.ApplicationContextBankAgency;

public class ActionAccountsLists implements Action {
    private String message = "";
    private String code = "";

    public ActionAccountsLists(String message, String code) {
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
    public void execute(ApplicationContextBankAgency context) throws Exception {
        context.bankAgency.print();
    }
}
