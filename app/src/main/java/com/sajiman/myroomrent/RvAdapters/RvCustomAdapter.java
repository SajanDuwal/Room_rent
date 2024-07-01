package com.sajiman.myroomrent.RvAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sajiman.myroomrent.Dtos.MemberInfoDto;
import com.sajiman.myroomrent.Dtos.PaymentInfoDto;
import com.sajiman.myroomrent.Dtos.RoomInfoDto;
import com.sajiman.myroomrent.Interface.OnRvClickListener;
import com.sajiman.myroomrent.R;
import com.sajiman.myroomrent.Utils.MiscellaneousUtils;

import java.util.List;

public class RvCustomAdapter extends RecyclerView.Adapter<RvCustomAdapter.ViewHolder> {

    private Context context;
    private List<PaymentInfoDto> paymentInfoList;
    private List<MemberInfoDto> memberNameList;
    private List<RoomInfoDto> roomIDList;


    private String monthName;
    private OnRvClickListener onRvClickListener;

    public RvCustomAdapter(Context context, List<MemberInfoDto> memberNameList, List<RoomInfoDto> roomIDList, List<PaymentInfoDto> paymentInfoList) {
        this.context = context;
        this.memberNameList = memberNameList;
        this.roomIDList = roomIDList;
        this.paymentInfoList = paymentInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_member, parent, false));
    }

    public void onSetClickListener(OnRvClickListener onRvClickListener) {
        this.onRvClickListener = onRvClickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        MemberInfoDto memberInfoDto = memberNameList.get(position);
        RoomInfoDto roomInfoDto = roomIDList.get(position);
        PaymentInfoDto paymentInfoDto = paymentInfoList.get(position);

        holder.tvName.setText(memberInfoDto.getName());
        holder.tvRoomId.setText(String.format("Room ID : %s", String.valueOf(roomInfoDto.getRoomId())));

        int monthPosition = paymentInfoDto.getRentClearedUptoMonth();
        monthName = MiscellaneousUtils.getMonthName(monthPosition);

        holder.tvRentClearUptoMonth.setText(monthName);
        if (!paymentInfoDto.getStatus()) {
            holder.tvStatus.setText(context.getResources().getString(R.string.string_unpaid));
            holder.tvStatus.setTextColor(Color.RED);
        } else {
            holder.tvStatus.setText(context.getResources().getString(R.string.string_paid));
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.darker_green));
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onRvClickListener.onItemLongClicked(position);
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRvClickListener.onItemClicked(position);
            }

        });

    }

    @Override
    public int getItemCount() {
        return memberNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRoomId;
        private TextView tvName;
        private TextView tvRentClearUptoMonth;
        private TextView tvStatus;

        private ViewHolder(View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvName = itemView.findViewById(R.id.tvName);
            tvRoomId = itemView.findViewById(R.id.tvRoomId);
            tvRentClearUptoMonth = itemView.findViewById(R.id.tvRentClearUptoMonth);
        }
    }
}
