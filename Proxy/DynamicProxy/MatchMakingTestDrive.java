package com.weltond.Proxy.DynamicProxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class MatchMakingTestDrive {
    HashMap<String, PersonBean> datingDB = new HashMap();
    //CopyOnWriteArrayList is a Copy-On-Write Proxy mode
    public static void main(String[] args) {
        MatchMakingTestDrive test = new MatchMakingTestDrive();
        test.drive();
    }

    public MatchMakingTestDrive() {
        initializeDatabase();
    }

    private void drive() {
        PersonBean joe = getPersonFromDataBase("Joe Javabean");
        PersonBean ownerProxy = getOwnerProxy(joe);
        System.out.println("Name is " + ownerProxy.getName());

        ownerProxy.setInterests("bowling, Pokemon Go");
        System.out.println("Interests set from owner proxy");

        try {
            ownerProxy.setHotOrNotRating(10);
        } catch (Exception e) {
            System.out.println("Can't set rating from owner proxy");
        }
        System.out.println("Rating is " + ownerProxy.getHotOrNotRating());

        PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
        System.out.println("Name is " + nonOwnerProxy.getName());

        try {
            nonOwnerProxy.setInterests("bowling, Pokemon Go");
        }
        catch (Exception e) {
            System.out.println("Can't set interests from non owner proxy");
        }

        nonOwnerProxy.setHotOrNotRating(3);
        System.out.println("Rating set from non owner proxy");
        System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
    }

    PersonBean getOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(),
                                                    personBean.getClass().getInterfaces(),
                                                    new OwnerInvocationHandler(personBean)
                );
    }

    PersonBean getNonOwnerProxy(PersonBean person) {
        return (PersonBean)Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
    }

    PersonBean getPersonFromDataBase(String name) {
        return (PersonBean) datingDB.get(name);
    }

    public void initializeDatabase(){
        PersonBean joe = new PersonBeanImpl();
        joe.setName("Joe Javabean");
        joe.setInterests("cars, computers, music");
        joe.setHotOrNotRating(7);
        datingDB.put(joe.getName(), joe);

        PersonBean kelly = new PersonBeanImpl();
        kelly.setName("Kelly Klosure");
        kelly.setInterests("ebay, movies, music");
        kelly.setHotOrNotRating(6);
        datingDB.put(kelly.getName(), kelly);
    }
}
