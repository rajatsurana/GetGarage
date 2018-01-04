package com.getgarage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Rajat on 16-09-2015.
 */
public class LoginFragment extends Fragment {
View view;
    Button activationButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.loginscreen, container, false);

activationButton = (Button)view.findViewById(R.id.sbutton);
        activationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeScreenFragment fragmentHome = new HomeScreenFragment();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.replace(R.id.splashFrame, fragmentHome);
                transaction1.commit();
            }
        });
        return view;
    }

}
