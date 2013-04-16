package CustomObject;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.myactivity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

public class DrawView extends ImageView {

	private static final float MINP = 0.25f;
	private static final float MAXP = 0.75f;
	// ArrayList<Path> paths = new ArrayList<Path>();
	// private ArrayList<Path> undonePaths = new ArrayList<Path>();

	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	private Path charPathe;
	private Paint mBitmapPaint;
	boolean notDrawnRight = false;
	String charToDraw;
	char[] numbers = new char[10];
	PathMeasure drawingPath;
	PathMeasure charPath;
	int textSize = 600;
	Paint p = new Paint();
	private RectF rectF;
	private Region region;
	// float numOfPionts;
	// float correctPoints;
	// float wrongPoints;
	int drawX = 100;
	int drawY = 550;
	HashMap<String, Integer> points;
	private boolean clean;
	ArrayList<Point> coleredPoints = new ArrayList<Point>(0);
	// private FlaotPoint[] charPathPoints;
	private int step;
	private Paint mPaint;
	int strokeSize = 40;

	public DrawView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);

		charPathe = new Path();
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
	//	setBackgroundResource(R.drawable.letter_1);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);

	}

	public boolean checkPath(float x, float y) {

		// if (region.contains((int) mX, (int) mY)) {
		// // correctPoints++;
		// if (points.get(Integer.toString((int) mX)
		// + Integer.toString((int) mY)) != 1)
		// points.put(
		// Integer.toString((int) mX) + Integer.toString((int) mY),
		// 1);
		// Point p = new Point(mX, mY);
		// coleredPoints.add(p);
		// // int u = points.get((int) mX
		// // +(int) mY);
		// if (!notDrawnRight) {
		// mPaint.setColor(0xFF00FF00);
		// }
		// return true;
		// } else {
		// // wrongPoints+=strokeSize/2;
		// mPaint.setColor(0xFFFF0000);
		// // notDrawnRight = true;
		// return false;
		// }
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// p.setPathEffect(new DashPathEffect(new float[] { 20, 10, 30, 10
		// },0));
		// p.setTypeface(App.DroidFace);
		// if (clean) {
		// mPath.reset();
		// mBitmap.eraseColor(Color.TRANSPARENT);
		// // canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
		// clean = false;
		// }

		canvas.drawPath(mPath, mPaint);
		Path pa;
		canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

	}

	private float mX, mY;
	private int colered;
	private double completnes;
	private static final float TOUCH_TOLERANCE = 4;

	public void touch_start(float x, float y) {
		// // /
		// undonePaths.clear();
		// // /
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
		invalidate();
	}

	public void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			checkPath(mX, mY);
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;

		}
		invalidate();
	}

	public void touch_up() {
		checkPath(mX, mY);

		// // //
		// paths.add(mPath);
		// mPath = new Path();
		// // //
		// kill this so we don't double draw
		// matchOathMatrix();
		// Toast.makeText(MainActivity.this,
		// "correct = "+(correctPoints/numOfPionts)*100,
		// Toast.LENGTH_LONG).show();
		colered = 0;
		
		mPath.lineTo(mX, mY);
		// commit the path to our offscreen
		mCanvas.drawPath(mPath, mPaint);

		// Toast.makeText(MainActivity.this,
		// "correct = " + (colered ) +" of "+points.size(),
		// Toast.LENGTH_LONG).show();
		mPath.reset();
		invalidate();
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		float x = event.getX();
//		float y = event.getY();
//		// if (checkPath(x, y)) {
//		switch (event.getAction()) {
//
//		case MotionEvent.ACTION_DOWN:
//			touch_start(x, y);
//			invalidate();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			touch_move(x, y);
//			invalidate();
//			break;
//		case MotionEvent.ACTION_UP:
//			touch_up();
//			// Mx1=(int) event.getX();
//			// My1= (int) event.getY();
//			invalidate();
//			break;
//		}
//		// }
//		return true;
//	}

}
