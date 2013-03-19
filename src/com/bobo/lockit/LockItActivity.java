package com.bobo.lockit;

import com.bobo.lockit.receiver.MyAdmin;
import com.bobo.lockit.service.ScreenService;
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

public class LockItActivity extends Activity {
    private SharedPreferences sp;
	private ComponentName who;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockit);
        sp = getPreferences(MODE_PRIVATE);
    }
    public void lockit(View view){
    	DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName who = new ComponentName(this, MyAdmin.class);
		if (dpm.isAdminActive(who)) {//判断who是否被激活
			dpm.lockNow();//锁屏
		}
    }
    public void createicon(View view){
    	installShortcut();
    }
    public void startService(View view){
    	startService(new Intent(this,ScreenService.class));
    }
    public void stopService(View view){
    	stopService(new Intent(this,ScreenService.class));
    }
    public void activeAdmin(View view){
    	// Launch the activity to have the user enable our admin.
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        who = new ComponentName(this, MyAdmin.class);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, who);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"激活了我，就可以随心所欲锁屏了哦！");
        startActivity(intent);

    }
    public void inActiveAdmin(View view){
    	DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
    	dpm.removeActiveAdmin(who);
    }
    /**
	 * 创建一键锁屏快捷图标
	 */
	private void installShortcut() {
		boolean shortcut  = sp.getBoolean("shortcut", false);
		if(shortcut){//如果已经创建了快捷图标 返回
//			return ;
		}
		Intent intent = new Intent();
    	intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
    	intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
    	intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "锁屏");
    	Intent callIntent = new Intent();
    	callIntent.setAction("lockit");
    	callIntent.addCategory("android.intent.category.DEFAULT");
    	intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, callIntent);
    	sendBroadcast(intent);
    	
    	Editor editor = sp.edit();
		editor.putBoolean("shortcut", true);
		editor.commit();

	}
	
}