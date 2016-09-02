package com.lingdian.saylove.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lingdian.saylove.R;

public class CheckWorkImageView extends ImageView {
	private Bitmap mask;
	private int resource;//R.drawable.xxx
	
    /** int resourc : R.drawable.xxx*/
	public CheckWorkImageView(Context paramContext) {
		super(paramContext);
		init();
	}

	public CheckWorkImageView(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		init();
	}

	public CheckWorkImageView(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		init();
	}

	public void setBackImg(int resource){
		this.resource = resource;
	}
	private void init() {
		this.mask = BitmapFactory.decodeResource(getResources(),
				R.drawable.places);
	}

	protected void onDraw(Canvas paramCanvas) {
	
		if (isInEditMode()) { return; }
		Drawable localDrawable = getDrawable();
		if ((localDrawable == null)|| (!(localDrawable instanceof BitmapDrawable)))
			return;
		Bitmap localBitmap = ((BitmapDrawable) localDrawable).getBitmap();
		Paint localPaint = new Paint(1);
		localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		RectF localRectF = new RectF(0.0F, 0.0F, getWidth(), getHeight());
		paramCanvas.saveLayer(localRectF, null, 31);
		paramCanvas
				.drawBitmap(localBitmap, new Rect(0, 0, localBitmap.getWidth(),
						localBitmap.getHeight()), localRectF, null);
		paramCanvas.drawBitmap(this.mask, new Rect(0, 0, this.mask.getWidth(),
				this.mask.getHeight()), localRectF, localPaint);
		paramCanvas.restore();
	}
		
		
}
