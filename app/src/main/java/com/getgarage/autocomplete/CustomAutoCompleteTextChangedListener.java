package com.getgarage.autocomplete;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import com.getgarage.AddVehicles;
import com.getgarage.GetGarageMainActivity;

/**
 * Created by Rajat on 23-10-2015.
 */
public class CustomAutoCompleteTextChangedListener implements TextWatcher {
    Context con;
    public CustomAutoCompleteTextChangedListener(Context context) {
        con =context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {

        AddVehicles mainActivity = new AddVehicles();

        // query the database based on the user input
        mainActivity.item = mainActivity.getItemsFromDb(userInput.toString());

        // update the adapater
        mainActivity.myAdapter.notifyDataSetChanged();
        mainActivity.myAdapter = new ArrayAdapter<String>(con, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.myAutoComplete.setAdapter(mainActivity.myAdapter);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
