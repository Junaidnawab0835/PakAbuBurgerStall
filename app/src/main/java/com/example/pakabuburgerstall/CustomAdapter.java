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

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyHolder> {
    private Context mContext;
    private List<DishItem> mData;
    int total_bill = 0;
    com.example.pakabuburgerstall.Interface.onValueUpdate onValueUpdate;
    public CustomAdapter(Context mContext, List<DishItem> mData,onValueUpdate onValueUpdate){
        this.mContext = mContext;
        this.mData = mData;
        this.onValueUpdate=onValueUpdate;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.item_layout,parent,false);
        return  new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(mData.get(position).name);
        holder.img_dish_thumbnail.setImageResource(mData.get(position).getThumbail());
        holder.name.setText(mData.get(position).name);
        holder.price_txt.setText(mData.get(position).getPrice()+" AED" +
                "");
        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.get(position).quantity = mData.get(position).quantity+1;
                holder.quantity_txt.setText(mData.get(position).quantity+" ");
                onValueUpdate.onValueUpdate();
            }
        });
        holder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mData.get(position).quantity>0)
                {
                    mData.get(position).quantity = mData.get(position).quantity-1;
                    holder.quantity_txt.setText(mData.get(position).quantity+" ");
                    onValueUpdate.onValueUpdate();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img_dish_thumbnail;
        CardView cardView;
        Button btn_minus;
        Button btn_plus;
        TextView quantity_txt;
        TextView price_txt;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_dish_name);
            img_dish_thumbnail = itemView.findViewById(R.id.img_dish);
            btn_minus = itemView.findViewById(R.id.btnMinus);
            btn_plus = itemView.findViewById(R.id.btnPlus);
            quantity_txt = itemView.findViewById(R.id.txtQuantity);
            price_txt = itemView.findViewById(R.id.price_txt);
        }
    }
}
