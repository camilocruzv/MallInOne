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
import android.widget.Toast;

import com.example.carlosmario.mallinone_app.ProductInfoActivity;
import com.example.carlosmario.mallinone_app.models.ListProduct;
//import com.example.carlosmario.mallinone_app.productInfoActivity;
import com.example.carlosmario.mallinone_app.ProductsActivity;
import com.example.carlosmario.mallinone_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MySearchProductAdapter extends RecyclerView.Adapter<MySearchProductAdapter.ViewHolderSearchProduct> {

    private List<ListProduct> listproducts;
    private Context context;

    public MySearchProductAdapter(Context context, List<ListProduct> listproducts) {
        this.listproducts = listproducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderSearchProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_product_list, parent, false);

        ViewHolderSearchProduct viewHolder = new ViewHolderSearchProduct(v);

        return viewHolder; //new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSearchProduct holder, int position) {
        final ListProduct listproduct = listproducts.get(position);

        holder.productName.setText(listproduct.getName());

        Picasso.with(context)
                .load(listproduct.getImage())
                .into(holder.productImage);

        holder.product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You clicked " + listproduct.getName() + " " + context, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ProductInfoActivity.class);//ProductsActivity.class);
                intent.putExtra("productId", listproduct.getId());
                intent.putExtra("productLocal", listproduct.getLocal());
                intent.putExtra("productName", listproduct.getName());
                intent.putExtra("productImage", listproduct.getImage());
                intent.putExtra("price", listproduct.getPrice());
                intent.putExtra("characteristics", listproduct.getCharacteristics());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listproducts.size();
    }


    public class ViewHolderSearchProduct extends RecyclerView.ViewHolder {

        public TextView productName;
        CardView product;
        ImageView productImage;

        public ViewHolderSearchProduct(View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.txtNameSearchProduct);
            product = (CardView) itemView.findViewById(R.id.searchProductCard);
            productImage = (ImageView) itemView.findViewById(R.id.searchProductImage);

            //mall.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
            //Log.d("LogViewHolder", "Fondo Clicked");
            //Toast.makeText(context, "You clicked", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(context, productsActivity.class);
            //context.startActivity(intent);
            //}
            //});
        }
    }
}
