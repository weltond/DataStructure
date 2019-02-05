package com.weltond.Proxy.RmiProxy;

import java.rmi.RemoteException;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class SoldOutState implements State {
    transient GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuater() {
        System.out.println("You can't insert a quarter, the machine is sold out");
    }

    @Override
    public void ejectQuater() {
        System.out.println("You can't eject, you haven't inserted a quarter yet");
    }

    @Override
    public void turnCrank() throws RemoteException {
        System.out.println("You turned, but there are no gumballs");
    }

    @Override
    public void dispense() throws RemoteException {
        System.out.println("No gumball dispensed");
    }

    public String toString() {
        return "sold out";
    }
}
