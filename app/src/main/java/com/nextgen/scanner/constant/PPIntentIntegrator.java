//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nextgen.scanner.constant;

import android.app.Activity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nextgen.scanner.activity.PPCaptureActivity;

import java.util.Collection;

public class PPIntentIntegrator extends IntentIntegrator {
    private static final String TAG = PPIntentIntegrator.class.getSimpleName();

    public PPIntentIntegrator(Activity activity) {
        super(activity);
        super.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
    }

    protected Class<?> getDefaultCaptureActivity() {
        return PPCaptureActivity.class;
    }

    public IntentIntegrator setDesiredBarcodeFormats(Collection<String> desiredBarcodeFormats) {
        return this;
    }
}

