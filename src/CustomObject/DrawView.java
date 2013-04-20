package CustomObject;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class DrawView extends ImageView {

	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	private Paint mBitmapPaint;
	Paint p = new Paint();
	HashMap<String, Integer> points;
	private Paint mPaint;
	int strokeSize = 30;
	private Bitmap imageOriginal;
	private int font_color = 0xffffffff;
	int outOfBoundry = 0;
	int maxError = 30;

	public DrawView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		mPath = new Path();
		mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		// charToDraw = getResources().getString(R.string.one);

		points = new HashMap<String, Integer>();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(0xFF00FF00);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(strokeSize);
		// imageOriginal = BitmapFactory.decodeResource(getResources(),
		// R.drawable.letter_2);
		//
		// setImageBitmap(imageOriginal);
		imageOriginal = ((BitmapDrawable) getDrawable()).getBitmap();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		imageOriginal = ((BitmapDrawable) getDrawable()).getBitmap();
	}

	public boolean checkPath(int x, int y) {

		try {

			int color = imageOriginal.getPixel(x, y);
			Log.d("color", color + "");
			if (outOfBoundry < maxError)
				if (color == font_color) {
					outOfBoundry = 0;
					return true;
				}
		} catch (Exception e) {
			// outOfBoundry++;
			return false;
		}
		outOfBoundry++;
		return false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawPath(mPath, mPaint);
		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	public void touch_start(float x, float y) {
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
		invalidate();
	}

	public void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if ((dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE)
				&& (outOfBoundry < maxError)) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
			invalidate();
		}
	}

	public void touch_up() {

		mPath.lineTo(mX, mY);
		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mPaint);
		mPath.reset();
		outOfBoundry = 0;
		invalidate();
	}

}
