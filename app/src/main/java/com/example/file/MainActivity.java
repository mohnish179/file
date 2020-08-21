package com.example.file;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    HashMap<String,List<String>> listItem;;

    int y2_size=0;

    public static final int FIRST_LEVEL_COUNT = 12;
    public static final int SECOND_LEVEL_COUNT = 4;
    public static final int THIRD_LEVEL_COUNT = 20;
    List<String> listGroup,listGroup3,listGroup2;
    List<String> get_2_lvl,get_3_lvl;
    List<String> splstore;


    File f2;
    int temp_cycle=0;
    private ExpandableListView expandableListView;
    int x,x2,x3;
    String[] values,values2,values3;
    List y2;

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.mainList);
        listGroup=new ArrayList<String>();
        splstore=new ArrayList<String>();
        get_2_lvl=new ArrayList<String>();


        listItem=new HashMap<>();
        listGroup2=new ArrayList<String>();

        listGroup3=new ArrayList<String>();
        expandableListView.setAdapter(new ParentLevel(this));
        y2_size=2;

        String path= Environment.getExternalStorageDirectory().getAbsolutePath();
        File f = new File(path);//converted string object to file
        values = f.list();//getting the list of files in string array




        List<String> list2;

        list2 = Arrays.asList(values);

        listGroup.addAll(list2);




    }

    public class ParentLevel extends BaseExpandableListAdapter {

        private Context context;

        public ParentLevel(Context context) {
            this.context = context;
        }

        @Override
        public Object getChild(int arg0, int arg1) {
            return arg1;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {

            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(MainActivity.this);



secondLevelELV.setOnTouchListener(new View.OnTouchListener() {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x2=childPosition;
        Log.d(TAG, "onTouch: "+childPosition);
        return false;
    }
});



            secondLevelELV.setAdapter(new SecondLevelAdapter(context));
            secondLevelELV.setGroupIndicator(null);



          //  Log.d(TAG, "getChildView: ");
            return secondLevelELV;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return get_2_lvl.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return FIRST_LEVEL_COUNT;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_first, null);
                TextView text = (TextView) convertView.findViewById(R.id.eventsListEventRowText);

                convertView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        Log.d(TAG, "onTouch: "+groupPosition);
                        x=groupPosition;

                        String path= Environment.getExternalStorageDirectory().getAbsolutePath();
                        File f = new File(path+"/"+listGroup.get(x));//converted string object to file

                        values2 = f.list();

                        //getting the list of files in string array}




                        get_2_lvl= Arrays.asList(values2);

                        splstore.addAll(get_2_lvl);


                        temp_cycle=0;
                        Log.d(TAG, "onTouch: "+temp_cycle);

                        return false;
                    }
                });


                Log.d(TAG, "getGroupView: "+isExpanded);

                Log.d(TAG, "getGroupView2: "+groupPosition);


                    text.setText(listGroup.get(groupPosition));
            }
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }





    }

    public class SecondLevelExpandableListView extends ExpandableListView {

        public SecondLevelExpandableListView(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public class SecondLevelAdapter extends BaseExpandableListAdapter {

        private Context context;

        public SecondLevelAdapter(Context context) {
            this.context = context;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }



        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_third, null);
                TextView text = (TextView) convertView.findViewById(R.id.eventsListEventRowText);


File f;

                String path= Environment.getExternalStorageDirectory().getAbsolutePath();


                f = new File(path+"/"+listGroup.get(x)+"/"+get_2_lvl.get(x2));//converted string object to file




                Log.d(TAG, "onTouch3: "+x2);

                values3= f.list();

                if(values3!=null)
                {
                    Log.d(TAG, "null it is: ");
                    y2= Arrays.asList(values3);
                    getChildrenCount(y2.size());

                }



               // List y3=Arrays.asList(values3);



              //  List y= Arrays.asList(values2);

                listGroup2=splstore;




                Log.d(TAG, "getGroupView4: "+temp_cycle);

  if(temp_cycle<splstore.size())
  {  text.setText(listGroup2.get(temp_cycle));}
  else
  {
      temp_cycle--;
  }
               temp_cycle++;
            }
            return convertView;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_second, null);
                TextView text = (TextView) convertView.findViewById(R.id.eventsListEventRowText);




               listGroup3=y2;

                text.setText(listGroup3.get(childPosition));
               //text.setText("hello");
            }
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
           return y2_size;
           //return  1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}