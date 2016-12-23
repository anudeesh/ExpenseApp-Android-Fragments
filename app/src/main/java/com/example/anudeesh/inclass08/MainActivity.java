package com.example.anudeesh.inclass08;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements ExpenseApp.OnFragmentInteractionListener, AddExpenseFragment.OnFragmentInteractionListener, ShowExpensesFragment.OnFragmentInteractionListener {

    ArrayList<Expense> expenseList = new ArrayList<Expense>();
    ExecutorService threadpool;
    Expense expense;
//    doWork d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*threadpool = Executors.newFixedThreadPool(4);
        d = new doWork();*/

        //myView = (ListView) findViewById(R.id.listViewExpenses);

        getFragmentManager().beginTransaction()
                .add(R.id.container, new ExpenseApp(), "expenseapp")
                .commit();
    }

    @Override
    public void onAdd() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new AddExpenseFragment(), "addexpense")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void removeExpense(int i) {
        if(expenseList!=null) {
            expenseList.remove(i);
        }
    }

    @Override
    public void showDetails(int i) {
        if(expenseList!=null) {
            expense = expenseList.get(i);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new ShowExpensesFragment(), "showexpense")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void createExpense(String name, String category, String amount) {
        if (name.isEmpty() || amount.isEmpty() || category.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            Expense newexp = new Expense(name, category, amount);
            expenseList.add(newexp);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0) {
            getFragmentManager().popBackStack();

        } else {
            super.onBackPressed();
        }
    }
}

