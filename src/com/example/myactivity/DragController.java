package com.example.myactivity;

import java.util.ArrayList;

import CustomObject.DrawView;
import CustomObject.MovingObject;
import CustomObject.TracedView;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public abstract class DragController {
	Context context;
	View root;
	MovingObject movingObject;
	ArrayList<TracedView> tracedViewsList;
	int currentIndex;
	TracedView currentTracedObject, pervTracedObject;
	DrawView drawView;
	private boolean finished;

	public DragController(Context context, View root,
			MovingObject movingObject, ArrayList<TracedView> tracedViewsList,
			DrawView drawView) {
		super();
		this.context = context;
		this.root = root;
		this.movingObject = movingObject;
		this.tracedViewsList = tracedViewsList;
		this.drawView = drawView;
		currentIndex = 0;
		currentTracedObject = tracedViewsList.get(currentIndex);
		pervTracedObject = tracedViewsList.get(currentIndex);
	}

	public DragController(Context context) {
		super();
		this.context = context;
	}

	public abstract boolean updateView();

	public boolean checkCollision(int x, int y) {
		if (!finished) {

			// if (currentTracedObject == tracedViewsList.get(currentIndex)) {
			if (currentTracedObject.isInsideBounds(x, y)) {
				currentIndex++;
				// Toast.makeText(context, "s " + currentIndex,
				// Toast.LENGTH_LONG)
				// .show();
				currentTracedObject.setVisibility(View.INVISIBLE);
				if (currentIndex >= tracedViewsList.size()) {
					Toast.makeText(context, "Finished", Toast.LENGTH_LONG)
							.show();
					// currentIndex--;
					finished = true;
					return false;
				} else {
					pervTracedObject = currentTracedObject;
					currentTracedObject = tracedViewsList.get(currentIndex);
					if (currentTracedObject.isNewPart()) {
						RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) movingObject
								.getLayoutParams();
						layoutParams.leftMargin = currentTracedObject.topLeftX;
						layoutParams.topMargin = currentTracedObject.topLeftY;
						layoutParams.rightMargin = -250;
						layoutParams.bottomMargin = -250;
						movingObject
								.setLayoutParams(layoutParams);
						Animation animation = new TranslateAnimation(
								movingObject.getCenterX(),
								currentTracedObject.topLeftX
										- movingObject.getCenterX(),
								movingObject.getCenterY(),
								currentTracedObject.topLeftY
										- movingObject.getCenterY());

						animation.setDuration(500);
						animation.setFillAfter(true);
						animation
								.setAnimationListener(new Animation.AnimationListener() {

									@Override
									public void onAnimationStart(
											Animation animation) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationRepeat(
											Animation animation) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationEnd(
											Animation animation) {
									
									}
								});
			//			movingObject.startAnimation(animation);
					}
				}
			}
			// }
			return false;
		}
		return false;
	}

	public void drawStart(float x, float y) {
		drawView.touch_start(x, y);
	}

	public void drawMove(float x, float y) {
		drawView.touch_move(x, y);
	}

	public void drawEnd() {
		drawView.touch_up();
	}

	public boolean checkBoundries(int x, int y) {
		// if a collision is found but not with the current or next star
		return (currentTracedObject.isInsideDrawingBounds(x, y) || pervTracedObject
				.isInsideDrawingBounds(x, y));
		// && !nextTracedObject.isInsideDrawingBounds(x, y))
		// return false;
		// return drawView.checkPath(x, y);
	}
}
