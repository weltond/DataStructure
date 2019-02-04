package com.weltond.Proxy.RmiProxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public interface GumballMachineRemote extends Remote {
    public int getCount() throws RemoteException;
    public String getLocation() throws RemoteException;
    public State getState() throws RemoteException;
}
