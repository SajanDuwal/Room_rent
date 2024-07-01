package com.sajiman.myroomrent.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

    /* initializing MEMBER_INFO_TABLE */
    public static final String MEMBER_INFO_TABLE = "member_info_table";
    public static final String COLUMN_ID_MAIN = "_id_main";
    public static final String COLUMN_IMAGE_PATH = "_image_path";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_ADDRESS = "_address";
    public static final String COLUMN_CONTACT = "_contact";

    /* initializing ROOM_INFO_TABLE */
    public static final String ROOM_INFO_TABLE = "room_info_table";
    public static final String COLUMN_ID_ROOM_INFO = "_id_room_info";
    public static final String COLUMN_ROOM_ID = "_room_id";
    public static final String COLUMN_ROOM_CHARGE = "_room_charge";
    public static final String COLUMN_ELECTRICITY_INITIAL_UNIT = "_electricity_initial_unit";
    public static final String COLUMN_ELECTRICITY_FINAL_UNIT = "_electricity_final_unit";

    /* initializing PAYMENT_INFO_TABLE */
    public static final String PAYMENT_INFO_TABLE = "payment_info_table";
    public static final String COLUMN_ID_PAYMENT_INFO = "_id_payment_info";
    public static final String COLUMN_RENT_PAID_UPTO_MONTH = "_rent_paid_upto_month";
    public static final String COLUMN_PAID_ROOM_CHARGE = "_paid_room_charge";
    public static final String COLUMN_PAID_ELECTRICITY_CHARGE = "_paid_electricity_charge";
    public static final String COLUMN_DATE_OF_RENT_PAID = "_date_of_rent_paid";
    public static final String COLUMN_RENT_PAID_STATUS = "_status";


    public DbOpenHelper(Context context) {
        super(context, "rentRoom_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            /* creating MEMBER_INFO_TABLE */
        db.execSQL("CREATE TABLE " + MEMBER_INFO_TABLE + "(" +
                COLUMN_ID_MAIN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE_PATH + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_CONTACT + " TEXT)"
        );

            /* creating ROOM_INFO_TABLE */
        db.execSQL("CREATE TABLE " + ROOM_INFO_TABLE + "(" +
                COLUMN_ID_ROOM_INFO + " INTEGER, " +
                COLUMN_ROOM_ID + " INTEGER, " +
                COLUMN_ROOM_CHARGE + " DOUBLE, " +
                COLUMN_ELECTRICITY_INITIAL_UNIT + " DOUBLE, " +
                COLUMN_ELECTRICITY_FINAL_UNIT + " DOUBLE)"
        );

            /* creating PAYMENT_INFO_TABLE */
        db.execSQL("CREATE TABLE " + PAYMENT_INFO_TABLE + "(" +
                COLUMN_ID_PAYMENT_INFO + " INTEGER, " +
                COLUMN_RENT_PAID_UPTO_MONTH + " Integer, " +
                COLUMN_PAID_ROOM_CHARGE + " DOUBLE, " +
                COLUMN_PAID_ELECTRICITY_CHARGE + " DOUBLE, " +
                COLUMN_DATE_OF_RENT_PAID + " TEXT, " +
                COLUMN_RENT_PAID_STATUS + " INTEGER)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MEMBER_INFO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ROOM_INFO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PAYMENT_INFO_TABLE);
    }
}