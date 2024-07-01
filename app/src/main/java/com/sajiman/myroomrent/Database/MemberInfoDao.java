package com.sajiman.myroomrent.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sajiman.myroomrent.Dtos.MemberInfoDto;

import java.util.ArrayList;
import java.util.List;


public class MemberInfoDao {

    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase database;
    private String[] allColumns = new String[]{
            DbOpenHelper.COLUMN_ID_MAIN,
            DbOpenHelper.COLUMN_NAME,
            DbOpenHelper.COLUMN_ADDRESS,
            DbOpenHelper.COLUMN_CONTACT
    };

    private String[] allNameColumn = new String[]{
            DbOpenHelper.COLUMN_NAME
    };

    public MemberInfoDao(Context context) {
        dbOpenHelper = new DbOpenHelper(context);
    }

    public long insertIntoMemberInfo(MemberInfoDto memberInfoDto) {

        database = dbOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.COLUMN_NAME, memberInfoDto.getName());
        values.put(DbOpenHelper.COLUMN_ADDRESS, memberInfoDto.getAddress());
        values.put(DbOpenHelper.COLUMN_CONTACT, memberInfoDto.getContact());

        long insertTestMemberInfo;
        try {
            insertTestMemberInfo = database.insert(DbOpenHelper.MEMBER_INFO_TABLE, null, values);
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return insertTestMemberInfo;
    }

    public int update(MemberInfoDto memberInfoDto) {
        database = dbOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.COLUMN_NAME, memberInfoDto.getName());
        values.put(DbOpenHelper.COLUMN_ADDRESS, memberInfoDto.getAddress());
        values.put(DbOpenHelper.COLUMN_CONTACT, memberInfoDto.getContact());

        int affectedUpdatedRows;
        try {
            affectedUpdatedRows = database.update(DbOpenHelper.MEMBER_INFO_TABLE, values, DbOpenHelper.COLUMN_ID_MAIN + "=?", new String[]{String.valueOf(memberInfoDto.getId())});
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return affectedUpdatedRows;
    }

    public List<MemberInfoDto> getMemberInfo() {
        database = dbOpenHelper.getReadableDatabase();

        List<MemberInfoDto> memberInfoDtoList = new ArrayList<>();

        Cursor cursor = database.query(DbOpenHelper.MEMBER_INFO_TABLE, allColumns, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int mainId = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ID_MAIN));
                    String name = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_NAME));
                    String address = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_ADDRESS));
                    String contact = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_CONTACT));

                    MemberInfoDto memberInfoDto = new MemberInfoDto(mainId, name, address, contact);
                    memberInfoDtoList.add(memberInfoDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return memberInfoDtoList;
    }

    public List<MemberInfoDto> getAllMemberName() {
        database = dbOpenHelper.getReadableDatabase();

        List<MemberInfoDto> memberNameList = new ArrayList<>();
        Cursor cursor = database.query(DbOpenHelper.MEMBER_INFO_TABLE, allNameColumn, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_NAME));

                    MemberInfoDto memberInfoDto = new MemberInfoDto(name);
                    memberNameList.add(memberInfoDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return memberNameList;
    }

    public int delete(MemberInfoDto memberInfoDto) {
        database = dbOpenHelper.getWritableDatabase();

        int affectedDeletedRow;
        try {
            affectedDeletedRow = database.delete(DbOpenHelper.MEMBER_INFO_TABLE, DbOpenHelper.COLUMN_ID_MAIN + "=?",
                    new String[]{String.valueOf(memberInfoDto.getId())});
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return affectedDeletedRow;
    }
}
