package com.sajiman.myroomrent.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sajiman.myroomrent.Dtos.RoomInfoDto;

import java.util.ArrayList;
import java.util.List;

public class RoomInfoDao {

    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase database;

    private String[] allColumn = new String[]{
            DbOpenHelper.COLUMN_ROOM_ID,
            DbOpenHelper.COLUMN_ROOM_CHARGE,
            DbOpenHelper.COLUMN_ELECTRICITY_INITIAL_UNIT,
            DbOpenHelper.COLUMN_ELECTRICITY_FINAL_UNIT

    };

    private String[] roomIDColumn = new String[]{
            DbOpenHelper.COLUMN_ROOM_ID
    };


    public RoomInfoDao(Context context) {
        dbOpenHelper = new DbOpenHelper(context);
    }

    public long insertIntoRoomInfo(RoomInfoDto roomInfoDto) {

        database = dbOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.COLUMN_ID_ROOM_INFO, roomInfoDto.getRoomInfoId());
        values.put(DbOpenHelper.COLUMN_ROOM_ID, roomInfoDto.getRoomId());
        values.put(DbOpenHelper.COLUMN_ROOM_CHARGE, roomInfoDto.getRoomCharge());
        values.put(DbOpenHelper.COLUMN_ELECTRICITY_INITIAL_UNIT, roomInfoDto.getElectricityInitialUnit());
        values.put(DbOpenHelper.COLUMN_ELECTRICITY_FINAL_UNIT, roomInfoDto.getElectricityFinalUnit());

        long insertTestRoomInfo;
        try {
            insertTestRoomInfo = database.insert(DbOpenHelper.ROOM_INFO_TABLE, null, values);
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return insertTestRoomInfo;
    }


    public int update(RoomInfoDto roomInfoDto) {
        database = dbOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.COLUMN_ID_ROOM_INFO, roomInfoDto.getRoomInfoId());
        values.put(DbOpenHelper.COLUMN_ROOM_ID, roomInfoDto.getRoomId());
        values.put(DbOpenHelper.COLUMN_ROOM_CHARGE, roomInfoDto.getRoomCharge());
        values.put(DbOpenHelper.COLUMN_ELECTRICITY_INITIAL_UNIT, roomInfoDto.getElectricityInitialUnit());

        int affectedUpdatedRows;
        try {
            affectedUpdatedRows = database.update(DbOpenHelper.ROOM_INFO_TABLE, values, DbOpenHelper.COLUMN_ID_ROOM_INFO + "=?", new String[]{String.valueOf(roomInfoDto.getRoomInfoId())});
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return affectedUpdatedRows;
    }

    public List<RoomInfoDto> getRoomInfo() {
        database = dbOpenHelper.getReadableDatabase();

        List<RoomInfoDto> roomInfoDtoList = new ArrayList<>();

        Cursor cursor = database.query(DbOpenHelper.ROOM_INFO_TABLE, allColumn, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int roomID = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROOM_ID));
                    double roomCharge = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROOM_CHARGE));
                    double electricityInitialUnit = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.COLUMN_ELECTRICITY_INITIAL_UNIT));
                    double electricityFinalUnit = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.COLUMN_ELECTRICITY_FINAL_UNIT));

                    RoomInfoDto roomInfoDto = new RoomInfoDto(roomID, roomCharge, electricityInitialUnit, electricityFinalUnit);
                    roomInfoDtoList.add(roomInfoDto);
                    cursor.moveToNext();
                }
            }

            cursor.moveToNext();
        }
        database.close();
        dbOpenHelper.close();
        return roomInfoDtoList;
    }

    public List<RoomInfoDto> getRoomId() {

        database = dbOpenHelper.getReadableDatabase();

        List<RoomInfoDto> roomIdList = new ArrayList<>();

        Cursor cursor = database.query(DbOpenHelper.ROOM_INFO_TABLE, roomIDColumn, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int roomID = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROOM_ID));
                    RoomInfoDto roomInfoDto = new RoomInfoDto(roomID);
                    roomIdList.add(roomInfoDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return roomIdList;
    }

    public int delete(RoomInfoDto roomInfoDto) {
        database = dbOpenHelper.getWritableDatabase();

        int affectedDeletedRow;
        try {
            affectedDeletedRow = database.delete(DbOpenHelper.ROOM_INFO_TABLE, DbOpenHelper.COLUMN_ID_ROOM_INFO + "=?",
                    new String[]{String.valueOf(roomInfoDto.getRoomInfoId())});
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return affectedDeletedRow;
    }
}
