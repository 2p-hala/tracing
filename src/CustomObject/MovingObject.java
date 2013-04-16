package CustomObject;

import com.example.myactivity.MainActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MovingObject extends ImageView implements View.OnTouchListener {
	int width;
	int height;
	int topLeftX;
	int topLeftY;
	int centerX;
	int centerY;
	private int _xDelta;
	private int _yDelta;

	public MovingObject(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnTouchListener(this);
	}

	public void setViewAttributes() {
		RelativeLayout.LayoutParams lp = (LayoutParams) getLayoutParams();
		width = lp.width;
		height = lp.height;
		topLeftX = lp.leftMargin;
		topLeftY = lp.topMargin;
		centerX = topLeftX + width / 2;
		centerY = topLeftY + height / 2;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX + width / 2;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY + height / 2;
	}

	public boolean onTouch(View view, MotionEvent event) {
		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:

			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			_xDelta = X - lParams.leftMargin;
			_yDelta = Y - lParams.topMargin;
			MainActivity.dragC.drawStart(centerX, centerY);
			break;
		case MotionEvent.ACTION_UP:
			MainActivity.dragC.drawEnd();
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:

			centerX = X - _xDelta + width / 2;
			centerY = Y - _yDelta + height / 2;

			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			layoutParams.leftMargin = X - _xDelta;
			layoutParams.topMargin = Y - _yDelta;
			layoutParams.rightMargin = -250;
			layoutParams.bottomMargin = -250;
			view.setLayoutParams(layoutParams);
			MainActivity.dragC.drawMove(centerX+ width / 4, centerY- height / 3);
			MainActivity.dragC.checkCollision(centerX, centerY);
//			 if (flowers.get(3).isInsideBounds(bee.getCenterX(),
			// bee.getCenterY())) {
			// Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
			// }
			break;
		}
		// _root.invalidate();
		return true;
	}
}
