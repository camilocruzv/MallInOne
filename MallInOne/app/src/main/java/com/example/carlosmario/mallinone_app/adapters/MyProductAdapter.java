package com.example.carlosmario.mallinone_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carlosmario.mallinone_app.LocalsActivity;
import com.example.carlosmario.mallinone_app.ProductsActivity;
import com.example.carlosmario.mallinone_app.R;
import com.example.carlosmario.mallinone_app.models.ListProduct;

import java.util.List;

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.ViewHolderProduct> {

    private List<ListProduct> listProducts;
    private Context context;
    String name, localImage;

    public MyProductAdapter(List<ListProduct> listProducts, Context context, String name, String localImage) {
        this.listProducts = listProducts;
        this.context = context;
        this.name = name;
        this.localImage = localImage;
    }

    @NonNull
    @Override
    public MyProductAdapter.ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list, parent, false);

        MyProductAdapter.ViewHolderProduct viewHolder = new MyProductAdapter.ViewHolderProduct(v);

        return viewHolder; //new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct holder, int position) {
        final ListProduct listProduct = listProducts.get(position);

        holder.productName.setText(listProduct.getName());

        Intent intent = new Intent(context, LocalsActivity.class);
        intent.putExtra("LocalNameProduct", name);
        intent.putExtra("LocalImageProduct", localImage);

        //holder.product.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Toast.makeText(context, "You clicked " + listMall.getName(), Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(context, Tab3Map.class);
                //intent.putExtra("URL", "https://maps.mapwize.io/#/p/parque_comercial_el_tesoro/bosi");
                //context.startActivity(intent);
            //}
        //});
    }


    @Override
    public int getItemCount() {
        return listProducts.size();
    }


    public class ViewHolderProduct extends RecyclerView.ViewHolder {

        public TextView productName;
        //CardView product;

        public ViewHolderProduct(View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.txtNameProduct);
            //product = (CardView) itemView.findViewById(R.id.product);

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
