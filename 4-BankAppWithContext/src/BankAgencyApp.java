import application.ApplicationContextBankAgency;
import application.action.ActionAccountsLists;
import application.action.ActionSeeAccountNumber;
import application.actionlist.GenericActionList;

public class BankAgencyApp {
    public static void main(String argv[]) throws Exception {
        ApplicationContextBankAgency context = ApplicationContextBankAgency.getInstance();
        GenericActionList menu = new GenericActionList("Generic Menu", "0", "Generic Menu");
        menu.addAction(new ActionAccountsLists("List of the Agency accounts", "1"));
        menu.addAction(new ActionSeeAccountNumber("See an account (by its number)", "2"));
        menu.execute(context);
    }
}
