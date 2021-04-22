package networking;

import shared.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntRMIDatabaseServer extends Remote {
    void addAccount(Account account) throws RemoteException;
}
