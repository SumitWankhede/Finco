package finco.framework.command;

import java.util.List;

import finco.framework.account.Account;
import finco.framework.singleton.LocalDataObject;

/**
 * Concrete Command : Add interest to all of accounts in bank
 * @author sunling
 *
 */
public class AddInterest implements Transaction {

	private LocalDataObject db =LocalDataObject.getInstance();
	
	@Override
	public void execute() {
		List<Account> accountList = db.getAllAccount();
		for (Account account : accountList) {
			account.addInterest();
		}
	}

}
