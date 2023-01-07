package com.lab2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lab2.R;
import com.lab2.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.inflater = LayoutInflater.from(context);
        this.productList = productList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.firmView.setText(product.getFirm());
        holder.typeView.setText(product.getType());
        holder.productView.setText(product.getProduct());
    }

    @Override
    public int getItemCount() {
        return productList.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView firmView, typeView, productView;
        public ViewHolder(View view) {
            super(view);
            firmView = view.findViewById(R.id.firms);
            typeView = view.findViewById(R.id.types);
            productView = view.findViewById(R.id.products);
        }
    }
}
