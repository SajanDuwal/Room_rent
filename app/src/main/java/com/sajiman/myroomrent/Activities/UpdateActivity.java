package com.sajiman.myroomrent.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.sajiman.myroomrent.Dtos.RoomRentDto;
import com.sajiman.myroomrent.R;
import com.sajiman.myroomrent.Utils.MiscellaneousUtils;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mTbUpdateTenants;
    private NestedScrollView mUpdateNestedScrollView;
    private RoomRentDto beforeUpdateRoomRentDto;
    private ImageView ivUpdatedTenantsImage;
    private EditText mEtUpdatedName;
    private EditText mEtUpdatedAddress;
    private EditText mEtUpdatedContact;
    private EditText mEtUpdatedRoomID;
    private EditText mEtUpdatedRoomCharge;
    private EditText mEtUpdatedElectricityInitialUnit;
    private CheckBox mCbRoomIdUpdatePermission;
    private TextView mTvUpdateNoteRoomId;
    private Spinner mUpdateSpinnerMonth;
    private List<Integer> roomIdList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mTbUpdateTenants = findViewById(R.id.toolbarUpdateTenants);
        setSupportActionBar(mTbUpdateTenants);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mUpdateNestedScrollView = findViewById(R.id.updatedNestedScrollView);
        MiscellaneousUtils.AnimationFadeIn(UpdateActivity.this, mUpdateNestedScrollView);

        beforeUpdateRoomRentDto = getIntent().getParcelableExtra("ROOM_RENT_DTO");
        initMemberUpdateInfo();
    }

    private void initMemberUpdateInfo() {
        ivUpdatedTenantsImage = findViewById(R.id.ivUpdatedTenantImage);
       /* Log.e(UpdateActivity.class.getSimpleName(), "Image path: " + beforeUpdateMemberDto.getImagePath());
        if(beforeUpdateMemberDto.getImagePath() != null) {
            File file = new File(beforeUpdateMemberDto.getImagePath());
            ivUpdatedTenantsImage.setImageURI(Uri.fromFile(file));
        }*/
        mEtUpdatedName = findViewById(R.id.etUpdatedName);
        mEtUpdatedName.setText(beforeUpdateRoomRentDto.getName());
        mEtUpdatedAddress = findViewById(R.id.etUpdatedAddress);
        mEtUpdatedAddress.setText(beforeUpdateRoomRentDto.getAddress());
        mEtUpdatedContact = findViewById(R.id.etUpdatedContact);
        mEtUpdatedContact.setText(beforeUpdateRoomRentDto.getContact());
        mCbRoomIdUpdatePermission = findViewById(R.id.cbRoomIdUpdatePermission);
        mTvUpdateNoteRoomId = findViewById(R.id.tvUpdateExistedRoomId);
        mEtUpdatedRoomID = findViewById(R.id.etUpdatedRoomId);
        mEtUpdatedRoomID.setText(String.valueOf(beforeUpdateRoomRentDto.getRoomId()));

        mCbRoomIdUpdatePermission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEtUpdatedRoomID.setEnabled(true);

                    RoomInfoDao roomInfoDao = new RoomInfoDao(UpdateActivity.this);
                    List<RoomInfoDto> allRoomId = roomInfoDao.getRoomId();
                    roomIdList.clear();
                    for (RoomInfoDto tempVar : allRoomId) {
                        if (tempVar.getRoomId() != beforeUpdateRoomRentDto.getRoomId()) {
                            roomIdList.add(tempVar.getRoomId());
                        }
                    }

                    String roomIds = "";
                    for (int i = 0; i < roomIdList.size(); i++) {
                        roomIds += roomIdList.get(i);
                        if (i != roomIdList.size() - 1) {
                            roomIds += ", ";
                        }
                    }
                    if (roomIdList.size() != 0) {
                        mTvUpdateNoteRoomId.setVisibility(View.VISIBLE);
                        mTvUpdateNoteRoomId.setText(String.format("Set roomId beside: %s", roomIds));
                    }
                } else {
                    mEtUpdatedRoomID.setEnabled(false);
                    mTvUpdateNoteRoomId.setVisibility(View.GONE);
                }
            }
        });

        mEtUpdatedRoomCharge = findViewById(R.id.etUpdatedRoomCharge);
        mEtUpdatedRoomCharge.setText(String.valueOf(beforeUpdateRoomRentDto.getRoomCharge()));
        mEtUpdatedElectricityInitialUnit = findViewById(R.id.etUpdatedElectricityInitialUnit);
        mEtUpdatedElectricityInitialUnit.setText(String.valueOf(beforeUpdateRoomRentDto.getElectricityInitialUnit()));

        mUpdateSpinnerMonth = findViewById(R.id.updateSpinnerMonth);

        findViewById(R.id.ivUpdatedTenantImage).setOnClickListener(this);
        findViewById(R.id.btnUpdate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                if (isValid()) {
                    String updatedName = MiscellaneousUtils.capitalize(mEtUpdatedName.getText().toString());
                    String updatedAddress = MiscellaneousUtils.capitalize(mEtUpdatedAddress.getText().toString());
                    String updatedContact = mEtUpdatedContact.getText().toString();
                    int updatedRoomId = Integer.parseInt(mEtUpdatedRoomID.getText().toString());
                    double updatedRoomCharge = Double.parseDouble(mEtUpdatedRoomCharge.getText().toString());
                    double updatedElectricityInitialUnit = Double.parseDouble(mEtUpdatedElectricityInitialUnit.getText().toString());

                    MemberInfoDto memberInfoDto = new MemberInfoDto();
                    memberInfoDto.setId(beforeUpdateRoomRentDto.getId());
                    memberInfoDto.setName(updatedName);
                    memberInfoDto.setAddress(updatedAddress);
                    memberInfoDto.setContact(updatedContact);

                    RoomInfoDto roomInfoDto = new RoomInfoDto();
                    roomInfoDto.setRoomInfoId(beforeUpdateRoomRentDto.getId());
                    roomInfoDto.setRoomId(updatedRoomId);
                    roomInfoDto.setRoomCharge(updatedRoomCharge);
                    roomInfoDto.setElectricityInitialUnit(updatedElectricityInitialUnit);

                    PaymentInfoDto paymentInfoDto = new PaymentInfoDto();
                    paymentInfoDto.setPaymentInfoId(beforeUpdateRoomRentDto.getId());
                    paymentInfoDto.setRentClearedUptoMonth(4);

                    MemberInfoDao memberInfoDao = new MemberInfoDao(UpdateActivity.this);
                    int affectedRowMember = memberInfoDao.update(memberInfoDto);

                    if (affectedRowMember > 0) {
                        RoomInfoDao roomInfoDao = new RoomInfoDao(UpdateActivity.this);
                        int affectedRowRoom = roomInfoDao.update(roomInfoDto);

                        if (affectedRowRoom > 0) {
                            PaymentInfoDao paymentInfoDao = new PaymentInfoDao(UpdateActivity.this);
                            int affectedRowPayment = paymentInfoDao.update(paymentInfoDto);

                            if (affectedRowPayment > 0) {
                                Toast.makeText(UpdateActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(UpdateActivity.this, "Update failed in Payment Table, contact admin", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(UpdateActivity.this, "Update failed in Room Table, contact admin", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UpdateActivity.this, "Update failed in Member Table, contact admin", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.ivUpdatedTenantImage:

                break;
        }
    }

    private boolean isValid() {
        if (TextUtils.isEmpty(mEtUpdatedName.getText().toString())) {
            mEtUpdatedName.setError("Empty Field!");
            mEtUpdatedName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mEtUpdatedAddress.getText().toString())) {
            mEtUpdatedAddress.setError("Empty Field!");
            mEtUpdatedAddress.requestFocus();
            return false;

        }

        int contactLength = mEtUpdatedContact.getText().toString().length();
        if (contactLength < 10 || contactLength > 10) {
            mEtUpdatedContact.setError("Please check the number!");
            mEtUpdatedContact.requestFocus();
            return false;

        }

        if (TextUtils.isEmpty(mEtUpdatedRoomID.getText().toString())) {
            mEtUpdatedRoomID.setError("Empty Field!");
            mEtUpdatedRoomID.requestFocus();
            return false;

        }

        if (roomIdList.contains(Integer.parseInt(mEtUpdatedRoomID.getText().toString()))) {
            mEtUpdatedRoomID.setError("Room ID already created");
            mEtUpdatedRoomID.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(mEtUpdatedRoomCharge.getText().toString())) {
            mEtUpdatedRoomCharge.setError("Empty Field!");
            mEtUpdatedRoomCharge.requestFocus();
            return false;

        }
        if (TextUtils.isEmpty(mEtUpdatedElectricityInitialUnit.getText().toString())) {
            mEtUpdatedElectricityInitialUnit.setError("Empty Field!");
            mEtUpdatedElectricityInitialUnit.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
