package com.example.anudeesh.inclass08;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpenseApp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ExpenseApp extends Fragment {

    private OnFragmentInteractionListener mListener;
    ListView myView;
    TextView noexp;

    public ExpenseApp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_app, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myView = (ListView) getActivity().findViewById(R.id.listViewExpenses);
        noexp = (TextView) getActivity().findViewById(R.id.textViewNoExpLabel);
        noexp.setVisibility(View.VISIBLE);
        myView.setVisibility(View.INVISIBLE);
        getActivity().findViewById(R.id.imageViewAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAdd();
            }
        });
    }

    public void showList(ArrayList<Expense> expenses) {
        if(expenses.size()>0) {
            noexp.setVisibility(View.INVISIBLE);
            myView.setVisibility(View.VISIBLE);
            ExpenseAdapter adapter = new ExpenseAdapter(getActivity(), R.layout.row_exp_item, expenses);
            myView.setAdapter(adapter);

            myView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                    /*new AlertDialog.Builder(getActivity())
                            .setTitle("Confirmation")
                            .setMessage("Do you really want to delete?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {*/
                                    mListener.removeExpense(position);
                                    Toast.makeText(getActivity(),"Expense Deleted",Toast.LENGTH_SHORT).show();
                                    setExp();
                                /*}})
                            .setNegativeButton(android.R.string.no, null).show();*/
                    return false;
                }
            });
            myView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mListener.showDetails(i);
                }
            });
        } else {
            noexp.setVisibility(View.VISIBLE);
            myView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        setExp();
    }

    public void setExp() {
        MainActivity activity = (MainActivity) mListener;
        showList(activity.expenseList);
    }

    public interface OnFragmentInteractionListener {
        public void onAdd();
        public void removeExpense(int i);
        public void showDetails(int i);
    }
}
