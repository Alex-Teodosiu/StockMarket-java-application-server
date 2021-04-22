package com.sep3.javaapplicationserver.network;

import shared.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntRMIApplicationClient extends Remote {
    void addAccount(Account account) throws RemoteException;
}
