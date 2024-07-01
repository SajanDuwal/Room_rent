package com.sajiman.myroomrent.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sajiman.myroomrent.Dtos.RoomRentDto;
import com.sajiman.myroomrent.R;
import com.sajiman.myroomrent.Utils.MiscellaneousUtils;

public class AccountManagementActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private RoomRentDto roomRentDto;
    private Toolbar mTbAccountManagement;
    private NestedScrollView nestedScrollViewAccountManagement;
    private TextView mTvRoomCharge;
    private TextView mTvElectricityInitialUnit;
    private TextView mTvRoomId;
    private TextView mTvRentPaidUpto;
    private Switch mSwitchDayMonth;
    private RelativeLayout mRelativeLayoutMonthBill;
    private RelativeLayout mRelativeLayoutDayBill;
    private RelativeLayout mTvMonthBill;
    private RelativeLayout mTvDayBill;
    private RelativeLayout mTvElectricityBill;
    private EditText mEtElectricityFinalUnit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_management);
        mTbAccountManagement = findViewById(R.id.toolbarAccountManagement);
        setSupportActionBar(mTbAccountManagement);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        roomRentDto = getIntent().getParcelableExtra("ROOM_RENT_DTO");
        mTbAccountManagement.setTitle(roomRentDto.getName());
        mTbAccountManagement.setSubtitle("Account Section");

        initAccountComponents();

    }

    private void initAccountComponents() {
        nestedScrollViewAccountManagement = findViewById(R.id.nestedScrollViewAccountManagement);
        MiscellaneousUtils.AnimationFadeIn(AccountManagementActivity.this, nestedScrollViewAccountManagement);


        mTvRentPaidUpto = findViewById(R.id.tvRoomRentPaidUpto);
        int monthPosition = roomRentDto.getRentClearedUptoMonth();
        String monthName = MiscellaneousUtils.getMonthName(monthPosition);
        mTvRentPaidUpto.setText(monthName);
        mTvRoomId = findViewById(R.id.tvRoomIdAccountManagement);
        mTvRoomId.setText(String.valueOf(roomRentDto.getRoomId()));
        mTvRoomCharge = findViewById(R.id.tvRoomChargeAccountManagement);
        mTvRoomCharge.setText(String.valueOf(roomRentDto.getRoomCharge()));
        mTvElectricityInitialUnit = findViewById(R.id.tvElectricityInitialUnitAccountManagement);
        mTvElectricityInitialUnit.setText(String.valueOf(roomRentDto.getElectricityInitialUnit()));

        mSwitchDayMonth = findViewById(R.id.switchDayMonth);
        mRelativeLayoutDayBill = findViewById(R.id.relativeDayBill);
        mRelativeLayoutMonthBill = findViewById(R.id.relativeMonthBill);

        mSwitchDayMonth.setOnCheckedChangeListener(this);

        findViewById(R.id.btnShowMonths).setOnClickListener(this);
        findViewById(R.id.btnShowDays).setOnClickListener(this);
        findViewById(R.id.btnGrandTotal).setOnClickListener(this);
        findViewById(R.id.btnCashRevived).setOnClickListener(this);

        mTvMonthBill = findViewById(R.id.rlMonthOperation);
        mTvDayBill = findViewById(R.id.rlDayOperation);
        mTvElectricityBill = findViewById(R.id.rlElectricityOperation);
        mEtElectricityFinalUnit = findViewById(R.id.etElectricityFinalUnit);

        mEtElectricityFinalUnit.addTextChangedListener(mOnTextChangedElectricity);
    }

    TextWatcher mOnTextChangedElectricity = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                mTvElectricityBill.setVisibility(View.GONE);
            } else {
                mTvElectricityBill.setVisibility(View.VISIBLE);

                double electricityFinalUnit = Double.parseDouble(mEtElectricityFinalUnit.getText().toString());
                double totalElectricityUnit = (electricityFinalUnit - roomRentDto.getElectricityInitialUnit());

                TextView tvElectricityUnit = findViewById(R.id.tvElectricityUnit);
                tvElectricityUnit.setText(String.format("Total %s unit(s)", totalElectricityUnit));

                double totalElectricityCharge = (totalElectricityUnit * 15);
                TextView tvElectricityOperation = findViewById(R.id.tvElectricityOperation);
                tvElectricityOperation.setText(String.format("( %s - %s ) * 15 = %s", electricityFinalUnit, roomRentDto.getElectricityInitialUnit(), totalElectricityCharge));
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowMonths:
                mTvMonthBill.setVisibility(View.VISIBLE);

                break;

            case R.id.btnShowDays:
                mTvDayBill.setVisibility(View.VISIBLE);
                break;

            case R.id.btnGrandTotal:
                Toast.makeText(AccountManagementActivity.this, "Grand Total", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnCashRevived:
                Toast.makeText(AccountManagementActivity.this, "Cash Received", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switchDayMonth:
                if (isChecked) {
                    if (mRelativeLayoutDayBill.getVisibility() == View.GONE) {
                        mRelativeLayoutDayBill.setVisibility(View.VISIBLE);
                        mRelativeLayoutMonthBill.setVisibility(View.GONE);
                    }
                } else {
                    mRelativeLayoutMonthBill.setVisibility(View.VISIBLE);
                    mRelativeLayoutDayBill.setVisibility(View.GONE);
                }
        }
    }
}