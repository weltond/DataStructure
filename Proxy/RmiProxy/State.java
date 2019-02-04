package com.weltond.Proxy.RmiProxy;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public interface State extends Serializable {
    public void insertQuater();
    public void ejectQuater();
    public void turnCrank() throws RemoteException;
    public void dispense() throws RemoteException;
}
