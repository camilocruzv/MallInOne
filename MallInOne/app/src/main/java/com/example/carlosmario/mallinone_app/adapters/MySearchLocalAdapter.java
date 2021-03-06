package com.example.carlosmario.mallinone_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carlosmario.mallinone_app.LocalInfoActivity;
import com.example.carlosmario.mallinone_app.ProductsActivity;
import com.example.carlosmario.mallinone_app.R;
import com.example.carlosmario.mallinone_app.models.ListLocal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MySearchLocalAdapter extends RecyclerView.Adapter<MySearchLocalAdapter.ViewHolderSearchLocal> {

    private List<ListLocal> listLocals;
    private Context context;

    public MySearchLocalAdapter(Context context, List<ListLocal> listLocals) {
        this.listLocals = listLocals;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderSearchLocal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_local_list, parent, false);

        ViewHolderSearchLocal viewHolder = new ViewHolderSearchLocal(v);

        return viewHolder; //new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSearchLocal holder, int position) {
        final ListLocal listLocal = listLocals.get(position);

        holder.localName.setText(listLocal.getName());

        Picasso.with(context)
                .load(listLocal.getimageLocal())
                .into(holder.localImage);

        holder.local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You clicked " + listLocal.getName() + " " + context, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LocalInfoActivity.class);//ProductsActivity.class);
                intent.putExtra("LocalId", listLocal.getId());
                intent.putExtra("LocalName", listLocal.getName());
                intent.putExtra("LocalImage", listLocal.getimageLocal());
                intent.putExtra("MapLocal", listLocal.getMapLocal());
                intent.putExtra("MallId", listLocal.getIdMall());
                intent.putExtra("highlighted", listLocal.getHighlighted());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLocals.size();
    }


    public class ViewHolderSearchLocal extends RecyclerView.ViewHolder {

        public TextView localName;
        CardView local;
        ImageView localImage;

        public ViewHolderSearchLocal(View itemView) {
            super(itemView);

            localName = (TextView) itemView.findViewById(R.id.txtNameSearchLocal);
            local = (CardView) itemView.findViewById(R.id.searchLocalCard);
            localImage = (ImageView) itemView.findViewById(R.id.searchLocalImage);

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
