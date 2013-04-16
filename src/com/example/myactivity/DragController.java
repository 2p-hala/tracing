package com.example.myactivity;

import java.util.ArrayList;

import CustomObject.DrawView;
import CustomObject.MovingObject;
import CustomObject.TracedView;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public abstract class DragController {
	Context context;
	View root;
	MovingObject movingObject;
	ArrayList<TracedView> tracedViewsList;
	int currentIndex;
	TracedView nextTracedObject;
	DrawView drawView;

	public DragController(Context context, View root,
			MovingObject movingObject, ArrayList<TracedView> tracedViewsList,
			DrawView drawView) {
		super();
		this.context = context;
		this.root = root;
		this.movingObject = movingObject;
		this.tracedViewsList = tracedViewsList;
		this.drawView = drawView;
		currentIndex = 3;
		nextTracedObject = tracedViewsList.get(currentIndex);
	}

	public DragController(Context context) {
		super();
		this.context = context;
	}

	public abstract boolean updateView();

	public boolean checkCollision(int x, int y) {
		if (nextTracedObject == tracedViewsList.get(currentIndex)) {
			if (nextTracedObject.isInsideBounds(x, y)) {
				currentIndex++;
				Toast.makeText(context, "s " + currentIndex, Toast.LENGTH_LONG)
						.show();
				if (currentIndex >= tracedViewsList.size()) {
					Toast.makeText(context, "Finished", Toast.LENGTH_LONG)
							.show();
					return false;
				}
				nextTracedObject = tracedViewsList.get(currentIndex);

			}
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
}
