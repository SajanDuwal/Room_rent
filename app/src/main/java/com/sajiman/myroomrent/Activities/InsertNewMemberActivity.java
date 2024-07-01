package com.sajiman.myroomrent.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sajiman.myroomrent.Database.MemberInfoDao;
import com.sajiman.myroomrent.Database.PaymentInfoDao;
import com.sajiman.myroomrent.Database.RoomInfoDao;
import com.sajiman.myroomrent.Dtos.MemberInfoDto;
import com.sajiman.myroomrent.Dtos.PaymentInfoDto;
import com.sajiman.myroomrent.Dtos.RoomInfoDto;
import com.sajiman.myroomrent.R;
import com.sajiman.myroomrent.Utils.AppLogUtils;
import com.sajiman.myroomrent.Utils.MiscellaneousUtils;

import java.util.ArrayList;
import java.util.List;

public class InsertNewMemberActivity extends AppCompatActivity implements View.OnClickListener {

    private NestedScrollView mInsertNestedScrollView;
    private Toolbar mTbInsertNewMember;
    private ImageView mIvMemberImage;
    private EditText mEtName;
    private EditText mEtAddress;
    private EditText mEtContact;
    private EditText mEtRoomID;
    private TextView mTvExistedRoomId;
    private EditText mEtRoomCharge;
    private EditText mEtElectricityInitialUnit;
    private List<Integer> roomIDList;
    private Spinner mSpinnerMonth;
    private int paidMonthPosition;
    private List<Integer> mRoomIdList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_member);

        mTbInsertNewMember = findViewById(R.id.toolbarAddNewTenants);
        setSupportActionBar(mTbInsertNewMember);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mInsertNestedScrollView = findViewById(R.id.insertNestedScrollView);
        MiscellaneousUtils.AnimationFadeIn(InsertNewMemberActivity.this, mInsertNestedScrollView);
        initMemberInfo();
    }

    private void initMemberInfo() {
        mIvMemberImage = findViewById(R.id.ivTenantImage);
        mEtName = findViewById(R.id.etName);
        mEtAddress = findViewById(R.id.etAddress);
        mEtContact = findViewById(R.id.etContact);
        mEtRoomID = findViewById(R.id.etRoomId);
        mTvExistedRoomId = findViewById(R.id.tvExistedRoomId);
        mEtRoomCharge = findViewById(R.id.etRoomCharge);
        mEtElectricityInitialUnit = findViewById(R.id.etElectricityInitialUnit);
        mSpinnerMonth = findViewById(R.id.spinnerMonth);

        /* getting all roomId from db */
        RoomInfoDao roomInfoDao = new RoomInfoDao(InsertNewMemberActivity.this);
        List<RoomInfoDto> roomIdList = roomInfoDao.getRoomId();

        mRoomIdList = new ArrayList<>();
        for (RoomInfoDto temVar : roomIdList) {
            mRoomIdList.add(temVar.getRoomId());
        }

        StringBuilder finalRoomId = new StringBuilder();
        for (int i = 0; i < mRoomIdList.size(); i++) {
            finalRoomId.append(mRoomIdList.get(i));
            if (i != (roomIdList.size() - 1)) {
                finalRoomId.append(", ");
            }
        }

        AppLogUtils.showLog(" " + InsertNewMemberActivity.class.getSimpleName(),
                "room Id = " + finalRoomId);

        if (!mRoomIdList.isEmpty()) {
            mTvExistedRoomId.setText(String.format("Set Room Id beside : %s", finalRoomId.toString()));
            mTvExistedRoomId.setVisibility(View.VISIBLE);
        } else {
            mTvExistedRoomId.setVisibility(View.GONE);
        }


        List<String> monthList = new ArrayList<>();
        monthList.add("none");
        monthList.add("बैशाख");
        monthList.add("जेठ");
        monthList.add("असार");
        monthList.add("श्रावण");
        monthList.add("भदौ");
        monthList.add("आश्विन");
        monthList.add("कार्तिक");
        monthList.add("मंसिर");
        monthList.add("पुष");
        monthList.add("माघ");
        monthList.add("फाल्गुन");
        monthList.add("चैत्र");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(InsertNewMemberActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, monthList);
        mSpinnerMonth.setAdapter(spinnerAdapter);
        mSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getMonthPositionOfPaidRent(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.ivTenantImage).setOnClickListener(this);
        findViewById(R.id.btnSave).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTenantImage:
                new AlertDialog.Builder(InsertNewMemberActivity.this)
                        .setItems(new String[]{"Take photo", "Choose photo from gallery"},
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                break;
                                            case 1:
                                                break;
                                        }
                                        dialog.dismiss();
                                    }
                                })
                        .create()
                        .show();
                break;

            case R.id.btnSave:
                if (isValid()) {
                    String name = MiscellaneousUtils.capitalize(mEtName.getText().toString());
                    String address = MiscellaneousUtils.capitalize(mEtAddress.getText().toString());
                    String contact = mEtContact.getText().toString();

                    int roomId = Integer.parseInt(mEtRoomID.getText().toString());
                    double roomCharge = Double.parseDouble(mEtRoomCharge.getText().toString());
                    double electricityInitialUnit = Double.parseDouble(mEtElectricityInitialUnit.getText().toString());
                    double electricityFinalUnit = 0.0;

                    int rentPaidUptoMonth = paidMonthPosition;
                    double paidRoomCharge = 0.0;
                    double paidElectricityCharge = 0.0;
                    String dateOfRentPaid = "";

                    MemberInfoDto memberInfoDto = new MemberInfoDto();
                    memberInfoDto.setName(name);
                    memberInfoDto.setAddress(address);
                    memberInfoDto.setContact(contact);

                    MemberInfoDao memberInfoDao = new MemberInfoDao(InsertNewMemberActivity.this);

                    if (!mRoomIdList.contains(roomId)) {
                        long insertTestMemberInfo;
                        insertTestMemberInfo = memberInfoDao.insertIntoMemberInfo(memberInfoDto);

                        int commonId;

                        if (insertTestMemberInfo == -1) {
                            Snackbar snackbarInsertFailed = Snackbar.make(v,
                                    "Registration Field in Member Info Table, please contact admin!!!",
                                    Snackbar.LENGTH_SHORT);
                            View snackbarInsertFailedView = snackbarInsertFailed.getView();
                            snackbarInsertFailedView.setBackgroundColor(Color.BLACK);
                            TextView tvErrorMessage = snackbarInsertFailedView.
                                    findViewById(android.support.design.R.id.snackbar_text);
                            tvErrorMessage.setTextColor(Color.RED);
                            snackbarInsertFailed.show();
                        } else {
                            commonId = (int) insertTestMemberInfo;

                            RoomInfoDto roomInfoDto = new RoomInfoDto();
                            roomInfoDto.setRoomInfoId(commonId);
                            roomInfoDto.setRoomId(roomId);
                            roomInfoDto.setRoomCharge(roomCharge);
                            roomInfoDto.setElectricityInitialUnit(electricityInitialUnit);
                            roomInfoDto.setElectricityFinalUnit(electricityFinalUnit);

                            RoomInfoDao roomInfoDao = new RoomInfoDao(InsertNewMemberActivity.this);
                            long insertTestRoomInfo;
                            insertTestRoomInfo = roomInfoDao.insertIntoRoomInfo(roomInfoDto);

                            if (insertTestRoomInfo == -1) {
                                Snackbar snackbarInsertFailed = Snackbar.make(v,
                                        "Registration Field in Room Info Table, please contact admin!!!", Snackbar.LENGTH_SHORT);
                                View snackbarInsertFailedView = snackbarInsertFailed.getView();
                                snackbarInsertFailedView.setBackgroundColor(Color.BLACK);
                                TextView tvErrorMessage = snackbarInsertFailedView.
                                        findViewById(android.support.design.R.id.snackbar_text);
                                tvErrorMessage.setTextColor(Color.RED);
                                snackbarInsertFailed.show();
                            } else {
                                PaymentInfoDto paymentInfoDto = new PaymentInfoDto();
                                paymentInfoDto.setPaymentInfoId(commonId);
                                paymentInfoDto.setRentClearedUptoMonth(rentPaidUptoMonth);
                                paymentInfoDto.setPaidRoomCharge(paidRoomCharge);
                                paymentInfoDto.setPaidElectricityCharge(paidElectricityCharge);
                                paymentInfoDto.setDateOfRentPaid(dateOfRentPaid);
                                paymentInfoDto.setStatus(false);

                                PaymentInfoDao paymentInfoDao = new PaymentInfoDao(InsertNewMemberActivity.this);
                                long insertTestPaymentInfo;
                                insertTestPaymentInfo = paymentInfoDao.insertIntoPaymentInfo(paymentInfoDto);

                                if (insertTestPaymentInfo == -1) {
                                    Snackbar snackbarInsertFailed = Snackbar.make(v,
                                            "Registration Field in Payment Info Table, please contact admin!!!",
                                            Snackbar.LENGTH_SHORT);
                                    View snackbarInsertFailedView = snackbarInsertFailed.getView();
                                    snackbarInsertFailedView.setBackgroundColor(Color.BLACK);
                                    TextView tvErrorMessage = snackbarInsertFailedView.
                                            findViewById(android.support.design.R.id.snackbar_text);
                                    tvErrorMessage.setTextColor(Color.RED);
                                    snackbarInsertFailed.show();
                                } else {
                                    AppLogUtils.showLog("" + InsertNewMemberActivity.class.getSimpleName(),
                                            "Inserted " + memberInfoDto.toString() +
                                                    roomInfoDto.toString() + paymentInfoDto.toString());
                                    Toast.makeText(InsertNewMemberActivity.this,
                                            "Successfully Inserted", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(InsertNewMemberActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        }
                    } else {
                        mEtRoomID.requestFocus();
                        Snackbar snackbar = Snackbar.make(v, "RoomID existed, provide different RoomID!!!",
                                Snackbar.LENGTH_SHORT);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(Color.BLACK);
                        TextView tvErrorMessage = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        tvErrorMessage.setTextColor(Color.RED);
                        snackbar.show();
                    }
                    break;
                }
        }
    }

    private boolean isValid() {
        if (TextUtils.isEmpty(mEtName.getText().toString())) {
            mEtName.setError("Empty Field!");
            mEtName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mEtAddress.getText().toString())) {
            mEtAddress.setError("Empty Field!");
            mEtAddress.requestFocus();
            return false;
        }

        int contactLength = mEtContact.getText().toString().length();
        if (contactLength < 10 || contactLength > 10) {
            mEtContact.setError("Please check the number!");
            mEtContact.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mEtRoomID.getText().toString())) {
            mEtRoomID.setError("Empty Field!");
            mEtRoomID.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mEtRoomCharge.getText().toString())) {
            mEtRoomCharge.setError("Empty Field!");
            mEtRoomCharge.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mEtElectricityInitialUnit.getText().toString())) {
            mEtElectricityInitialUnit.setError("Empty Field!");
            mEtElectricityInitialUnit.requestFocus();
            return false;
        }
        return true;
    }

    public void getMonthPositionOfPaidRent(int position) {
        switch (position) {
            case 0:
                paidMonthPosition = 0;
                break;
            case 1:
                paidMonthPosition = 1;
                break;

            case 2:
                paidMonthPosition = 2;
                break;

            case 3:
                paidMonthPosition = 3;
                break;

            case 4:
                paidMonthPosition = 4;
                break;

            case 5:
                paidMonthPosition = 5;
                break;

            case 6:
                paidMonthPosition = 6;
                break;

            case 7:
                paidMonthPosition = 7;
                break;

            case 8:
                paidMonthPosition = 8;
                break;

            case 9:
                paidMonthPosition = 9;
                break;

            case 10:
                paidMonthPosition = 10;
                break;

            case 11:
                paidMonthPosition = 11;
                break;

            case 12:
                paidMonthPosition = 12;
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}