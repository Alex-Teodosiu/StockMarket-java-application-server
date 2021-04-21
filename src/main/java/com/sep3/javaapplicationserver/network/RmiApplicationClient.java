package com.sep3.javaapplicationserver.network;

import com.sep3.javaapplicationserver.Account;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class RmiApplicationClient implements ClientCallback {
    private ClientCallback intRmiDatabaseServer;
    
    public RmiApplicationClient(){
        startClient();
    }

    public void startClient(){
        try
        {
            UnicastRemoteObject.exportObject(this, 0);
            Registry localhost = LocateRegistry.getRegistry("localhost", 1099);
            intRmiDatabaseServer = (ClientCallback)localhost.lookup("server");
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
