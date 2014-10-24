package com.example.rjhy.adapter_kit_text;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rjhy.adapter.MyArrayAdapter;
import com.example.rjhy.modul.Body;
import com.mobsandgeeks.adapters.CircularListAdapter;
import com.mobsandgeeks.adapters.InstantAdapter;
import com.mobsandgeeks.adapters.Sectionizer;
import com.mobsandgeeks.adapters.SimpleSectionAdapter;
import com.mobsandgeeks.adapters.ViewHandler;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {
    public static final String TAG = "MyActivity";
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        listView = (ListView) findViewById(R.id.listview);

//        initInstantAdapter();
//        initMyArrayAdapter();
        initSimpleSectionAdapter();
//        initCircularListAdapter();
    }

    private MyArrayAdapter getMyArrayAdapter(){
        List<String> list = new ArrayList<String>();
        for(int i=0; i<10; i++){
            list.add("myArrayAdapter"+i);
        }
        MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        return adapter;
    }

    private InstantAdapter getInstantAdapter(){
        List<Body> list = new ArrayList<Body>();
        for(int i=0; i<10; i++){
            Body body = new Body();
            body.setIsChecked(i%2==0?true:false);
            body.setName("text"+i);
            list.add(body);
        }
        InstantAdapter<Body> adapter = new InstantAdapter<Body>(this, R.layout.list_item, Body.class, list);
        return adapter;
    }

    private void initCircularListAdapter(){
//        InstantAdapter<Body> adapter = getInstantAdapter();
        MyArrayAdapter adapter = getMyArrayAdapter();
        CircularListAdapter circularListAdapter = new CircularListAdapter(adapter);
        listView.setAdapter(circularListAdapter);
    }

    private void initSimpleSectionAdapter(){
        InstantAdapter<Body> adapter = getInstantAdapter();
        SimpleSectionAdapter sectionAdapter = new SimpleSectionAdapter(this, adapter, R.layout.head, R.id.head_text1, new Sectionizer<Body>() {
            @Override
            public String getSectionTitleForItem(Body instance) {
                Log.i(TAG, instance.getName());
                return instance.getName();
            }
        });
        //可以使用自定义的adapter
//        MyArrayAdapter adapter = getMyArrayAdapter();
//        SimpleSectionAdapter sectionAdapter = new SimpleSectionAdapter(this, adapter, R.layout.head, R.id.head_text1, new Sectionizer<String>() {
//            @Override
//            public String getSectionTitleForItem(String instance) {
//                return instance;
//            }
//        });

        listView.setAdapter(sectionAdapter);
    }

    private void initInstantAdapter(){
        InstantAdapter<Body> adapter = getInstantAdapter();
        //非TextView控件只能通过setViewHandler（）来赋值（单独对每个控件设置ViewHandler）
        adapter.setViewHandler(R.id.checkbox, new ViewHandler<Body>() {
            /*
             * @param1 adapter
             * @param2 是public View getView(final int position, final View convertView, final ViewGroup parent) {...}方法中的parent对象
             * @param3 是R.id.checkbox对象
             */
            @Override
            public void handleView(ListAdapter adapter, View parent, View view, Body instance, int position) {
                Log.i(TAG, "R.id.checkbox----"+instance.getIsChecked());
                CheckBox checkBox = (CheckBox) view;
                checkBox.setChecked(instance.getIsChecked());
            }
        });

        //对list_item的layout设置ViewHandler（可对item中的每个控件进行赋值操作）
        adapter.setViewHandler(R.layout.list_item, new ViewHandler<Body>() {
            /*
             * @param1 adapter
             * @param2 是public View getView(final int position, final View convertView, final ViewGroup parent) {...}方法中的parent对象
             * @param3 是public View getView(final int position, final View convertView, final ViewGroup parent) {...}方法中的convertView对象
             */
            @Override
            public void handleView(ListAdapter adapter, View parent, View view, Body instance, int position) {
                TextView textView = (TextView) view.findViewById(R.id.text1);
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
                checkBox.setChecked(instance.getIsChecked());
                textView.setText(instance.getName());
            }
        });
        //注解和setViewHandler不要一起用，否则handleView（）方法会执行两次
//        adapter.setViewHandler(R.id.text1, new ViewHandler<Body>() {
//            @Override
//            public void handleView(ListAdapter adapter, View parent, View view, Body instance, int position) {
//                Log.i(TAG, "R.id.text1---"+instance.getName());
//                TextView textView = (TextView) view;
//                textView.setText("handleView--"+instance.getName());
//            }
//        });
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
