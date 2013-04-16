package com.example.myactivity;

import java.util.ArrayList;

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

	public DragController(Context context, View root,
			MovingObject movingObject, ArrayList<TracedView> tracedViewsList) {
		super();
		this.context = context;
		this.root = root;
		this.movingObject = movingObject;
		this.tracedViewsList = tracedViewsList;
		nextTracedObject = tracedViewsList.get(0);
	}

	public DragController(Context context) {
		super();
		this.context = context;
	}

	public abstract boolean updateView();

	public boolean checkCollision(int x, int y) {
		if (nextTracedObject.isInsideBounds(movingObject.getCenterX(),
				movingObject.getCenterY())) {
			currentIndex++;
			if (currentIndex >= tracedViewsList.size()) {
				Toast.makeText(context, "Finished", Toast.LENGTH_LONG).show();
				return false;
			}
			nextTracedObject = tracedViewsList.get(currentIndex);
			
		}
		return false;
	}
}
