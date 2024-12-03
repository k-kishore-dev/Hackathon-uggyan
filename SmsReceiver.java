package com.example.adminapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = message.getOriginatingAddress();
                    String body = message.getMessageBody();

                    Log.d("SMSReceiver", "Message from: " + sender);
                    Log.d("SMSReceiver", "Message content: " + body);

                    // Transaction validation logic
                    if (body.contains("Transaction ID") && body.contains("Amount")) {
                        Toast.makeText(context, "Transaction Approved", Toast.LENGTH_SHORT).show();
                        // Optionally, send a response SMS to the sender
                    }
                }
            }
        }
    }
}
