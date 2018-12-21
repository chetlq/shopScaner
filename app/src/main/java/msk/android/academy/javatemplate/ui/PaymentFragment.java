package msk.android.academy.javatemplate.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import msk.android.academy.javatemplate.R;
import msk.android.academy.javatemplate.adapter.ProductAdapter;
import msk.android.academy.javatemplate.model.DataProduct;
import msk.android.academy.javatemplate.model.Product;

public class PaymentFragment extends DialogFragment {

    private RecyclerView paymentRecycler;
    private ProductAdapter productAdapter;
    private Button approveButton;
    private Button cancelButton;
    private TextView totalPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payment_fragment, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        paymentRecycler = view.findViewById(R.id.payment_recycler);
        productAdapter = new ProductAdapter(new ArrayList<Product>(),getContext());
        approveButton = view.findViewById(R.id.approve_button);
        approveButton.setOnClickListener(v -> {

        });

        cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(v -> {

        });

        totalPrice = view.findViewById(R.id.total_price);
    }
}
