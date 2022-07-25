package com.example.pakabuburgerstall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pakabuburgerstall.Interface.onValueUpdate;

import java.util.List;

public class HistoryCustomAdapter extends RecyclerView.Adapter<HistoryCustomAdapter.MyHolder>  {
    private Context mContext;
    private List<Orders> mData;
    public HistoryCustomAdapter(Context mContext, List<Orders> mData){
        this.mContext = mContext;
        this.mData = mData;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.item_layout_history_item,parent,false);
        return  new HistoryCustomAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.total_payment.setText(mData.get(position).getTotal_payment());
        holder.order_id.setText(mData.get(position).getOrder_id());
        holder.payment_method.setText(mData.get(position).getPayment_method());
        holder.order_date.setText(mData.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView order_id;
        TextView payment_method;
        TextView total_payment;
        TextView order_date;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            order_date = itemView.findViewById(R.id.date_txt);
            payment_method = itemView.findViewById(R.id.payment_method_txt);
            order_id = itemView.findViewById(R.id.order_id_txt);
            total_payment = itemView.findViewById(R.id.total_payment_txt);
        }
    }
}
