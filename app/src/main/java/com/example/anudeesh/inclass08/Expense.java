package com.example.anudeesh.inclass08;

import java.io.Serializable;

/**
 * Created by Anudeesh on 10/17/2016.
 */
public class Expense implements Serializable {
    String exp_name, exp_category,exp_amount;

    public Expense(String exp_name, String exp_category, String exp_amount) {
        this.exp_name = exp_name;
        this.exp_category = exp_category;
        this.exp_amount = exp_amount;
    }

    public String getExp_name() {
        return exp_name;
    }

    public void setExp_name(String exp_name) {
        this.exp_name = exp_name;
    }

    public String getExp_category() {
        return exp_category;
    }

    public void setExp_category(String exp_category) {
        this.exp_category = exp_category;
    }

    public String getExp_amount() {
        return exp_amount;
    }

    public void setExp_amount(String exp_amount) {
        this.exp_amount = exp_amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "exp_name='" + exp_name + '\'' +
                ", exp_category='" + exp_category + '\'' +
                ", exp_amount='" + exp_amount + '\'' +
                '}';
    }
}
