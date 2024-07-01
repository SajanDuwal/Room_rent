package com.sajiman.myroomrent.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sajiman.myroomrent.Dtos.PaymentInfoDto;

import java.util.ArrayList;
import java.util.List;

public class PaymentInfoDao {


    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase database;
    private String[] allColumn = new String[]{
            DbOpenHelper.COLUMN_RENT_PAID_UPTO_MONTH,
            DbOpenHelper.COLUMN_PAID_ROOM_CHARGE,
            DbOpenHelper.COLUMN_PAID_ELECTRICITY_CHARGE,
            DbOpenHelper.COLUMN_DATE_OF_RENT_PAID,
            DbOpenHelper.COLUMN_RENT_PAID_STATUS

    };
    private String[] rowPaymentInfo = new String[]{
            DbOpenHelper.COLUMN_RENT_PAID_UPTO_MONTH,
            DbOpenHelper.COLUMN_RENT_PAID_STATUS
    };

    public PaymentInfoDao(Context context) {
        dbOpenHelper = new DbOpenHelper(context);
    }

    public long insertIntoPaymentInfo(PaymentInfoDto paymentInfoDto) {
        database = dbOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.COLUMN_ID_PAYMENT_INFO, paymentInfoDto.getPaymentInfoId());
        values.put(DbOpenHelper.COLUMN_RENT_PAID_UPTO_MONTH, paymentInfoDto.getRentClearedUptoMonth());
        values.put(DbOpenHelper.COLUMN_PAID_ROOM_CHARGE, paymentInfoDto.getPaidRoomCharge());
        values.put(DbOpenHelper.COLUMN_PAID_ELECTRICITY_CHARGE, paymentInfoDto.getPaidElectricityCharge());
        values.put(DbOpenHelper.COLUMN_DATE_OF_RENT_PAID, paymentInfoDto.getDateOfRentPaid());
        values.put(DbOpenHelper.COLUMN_RENT_PAID_STATUS, paymentInfoDto.getStatus() ? 1 : 0);

        long insertTestPaymentInfo;
        try {
            insertTestPaymentInfo = database.insert(DbOpenHelper.PAYMENT_INFO_TABLE, null, values);
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return insertTestPaymentInfo;
    }


    public int update(PaymentInfoDto paymentInfoDto) {
        database = dbOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.COLUMN_ID_PAYMENT_INFO, paymentInfoDto.getPaymentInfoId());
        values.put(DbOpenHelper.COLUMN_RENT_PAID_UPTO_MONTH, paymentInfoDto.getRentClearedUptoMonth());

        int affectedUpdatedRows;
        try {
            affectedUpdatedRows = database.update(DbOpenHelper.PAYMENT_INFO_TABLE, values, DbOpenHelper.COLUMN_ID_PAYMENT_INFO + "=?", new String[]{String.valueOf(paymentInfoDto.getPaymentInfoId())});
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return affectedUpdatedRows;
    }

    public List<PaymentInfoDto> getPaymentInfo() {
        database = dbOpenHelper.getReadableDatabase();

        List<PaymentInfoDto> paymentInfoDtoList = new ArrayList<>();

        Cursor cursor = database.query(DbOpenHelper.PAYMENT_INFO_TABLE, allColumn, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int rentClearedUptoMonth = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_RENT_PAID_UPTO_MONTH));
                    double paidRoomCharge = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.COLUMN_PAID_ROOM_CHARGE));
                    double paidElectricityCharge = cursor.getDouble(cursor.getColumnIndex(DbOpenHelper.COLUMN_PAID_ELECTRICITY_CHARGE));
                    String dateOfRentCleared = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_DATE_OF_RENT_PAID));
                    Boolean status = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_RENT_PAID_STATUS)));

                    PaymentInfoDto paymentInfoDto = new PaymentInfoDto(rentClearedUptoMonth, paidRoomCharge, paidElectricityCharge, dateOfRentCleared, status);

                    paymentInfoDtoList.add(paymentInfoDto);
                    cursor.moveToNext();
                }
            }

            cursor.moveToNext();
        }
        database.close();
        dbOpenHelper.close();
        return paymentInfoDtoList;
    }

    public int delete(PaymentInfoDto paymentInfoDto) {
        database = dbOpenHelper.getWritableDatabase();

        int affectedDeletedRow;
        try {
            affectedDeletedRow = database.delete(DbOpenHelper.PAYMENT_INFO_TABLE, DbOpenHelper.COLUMN_ID_PAYMENT_INFO + "=?",
                    new String[]{String.valueOf(paymentInfoDto.getPaymentInfoId())});
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return affectedDeletedRow;
    }

    public List<PaymentInfoDto> getRowPaymentInfo() {
        database = dbOpenHelper.getReadableDatabase();

        List<PaymentInfoDto> rowPaymentInfoList = new ArrayList<>();

        Cursor cursor = database.query(DbOpenHelper.PAYMENT_INFO_TABLE, rowPaymentInfo, null, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int rentPaidUptoMonth = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_RENT_PAID_UPTO_MONTH));
                    Boolean status = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_RENT_PAID_STATUS)));

                    PaymentInfoDto paymentInfoDto = new PaymentInfoDto(rentPaidUptoMonth, status);
                    rowPaymentInfoList.add(paymentInfoDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return rowPaymentInfoList;
    }
}
