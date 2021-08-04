//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nextgen.scanner.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import com.journeyapps.barcodescanner.ViewfinderView;
import com.nextgen.scanner.R.color;
import com.nextgen.scanner.R.styleable;

public class PPQrFinderIndicatorView extends ViewfinderView {
    private static final long ANIMATION_DELAY = 10L;
    private static final int CORNER_RECT_WIDTH = 8;
    private static final int CORNER_RECT_LENGTH = 40;
    private static final int LASER_THICKNESS = 8;
    private static final int SCANNER_LINE_MOVE_DISTANCE = 5;
    private final int laserColor;
    private final int cornerColor;
    private final float cornerWidth;
    private final float cornerLength;
    private final float laserThickness;
    private int width = 0;
    private int height = 0;
    private float scannerStart = 0.0F;
    private float scannerEnd = 0.0F;
    private boolean scannerIncrease = true;

    public PPQrFinderIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources resources = this.getResources();
        TypedArray array = context.obtainStyledAttributes(attrs, styleable.PPQrFinderIndicatorView);
        this.laserColor = array.getColor(styleable.PPQrFinderIndicatorView_ppscan_laser_color, resources.getColor(color.colorGoldenYellow));
        this.cornerColor = array.getColor(styleable.PPQrFinderIndicatorView_ppscan_corner_color, resources.getColor(color.colorGoldenYellow));
        this.cornerWidth = array.getDimension(styleable.PPQrFinderIndicatorView_ppscan_corner_width, 8.0F);
        this.cornerLength = array.getDimension(styleable.PPQrFinderIndicatorView_ppscan_corner_length, 40.0F);
        this.laserThickness = array.getDimension(styleable.PPQrFinderIndicatorView_ppscan_laser_thickness, 8.0F);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    protected void refreshSizes() {
        super.refreshSizes();
        if (this.framingRect != null) {
            float end = (float)(this.framingRect.bottom - this.framingRect.top) - this.cornerWidth;
            if (this.scannerEnd != end) {
                this.scannerStart = this.cornerWidth;
                this.scannerEnd = end;
            }
        }

    }

    public void onDraw(Canvas canvas) {
        this.refreshSizes();
        if (this.framingRect == null) {
            if (!this.isInEditMode()) {
                return;
            }

            this.framingRect = new Rect(0, 0, this.width, this.height);
        }

        Rect frame = this.framingRect;
        this.drawExterior(canvas, frame);
        this.drawCorner(canvas, frame);
        this.drawLaserScanner(canvas, frame);
        this.postInvalidateDelayed(10L, frame.left, frame.top, frame.right, frame.bottom);
    }

    private void drawExterior(Canvas canvas, Rect frame) {
        this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
        canvas.drawRect(0.0F, 0.0F, (float)this.width, (float)frame.top, this.paint);
        canvas.drawRect(0.0F, (float)frame.top, (float)frame.left, (float)(frame.bottom + 1), this.paint);
        canvas.drawRect((float)(frame.right + 1), (float)frame.top, (float)this.width, (float)(frame.bottom + 1), this.paint);
        canvas.drawRect(0.0F, (float)(frame.bottom + 1), (float)this.width, (float)this.height, this.paint);
    }

    private void drawCorner(Canvas canvas, Rect frame) {
        this.paint.setColor(this.cornerColor);
        canvas.drawRect((float)frame.left, (float)frame.top, (float)frame.left + this.cornerWidth, (float)frame.top + this.cornerLength, this.paint);
        canvas.drawRect((float)frame.left, (float)frame.top, (float)frame.left + this.cornerLength, (float)frame.top + this.cornerWidth, this.paint);
        canvas.drawRect((float)frame.right - this.cornerWidth, (float)frame.top, (float)frame.right, (float)frame.top + this.cornerLength, this.paint);
        canvas.drawRect((float)frame.right - this.cornerLength, (float)frame.top, (float)frame.right, (float)frame.top + this.cornerWidth, this.paint);
        canvas.drawRect((float)frame.left, (float)frame.bottom - this.cornerWidth, (float)frame.left + this.cornerLength, (float)frame.bottom, this.paint);
        canvas.drawRect((float)frame.left, (float)frame.bottom - this.cornerLength, (float)frame.left + this.cornerWidth, (float)frame.bottom, this.paint);
        canvas.drawRect((float)frame.right - this.cornerWidth, (float)frame.bottom - this.cornerLength, (float)frame.right, (float)frame.bottom, this.paint);
        canvas.drawRect((float)frame.right - this.cornerLength, (float)frame.bottom - this.cornerWidth, (float)frame.right, (float)frame.bottom, this.paint);
    }

    private void drawLaserScanner(Canvas canvas, Rect frame) {
        this.paint.setColor(this.laserColor);
        RadialGradient radialGradient = new RadialGradient((float)(this.width / 2), this.scannerStart + (float)frame.top + this.laserThickness / 2.0F, 360.0F, this.laserColor, this.shadeColor(this.laserColor), TileMode.MIRROR);
        this.paint.setShader(radialGradient);
        if (this.scannerStart <= this.scannerEnd && this.scannerStart >= this.cornerWidth) {
            RectF rectF = new RectF((float)frame.left + 2.0F * this.laserThickness, this.scannerStart + (float)frame.top, (float)frame.right - 2.0F * this.laserThickness, this.scannerStart + (float)frame.top + this.laserThickness);
            canvas.drawOval(rectF, this.paint);
            this.changeScannerStart();
        } else {
            this.scannerIncrease = !this.scannerIncrease;
            this.changeScannerStart();
        }

        this.paint.setShader((Shader)null);
    }

    private int shadeColor(int color) {
        String hax = Integer.toHexString(color);
        String result = "20" + hax.substring(2);
        return Integer.valueOf(result, 16);
    }

    private void changeScannerStart() {
        if (this.scannerIncrease) {
            this.scannerStart += 5.0F;
        } else {
            this.scannerStart -= 5.0F;
        }

    }
}
