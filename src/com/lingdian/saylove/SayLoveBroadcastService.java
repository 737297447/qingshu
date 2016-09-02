package com.lingdian.saylove;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SayLoveBroadcastService extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String outphoneName = this.getResultData();

		if (outphoneName.equals("5201314")) {
			Intent i = new Intent(context,
					com.lingdian.saylove.WriteLove.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
			setResultData(null);

		}

	}

}
