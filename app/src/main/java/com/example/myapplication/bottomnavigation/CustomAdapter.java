package com.example.myapplication.bottomnavigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<DataModel> localDataSet;
    DBHelper DB;
    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private ImageView imageView2;
        private ImageView heart;

        private TextView textView2;

        private TextView textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            imageView2 = itemView.findViewById(R.id.imageView2);
            heart = itemView.findViewById(R.id.imageView3);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
        }
    }
    //========================================================================

    //----- 생성자 --------------------------------------
    // 생성자를 통해서 데이터를 전달받도록 함
    public CustomAdapter (ArrayList<DataModel> dataSet, DBHelper database) {
        localDataSet = dataSet;
        DB = database;
    }
    //--------------------------------------------------

    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴한다.
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        CustomAdapter.ViewHolder viewHolder = new CustomAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        DataModel data = localDataSet.get(position);
        String text = data.title;
        holder.textView.setText(text);
        holder.imageView.setImageResource(data.image_path);

        String dpr1 = data.dpr1;
        String dpr3 = data.dpr3;
        holder.textView2.setText("이번주 가격: " + dpr1);
        holder.textView3.setText("지난주 가격: " + dpr3);

        String change;

        if(dpr1.contains("-")||dpr3.contains("-")){
            change = "-";
        }
        else{
            float d1 = Integer.parseInt(dpr1.replace(",",""));
            float d3 = Integer.parseInt(dpr3.replace(",",""));
            float rate = (d1-d3) / d3;

            if(rate>0) change="UP";
            else if(rate==0) change="-";
            else change="DOWN";
        }


        int arrow;
        if(change=="UP") arrow = R.drawable.up;
        else if(change=="-") arrow = R.drawable.dash;
        else arrow = R.drawable.down;

        holder.imageView2.setImageResource(arrow);

        if(DB.selectdata(text)==false){
            holder.heart.setImageResource(R.drawable.heart_b);
        }
        else{
            holder.heart.setImageResource(R.drawable.heart);
        }

        holder.heart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int flag;
                if(DB.selectdata(text)==false) flag=0;
                else flag = 1;

                String title = text;
                if(flag==0) {
                    holder.heart.setImageResource(R.drawable.heart);
                    Boolean checkinsertdata = DB.insertuserdata(title, dpr1, dpr3, data.image_path);
                    if(checkinsertdata==true)
                        Toast.makeText(holder.heart.getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(holder.heart.getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    holder.heart.setImageResource(R.drawable.heart_b);
                    Boolean checkdeletedata = DB.deletedata(title);
                    if(checkdeletedata==true)
                        Toast.makeText(holder.heart.getContext(), "Entry Deleted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(holder.heart.getContext(), "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }

            }        });

    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return localDataSet.size();
    }

    public void setItems(ArrayList<DataModel> list){
        localDataSet = list;
        notifyDataSetChanged();
    }
}
