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
import android.support.v4.view.ViewPager;
import android.view.View;

public class LockScreenActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_lockit);
		DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName who = new ComponentName(this, MyAdmin.class);
		if (dpm.isAdminActive(who)) {// 判断who是否被激活
			dpm.lockNow();// 锁屏
		}
		finish();//关闭当前activity,一闪而过,不让用户知道我是通过上面图标打开activity,在activity中进行锁屏操作的
	}

}