package CustomObject;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class TracedView extends ImageView {
	int width;
	int height;
	public int topLeftX;
	public int topLeftY;
	int topLeftX2;
	int topLeftY2;
	// int drawingBoundsW;
	// int drawingBoundsH;
	int marginW = 10;
	int marginH = 20;
	boolean isNewPart;

	public TracedView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// setViewAttributes();
	}

	public void setViewAttributes() {
		RelativeLayout.LayoutParams lp = (LayoutParams) getLayoutParams();
		width = lp.width;
		height = lp.height;
		topLeftX = lp.leftMargin;
		topLeftY = lp.topMargin;
		topLeftX2 = topLeftX + width;
		topLeftY2 = topLeftY + height;
	}

	public boolean isInsideBounds(int x, int y) {
		if ((x > topLeftX && x < topLeftX2) && (y > topLeftY && y < topLeftY2)) {
			return true;
		}
		return false;
	}

	public boolean isInsideDrawingBounds(int x, int y) {
		if ((x > topLeftX - marginW && x < topLeftX2 + marginW)
				&& (y > topLeftY - marginH && y < topLeftY2 + marginH)) {
			return true;
		}
		return false;
	}

	public boolean isNewPart() {
		return isNewPart;
	}

	public void setNewPart(boolean isNewPart) {
		this.isNewPart = isNewPart;
	}

}
