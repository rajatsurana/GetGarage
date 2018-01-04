package com.getgarage;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Rajat on 23-10-2015.
 */
public class BookingToggleFragment extends Fragment {
    ToggleButton toggleButton;
    TextView text;
    Toolbar toolbar;
    ActionBar actionbar;
    int titleId;
View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bookappointmentlayout, container, false);
        GetGarageMainActivity.List_Id=0;
        GetGarageMainActivity.Part=false;
        toolbar=(Toolbar)view.findViewById(R.id.tool_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionbar=((AppCompatActivity) getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        getActivity().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Book Appointment" + "</font>")));
        actionbar.setHomeAsUpIndicator(R.drawable.arrow_right);
        actionbar.show();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExpandableListFragment elf = new ExpandableListFragment();
                final FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                // transaction2.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction2.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, elf);
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
                /*
                toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton1);
                text = (TextView) view.findViewById(R.id.textView1);

                toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                        text.setText("Status: " + isChecked);
                    }
                });
*/
            }
        });
        return view;
    }


}
