package com.bobo.lockit.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ToMainReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("Sreen on");
	}

}
