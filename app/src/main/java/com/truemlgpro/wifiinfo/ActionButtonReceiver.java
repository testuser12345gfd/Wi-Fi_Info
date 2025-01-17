package com.truemlgpro.wifiinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ActionButtonReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (intent.getAction() != null && intent.getAction().equals("ACTION_STOP")) {
			context.sendBroadcast(new Intent(context, NotificationService.NotificationServiceStopReceiver.class).setAction("ACTION_STOP_FOREGROUND"));
			context.stopService(new Intent(context, NotificationService.class));
			context.stopService(new Intent(context, ConnectionStateService.class));
			MainActivity.isServiceRunning = false;
		}
		
		if (intent.getAction() != null && intent.getAction().equals("ACTION_STOP_CONN_STATE_SERVICE")) {
			context.stopService(new Intent(context, ConnectionStateService.class));
			MainActivity.isServiceRunning = false;
		}
				
		if (intent.getAction() != null && intent.getAction().equals("ACTION_NTFC_SETTINGS")) {
			context.startService(new Intent(context, NotificationService.class).setAction("ACTION_NTFC_SETTINGS"));
			
			Intent StatusBarCloseIntent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
			context.sendBroadcast(StatusBarCloseIntent);
		}
	}
}
