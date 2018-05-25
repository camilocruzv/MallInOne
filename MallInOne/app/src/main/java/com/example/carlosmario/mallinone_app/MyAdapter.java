package com.example.carlosmario.mallinone_app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListMall> listMalls;
    private Context context;

    public MyAdapter(List<ListMall> listMalls, Context context) {
        this.listMalls = listMalls;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.mall_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder; //new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListMall listMall = listMalls.get(position);

        holder.textViewName.setText(listMall.getName());

        holder.mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You clicked " + listMall.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LocalsActivity.class);
                intent.putExtra("MallName", listMall.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        CardView mall;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.txtName);
            mall = (CardView) itemView.findViewById(R.id.mall);

            //mall.setOnClickListener(new View.OnClickListener() {
                //@Override
                //public void onClick(View v) {
                    //Log.d("LogViewHolder", "Fondo Clicked");
                    //Toast.makeText(context, "You clicked", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(context, LocalsActivity.class);
                    //context.startActivity(intent);
                //}
            //});
        }
    }
}
