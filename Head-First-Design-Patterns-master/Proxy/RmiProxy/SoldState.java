package com.weltond.Proxy.RmiProxy;

import java.rmi.RemoteException;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class SoldState implements State {
    transient GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuater() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuater() {
        System.out.println("Sorry, you already turned the crank");
    }

    @Override
    public void turnCrank() throws RemoteException {
        System.out.println("Turning twice doesn't get you another gumball!");
    }

    @Override
    public void dispense() throws RemoteException {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }
        else {
            System.out.println("Oops, out of gumballs now!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }

    public String toString() {
        return "dispensing a gumball";
    }
}
