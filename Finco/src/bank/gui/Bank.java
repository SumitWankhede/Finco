package bank.gui;


import javax.swing.table.DefaultTableModel;

import bank.controller.BankController;
import finco.framework.FinCo;
import finco.framework.IFinCo;
import finco.framework.command.Operation;
import finco.framework.command.Transaction;
import finco.framework.mvc.view.IMainUI;
import finco.framework.proxy.EmptyProxyFunc;
import finco.framework.proxy.ProxyFinCo;
import finco.framework.proxy.ProxyFunctor;

/**
 * @author: Enkhbayasgalan Galsandorj
 */
public class Bank {
    public static void main(String[] args) {

        IFinCo finCo = new FinCo();
        ProxyFunctor<Operation> operationFunc = new EmptyProxyFunc<Operation>();
        ProxyFunctor<Transaction> transactionFunctor = new EmptyProxyFunc<Transaction>();
        finCo = new ProxyFinCo(finCo,operationFunc,transactionFunctor);
        
        DefaultTableModel model = new DefaultTableModel();
        IMainUI ui = new BankUI(model);

        new BankController(finCo, ui, model);

        ui.initialize();
    }
}
