package msk.android.academy.javatemplate.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import msk.android.academy.javatemplate.R;
import msk.android.academy.javatemplate.model.Shop;
import msk.android.academy.javatemplate.ui.MainActivity;
import msk.android.academy.javatemplate.ui.ShopsActivity;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopAdapterViewHolder>{

    private ArrayList<Shop> shopList;
    private final LayoutInflater inflater;
    private final Activity activity;

    public ShopAdapter(ArrayList<Shop> shopList, Activity activity){
        this.shopList = shopList;
        this.inflater = LayoutInflater.from(activity);
        this.activity = activity;
    }

    @NonNull
    @Override
    public ShopAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ShopAdapterViewHolder(inflater.inflate(R.layout.item_shop, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapterViewHolder shopAdapterViewHolder, int i) {
        shopAdapterViewHolder.bind(shopList.get(i));
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class ShopAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        public ShopAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_item_shop);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.start(activity);

                }
            });
        }

        void bind(Shop shop) {
            name.setText(shop.getName());
        }
    }
}
