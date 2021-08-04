//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nextgen.scanner.constant;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import com.mastercard.mpqr.pushpayment.exception.FormatException;
import com.mastercard.mpqr.pushpayment.model.PushPaymentData;
import com.mastercard.mpqr.pushpayment.parser.Parser;

public class PPCaptureManager extends CaptureManager {
    private Activity activity;

    public PPCaptureManager(Activity activity, DecoratedBarcodeView barcodeView) {
        super(activity, barcodeView);
        this.activity = activity;
    }

    protected void returnResult(BarcodeResult rawResult) {
        String content = rawResult.getText();
        Log.w("PPCaptureManager","content: "+content);
        PushPaymentData qrcode = null;

        try {
            qrcode = Parser.parseWithoutTagValidation(content);
            qrcode.validate();
            Intent intent = resultIntent(rawResult, (String)null);
            intent.putExtra("PUSH_PAYMENT_DATA", qrcode);
            this.activity.setResult(-1, intent);
            Log.w("PPCaptureManager","qrcode: "+qrcode);
        } catch (FormatException var6) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("PARSE_ERROR", var6);
            if (qrcode != null) {
                intent.putExtra("PUSH_PAYMENT_DATA", qrcode);
            }

            this.activity.setResult(0, intent);
        }

        this.activity.finish();
    }
}
