package bank.command;

import bank.account.Checkings;
import bank.account.Savings;
import bank.model.CompanyAccount;
import finco.framework.account.Account;
import finco.framework.command.Operation;
import finco.framework.party.Company;
import finco.framework.singleton.LocalDataObject;

public class AddCompanyAccount implements Operation {
    private LocalDataObject db =LocalDataObject.getInstance();

    private CompanyAccount accountDTO;
    private Company customer ;
    protected Account account;

    public AddCompanyAccount(CompanyAccount personalAccount) {
        this.accountDTO = personalAccount;
    }

    @Override
    public void execute() {
        // TODO: use factory
        customer = new Company();

        // customerAccount to customer
        customer.setName(accountDTO.getCustomerName());
        customer.setEmail(accountDTO.getEmail());
        customer.setStreet(accountDTO.getStreet());
        customer.setCity(accountDTO.getCity());
        customer.setState(accountDTO.getState());
        customer.setZip(accountDTO.getZip());
        try {
            customer.setNoOfEmployees(Integer.parseInt(accountDTO.getNumOfEmployee()));
        } catch (NumberFormatException e) {
            customer.setNoOfEmployees(0);
        }

        account = createAccount();
        customer.addAccount(account);

        //add an account
        db.getAllCustomer().add(customer);
        //add a customer
        db.getAllAccount().add(account);
    }

    private Account createAccount() {
        if ("Savings".equals(accountDTO.getAccountType())) {
            return new Savings(accountDTO.getAccountNo(), customer);
        } else {
            return new Checkings(accountDTO.getAccountNo(), customer);
        }
    }
}
