package msk.android.academy.javatemplate.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import msk.android.academy.javatemplate.R;
import msk.android.academy.javatemplate.Utils;
import msk.android.academy.javatemplate.adapter.OnRemoveListCallback;
import msk.android.academy.javatemplate.adapter.ProductAdapter;
import msk.android.academy.javatemplate.adapter.SimpleItemTouchHelperCallback;
import msk.android.academy.javatemplate.model.DataProduct;
import msk.android.academy.javatemplate.model.Product;


public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler,
        OnRemoveListCallback {
public static int summ =0;
    private RecyclerView productRecycler;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Product> generateList;
    private ItemTouchHelper mItemTouchHelper;
    private Button payFab;

   // private CompositeDisposable compositeDisposable;
    private ZXingScannerView mScannerView;

    private ViewGroup sceneRoot;
    private LinearLayout linearLayout;
    private FrameLayout frameLayout;


    public static void start(Activity activity) {
        final Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        createRecycler();


    }



    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setAspectTolerance(0.2f);
        mScannerView.setFocusable(true);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
     //   loadFromNetwork(Long.valueOf(rawResult.getText()));
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        updateRecycler(rawResult.getText());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(MainActivity.this);
            }
        }, 2000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
//            compositeDisposable.dispose();
//            compositeDisposable = null;
//        }
    }

    private void init() {
        payFab = (Button) findViewById(R.id.pay_fab);
//        linearLayout = findViewById(R.id.linear_container);
//        frameLayout;
        payFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                    ad.setTitle("Подтверждение оплаты");  // заголовок
                    ad.setMessage("Общая сумма: "+summ); // сообщение
                    ad.setPositiveButton("Оплатить", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {

                        }
                    });
                    ad.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {

                        }
                    });
                    ad.setCancelable(true);
                    ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {

                        }
                    });
                    ad.show();
                }
            }
        } );
        productAdapter = new ProductAdapter(new ArrayList<Product>(),this);
        productList = new ArrayList<Product>();
        generateList = DataProduct.generateProducts();
        ViewGroup contentFrame = findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);

        productRecycler = findViewById(R.id.recycler_view);
        //compositeDisposable = new CompositeDisposable();

    }

    private void createRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        productRecycler.setLayoutManager(layoutManager);
        productRecycler.setAdapter(productAdapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(productAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(productRecycler);

        ItemTouchHelper.Callback itemTouchHelperCallback = new SimpleItemTouchHelperCallback(productAdapter);
        mItemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        mItemTouchHelper.attachToRecyclerView(productRecycler);

    }
//
//    private void loadFromNetwork(long code) {
//
//        EndPoint endPoint = RestApi.getInstance().getEndPoint();
//
//        Disposable disposable = endPoint.getData(code)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Product>() {
//                               @Override
//                               public void accept(Product product) throws Exception {
//
//                                   MainActivity.this.updateRecycler(product);
//                               }
//                           },
//                        this::handleError);
//
//        compositeDisposable.add(disposable);
//    }

    private void updateRecycler(String code) {
        boolean isEquals = false;
        for (Product i : productList) {
            if (i.getCode().equals(code)) {
                isEquals = true;
                Snackbar.make(productRecycler, "Товар уже добавлен", Snackbar.LENGTH_LONG).show();
            }
        }
        if (!isEquals) {
            for (Product product : generateList) {
                if (product.getCode().equals(code)) {
                    isEquals = true;
                    product.setCount(1);
                    productList.add(product);
                    summ =summ+Integer.parseInt( product.getPrice());
                }
            }
        }
        if(!isEquals){
            Snackbar.make(productRecycler, "Товар отсутствует в базе", Snackbar.LENGTH_LONG).show();
        }
        if (productAdapter != null) {
            productAdapter.replaceItem(productList);
        }
    }

    private void handleError(Throwable th) {
        Utils.log(th.getMessage() + th);
    }


    @Override
    public void onRemove(int id) {
        productList.remove(id);
    }


}
