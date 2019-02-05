package com.weltond.Proxy.RmiProxy;

import java.rmi.RemoteException;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class GumballMonitor {
    GumballMachineRemote machine;

    public GumballMonitor(GumballMachineRemote machine) {
        this.machine = machine;
    }

    public void report() {
        try {
            System.out.println("Gumball Machine: " + machine.getLocation());
            System.out.println("Current inventory: " + machine.getCount() + "gumballs");
            System.out.println("Current state: " + machine.getState());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
