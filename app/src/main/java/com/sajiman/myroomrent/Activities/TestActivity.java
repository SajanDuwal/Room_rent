/*
package com.sajiman.myroomrent.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sajiman.myroomrent.Database.MemberInfoDao;
import com.sajiman.myroomrent.Database.PaymentInfoDao;
import com.sajiman.myroomrent.Database.RoomInfoDao;
import com.sajiman.myroomrent.Database.RoomRentDao;
import com.sajiman.myroomrent.Dtos.MemberInfoDto;
import com.sajiman.myroomrent.Dtos.PaymentInfoDto;
import com.sajiman.myroomrent.Dtos.RoomInfoDto;
import com.sajiman.myroomrent.Dtos.RoomRentDto;
import com.sajiman.myroomrent.Interface.OnRvClickListener;
import com.sajiman.myroomrent.R;
import com.sajiman.myroomrent.RvAdapters.RvCustomAdapter;
import com.sajiman.myroomrent.RvAdapters.RvCustomIconDecoration;
import com.sajiman.myroomrent.Utils.AppLogUtils;
import com.sajiman.myroomrent.Utils.MiscellaneousUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

*/
/**
 * Created by HP on 2/1/2018.
 *//*


public class Maitivity extends AppCompatActivity {

    private Toolbar mTbMain;
    private RecyclerView rvMemberList;
    private TextView mTvEmptyMessage;

    private MemberInfoDao memberInfoDao;
    private RoomInfoDao roomInfoDao;
    private PaymentInfoDao paymentInfoDao;

    private List<MemberInfoDto> memberInfoDtoList;
    private List<RoomInfoDto> roomInfoDtoList;
    private List<PaymentInfoDto> paymentInfoDtoList;

    private RoomRentDao roomRentDao;
    private List<RoomRentDto> roomRentDtoList;
    private RvCustomAdapter rvCustomAdapter;

    private BottomSheetDialog bottomSheetDialogMain;
    private TextView tvMemberName;
    private TextView tvMemberInfo;
    private TextView tvMemberEdit;
    private TextView tvMemberDelete;
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTbMain = findViewById(R.id.toolbarMain);
        setSupportActionBar(mTbMain);

        memberInfoDao = new MemberInfoDao(MainActivity.this);
        roomInfoDao = new RoomInfoDao(MainActivity.this);
        paymentInfoDao = new PaymentInfoDao(MainActivity.this);

        roomRentDao = new RoomRentDao(MainActivity.this);


        mTvEmptyMessage = findViewById(R.id.tvEmptyMessage);
        rvMemberList = findViewById(R.id.rvTenantsList);
        rvMemberList.addItemDecoration(new RvCustomIconDecoration());
        generateBottomSheet();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializingContents();
    }

    private void initializingContents() {

        memberInfoDtoList = memberInfoDao.getMemberInfo();

        roomInfoDtoList = roomInfoDao.getRoomInfo();

        paymentInfoDtoList = paymentInfoDao.getPaymentInfo();

        roomRentDtoList = roomRentDao.getAllInfo();


        AppLogUtils.showLog("MainActivity", "Member Info All Data " + memberInfoDtoList.toString());
        AppLogUtils.showLog("MainActivity", "Room Info All Data " + roomRentDtoList.toString());
        AppLogUtils.showLog("MainActivity", "Payment Info All Data " + paymentInfoDtoList.toString());

        Collections.sort(memberInfoDtoList, new Comparator<MemberInfoDto>() {
            @Override
            public int compare(MemberInfoDto o1, MemberInfoDto o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        if (roomRentDtoList.size() == 0) {
            mTvEmptyMessage.setText(R.string.empty_message);
            mTvEmptyMessage.setVisibility(View.VISIBLE);
            rvMemberList.setVisibility(View.GONE);
        } else {
            mTvEmptyMessage.setVisibility(View.GONE);
            rvMemberList.setVisibility(View.VISIBLE);
            rvCustomAdapter = new RvCustomAdapter(this, roomRentDtoList);
            rvMemberList.setLayoutManager(new LinearLayoutManager(this));
            rvCustomAdapter.onSetClickListener(mOnRvClickListener);
            rvMemberList.setAdapter(rvCustomAdapter);
        }
    }

    private void generateBottomSheet() {

        bottomSheetDialogMain = new BottomSheetDialog(MainActivity.this);
        final View bottomSheetDialogMainView = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_main, null, false);
        bottomSheetDialogMain.setContentView(bottomSheetDialogMainView);

        tvMemberName = bottomSheetDialogMainView.findViewById(R.id.tvMemberName);
        tvMemberInfo = bottomSheetDialogMainView.findViewById(R.id.tvMemberInfo);
        tvMemberEdit = bottomSheetDialogMainView.findViewById(R.id.tvMemberEdit);
        tvMemberDelete = bottomSheetDialogMainView.findViewById(R.id.tvMemberDelete);



            */
/* memberInfo onClick*//*

        tvMemberInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialogMemberInfo = new BottomSheetDialog(MainActivity.this);
                View bottomSheetDialogMemberInfoView = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_member_info, null, false);
                bottomSheetDialogMemberInfo.setContentView(bottomSheetDialogMemberInfoView);

                ImageView ivMemberImage = bottomSheetDialogMemberInfoView.findViewById(R.id.ivImageViewBottomSheetDialogMemberInfo);
                TextView tvName = bottomSheetDialogMemberInfoView.findViewById(R.id.tvNameBottomSheetDialogMemberInfo);
                TextView tvAddress = bottomSheetDialogMemberInfoView.findViewById(R.id.tvAddressBottomSheetDialogMemberInfo);
                TextView tvContact = bottomSheetDialogMemberInfoView.findViewById(R.id.tvContactBottomSheetDialogMemberInfo);
                ImageView ivCall = bottomSheetDialogMemberInfoView.findViewById(R.id.ivImageViewCallBottomSheetDialogMemberInfo);

                tvName.setText(roomRentDtoList.get(position).getName());
                tvAddress.setText(roomRentDtoList.get(position).getAddress());
                tvContact.setText(String.valueOf(roomRentDtoList.get(position).getContact()));

                    */
/* BottomSheetDialogMemberInfo Call Image View setOnClickListener *//*

                ivCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Call", Toast.LENGTH_SHORT).show();

                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + roomRentDtoList.get(position).getContact()));
                        startActivity(callIntent);
                        bottomSheetDialogMemberInfo.dismiss();
                    }
                });
                MiscellaneousUtils.AnimationFadeInDialog(MainActivity.this, bottomSheetDialogMemberInfoView);
                bottomSheetDialogMain.dismiss();
                bottomSheetDialogMemberInfo.show();
            }
        });

            */
/* memberEdit onClick*//*

        tvMemberEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
                updateIntent.putExtra("ROOM_RENT_DTO", roomRentDtoList.get(position));
                startActivity(updateIntent);
                bottomSheetDialogMain.dismiss();
            }
        });

            */
/* memberDelete onClick*//*

        tvMemberDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(MainActivity.class.getSimpleName(), "Member delete clicked");
                final BottomSheetDialog bottomSheetDialogConfirmDelete = new BottomSheetDialog(MainActivity.this);
                final View bottomSheetDialogConfirmDelteView = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_confirm_delete, null, false);
                bottomSheetDialogConfirmDelete.setContentView(bottomSheetDialogConfirmDelteView);
                bottomSheetDialogConfirmDelete.setCancelable(false);

                TextView tvConfirmDeleteInfo = bottomSheetDialogConfirmDelteView.findViewById(R.id.tvConfirmDeleteInfo);
                TextView tvCancel = bottomSheetDialogConfirmDelteView.findViewById(R.id.tvCancel);
                TextView tvDelete = bottomSheetDialogConfirmDelteView.findViewById(R.id.tvDelete);

                tvConfirmDeleteInfo.setText(String.format("Do you want to permanently delete %s?", roomRentDtoList.get(position).getName()));

                      */
/* BottomSheetConfirmDeleteDialog Cancel setOnClickListener  *//*

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialogConfirmDelete.dismiss();
                    }
                });

                     */
/* BottomSheetConfirmDeleteDialog Delete setOnClickListener  *//*

                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MemberInfoDto memberInfoDto = memberInfoDtoList.get(position);
                        int affectedRowMemberInfo;
                        affectedRowMemberInfo = memberInfoDao.delete(memberInfoDto);
                        Log.e(MainActivity.class.getSimpleName(), "Affected row member info: " + affectedRowMemberInfo);
                        if (affectedRowMemberInfo > 0) {

                            RoomInfoDto roomInfoDto = roomInfoDtoList.get(position);
                            int affectedRowRoomInfo;
                            affectedRowRoomInfo = roomInfoDao.delete(roomInfoDto);
                            Log.e(MainActivity.class.getSimpleName(), "affected room info number: " + affectedRowRoomInfo);
                            if (affectedRowRoomInfo > 0) {

                                PaymentInfoDto paymentInfoDto = paymentInfoDtoList.get(position);
                                int affectedRowPaymentInfo;
                                affectedRowPaymentInfo = paymentInfoDao.delete(paymentInfoDto);

                                if (affectedRowPaymentInfo > 0) {
                                    Toast.makeText(MainActivity.this, "Delete successful", Toast.LENGTH_SHORT).show();
                                    */
/*    roomRentDtoList.remove(position);
                                        rvCustomAdapter.notifyDataSetChanged();*//*


                                    if (roomRentDtoList.size() == 0) {
                                        mTvEmptyMessage.setText(R.string.empty_message);
                                        mTvEmptyMessage.setVisibility(View.VISIBLE);
                                        rvMemberList.setVisibility(View.GONE);
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Delete failed from Payment Info Table", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Delete failed from Room Info Table", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Delete failed from Member Info Table", Toast.LENGTH_SHORT).show();
                        }
                        bottomSheetDialogConfirmDelete.dismiss();
                    }
                });

                MiscellaneousUtils.AnimationFadeInDialog(MainActivity.this, bottomSheetDialogConfirmDelteView);
                bottomSheetDialogMain.dismiss();
                bottomSheetDialogConfirmDelete.show();

                MiscellaneousUtils.AnimationFadeInDialog(MainActivity.this, bottomSheetDialogMainView);
            }
        });
    }

    */
/* RvItemClickedListener *//*


    public OnRvClickListener mOnRvClickListener = new OnRvClickListener() {

        */
/* onRvItemLongClicked *//*

        @Override
        public void onItemLongClicked(final int position) {
            MainActivity.this.position = position;
            bottomSheetDialogMain.show();
            tvMemberName.setText(roomRentDtoList.get(position).getName());
        }


        */
/*  onRvItemClicked *//*

        @Override
        public void onItemClicked(int position) {
            Intent accountManagementIntent = new Intent(MainActivity.this, AccountManagementActivity.class);
            accountManagementIntent.putExtra("ROOM_RENT_DTO", roomRentDtoList.get(position));
            startActivity(accountManagementIntent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_new_member:
                startActivity(new Intent(MainActivity.this, InsertNewMemberActivity.class));
                break;

            case R.id.action_synchronize_from_server:
                Log.e(MainActivity.class.getSimpleName(), "All Info " + roomRentDtoList.toString());
                Toast.makeText(this, "Synchronizing from server", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}*/
