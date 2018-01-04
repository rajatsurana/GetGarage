package com.getgarage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class GetGarageMainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1500;
    public static int List_Id=0;
    public static boolean Part=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //doit
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {


                try {


                    LoginFragment fragmentLogin = new LoginFragment();
                    FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();

                    transaction1.replace(R.id.splashFrame, fragmentLogin);

                    transaction1.commit();


                } catch (Exception e) {
                    Toast.makeText(GetGarageMainActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();

                    e.printStackTrace();

                }


            }
        }, SPLASH_TIME_OUT);


    }
    boolean doubleBackToExitPressedOnce=false;
    @Override
    public void onBackPressed() {



        if(GetGarageMainActivity.Part) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
        //GetGarageMainActivity.Part==false
        else{
            if(GetGarageMainActivity.List_Id==1 ){
                VehiclesFragment vf2 = new VehiclesFragment();
                final FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, vf2);
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
            }else if(GetGarageMainActivity.List_Id==0 ){
                HomeScreenFragment hf2 = new HomeScreenFragment();
                final FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, hf2);
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
        }
        }


}
class MyAdapter extends BaseAdapter {
    private Context context;
    public static String[] garageAppNav;
    static int[] images = {R.drawable.home,R.drawable.vehicle,R.drawable.pay,R.drawable.help,R.drawable.help,R.drawable.about};
    static int[] images_active = {R.drawable.icon_gps_active,R.drawable.icon_wifi_enable,R.drawable.icon_gps_active,R.drawable.icon_gps_active,R.drawable.icon_wifi_enable,R.drawable.icon_wifi_enable};
    public MyAdapter(Context context){
        this.context=context;
        garageAppNav = context.getResources().getStringArray(R.array.social);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        return garageAppNav.length;
    }

    @Override
    public Object getItem(int position) {
        return garageAppNav[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder{

        public TextView titleTextView;

        public ImageView titleImageView;

    }

   // public static HashMap<String,Boolean> map = new HashMap<>();



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View row = null;
        // final TextView titleTextView;
        // final ImageView titleImageView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_row, parent, false);

            ViewHolder holder;

            /****** View Holder Object to contain custom_row.xml file elements ******/

            holder = new ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.textView1);

            holder.titleImageView = (ImageView) row.findViewById(R.id.imageView1);

            /************  Set holder with LayoutInflater ************/
            row.setTag(holder);
        } else {
            row = convertView;
        }
        ViewHolder holder = (ViewHolder) row.getTag();
        String s = garageAppNav[position];

        if(position==GetGarageMainActivity.List_Id){
            holder.titleTextView.setText(s);
            holder.titleTextView.setTextColor(0xffff8800);

            holder.titleImageView.setImageResource(images_active[position]);


        }else if (position != 3) {
            holder.titleTextView.setText(s);

            holder.titleImageView.setImageResource(images[position]);



        }else {
            holder.titleTextView.setText(s);
            holder.titleImageView.setImageResource(images[position]);

            holder.titleTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
            holder.titleTextView.setBackgroundColor(0xffa9a9a9);
            holder.titleImageView.setImageResource(images[position]);
            holder.titleImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
            holder.titleImageView.setBackgroundColor(0xffa9a9a9);


        }


        return row;
    }

}