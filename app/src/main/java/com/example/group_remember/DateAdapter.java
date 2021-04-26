package com.example.group_remember;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> implements View.OnClickListener  {

    Context context;
    ArrayList<Date> dates;


    public DateAdapter(Context context, ArrayList<Date> dates) {
        this.context = context;
        this.dates = dates;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.datelayout, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Date date = dates.get(position);
        holder.bind(date);
        holder.modify.setTag(position);
//        holder.modify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "onclick" + position, Toast.LENGTH_LONG).show();
//                addData(holder.getLayoutPosition());
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return dates.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView topic;
        TextView text;
        TextView time;
        Button modify;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic);
            text = itemView.findViewById(R.id.Date);
            time = itemView.findViewById(R.id.time);
            modify = itemView.findViewById(R.id.ViewDay);
            modify.setOnClickListener(DateAdapter.this);

        }

        public void bind(Date date) {
            topic.setText(date.getTopic());
            text.setText(date.getText());
        }

        public Button returnButton(){
            return modify;
        }
    }


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        //注意这里使用getTag方法获取数据
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            switch (v.getId()){
                case R.id.ViewDay:
                    mOnItemClickListener.onClick(v, position);
                    break;
            }
        }
    }

}
