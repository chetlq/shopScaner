package msk.android.academy.javatemplate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import msk.android.academy.javatemplate.R;
import msk.android.academy.javatemplate.model.Product;

import static msk.android.academy.javatemplate.ui.MainActivity.summ;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
        implements ItemTouchHelperAdapter {

    private List<Product> productList = new ArrayList<>();
    //private int count;
    private OnRemoveListCallback onRemoveListCallback;


    public ProductAdapter(@NonNull List<Product> productList,Context context) {

        onRemoveListCallback = (OnRemoveListCallback) context;
        this.productList=productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product, viewGroup, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {
        productViewHolder.bind(productList.get(position), position);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void replaceItem(List<Product> list) {
        productList.clear();
        productList.addAll(list);
        notifyDataSetChanged();
    }

    public void deleteItem(Product product, int position) {
        productList.remove(product);
        notifyItemRemoved(position);
        onRemoveListCallback.onRemove(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(productList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        summ = summ - productList.get(position).getCount()*Integer.parseInt(productList.get(position).getPrice());

        productList.remove(position);
        notifyItemRemoved(position);
        onRemoveListCallback.onRemove(position);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView price;
        private TextView productCount;
        private ImageView addProduct;
        private ImageView removeProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_product_name);
            price = itemView.findViewById(R.id.tv_product_price);
            productCount = itemView.findViewById(R.id.tv_product_count);
            addProduct = itemView.findViewById(R.id.iv_pluse);
            removeProduct = itemView.findViewById(R.id.iv_munis);
        }

        void bind(Product product, int position) {

            title.setText(product.getName());

                price.setText("" + product.getCount() * (Integer.valueOf(product.getPrice())));


            productCount.setText(String.valueOf(product.getCount()));

            addProduct.setOnClickListener(v -> {
                if (summ==0) summ = Integer.valueOf(product.getPrice());
                summ = summ+Integer.valueOf(product.getPrice());
                productCount.setText(String.valueOf(1+product.getCount()));
                product.setCount(product.getCount()+1);

                price.setText("" + product.getCount() * (Integer.valueOf(product.getPrice())));

            });
            removeProduct.setOnClickListener(v -> {
                summ = summ-Integer.valueOf(product.getPrice());
                if (summ<0)summ=0;
                int count=product.getCount()-1;
                Integer.valueOf(count);
                product.setCount(count);
                if (count <= 0) {
                    deleteItem(product, position);

                }else{
                    productCount.setText(String.valueOf(count));
                    price.setText("" + count * (Integer.valueOf(product.getPrice())));
                }

            });

        }
    }
}
