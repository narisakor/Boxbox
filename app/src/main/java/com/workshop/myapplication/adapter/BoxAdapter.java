package com.workshop.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.workshop.myapplication.R;
import com.workshop.myapplication.model.Box;

import java.util.List;


public class BoxAdapter extends
        RecyclerView.Adapter<BoxAdapter.ItemHolder> {
    private ICustomItemClickedListener listener;
    private ICustomItemClickedListener listenerDelete;
    private List<Box> data;
    private Context context;

    public BoxAdapter(Context context,
                      List<Box> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {

        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    public Box getItem(int position) {
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Box box = data.get(position);
        holder.tvBoxName.setText(box.getBoxName());

        if(box.isAvailable()){
            holder.lnRoot.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
        }else {
            holder.lnRoot.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
        }

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.item_box, viewGroup, false);
        final ItemHolder listHolder = new ItemHolder(mainGroup);
        mainGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClicked(v, listHolder.getAdapterPosition());
            }
        });

//        if (listenerDelete != null)
//            listHolder.bt_lert_car.setVisibility(View.VISIBLE);
//        listHolder.bt_lert_car.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listenerDelete != null)
//                    listenerDelete.onItemClicked(v, listHolder.getAdapterPosition());
//            }
//        });

        return listHolder;
    }

    public void addItemClickedListener(ICustomItemClickedListener listener) {
        this.listener = listener;
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvBoxName;
        LinearLayout lnRoot;

        public ItemHolder(View view) {
            super(view);
            tvBoxName = (TextView) view.findViewById(R.id.tv_box_name);
            lnRoot  =(LinearLayout)view.findViewById(R.id.ln_root);

        }
    }
}
