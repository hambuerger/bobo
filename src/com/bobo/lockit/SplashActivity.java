package com.bobo.lockit;

import java.util.ArrayList;
import java.util.List;
import com.bobo.lockit.receiver.MyAdmin;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class SplashActivity extends Activity {
    private SharedPreferences sp;
    private ViewPager viewPager;
	private List<View> lists = new ArrayList<View>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        //第一次进入显示splash界面
        boolean isFirstTime = sp.getBoolean("isFirstTime", false);
        if(!isFirstTime){
        	Editor editor = sp.edit();
        	editor.putBoolean("isFirstTime", true);
        	editor.commit();
        	showSplash();
        	
        }else{
        	Intent intent = new Intent(this,LockItActivity.class);
        	startActivity(intent);
        }
        finish();
    }
    
    private void showSplash() {
    	lists.add(getLayoutInflater().inflate(R.layout.view1, null));
		lists.add(getLayoutInflater().inflate(R.layout.activity_start, null));

		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(new MyPageAdapter(lists));
		
	}
    //ViewPager的适配器，实现ViewPager内容的填充
	private class MyPageAdapter extends PagerAdapter {
		List<View> viewLists;

		public MyPageAdapter(List<View> lists) {
			viewLists = lists;
		}

		@Override
		public int getCount() {
			return viewLists.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(viewLists.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager)container).addView(viewLists.get(position),0);
			return viewLists.get(position);
		}

	}
	public void go(View view){
		Intent intent = new Intent(this,LockItActivity.class);
		startActivity(intent);
	}
	
   
}