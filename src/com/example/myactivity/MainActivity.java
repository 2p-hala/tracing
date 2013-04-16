package com.example.myactivity;

import java.util.ArrayList;

import CustomObject.MovingObject;
import CustomObject.TracedView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnTouchListener {

	// TextView _view;
	ViewGroup _root;
	MovingObject bee;
	private int _xDelta;
	private int _yDelta;
	private int currentindex = 0;
	private int bee_width, bee_height, bee_top, bee_left, bee_center_x,
			bee_center_y;
	ArrayList<TracedView> flowers = new ArrayList<TracedView>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		flowers.add((TracedView) findViewById(R.id.point_1));
		flowers.add((TracedView) findViewById(R.id.point_2));
		flowers.add((TracedView) findViewById(R.id.point_3));
		flowers.add((TracedView) findViewById(R.id.s1));
		flowers.add((TracedView) findViewById(R.id.s2));
		flowers.add((TracedView) findViewById(R.id.s3));
		flowers.add((TracedView) findViewById(R.id.s4));
		flowers.add((TracedView) findViewById(R.id.s5));
		flowers.add((TracedView) findViewById(R.id.s6));
		flowers.add((TracedView) findViewById(R.id.s7));
		for (TracedView v : flowers) {
			v.setViewAttributes();
		}
		bee = (MovingObject) findViewById(R.id.bee);
		bee.setViewAttributes();

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
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:

			bee_center_x = X - _xDelta + bee_width / 2;
			bee_center_y = Y - _yDelta + bee_height / 2;

			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			layoutParams.leftMargin = X - _xDelta;
			layoutParams.topMargin = Y - _yDelta;
			layoutParams.rightMargin = -250;
			layoutParams.bottomMargin = -250;
			view.setLayoutParams(layoutParams);
			bee.setCenterX(X - _xDelta);
			bee.setCenterY(Y - _yDelta);
			if (flowers.get(3).isInsideBounds(bee.getCenterX(), bee.getCenterY())) {
				Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
			}
			break;
		}
		_root.invalidate();
		return true;
	}
}