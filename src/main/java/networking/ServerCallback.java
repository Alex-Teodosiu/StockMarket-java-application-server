package networking;

import shared.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;

// client (application-layer) call these methods on the server
public interface ServerCallback extends Remote {
    void addAccount(Account account) throws RemoteException;
}
