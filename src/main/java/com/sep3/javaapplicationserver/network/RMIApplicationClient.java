package com.sep3.javaapplicationserver.network;

import shared.Account;
import networking.IntRMIDatabaseServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class RMIApplicationClient implements IntRMIApplicationClient {
    private IntRMIDatabaseServer intRmiDatabaseServer;
    
    public RMIApplicationClient(){
        startClient();
    }

    public void startClient(){
        try
        {
            UnicastRemoteObject.exportObject(this, 0);
            Registry localhost = LocateRegistry.getRegistry("localhost", 1099);
            intRmiDatabaseServer = (IntRMIDatabaseServer) localhost.lookup("server");
            System.out.println("Client connected...");
        }
        catch (NotBoundException | RemoteException e)
        {
            e.printStackTrace();
        }
    }


    public void addAccount(Account account)
    {
        try
        {
            intRmiDatabaseServer.addAccount(account);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

}
