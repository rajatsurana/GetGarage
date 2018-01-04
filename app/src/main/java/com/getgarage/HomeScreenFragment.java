package com.getgarage;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;



/**
 * Created by Rajat on 14-09-2015.
 */
public class HomeScreenFragment extends Fragment {
    private ActionBarDrawerToggle drawerListener;
    public static DrawerLayout drawerLayout;
    public static ListView listView;

    View view;
    Toolbar toolbar;
    ActionBar actionbar;
    int titleId;
    private MyAdapter myAdapter;
    Button chooseService;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homescreen, container, false);

        GetGarageMainActivity.List_Id=0;
        GetGarageMainActivity.Part=true;
        Toast.makeText(getActivity(),"Home aa gaya",Toast.LENGTH_SHORT).show();
        Log.i("Transac", "Home aa gyaa");
        toolbar=(Toolbar)view.findViewById(R.id.tool_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionbar = 	((AppCompatActivity) getActivity()).getSupportActionBar();

        titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        getActivity().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "GetGarage" + "</font>")));

        toolbar.setBackgroundResource(R.color.dullwhite);

         actionbar.show();


        drawerLayout =(DrawerLayout) view.findViewById(R.id.drawerlayout1);
        drawerListener = new ActionBarDrawerToggle(getActivity(), drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerListener);

        listView = (ListView) view.findViewById(R.id.drawerList);
        myAdapter = new MyAdapter(getActivity());//doit
        listView.setAdapter(myAdapter);//doit
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                drawerLayout.closeDrawer(Gravity.LEFT);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            HomeScreenFragment fragmentHome2 = new HomeScreenFragment();
                            FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                            transaction2.replace(R.id.splashFrame, fragmentHome2);
                            transaction2.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 400);
                break;
            case 1:
                drawerLayout.closeDrawer(Gravity.LEFT);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            VehiclesFragment fragmentVehicles = new VehiclesFragment();
                            FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                            transaction3.replace(R.id.splashFrame, fragmentVehicles);
                            transaction3.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 400);


                break;
            case 2:
                Toast.makeText(getActivity(), MyAdapter.garageAppNav[position], Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case 4:
                Toast.makeText(getActivity(), MyAdapter.garageAppNav[position], Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getActivity(), MyAdapter.garageAppNav[position], Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }

    }

});

chooseService =(Button)view.findViewById(R.id.chooseservice);
        chooseService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ListViewCheckboxesFragment lvcf = new ListViewCheckboxesFragment();
                ExpandableListFragment lvcf = new ExpandableListFragment();
                final FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                //transaction3.setCustomAnimations(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_top);
                transaction3.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                transaction3.replace(R.id.splashFrame, lvcf);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            transaction3.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 300);
            }
        });
        return view;
    }
   /* public float getXFraction() {
        return getX() / getWidth(); // TODO: guard divide-by-zero
    }

    public void setXFraction(float xFraction) {
        // TODO: cache width
        final int width = getWidth();
        setX((width > 0) ? (xFraction * width) : -9999);
    }*/
    @Override
    public void onResume() {
        super.onResume();
        drawerListener.syncState();
    }

}
