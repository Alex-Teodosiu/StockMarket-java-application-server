package com.sep3.javaapplicationserver.network;

import com.sep3.javaapplicationserver.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void addAccount(Account account) throws RemoteException;
}
