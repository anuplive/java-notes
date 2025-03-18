package labs.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankDemo {

    // Members
    // Account
    // Create a member will have an Account
    // Member can withdraw from the account and deposit from the account
    // Two threads representing the two members with same account
    // member1 will deposit and member two will withdraw
    // Account should not go negative
    // Will do this later


}
 class Account{
    int balance;
    Object lock;
    // Thread safe methods
    void deposit(int amount){};
    int withdraw(int amount){return amount;}
 }

 class Member{
    Account accout;
 }







