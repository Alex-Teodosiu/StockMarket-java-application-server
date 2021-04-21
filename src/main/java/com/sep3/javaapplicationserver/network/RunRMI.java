package com.sep3.javaapplicationserver.network;

import java.rmi.RemoteException;

public class RunRMI {
    public static void main(String[] args) throws RemoteException
    {
        //System.setProperty("java.security.policy", "C:\\Users\\Mihail\\IdeaProjects\\java-application-server\\all.policy");
        System.setProperty("java.security.policy", "all.policy");
        //System.setProperty("java.rmi.server.hostname","192.168.1.30");

        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }
        RmiApplicationClient client = new RmiApplicationClient();
    }

}
