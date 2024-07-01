package com.sajiman.myroomrent.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sajiman.myroomrent.Dtos.RoomRentDto;

import java.util.ArrayList;
import java.util.List;

public class RoomRentDao {

    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase database;
    private String[] roomIdArray = new String[]{
            DbOpenHelper.COLUMN_ROOM_ID
    };

    public RoomRentDao(Context context) {
        dbOpenHelper = new DbOpenHelper(context);
    }

    public List<RoomRentDto> getAllInfo() {

        database = dbOpenHelper.getReadableDatabase();

        List<RoomRentDto> roomRentDtoList = new ArrayList<>();

        Cursor cursor = database.rawQuery("select " +
                "mi." + DbOpenHelper.COLUMN_ID_MAIN + ", mi." + DbOpenHelper.COLUMN_NAME + ", mi." + DbOpenHelper.COLUMN_ADDRESS + ", mi." + DbOpenHelper.COLUMN_CONTACT +
                ", ri." + DbOpenHelper.COLUMN_ROOM_ID + ", ri." + DbOpenHelper.COLUMN_ROOM_CHARGE + ", ri." + DbOpenHelper.COLUMN_ELECTRICITY_INITIAL_UNIT + ", ri. " + DbOpenHelper.COLUMN_ELECTRICITY_FINAL_UNIT +
                ", pi." + DbOpenHelper.COLUMN_RENT_PAID_UPTO_MONTH + ", pi." + DbOpenHelper.COLUMN_PAID_ROOM_CHARGE + ", pi." + DbOpenHelper.COLUMN_PAID_ELECTRICITY_CHARGE + ", pi." + DbOpenHelper.COLUMN_DATE_OF_RENT_PAID + ", pi." + DbOpenHelper.COLUMN_RENT_PAID_STATUS +
                " from " + DbOpenHelper.MEMBER_INFO_TABLE + " as mi, " + DbOpenHelper.ROOM_INFO_TABLE + " as ri, " + DbOpenHelper.PAYMENT_INFO_TABLE + " as pi " +
                "where mi." + DbOpenHelper.COLUMN_ID_MAIN + "=ri." + DbOpenHelper.COLUMN_ID_ROOM_INFO + " and pi." + DbOpenHelper.COLUMN_ID_PAYMENT_INFO + "=mi." + DbOpenHelper.COLUMN_ID_MAIN, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ID_MAIN));
                    String name = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_NAME));
                    String address = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_ADDRESS));
                    String contact = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_CONTACT));

                    int roomID = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROOM_ID));
                    double roomCharge = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROOM_CHARGE)));
                    double electricityInitialUnit = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_ELECTRICITY_INITIAL_UNIT)));
                    double electricityFinalUnit = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_ELECTRICITY_FINAL_UNIT)));

                    int rentClearedUptoMonth = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_RENT_PAID_UPTO_MONTH));
                    double paidRoomCharge = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_PAID_ROOM_CHARGE)));
                    double paidElectricityCharge = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_PAID_ELECTRICITY_CHARGE)));
                    String dateOfRentPaid = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_DATE_OF_RENT_PAID));
                    Boolean status = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_RENT_PAID_STATUS)) == 1;

                    RoomRentDto roomRentDto = new RoomRentDto(id, name, address, contact, roomID, roomCharge, electricityInitialUnit,
                            electricityFinalUnit, rentClearedUptoMonth, paidRoomCharge, paidElectricityCharge, dateOfRentPaid, status);

                    roomRentDtoList.add(roomRentDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return roomRentDtoList;
    }


    public List<RoomRentDto> getRoomId() {

        database = dbOpenHelper.getReadableDatabase();

        List<RoomRentDto> roomIdList = new ArrayList<>();

        Cursor cursor = database.query(DbOpenHelper.ROOM_INFO_TABLE, roomIdArray, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int roomID = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROOM_ID));
                    RoomRentDto roomRentDto = new RoomRentDto(roomID);
                    roomIdList.add(roomRentDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return roomIdList;
    }
}
