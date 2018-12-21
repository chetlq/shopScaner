package msk.android.academy.javatemplate.model;

import android.app.Application;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Buffer extends Application {

    private Map<Integer, Integer> Order;





    class Order{
        private int id;
        private int count;
        Order(int id,int count){
            this.count=count;
            this.id=id;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
