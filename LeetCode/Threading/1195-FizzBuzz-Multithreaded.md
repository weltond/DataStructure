## [1195. Fizz Buzz Multithreaded](https://leetcode.com/problems/fizz-buzz-multithreaded/)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Write a program that outputs the string representation of numbers from 1 to n, however:

- If the number is divisible by 3, output "fizz".
- If the number is divisible by 5, output "buzz".
- If the number is divisible by both 3 and 5, output "fizzbuzz".

For example, for n = 15, we output: `1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz`.

Suppose you are given the following code:

```
class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}
```

Implement a multithreaded version of FizzBuzz with four threads. The same instance of FizzBuzz will be passed to four different threads:

- Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
- Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
- Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
- Thread D will call number() which should only output the numbers.

## Answer
### Method 1 - Semaphore - :rabbit: 6ms (54.22%)

```java
class FizzBuzz {
    private int n;
    private Semaphore sem, sem3, sem5, sem15;
    public FizzBuzz(int n) {
        this.n = n;
        sem = new Semaphore(1);
        sem3 = new Semaphore(0);
        sem5 = new Semaphore(0);
        sem15 = new Semaphore(0);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i+= 3) {
            sem3.acquire();
            printFizz.run();
            if ((i + 3) % 5 == 0)   // skip multiplies of 15
                i += 3;
            
            sem.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i+= 5) {
            sem5.acquire();
            printBuzz.run();
            if ((i + 5) % 3 == 0)   // skip multiplies of 15
                i += 5;
            
            sem.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i+= 15) {
            sem15.acquire();
            printFizzBuzz.run();
            sem.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            sem.acquire();
            if (i % 15 == 0) {
                sem15.release();
            } else if (i % 5 == 0) {
                sem5.release();
            } else if (i % 3 == 0) {
                sem3.release();
            } else {
                printNumber.accept(i);
                sem.release();
            }
        }
    }
}
```
