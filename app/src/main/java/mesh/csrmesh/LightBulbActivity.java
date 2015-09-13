package mesh.csrmesh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import android.graphics.Matrix;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mesh.csrmesh.adapter.LightBulbAdapter;
import mesh.csrmesh.entities.LightBulbModel;
import mesh.csrmesh.view.SwipeMenu;
import mesh.csrmesh.view.SwipeMenuCreator;
import mesh.csrmesh.view.SwipeMenuItem;
import mesh.csrmesh.view.SwipeMenuListView;
import mesh.csrmesh.view.ViewPagerAdapter;

public class LightBulbActivity extends BaseActivity {
    private ImageView back,add;
    private ViewPager viewpage;
    private TextView induvidual, group;

    private SwipeMenuListView groublist,induviduallist;

    private List<View> viewList = new ArrayList<View>();
    private View viewGroup, viewInduvidual;

    private LightBulbAdapter lightBulbAdapterInduvidual,lightBulbAdapterGroup;

    public List<LightBulbModel> groups,induviduals;


    private int bmpW;
    private int offset = 0;
    private ImageView imageView;

    private int defaultpage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightbulb);
        initFindViewById();
        initCursorImageView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewpage.setCurrentItem(defaultpage);
    }

    private void initCursorImageView() {
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.navmenu).getWidth();// Get the picture width
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// Get resolution width
        offset = (screenW / 2 - bmpW) / 2;// Calculation offset
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// Set the initial position of the animation
    }

    private void initFindViewById() {
        back= (ImageView) findViewById(R.id.back);
        add= (ImageView) findViewById(R.id.add);

        viewpage = (ViewPager) findViewById(R.id.viewpage);
        induvidual = (TextView) findViewById(R.id.induvidual);
        group = (TextView) findViewById(R.id.group);
        imageView = (ImageView) findViewById(R.id.imageView);

        LayoutInflater inflater = getLayoutInflater().from(this);
        viewInduvidual = inflater.inflate(R.layout.fragment_induvidual, null);
        viewGroup = inflater.inflate(R.layout.fragment_group, null);
        viewList.add(viewInduvidual);
        viewList.add(viewGroup);
        viewpage.setAdapter(new ViewPagerAdapter(viewList));

        induviduallist= (SwipeMenuListView) viewInduvidual.findViewById(R.id.induviduallist);
        groublist= (SwipeMenuListView)viewGroup.findViewById(R.id.grouplist);

        induviduals=new ArrayList<LightBulbModel>();
        lightBulbAdapterInduvidual=new LightBulbAdapter(this,induviduals);
        induviduallist.setAdapter(lightBulbAdapterInduvidual);

        SwipeMenuCreator menu1=new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.menu_setting);
                int width=bitmap.getWidth();

                SwipeMenuItem setItem = new SwipeMenuItem(getApplicationContext());
                setItem.setWidth(width);
                setItem.setIcon(R.mipmap.menu_setting);
                menu.addMenuItem(setItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setWidth(width);
                deleteItem.setIcon(R.mipmap.menu_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        induviduallist.setMenuCreator(menu1);


        groups=new ArrayList<LightBulbModel>();
        lightBulbAdapterGroup=new LightBulbAdapter(this,groups);
        groublist.setAdapter(lightBulbAdapterGroup);

        groublist.setMenuCreator(menu1);


        back.setOnClickListener(this);
        add.setOnClickListener(this);

        induvidual.setOnClickListener(this);
        group.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (int i=0;i<10;i++){
            induviduals.add(new LightBulbModel(i%2==0?true:false,"INDUVIDUAL"+i));
            groups.add(new LightBulbModel(i%2!=0?true:false,"GROUP"+i));
        }
        lightBulbAdapterInduvidual.notifyDataSetChanged();
        lightBulbAdapterGroup.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add:
                Intent intent=new Intent();
                if (defaultpage==0)
                    intent.setClass(this,AddDeviceActivity.class);
                else if(defaultpage==1)
                    intent.setClass(this,AddGroupActivity.class);
                startActivity(intent);
                break;
            case R.id.induvidual:
                if (defaultpage != 0) {
                    defaultpage = 0;
                    movingSlider(defaultpage, offset * 2 + bmpW, 0);
                }
                break;
            case R.id.group:
                if (defaultpage != 1) {
                    defaultpage = 1;
                    movingSlider(defaultpage, offset, offset * 2 + bmpW);
                }
                break;
        }
    }

    public void movingSlider(int page, int one, int two) {
        viewpage.setCurrentItem(defaultpage);//Display layout
        Animation animation = new TranslateAnimation(one, two, 0, 0);
        animation.setFillAfter(true);// true:The picture is stopped at the end of the animation.
        animation.setDuration(100);
        imageView.startAnimation(animation);
        group.setTextColor(page == 0 ? Color.BLACK : Color.YELLOW);
        induvidual.setTextColor(page == 0 ? Color.YELLOW : Color.BLACK);
    }
}
