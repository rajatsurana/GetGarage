package com.getgarage;


        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
        import android.text.Html;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;

        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;

        import android.widget.Toast;

        import com.getgarage.cards.DataObject;
        import com.getgarage.cards.MyRecyclerViewAdapter;
        import com.getgarage.getgaragedb.DatabaseHandler;
        import com.getgarage.getgaragedb.MyVehicles;

        import java.io.ByteArrayOutputStream;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Rajat on 14-09-2015.
 */
public class VehiclesFragment extends Fragment {

    private ActionBarDrawerToggle drawerListener;
    public static DrawerLayout drawerLayout;
    public static ListView listView;

    Toolbar toolbar;
    ActionBar actionbar;
    int titleId;
    private MyAdapter myAdapter;
    View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static RecyclerView.Adapter mAdapter;
    public static  ArrayList<DataObject> results;
    Button addVehicleButton;
    List<MyVehicles> myVehi;
    public DatabaseHandler databaseH;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.vehicles, container, false);
        GetGarageMainActivity.List_Id=1;
        GetGarageMainActivity.Part=true;

Toast.makeText(getActivity(),"Vehicles aa gaya",Toast.LENGTH_SHORT).show();
        Log.i("Transac", "Vehicle aa gyaa");

        toolbar=(Toolbar)view.findViewById(R.id.tool_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionbar = 	((AppCompatActivity) getActivity()).getSupportActionBar();

        titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        getActivity().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "My Vehicles" + "</font>")));

        toolbar.setBackgroundResource(R.color.dullwhite);

         actionbar.show();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        results=new ArrayList<DataObject>();
        //results = getDataSet();

        myVehi=databaseH.readAllMyVehicles();
        results=retrieveDataBase();
        mAdapter = new MyRecyclerViewAdapter(results,false);
        mRecyclerView.setAdapter(mAdapter);

        drawerLayout =(DrawerLayout) view.findViewById(R.id.drawerlayout2);
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
       // TextView tv = (TextView) listView.
       // tv.setTextColor(0xffffffff);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:

                         drawerLayout.closeDrawer(Gravity.LEFT);
                        //Toast.makeText(getActivity(), MyAdapter.garageAppNav[position] + " aa gaya", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(),MyAdapter.garageAppNav[position],Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }


            }

        });

        addVehicleButton= (Button) view.findViewById(R.id.addvehiclebutton);
        addVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddVehicles addvehicleFragment = new AddVehicles();
                final FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
               // transaction3.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction3.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                transaction3.replace(R.id.splashFrame, addvehicleFragment);
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
    private ArrayList<DataObject> getDataSet(){
        //results.clear();
        byte[] mImg1=null;
        ImageView iv=new ImageView(getActivity());
        iv.setImageResource(R.drawable.bmwredu);
        Bitmap b= ((BitmapDrawable)iv.getDrawable()).getBitmap();
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        mImg1=bos.toByteArray();
        for (int index = 0; index < 6; index++) {
            DataObject obj = new DataObject(mImg1,"BrandName " + index,"ModelName "+ index,"CarPlateNumber " + index,
                    "Mileage "+index);
            results.add(index, obj);
        }
        return results;
    }
    private ArrayList<DataObject> retrieveDataBase(){
        //results.clear();
        byte[] mImg1=null;
        ImageView iv=new ImageView(getActivity());
        iv.setImageResource(R.drawable.bmwredu);
        Bitmap b= ((BitmapDrawable)iv.getDrawable()).getBitmap();
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        mImg1=bos.toByteArray();


        for (int index = 0; index < myVehi.size(); index++) {
            DataObject obj = new DataObject(mImg1,"Brand: "+myVehi.get(index).getBrand(),"Model: "+ myVehi.get(index).getModel(),"PlateNumber: " +myVehi.get(index).getPlatenumber(),
                    "Mileage: "+String.valueOf(myVehi.get(index).getMileage()));
            results.add(obj);
        }
        return results;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TOD1O Auto-generated method stub
        databaseH = new DatabaseHandler(getActivity());

        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        inflater.inflate(R.menu.menu_get_garage_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_setting) {

            Log.i("rajat", "action_settings");
            Toast.makeText(getActivity(),"Setting karo",Toast.LENGTH_SHORT).show();
           // return true;
        }
        else if (id == R.id.action_report) {
            Log.i("rajat", "action_report");
            Toast.makeText(getActivity(),"report karo",Toast.LENGTH_SHORT).show();
            //return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        drawerListener.syncState();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListenerReport() {
            @Override
            public void onItemClick(int position, View v) {
                    int id= myVehi.get(position).getMyVehicleId();
                Log.i("rajat", " Clicked on Item " + position);
                EditMyVehicleFragment emvf = new EditMyVehicleFragment(id);
                final FragmentTransaction transaction3 = getFragmentManager().beginTransaction();

                transaction3.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                transaction3.replace(R.id.splashFrame, emvf);
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

    }

}
