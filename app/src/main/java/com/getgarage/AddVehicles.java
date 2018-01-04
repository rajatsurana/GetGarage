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

//import com.getgarage.autocomplete.CustomAutoCompleteTextChangedListener;
import com.getgarage.autocomplete.CustomAutoCompleteView;

import com.getgarage.getgaragedb.Brands;
import com.getgarage.getgaragedb.DatabaseHandler;
import com.getgarage.getgaragedb.Models;
import com.getgarage.getgaragedb.MyVehicles;

import java.util.List;

/**
 * Created by Rajat on 26-09-2015.
 */
public class AddVehicles extends Fragment {
    View view;
    Toolbar toolbar;
    ActionBar actionbar;
    int titleId;
    AutoCompleteTextView acTextView;
    /*
    String[] brands ={
            "Aaja",
            "Kal",
            "Yahan",
            "Thand",
            "Bahut",
            "Hai",
            "Aajkal",
            "Kite",
            "Lion",
            "Leja",
            "Aarjoo",
            "Ashita",
            "Baloon"
    };
*/
    public CustomAutoCompleteView myBrandAutoComplete;

    // adapter for auto-complete
    public ArrayAdapter<String> myBrandAdapter;
    public CustomAutoCompleteView myModelAutoComplete;

    // adapter for auto-complete
    public ArrayAdapter<String> myModelAdapter;
    public String[] brands = new String[] {"Please search..."};
    public String[] models = new String[] {"Please search..."};
    public CustomAutoCompleteView myAutoComplete;

    // adapter for auto-complete
    public ArrayAdapter<String> myAdapter;

    // for database operations
   public DatabaseHandler databaseH;

    // just to add some initial value
    public String[] item = new String[] {"Please search..."};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.addvehiclelayout, container, false);
        GetGarageMainActivity.List_Id=1;
        GetGarageMainActivity.Part=false;
        toolbar=(Toolbar)view.findViewById(R.id.tool_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionbar=((AppCompatActivity) getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        getActivity().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Add Vehicles" + "</font>")));
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
        Button saveNewVehicle=(Button)view.findViewById(R.id.saveNewVehicle);

        //autocomplete
        acTextView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteBrands);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.select_dialog_item_material, brands);

        acTextView.setThreshold(1);
        acTextView.setAdapter(adapter);

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
        final EditText plateNum = (EditText) view.findViewById(R.id.plateNumber);
        final EditText mile = (EditText) view.findViewById(R.id.mileage);
saveNewVehicle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MyVehicles mv = new MyVehicles(myBrandAutoComplete.getText().toString(),myModelAutoComplete.getText().toString(), plateNum.getText().toString(),Double.valueOf(mile.getText().toString()));
        databaseH.createMyVehicle(mv);

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseH = new DatabaseHandler(getActivity());
        // put sample data to database
        insertBrandData();
        insertModelData();
        List<Brands> my=databaseH.readAll();
        String[] myNew=new String[my.size()];
        for(int i=0; i<my.size();i++){
            myNew[i]=my.get(i).getBrandName();
        }
        brands=myNew;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void insertBrandData(){

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
    public String[] getItemsFromDb(String searchTerm){

        // add items on the array dynamically
        List<Brands> products = databaseH.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (Brands record : products) {

            item[x] = record.getBrandName();
            x++;
        }

        return item;
    }
}
