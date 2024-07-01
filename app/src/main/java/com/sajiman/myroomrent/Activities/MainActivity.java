package com.sajiman.myroomrent.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sajiman.myroomrent.Database.MemberInfoDao;
import com.sajiman.myroomrent.Database.PaymentInfoDao;
import com.sajiman.myroomrent.Database.RoomInfoDao;
import com.sajiman.myroomrent.Dtos.MemberInfoDto;
import com.sajiman.myroomrent.Dtos.PaymentInfoDto;
import com.sajiman.myroomrent.Dtos.RoomInfoDto;
import com.sajiman.myroomrent.Interface.OnRvClickListener;
import com.sajiman.myroomrent.R;
import com.sajiman.myroomrent.RvAdapters.RvCustomAdapter;
import com.sajiman.myroomrent.RvAdapters.RvCustomIconDecoration;
import com.sajiman.myroomrent.Utils.AppLogUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by HP on 2/1/2018.
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar mTbMain;
    private RecyclerView rvMemberList;
    private TextView mTvEmptyMessage;

    private MemberInfoDao memberInfoDao;
    private RoomInfoDao roomInfoDao;
    private PaymentInfoDao paymentInfoDao;

    private List<MemberInfoDto> memberNameList;
    private List<RoomInfoDto> roomIDList;
    private List<PaymentInfoDto> paymentInfoList;

    private RvCustomAdapter rvCustomAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTbMain = findViewById(R.id.toolbarMain);
        setSupportActionBar(mTbMain);

        mTvEmptyMessage = findViewById(R.id.tvEmptyMessage);
        rvMemberList = findViewById(R.id.rvTenantsList);

        memberInfoDao = new MemberInfoDao(MainActivity.this);
        roomInfoDao = new RoomInfoDao(MainActivity.this);
        paymentInfoDao = new PaymentInfoDao(MainActivity.this);

        rvMemberList.addItemDecoration(new RvCustomIconDecoration());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializingMemberRow();
    }

    private void initializingMemberRow() {
        memberNameList = memberInfoDao.getAllMemberName();
        roomIDList = roomInfoDao.getRoomId();
        paymentInfoList = paymentInfoDao.getRowPaymentInfo();

        if (readyToDisplay()) {
            mTvEmptyMessage.setVisibility(View.GONE);
            rvMemberList.setVisibility(View.VISIBLE);
            rvCustomAdapter = new RvCustomAdapter(this, memberNameList, roomIDList, paymentInfoList);
            rvMemberList.setLayoutManager(new LinearLayoutManager(this));
            rvCustomAdapter.onSetClickListener(mOnRvClickListener);
            rvMemberList.setAdapter(rvCustomAdapter);
        } else {
            mTvEmptyMessage.setText(R.string.empty_message);
            mTvEmptyMessage.setVisibility(View.VISIBLE);
            rvMemberList.setVisibility(View.GONE);
        }
    }

    private OnRvClickListener mOnRvClickListener = new OnRvClickListener() {
        @Override
        public void onItemLongClicked(int position) {
            Toast.makeText(MainActivity.this, "Long Clicked on item", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemClicked(int position) {

        }
    };

    private boolean readyToDisplay() {
        if (!memberNameList.isEmpty() && !roomIDList.isEmpty() && !paymentInfoList.isEmpty()) {
            AppLogUtils.showLog("Main Activity -- > READY TO DISPLAY ::: ", " Name =  " + memberNameList + "Room ID = "
                    + roomIDList + " Date Of rent Clearance and status = " + paymentInfoList);

            Collections.sort(memberNameList, new Comparator<MemberInfoDto>() {
                @Override
                public int compare(MemberInfoDto o1, MemberInfoDto o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            return true;
        } else {
            return false;
        }
    }

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
                Toast.makeText(this, "Synchronizing from server", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}