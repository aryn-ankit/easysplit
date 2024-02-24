package org.splitwise;

import org.splitwise.controller.ExpenseManager;
import org.splitwise.entitites.Split;
import org.splitwise.entitites.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SplitwiseApplication {
    public static void main(String[] args) {
        String expenseType;
        Double amount;
        int numSplits;
        String payeeUserName;
        List<User> users = new ArrayList<>();
        users.add(new User(1, "ankit.aryan", "Ankit", "Aryan", "ankitarya780@gmail.com", "9887432068"));
        users.add(new User(2, "sourav.singh", "Sourav", "Singh", "ankitarya780@gmail.com", "9887432068"));
        users.add(new User(1, "bhuvan.rai", "Bhuvan", "Rai", "ankitarya780@gmail.com", "9887432068"));
        users.add(new User(1, "anand.singh", "Anand", "Singh", "ankitarya780@gmail.com", "9887432068"));
        ExpenseManager expenseManager = new ExpenseManager();
        users.forEach(expenseManager::addUser);



        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter Expense Type : ");
//        expenseType = sc.nextLine();

        System.out.println("Enter userName of payee : ");
        payeeUserName = sc.nextLine();

        System.out.println("Enter amount : ");
        amount = sc.nextDouble();

        System.out.println("Enter number of users : ");
        numSplits = sc.nextInt();
        sc.nextLine();



        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < numSplits ; i++) {
            System.out.println(i + " user name : ");
            String userName = sc.nextLine();
            splits.add(new Split(expenseManager.getUser(userName), amount/numSplits));
        }

        expenseManager.addExpense(amount, payeeUserName, splits);
        expenseManager.showAllBalances();

    }
}