package vd.android.vdmask.component;

import android.content.Context;
import android.graphics.Canvas;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXViewUtils;

import java.lang.ref.SoftReference;

/**
 * Created by Carry on 2017/4/28.
 */

public class VDMaskLayout extends FrameLayout implements WXGestureObservable {
    private WXGesture wxGesture;
    private SoftReference<VDMask> mComponent;

    public VDMaskLayout(Context context) {
        super(context);
    }

    @Override
    public void registerGestureListener(WXGesture wxGesture) {
        this.wxGesture = wxGesture;
    }

    @Override
    public WXGesture getGestureListener() {
        return this.wxGesture;
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        if (wxGesture != null) {
            result |= wxGesture.onTouch(this, event);
        }
        return result;
    }

    protected void onDraw(Canvas canvas) {
        WXViewUtils.clipCanvasWithinBorderBox(this, canvas);
        super.onDraw(canvas);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mComponent != null) {
            VDMask vdMask = mComponent.get();
            if (VDMask.TRANSLATE.equalsIgnoreCase(vdMask.getCurrentAnimationType()) && VDMask
                    .BOTTOM.equalsIgnoreCase(vdMask.getCurrentAnimationPosition())) {
                layoutChildToBottom();
            }
        }
    }


    private void layoutChildToBottom() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child
                    .getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            layoutParams.gravity = Gravity.BOTTOM;
            child.setLayoutParams(layoutParams);
        }
    }


    public void setHoldComponent(VDMask mask) {
        if (mComponent == null) {
            mComponent = new SoftReference<VDMask>(mask);
        }
    }


}
