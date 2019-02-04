package com.weltond.CompoundPattern.combining.step6observer;

/**
 * @author weltond
 * @project LeetCode
 * @date 2/1/2019
 */
public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        //simulator.simulate();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();
        simulator.simulate(duckFactory);
    }

    void simulate(AbstractDuckFactory duckFactory){
        //Quackable mallardDuck = duckFactory.createMallardDuck();
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());

        System.out.println("\nDuck Simulator: With Observer");

        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);  // leaf
        flockOfDucks.add(duckCall);     // leaf
        flockOfDucks.add(rubberDuck);   // leaf
        flockOfDucks.add(gooseDuck);    // leaf

        Flock flockOfMallards = new Flock();    // group
        Quackable mallardOne = duckFactory.createMallardDuck();
        Quackable mallardTwo = duckFactory.createMallardDuck();
        Quackable mallardThree = duckFactory.createMallardDuck();
        Quackable mallardFour = duckFactory.createMallardDuck();
        flockOfMallards.add(mallardOne);    // group's leaf
        flockOfMallards.add(mallardTwo);
        flockOfMallards.add(mallardThree);
        flockOfMallards.add(mallardFour);

        flockOfDucks.add(flockOfMallards);  // add a group of Mallard

        //simulate(mallardDuck);
//        simulate(redheadDuck);
//        simulate(duckCall);
//        simulate(rubberDuck);
//        simulate(gooseDuck);

        System.out.println("\nDuck Simulator: Whole Flock Simulation");
        simulate(flockOfDucks);

        System.out.println("\nDuck Simulator: Mallard Flock Simulation");
        simulate(flockOfMallards);
        System.out.println("The step1ducks quacked " + QuackCounter.getQuacks() + " times.");
    }

    void simulate(Quackable duck) {
        duck.quack();
    }
}
