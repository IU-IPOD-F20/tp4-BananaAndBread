import application.action.ActionAccountsLists;
import application.action.ActionSeeAccountNumber;
import application.actionlist.GenericActionList;
import bank.BankAgency;
import application.AccesBankAgency;

public class BankAgencyApp {
    public static void main(String argv[]) throws Exception {
        BankAgency myAgency ;
        myAgency = AccesBankAgency.getBankAgency();
        GenericActionList menu = new GenericActionList("Generic Menu", "0", "Generic Menu");
        menu.addAction(new ActionAccountsLists("List of the Agency accounts", "1"));
        menu.addAction(new ActionSeeAccountNumber("See an account (by its number)", "2"));
        menu.execute(myAgency);
    }
}
