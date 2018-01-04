package com.getgarage.cards;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.getgarage.R;
import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private Boolean isfromloc=false;
    private static MyClickListenerReport myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView brandName;
        TextView modelName;
        TextView carPlateNumber;
        TextView mileage;
        ImageView brandLogo;


        public DataObjectHolder(View itemView) {
            super(itemView);
            brandName = (TextView) itemView.findViewById(R.id.brandname);
            mileage = (TextView) itemView.findViewById(R.id.mileage);
            modelName=(TextView) itemView.findViewById(R.id.modelname);
            carPlateNumber=(TextView) itemView.findViewById(R.id.carplatenumber);
            brandLogo=(ImageView)itemView.findViewById(R.id.brandlogo);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListenerReport myClickListener) {
        this.myClickListener = myClickListener;

    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset,Boolean isFromLocationScreen) {
        mDataset = myDataset;
        this.isfromloc=isFromLocationScreen;
    }
    View view;
    public static CardView mycardview;
    Context mycontext;
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
         view = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.card_view_row, parent, false);
        mycardview = (CardView) view.findViewById(R.id.card_view);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
       mycontext=parent.getContext();
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        if(position == 0 && isfromloc)

        {


            mycardview.setBackgroundColor(mycontext.getResources().getColor(R.color.needhelpcolor));


        }
        mycardview.setBackgroundColor(mycontext.getResources().getColor(R.color.cards));
        holder.brandName.setText(mDataset.get(position).getmText1());
        holder.modelName.setText(mDataset.get(position).getmText2());
        holder.carPlateNumber.setText(mDataset.get(position).getmText3());
        holder.mileage.setText(mDataset.get(position).getmText4());
        Bitmap bitmap = BitmapFactory.decodeByteArray(mDataset.get(position).getmImg1(), 0, mDataset.get(position).getmImg1().length);
        holder.brandLogo.setImageBitmap(bitmap);

    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public  void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListenerReport {
        public void onItemClick(int position, View v);
    }
}