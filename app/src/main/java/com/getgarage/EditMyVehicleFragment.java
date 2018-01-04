package com.getgarage;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.getgarage.autocomplete.CustomAutoCompleteView;

import com.getgarage.getgaragedb.Brands;
import com.getgarage.getgaragedb.DatabaseHandler;
import com.getgarage.getgaragedb.Models;
import com.getgarage.getgaragedb.MyVehicles;

import java.util.List;

/**
 * Created by Rajat on 23-10-2015.
 */
public class EditMyVehicleFragment extends Fragment {
    View view;
    Toolbar toolbar;
    ActionBar actionbar;
    int titleId;
    int cardId;
   EditText plateNum;
    EditText mile;
    public EditMyVehicleFragment(){}
public EditMyVehicleFragment(int id){
    cardId=id;
}
    public CustomAutoCompleteView myBrandAutoComplete;

    // adapter for auto-complete
    public ArrayAdapter<String> myBrandAdapter;
    public CustomAutoCompleteView myModelAutoComplete;

    // adapter for auto-complete
    public ArrayAdapter<String> myModelAdapter;
    // for database operations
    public DatabaseHandler databaseH;

    // just to add some initial value
    public String[] brands = new String[] {"Please search..."};
    public String[] models = new String[] {"Please search..."};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.editvehiclelayout, container, false);
        GetGarageMainActivity.List_Id=1;
        GetGarageMainActivity.Part=false;
        toolbar=(Toolbar)view.findViewById(R.id.tool_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionbar=((AppCompatActivity) getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        getActivity().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Edit Vehicle" + "</font>")));
        actionbar.setHomeAsUpIndicator(R.drawable.arrow_right);
        actionbar.show();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VehiclesFragment vf = new VehiclesFragment();
                final FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                // transaction2.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction2.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, vf);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            transaction2.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 300);


            }
        });
        //autocomplete
        Button saveOldVehicle=(Button)view.findViewById(R.id.saveOldVehicle);
        Button deleteVehicle=(Button)view.findViewById(R.id.deleteVehicle);
        Button deleteAllVehicle=(Button)view.findViewById(R.id.deleteAllVehicle);
        try{
            myBrandAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, brands);
            myBrandAutoComplete = (CustomAutoCompleteView) view.findViewById(R.id.mybrandautocomplete);
            myBrandAutoComplete.setAdapter(myBrandAdapter);


            myBrandAutoComplete.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence userInput, int start, int before, int count) {
                    List<Brands> products = databaseH.read(userInput.toString());
                    int rowCount = products.size();

                    String[] brand = new String[rowCount];
                    int x = 0;

                    for (Brands record : products) {

                        brand[x] = record.getBrandName();
                        x++;
                    }

                    //con.item = mainActivity.getItemsFromDb(userInput.toString());

                    // update the adapater
                    myBrandAdapter.notifyDataSetChanged();
                    myBrandAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, brand);
                    myBrandAutoComplete.setAdapter(myBrandAdapter);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }catch (NullPointerException e){
            Log.i("rajat", "onCreateView " + e.getLocalizedMessage());
        }
        try{
            myModelAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, models);
            myModelAutoComplete = (CustomAutoCompleteView) view.findViewById(R.id.mymodelautocomplete);
            myModelAutoComplete.setAdapter(myModelAdapter);


            myModelAutoComplete.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence userInput, int start, int before, int count) {
                    List<Models> products = databaseH.readModels(userInput.toString());
                    int rowCount = products.size();

                    String[] model = new String[rowCount];
                    int x = 0;

                    for (Models record : products) {

                        model[x] = record.getModelName();
                        x++;
                    }
                    //con.item = mainActivity.getItemsFromDb(userInput.toString());

                    // update the adapater
                    myModelAdapter.notifyDataSetChanged();
                    myModelAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, model);
                    myModelAutoComplete.setAdapter(myModelAdapter);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }catch (NullPointerException e){
            Log.i("rajat", "onCreateView " + e.getLocalizedMessage());
        }
       plateNum = (EditText) view.findViewById(R.id.plateNumber);
        mile = (EditText) view.findViewById(R.id.mileage);
        //plateNum.setText();
        setTextAll();
        saveOldVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyVehicles mv = new MyVehicles(cardId, myBrandAutoComplete.getText().toString(), myModelAutoComplete.getText().toString(), plateNum.getText().toString(), Double.valueOf(mile.getText().toString()));
                databaseH.updateMyVehicle(mv);


                VehiclesFragment vf = new VehiclesFragment();
                final FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                // transaction2.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction2.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, vf);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            transaction2.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 300);
            }
        });
        deleteVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseH.deleteMyVehicle(cardId);


                VehiclesFragment vf = new VehiclesFragment();
                final FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                // transaction2.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction2.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, vf);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            transaction2.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 300);
            }
        });
        deleteAllVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseH.deleteAllMyVehicle();
                VehiclesFragment vf = new VehiclesFragment();
                final FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                // transaction2.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction2.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, vf);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            transaction2.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 300);
            }
        });
        return view;
    }

public void setTextAll(){
    MyVehicles mv=databaseH.readMyVehicleAtPosition(cardId);
    if(mv!=null){
        mile.setText(String.valueOf(mv.getMileage()));
    plateNum.setText(mv.getPlatenumber());
    myBrandAutoComplete.setText(mv.getBrand());
    myModelAutoComplete.setText(mv.getModel());
    }
}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseH = new DatabaseHandler(getActivity());
        // put sample data to database
        insertSampleData();
        insertModelData();
    }

    private void insertModelData() {
        databaseH.createModels(new Models("January"));
        databaseH.createModels(new Models("February"));
        databaseH.createModels(new Models("march"));
        databaseH.createModels(new Models("april"));
        databaseH.createModels(new Models("June"));
        databaseH.createModels(new Models("July"));
        databaseH.createModels(new Models("August"));
        databaseH.createModels(new Models("September"));
        databaseH.createModels(new Models("Oct"));
        databaseH.createModels(new Models("Novem"));
        databaseH.createModels(new Models("Decem"));
        databaseH.createModels(new Models("January last"));
        databaseH.createModels(new Models("January first"));
        databaseH.createModels(new Models("January wow"));
        databaseH.createModels(new Models("January yes"));
        databaseH.createModels(new Models("January good"));
        databaseH.createModels(new Models("January no"));
        databaseH.createModels(new Models("January blo"));
        databaseH.createModels(new Models("January blabla"));
        databaseH.createModels(new Models("January yup"));







    }


    public void insertSampleData(){

        // CREATE
        databaseH.create(new Brands("January"));
        databaseH.create(new Brands("February"));
        databaseH.create(new Brands("March"));
        databaseH.create(new Brands("April"));
        databaseH.create(new Brands("May"));
        databaseH.create(new Brands("June"));
        databaseH.create(new Brands("July"));
        databaseH.create(new Brands("August"));
        databaseH.create(new Brands("September"));
        databaseH.create(new Brands("October"));
        databaseH.create(new Brands("November"));
        databaseH.create(new Brands("December"));
        databaseH.create(new Brands("New Caledonia"));
        databaseH.create(new Brands("New Zealand"));
        databaseH.create(new Brands("Papua New Guinea"));
        databaseH.create(new Brands("COFFEE-1K"));
        databaseH.create(new Brands("coffee raw"));
        databaseH.create(new Brands("authentic COFFEE"));
        databaseH.create(new Brands("k12-coffee"));
        databaseH.create(new Brands("view coffee"));
        databaseH.create(new Brands("Indian-coffee-two"));

    }

    // this function is used in CustomAutoCompleteTextChangedListener.java

}
