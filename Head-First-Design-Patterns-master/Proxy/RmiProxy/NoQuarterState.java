package com.weltond.Proxy.RmiProxy;

import java.rmi.RemoteException;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class NoQuarterState implements State {
    transient GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuater() {
        System.out.println("You inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuater() {
        System.out.println("You haven't inserted a quarter");
    }

    @Override
    public void turnCrank() throws RemoteException {
        System.out.println("You turned, but there's no quarter");
    }

    @Override
    public void dispense() throws RemoteException {
        System.out.println("You need to pay first");
    }

    public String toString() {
        return "waiting for quarter";
    }
}
