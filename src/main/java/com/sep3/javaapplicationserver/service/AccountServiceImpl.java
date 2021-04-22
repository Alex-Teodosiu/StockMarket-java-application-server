package com.sep3.javaapplicationserver.service;

import networking.ClientCallback;
import networking.IntRMIDatabaseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shared.Account;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@Service
public class AccountServiceImpl implements AccountService, ClientCallback {

    private IntRMIDatabaseServer intRmiDatabaseServer;

    @Autowired
    public AccountServiceImpl() {
        Start();
    }

    @Override
    public void Start() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            Registry localhost = LocateRegistry.getRegistry("localhost", 1099);
            intRmiDatabaseServer = (IntRMIDatabaseServer) localhost.lookup("server");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AddNewAccount(Account account) {
        try {
            intRmiDatabaseServer.addAccount(account);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
