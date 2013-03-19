package com.bobo.lockit.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ScreenService extends Service {
	private ScreenUnLockReceiver screenUnLockReceiver;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		System.out.println("Service started");
		screenUnLockReceiver = new ScreenUnLockReceiver();
		IntentFilter unLockFilter = new IntentFilter();
		unLockFilter.setPriority(1001);
		unLockFilter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(screenUnLockReceiver, unLockFilter);
		
		super.onCreate();
	}
	@Override
	public void onDestroy() {
		System.out.println("Service destroyed");
		unregisterReceiver(screenUnLockReceiver);
		super.onDestroy();
	}
	
	private class ScreenUnLockReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			System.out.println("Sreen on");
		}
		
	}
	public void toHome(){
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.HOME");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addCategory("android.intent.category.MONKEY");
		startActivity(intent);
	}
}
