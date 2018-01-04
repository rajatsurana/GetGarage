package com.getgarage.getgaragedb;

/**
 * Created by Rajat on 22-10-2015.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper {

    // for our logs
    public static final String TAG = "DatabaseHandler";

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    protected static final String DATABASE_NAME = "getgarageDB";

    // table details
   /* public String tableName = "locations";
    public String fieldObjectId = "id";
    public String fieldObjectName = "name";
    */
    private static final String TABLE_BRANDS= "brands";
    private static final String KEY_BRAND_ID="brand_id";
    private static final String KEY_BRAND_NAME="brand_name";

    private static final String TABLE_MODELS= "models";
    private static final String KEY_MODEL_ID="model_id";
    private static final String KEY_MODEL_NAME="model_name";

    private static final String TABLE_MY_VEHICLES= "my_vehicles";
    private static final String KEY_MY_VEHICLE_ID="my_vehicle_id";
    private static final String KEY_MY_VEHICLE_MODEL_NAME="my_vehicle_model_name";
    private static final String KEY_MY_VEHICLE_MODEL_ID="my_vehicle_model_id";
    private static final String KEY_MY_VEHICLE_BRAND_NAME="my_vehicle_brand_name";
    private static final String KEY_MY_VEHICLE_BRAND_ID="my_vehicle_brand_id";
    private static final String KEY_MY_VEHICLE_MILEAGE="my_vehicle_mileage";
    private static final String KEY_MY_VEHICLE_PLATE_NUMBER="my_vehicle_plate_number";
    // constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static final String CREATE_TABLE_BRANDS = "CREATE TABLE "
            + TABLE_BRANDS + "(" +  KEY_BRAND_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_BRAND_NAME + " TEXT"
            + ")";
    private static final String CREATE_TABLE_MODELS = "CREATE TABLE "
            + TABLE_MODELS + "(" +  KEY_MODEL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_MODEL_NAME + " TEXT"
            + ")";
    private static final String CREATE_TABLE_MY_VEHICLES = "CREATE TABLE "
            + TABLE_MY_VEHICLES + "(" +  KEY_MY_VEHICLE_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_MY_VEHICLE_BRAND_ID + " INTEGER,"
            + KEY_MY_VEHICLE_BRAND_NAME + " TEXT,"
            + KEY_MY_VEHICLE_MODEL_ID + " INTEGER,"
            + KEY_MY_VEHICLE_MODEL_NAME + " TEXT,"
            + KEY_MY_VEHICLE_MILEAGE + " FLOAT,"
            + KEY_MY_VEHICLE_PLATE_NUMBER + " TEXT"
            + ")";
    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
/*
        String sql = "";

        sql += "CREATE TABLE " + tableName;
        sql += " ( ";
        sql += fieldObjectId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += fieldObjectName + " TEXT ";
        sql += " ) ";
        */

        db.execSQL(CREATE_TABLE_BRANDS);
        db.execSQL(CREATE_TABLE_MODELS);
        db.execSQL(CREATE_TABLE_MY_VEHICLES);

    }

    // When upgrading the database, it will drop the current table and recreate.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRANDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODELS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_VEHICLES);
        onCreate(db);
    }

    // create new record
    // @param myObj contains details to be added as single row.
    public boolean create(Brands myObj) {

        boolean createSuccessful = false;

        if(!checkBrandIfExists(myObj.brandName)){

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_BRAND_NAME, myObj.brandName);
            createSuccessful = db.insert(TABLE_BRANDS, null, values) > 0;

           // db.close();

            if(createSuccessful){
                Log.e(TAG, myObj.brandName + " created.");
            }
        }

        return createSuccessful;
    }
    public boolean createModels(Models myObj) {

        boolean createSuccessful = false;

        if(!checkModelIfExists(myObj.modelName)){

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_MODEL_NAME, myObj.modelName);
            createSuccessful = db.insert(TABLE_MODELS, null, values) > 0;

            // db.close();

            if(createSuccessful){
                Log.e(TAG, myObj.modelName + " created.");
            }
        }

        return createSuccessful;
    }
    public long createMyVehicle(MyVehicles myObj) {

        //boolean createSuccessful = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MY_VEHICLES, null);
        int count=0;
if (c.getCount()>0) {
    c.moveToLast();

    count = c.getInt(c.getColumnIndex(KEY_MY_VEHICLE_ID));
}
        values.put(KEY_MY_VEHICLE_ID,count+1);
        values.put(KEY_MY_VEHICLE_BRAND_NAME, myObj.getBrand());
        values.put(KEY_MY_VEHICLE_MODEL_NAME, myObj.getModel());
        values.put(KEY_MY_VEHICLE_MILEAGE, myObj.getMileage());
        values.put(KEY_MY_VEHICLE_PLATE_NUMBER, myObj.getPlatenumber());


        return db.insert(TABLE_MY_VEHICLES, null, values) ;
    }
    // check if a record exists so it won't insert the next time you run this code
    public boolean checkBrandIfExists(String objectName){

        boolean recordExists = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_BRAND_ID + " FROM " + TABLE_BRANDS + " WHERE " + KEY_BRAND_NAME + " = '" + objectName + "'", null);

        if(cursor!=null) {

            if(cursor.getCount()>0) {
                recordExists = true;
            }
        }

        cursor.close();
       // db.close();

        return recordExists;
    }
    public boolean checkModelIfExists(String objectName){

        boolean recordExists = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_MODEL_ID + " FROM " + TABLE_MODELS + " WHERE " + KEY_MODEL_NAME + " = '" + objectName + "'", null);

        if(cursor!=null) {

            if(cursor.getCount()>0) {
                recordExists = true;
            }
        }

        cursor.close();
        // db.close();

        return recordExists;
    }


    // Read records related to the search term
    public List<Brands> read(String searchTerm) {

        List<Brands> recordsList = new ArrayList<Brands>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_BRANDS;
        sql += " WHERE " + KEY_BRAND_NAME + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + KEY_BRAND_ID+ " DESC";
        sql += " LIMIT 0,5";

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex(KEY_BRAND_NAME));
                Brands Brands = new Brands(objectName);

                // add to list
                recordsList.add(Brands);

            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return the list of records
        return recordsList;
    }
    public List<Models> readModels(String searchTerm) {

        List<Models> recordsList = new ArrayList<Models>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_MODELS;
        sql += " WHERE " + KEY_MODEL_NAME + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + KEY_MODEL_ID+ " DESC";
        sql += " LIMIT 0,5";

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex(KEY_MODEL_NAME));
                Brands brands = new Brands(objectName);
Models model= new Models(objectName);
                // add to list
                recordsList.add(model);

            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return the list of records
        return recordsList;
    }
    public List<Brands> readAll() {

        List<Brands> recordsList = new ArrayList<Brands>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_BRANDS;


        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex(KEY_BRAND_NAME));
                Brands brands = new Brands(objectName);

                // add to list
                recordsList.add(brands);

            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return the list of records
        return recordsList;
    }
    public List<MyVehicles> readAllMyVehicles() {

        List<MyVehicles> recordsList = new ArrayList<MyVehicles>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_MY_VEHICLES ;


        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int myVehicleId= cursor.getInt(cursor.getColumnIndex(KEY_MY_VEHICLE_ID));
                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String brand  = cursor.getString(cursor.getColumnIndex(KEY_MY_VEHICLE_BRAND_NAME));
                String model  = cursor.getString(cursor.getColumnIndex(KEY_MY_VEHICLE_MODEL_NAME));
                String plateNumb= cursor.getString(cursor.getColumnIndex(KEY_MY_VEHICLE_PLATE_NUMBER));
                String mileag= cursor.getString(cursor.getColumnIndex(KEY_MY_VEHICLE_MILEAGE));

                MyVehicles mv= new MyVehicles(myVehicleId,brand,model,plateNumb,Double.valueOf(mileag));
                // add to list
                recordsList.add(mv);

            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return the list of records
        return recordsList;
    }

    public void updateMyVehicle(MyVehicles myObj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(KEY_MY_VEHICLE_BRAND_NAME, myObj.getBrand());
        values.put(KEY_MY_VEHICLE_MODEL_NAME, myObj.getModel());
        values.put(KEY_MY_VEHICLE_MILEAGE, myObj.getMileage());
        values.put(KEY_MY_VEHICLE_PLATE_NUMBER, myObj.getPlatenumber());
        // Credentials cred = new Credentials();


        db.update(TABLE_MY_VEHICLES, values, KEY_MY_VEHICLE_ID + "=" + myObj.myVehicleId, null);
    }

    public MyVehicles readMyVehicleAtPosition(int position) {
        List<MyVehicles> recordsList = new ArrayList<MyVehicles>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_MY_VEHICLES +" WHERE " + KEY_MY_VEHICLE_ID + " = '" + position + "'";


        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);
        MyVehicles mv=null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {


                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String brand  = cursor.getString(cursor.getColumnIndex(KEY_MY_VEHICLE_BRAND_NAME));
                String model  = cursor.getString(cursor.getColumnIndex(KEY_MY_VEHICLE_MODEL_NAME));
                String plateNumb= cursor.getString(cursor.getColumnIndex(KEY_MY_VEHICLE_PLATE_NUMBER));
                String mileag= cursor.getString(cursor.getColumnIndex(KEY_MY_VEHICLE_MILEAGE));

                mv= new MyVehicles(brand,model,plateNumb,Double.valueOf(mileag));
                // add to list
                //recordsList.add(mv);


        }

        cursor.close();
        //db.close();

        // return the list of records
        return mv;
    }

    public void deleteMyVehicle(int cardId) {
        //SELECT MAX( `column` ) FROM `table` ;
        //ALTER TABLE `table` AUTO_INCREMENT = number;

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " +TABLE_MY_VEHICLES +" WHERE " +KEY_MY_VEHICLE_ID +" = '" +cardId+"'";
        //Cursor cursorDelete = db.rawQuery(sql, null);
        db.execSQL(sql);
       // cursorDelete.close();
      /*
        int prev = cardPos-1;
        String sqlCount = "SELECT "+KEY_MY_VEHICLE_BRAND_NAME+" FROM "+ TABLE_MY_VEHICLES;
        Cursor cursorCount = db.rawQuery(sqlCount, null);

        int offset=cursorCount.getCount()-cardPos;

        String sqliChange = "SELECT "+KEY_MY_VEHICLE_ID+" FROM "+TABLE_MY_VEHICLES +" LIMIT '" +prev +"','"+offset+"'";
            Cursor cursorUpdate=db.rawQuery(sqliChange,null);

       // MyVehicles mv=null;
Log.i("rajat",cursorUpdate.getCount()+" "+cursorCount.getCount()+" "+offset);
             //db.delete(TABLE_MY_VEHICLES, KEY_MY_VEHICLE_ID + "=" + cardPos, null);
        int in=0;
        //cursorCount.moveToFirst();
        if (cursorUpdate.moveToFirst()) {
            //cursorCount.moveToFirst();
            do {
                cursorCount.moveToPosition(cardPos-1+in);

                String sqliUpd= "UPDATE "+TABLE_MY_VEHICLES +" SET "+ KEY_MY_VEHICLE_ID +" = "+ cardPos+in +" WHERE "+ KEY_MY_VEHICLE_BRAND_NAME +" = "+ cursorCount.getString(cursorCount.getColumnIndex(KEY_MY_VEHICLE_BRAND_NAME)) ;

                db.execSQL(sqliUpd);
                //
                ContentValues cv = new ContentValues();
                cv.put(KEY_MY_VEHICLE_ID,cardPos+in);

                //db.update(TABLE_MY_VEHICLES,cv,KEY_MY_VEHICLE_BRAND_NAME + " = "+cursorCount.getString(cursorCount.getColumnIndex(KEY_MY_VEHICLE_BRAND_NAME)) ,null);
                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));

                in++;
                //MyVehicles mv= new MyVehicles(brand,model,plateNumb,Double.valueOf(mileag));
                // add to list
                //recordsList.add(mv);

            } while (cursorUpdate.moveToNext());
        }*/
    }
    public void deleteAllMyVehicle() {
        //SELECT MAX( `column` ) FROM `table` ;
        //ALTER TABLE `table` AUTO_INCREMENT = number;

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_MY_VEHICLES ;//+ " WHERE " + KEY_MY_VEHICLE_ID + " = '" + cardPos + "'";
        //Cursor cursorDelete = db.rawQuery(sql, null);
        db.execSQL(sql);
    }
}