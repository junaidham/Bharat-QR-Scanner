//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nextgen.scanner.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView.TorchListener;

import com.nextgen.scanner.constant.PPCaptureManager;
import com.nextgen.scanner.R.id;
import com.nextgen.scanner.R.layout;

public class PPCaptureActivity extends AppCompatActivity implements TorchListener {
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private ImageView torchImageView;
    private boolean isTorchOn = false;

    public PPCaptureActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.barcodeScannerView = this.initializeContent();
        this.barcodeScannerView.setTorchListener(this);
        this.torchImageView = (ImageView)this.findViewById(id.torch_image);
        if (!this.hasTorch() && this.torchImageView != null) {
            this.torchImageView.setVisibility(View.GONE);
        }

        this.capture = new PPCaptureManager(this, this.barcodeScannerView);
        this.capture.initializeFromIntent(this.getIntent(), savedInstanceState);
        this.capture.decode();
    }

    protected DecoratedBarcodeView initializeContent() {
        this.setContentView(layout.activity_pp_capture);
        return (DecoratedBarcodeView)this.findViewById(id.zxing_barcode_scanner);
    }

    protected void onResume() {
        super.onResume();
        this.capture.onResume();
    }

    protected void onPause() {
        super.onPause();
        this.capture.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.capture.onDestroy();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.capture.onSaveInstanceState(outState);
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return this.barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    public void onTorchOn() {
        this.isTorchOn = true;
    }

    public void onTorchOff() {
        this.isTorchOn = false;
    }

    private boolean hasTorch() {
        return this.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    public void toggleTorch(View view) {
        if (this.isTorchOn) {
            this.barcodeScannerView.setTorchOff();
        } else {
            this.barcodeScannerView.setTorchOn();
        }

    }

    public void goBack(View view) {
        this.finish();
    }
}
