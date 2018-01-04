package com.getgarage;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getgarage.expandlist.Child;
import com.getgarage.expandlist.Parent;

import java.util.ArrayList;

public class ExpandableListFragment extends Fragment {
//
private static final String STR_CHECKED = " has Checked!";
    private static final String STR_UNCHECKED = " has unChecked!";
    private int ParentClickStatus=-1;
    private int ChildClickStatus=-1;
    private ArrayList<Parent> parents;
    MyExpandableListAdapter mExla;
    Toolbar toolbar;
    ActionBar actionbar;
    int titleId;
    //
    View rootView;
    ExpandableListView lv;



    public ExpandableListFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

               final ArrayList<Parent> dummyList = buildDummyData();
        loadHosts(dummyList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lineup, container, false);

        GetGarageMainActivity.List_Id=0;
        GetGarageMainActivity.Part=false;
        toolbar=(Toolbar)rootView.findViewById(R.id.tool_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionbar=((AppCompatActivity) getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        titleId = getResources().getIdentifier("action_bar_title", "id","android");
        getActivity().setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Choose Services" + "</font>")));
        actionbar.setHomeAsUpIndicator(R.drawable.arrow_right);
        actionbar.show();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeScreenFragment hf = new HomeScreenFragment();
                final FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                // transaction2.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction2.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, hf);
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
        checkButtonClick();
        Button bookButton= (Button)rootView.findViewById(R.id.bookService);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingToggleFragment btf = new BookingToggleFragment();
                final FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                // transaction2.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction2.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction2.replace(R.id.splashFrame, btf);
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
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ExpandableListView) view.findViewById(R.id.expListView);

        // Drawable devider = ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher);
        mExla=new MyExpandableListAdapter();
        lv.setAdapter(mExla);
        lv.setGroupIndicator(null);
        //lv.setDivider(devider);
        //lv.setChildDivider(devider);
        //lv.setDividerHeight(1);
      registerForContextMenu(lv);

    }
    private ArrayList<Parent> buildDummyData()
    {
        // Creating ArrayList of type parent class to store parent class objects
        final ArrayList<Parent> list = new ArrayList<Parent>();
        for (int i = 1; i < 6; i++)
        {
            //Create parent class object
            final Parent parent = new Parent();

            // Set values in parent class object
            if(i==1){
                parent.setName("" + (i-1));
                parent.setText1("Parent 0");
                parent.setText2("Disable App On \nBattery Low");
                parent.setChildren(new ArrayList<Child>());

                // Create Child class object
                final Child child = new Child();
                child.setName("" + 0);
                child.setText1("Child 0");

                //Add Child class object to parent class object
                parent.getChildren().add(child);
            }
            else if(i==2){
                parent.setName("" + (i-1));
                parent.setText1("Parent 1");
                parent.setText2("Auto disable/enable App \n at specified time");
                parent.setChildren(new ArrayList<Child>());

                final Child child = new Child();
                child.setName("" + 1);
                child.setText1("Child 0");
                parent.getChildren().add(child);
                final Child child1 = new Child();
                child1.setName("" + 2);
                child1.setText1("Child 1");
                parent.getChildren().add(child1);
            }
            else if(i==3 ){
                parent.setName("" + (i-1));
                parent.setText1("Parent "+ (i-1));
                parent.setText2("Show App Icon on \nnotification bar");
                parent.setChildren(new ArrayList<Child>());

                final Child child = new Child();
                child.setName("" + 3);
                child.setText1("Child 0");
                parent.getChildren().add(child);
                final Child child1 = new Child();
                child1.setName("" + 4);
                child1.setText1("Child 1");
                parent.getChildren().add(child1);
                final Child child2 = new Child();
                child2.setName("" + 5);
                child2.setText1("Child 2");
                parent.getChildren().add(child2);
                final Child child3 = new Child();
                child3.setName("" + 6);
                child3.setText1("Child 3");
                parent.getChildren().add(child3);
            }
            else if(i==4 ){
                parent.setName("" + (i-1));
                parent.setText1("Parent "+ (i-1));
                parent.setText2("Show App Icon on \nnotification bar");
                parent.setChildren(new ArrayList<Child>());

                final Child child = new Child();
                child.setName("" + 7);
                child.setText1("Child 0");
                parent.getChildren().add(child);
                final Child child1 = new Child();
                child1.setName("" + 8);
                child1.setText1("Child 1");
                parent.getChildren().add(child1);
                final Child child2 = new Child();
                child2.setName("" + 9);
                child2.setText1("Child 2");
                parent.getChildren().add(child2);
                final Child child3 = new Child();
                child3.setName("" + 10);
                child3.setText1("Child 3");
                parent.getChildren().add(child3);
            }else if(i==5 ){
                parent.setName("" + (i-1));
                parent.setText1("Parent "+ (i-1));
                parent.setText2("Show App Icon on \nnotification bar");
                parent.setChildren(new ArrayList<Child>());

                final Child child = new Child();
                child.setName("" + 11);
                child.setText1("Child 0");
                parent.getChildren().add(child);
                final Child child1 = new Child();
                child1.setName("" + 12);
                child1.setText1("Child 1");
                parent.getChildren().add(child1);
                final Child child2 = new Child();
                child2.setName("" + 13);
                child2.setText1("Child 2");
                parent.getChildren().add(child2);
                final Child child3 = new Child();
                child3.setName("" + 14);
                child3.setText1("Child 3");
                parent.getChildren().add(child3);
            }

            //Adding Parent class object to ArrayList
            list.add(parent);
        }
        return list;
    }
    private void loadHosts(final ArrayList<Parent> newParents)
    {
        if (newParents == null)
            return;

        parents = newParents;

        // Check for ExpandableListAdapter object
        if (this.mExla == null)
        {
            //Create ExpandableListAdapter Object
            final MyExpandableListAdapter mAdapter = new MyExpandableListAdapter();
mExla=mAdapter;
            // Set Adapter to ExpandableList Adapter
            //lv.setListAdapter(mExla);

        }
        else
        {
            // Refresh ExpandableListView data
            mExla.notifyDataSetChanged();
        }
    }
    private void checkButtonClick() {


        Button myButton = (Button) rootView.findViewById(R.id.findSelect);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Parent> parentList = parents;
                for(int i=0;i<parentList.size();i++){
                   Parent parent = parentList.get(i);
                    ArrayList<Child> childList = parent.getChildren();
                    responseText.append("\n" +"Child: " );
                    for(int j=0;j<childList.size();j++){
                        Child child = childList.get(j);
                        if (child.isChildChecked()){
                            responseText.append( child.getName()+", ");
                        }
                    }
                    if(parent.isChecked()){
                        responseText.append("-Parent: "+ parent.getName());
                    }
                }
                Log.i("checker",responseText+"");
                Toast.makeText(getActivity(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }
    private class MyExpandableListAdapter extends BaseExpandableListAdapter {
       // View view;
        //ViewGroup viewGroup;

        private LayoutInflater inflater;

        public MyExpandableListAdapter() {
            // Create Layout Inflator
            inflater = LayoutInflater.from(getActivity());
        }

        // This Function used to inflate parent rows view

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parentView) {
            final Parent parent = parents.get(groupPosition);

            //viewGroup=parentView;
            // Inflate grouprow.xml file for parent rows

                convertView = inflater.inflate(R.layout.grouprow, parentView, false);


            // Get grouprow.xml file elements and set values
            ((TextView) convertView.findViewById(R.id.text1)).setText(parent.getText1());
            ((TextView) convertView.findViewById(R.id.text)).setText(parent.getText2());


            ImageView rightcheck = (ImageView) convertView.findViewById(R.id.rightcheck);

            //Log.i("onCheckedChanged", "isChecked: "+parent.isChecked());

            // Change right check image on parent at runtime
            if (parent.isChecked() == true) {
                rightcheck.setImageResource(
                        getResources().getIdentifier(
                                "com.getgarage:drawable/about", null, null));
            } else {
                rightcheck.setImageResource(
                        getResources().getIdentifier(
                                "com.getgarage:drawable/help", null, null));
            }

            // Get grouprow.xml file checkbox elements
            final CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
            checkbox.setChecked(parent.isChecked());

            // Set CheckUpdateListener for CheckBox (see below CheckUpdateListener class)
            checkbox.setOnCheckedChangeListener(new CheckUpdateListener(parent));

            return convertView;
        }


        // This Function used to inflate child rows view
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parentView) {
            final Parent parent = parents.get(groupPosition);
            final Child child = parent.getChildren().get(childPosition);
            // view=convertView;
            //viewGroup=parentView;
            // Inflate childrow.xml file for child rows
            //if(convertView==null){
                convertView = inflater.inflate(R.layout.childrow, parentView, false);


            // Get childrow.xml file elements and set values
            ((TextView) convertView.findViewById(R.id.text1)).setText(child.getText1());
            //ImageView image = (ImageView) convertView.findViewById(R.id.image);

            ImageView imgcheck = (ImageView) convertView.findViewById(R.id.image_check);

            if (child.isChildChecked() == true) {
                imgcheck.setImageResource(
                        getResources().getIdentifier(
                                "com.getgarage:drawable/about", null, null));
            } else {
                imgcheck.setImageResource(
                        getResources().getIdentifier(
                                "com.getgarage:drawable/help", null, null));
            }

            // Get grouprow.xml file checkbox elements
            CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox2);
            checkbox.setChecked(child.isChildChecked());
            //// CheckBox checkbox2 = (CheckBox) convertView.findViewById(R.id.checkbox);
            // checkbox2.setChecked(parent.isChecked());
            // Set CheckUpdateListener for CheckBox (see below CheckUpdateListener class)
            checkbox.setOnCheckedChangeListener(new CheckChildUpdateListener(child,parent));
            // checkbox2.setOnCheckedChangeListener(new CheckUpdateListener(parent));
            return convertView;


        }


        @Override
        public Object getChild(int groupPosition, int childPosition) {
            Log.i("Childs", groupPosition + "=  getChild ==" + childPosition);
            return parents.get(groupPosition).getChildren().get(childPosition);
        }

        //Call when child row clicked
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            /****** When Child row clicked then this function call *******/

            Log.i("Noise", "parent == "+groupPosition+"=  child : =="+childPosition);
            if (ChildClickStatus != childPosition) {
                ChildClickStatus = childPosition;
/*
                Toast.makeText(getApplicationContext(), "Parent :" + groupPosition + " Child :" + childPosition,
                        Toast.LENGTH_SHORT).show();
                        */
            }

            return childPosition;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            int size = 0;
            if (parents.get(groupPosition).getChildren() != null)
                size = parents.get(groupPosition).getChildren().size();
            return size;
        }


        @Override
        public Object getGroup(int groupPosition) {
            Log.i("Parent", groupPosition + "=  getGroup ");

            return parents.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return parents.size();
        }

        //Call when parent row clicked
        @Override
        public long getGroupId(int groupPosition) {
            Log.i("Parent", groupPosition + "=  getGroupId " + ParentClickStatus);

            if (groupPosition == 4 && ParentClickStatus != groupPosition) {

    /*            //Alert to user
                Toast.makeText(getApplicationContext(), "Parent see :" + groupPosition,
                        Toast.LENGTH_SHORT).show();*/
            }

            ParentClickStatus = groupPosition;
            if (ParentClickStatus == 0)
                ParentClickStatus = -1;

            return groupPosition;
        }

        @Override
        public void notifyDataSetChanged() {
            // Refresh List rows
            super.notifyDataSetChanged();
        }

        @Override
        public boolean isEmpty() {
            return ((parents == null) || parents.isEmpty());
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }


        /**
         * **************** Checkbox Checked Change Listener *******************
         */
        private final class CheckChildUpdateListener implements CompoundButton.OnCheckedChangeListener
        {
            private final Child child;
            private final Parent parent;
            private CheckChildUpdateListener(Child child, Parent parent) {
                this.child = child;
                this.parent=parent;
            }

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("onCheckedChanged", "isChildChecked: " + isChecked);
                child.setChildChecked(isChecked);

//
                ArrayList<Child> children=parent.getChildren();

                int s = children.size();
                Child[] childrens = new Child[s];

                int i=0;
                for(Child c:children){
                    childrens[i++]=c;
                }
                int ii=0;

                int count=0;
                while(ii<children.size()){
                    if(childrens[ii].isChildChecked()){
                        ii++;
                        count++;
                    }else{
                        ii++;
                    }
                }
                if(count!=0){
                    parent.setChecked(true);
                }else{
                    parent.setChecked(false);
                    lv.collapseGroup(Integer.valueOf(parent.getName()));
                }
//

                mExla.notifyDataSetChanged();

                final Boolean checked = child.isChildChecked();

                Toast.makeText(getActivity(),
                        "Child : " + child.getName() + " of Parent:" + parent.getName() + " " + (checked ? STR_CHECKED : STR_UNCHECKED),
                        Toast.LENGTH_SHORT).show();

            }
        }
        private final class CheckUpdateListener implements CompoundButton.OnCheckedChangeListener
        {
            private final Parent parent;

            private CheckUpdateListener(Parent parent)
            {
                this.parent = parent;
            }
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {

                Log.i("onCheckedChanged", "isChecked: " + isChecked);
                parent.setChecked(isChecked);
                //
                ArrayList<Child> children=parent.getChildren();

                int s = children.size();
                Child[] childrens = new Child[s];

                int i=0;
                for(Child c:children){
                    childrens[i++]=c;
                }

                //


                //final Boolean checked = parent.isChecked();



                if(isChecked){
                    lv.expandGroup(Integer.valueOf(parent.getName()));

                }else{
                    for(Child c:childrens){
                        if(c.isChildChecked()){                c.setChildChecked(false);}
                    }
                    lv.collapseGroup(Integer.valueOf(parent.getName()));
                }
                mExla.notifyDataSetChanged();
  /*              Toast.makeText(getApplicationContext(),
                        "Parent : "+parent.getName() + " " + (checked ? STR_CHECKED : STR_UNCHECKED),
                        Toast.LENGTH_SHORT).show();
                        */
            }

        }
        /***********************************************************************/

    }
    /*
    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;

        public ExpandableListAdapter(String[] groups, String[][] children) {
            this.groups = groups;
            this.children = children;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.childrow, parent, false);
                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.grouprow, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }
    */
}