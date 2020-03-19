package vd.android.vdmask.component;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXVContainer;

import vd.android.vdmask.R;

/**
 * Created by Carry on 2017/4/28.
 */

public class VDPop extends WXVContainer<FrameLayout> {
    private Animation mShowAnimation;
    private Animation mHideAnimation;
    private Animation mCenterShow;
    private Animation mCenterHide;
    private Animation mAlphaShow;
    private Animation mAlphaHide;
    private Animation mTranslateTopShow;
    private Animation mTranslateTopHide;
    private FrameLayout mHost;
    private int mHeight;
    private static final int STATUS_SHOW = 0;
    private static final int STATUS_HIDE = 1;
    private int mCurrentStatus = STATUS_HIDE;
    private boolean isAnimating;

    public VDPop(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);

    }

    @Override
    protected FrameLayout initComponentHostView(Context context) {
        mHost = new FrameLayout(context);
        return mHost;
    }


    private void getAnimationHeight() {
        WXStyle wxStyle = getStyles();
        Object object = wxStyle.get("height");
        if (object == null) return;
        mHeight = Integer.valueOf(object.toString());
    }

    /**
     * @param animationType 默认值translate
     * @param position      默认值bottom
     */
    void showPop(long duration, String animationType, String position) {
        switch (animationType) {
            case VDMask.APLHA:
                showAplha(duration);
                break;
            case VDMask.TRANSLATE:
                if (VDMask.BOTTOM.equals(position)) {
                    show(duration);
                } else if (VDMask.TOP.equals(position)) {
                    translateShow(duration);
                }
                break;
            case VDMask.SCALE:
                showInCenter(duration);
                break;
        }
    }

    private void translateShow(long duration) {
        if (isAnimating || mCurrentStatus == STATUS_SHOW) {
            return;
        }
        if (mTranslateTopShow == null) {
            mTranslateTopShow = AnimationUtils.loadAnimation(getContext(), R.anim.anim_topin);
        }
        mTranslateTopShow.setDuration(duration);
        mTranslateTopShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
                mCurrentStatus = STATUS_SHOW;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        getHostView().startAnimation(mTranslateTopShow);
    }

    private void showAplha(long duration) {
        if (isAnimating || mCurrentStatus == STATUS_SHOW) {
            return;
        }
        if (mAlphaShow == null) {
            mAlphaShow = AnimationUtils.loadAnimation(getContext(), R.anim.anim_alpha_show);
        }
        mAlphaShow.setDuration(duration);
        mAlphaShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
                mCurrentStatus = STATUS_SHOW;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        getHostView().startAnimation(mAlphaShow);
    }


    private void showInCenter(long duration) {
        if (isAnimating || mCurrentStatus == STATUS_SHOW) {
            return;
        }
        if (mCenterShow == null) {
            mCenterShow = AnimationUtils.loadAnimation(getContext(), R.anim.anim_enlarge);
        }
        mCenterShow.setDuration(duration);
        mCenterShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
                mCurrentStatus = STATUS_SHOW;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        getHostView().startAnimation(mCenterShow);

    }

    private void show(long duration) {
        if (isAnimating || mCurrentStatus == STATUS_SHOW) {
            return;
        }

        if (mShowAnimation == null) {
//            getAnimationHeight();
//            mShowAnimation = ValueAnimator.ofFloat(mHeight, 0);
            mShowAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_bottom_in);
        }
        mShowAnimation.setDuration(duration);
//        mShowAnimation.setTarget(mHost);
//        mShowAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mHost.setTranslationY((float) animation.getAnimatedValue());
//            }
//        });
        mShowAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
                mCurrentStatus = STATUS_SHOW;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        mShowAnimation.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                isAnimating = true;
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                isAnimating = false;
//                mCurrentStatus = STATUS_SHOW;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });

        getHostView().startAnimation(mShowAnimation);
//        mShowAnimation.start();

    }

    void hidePop(long duration, String animationType, String position) {
        switch (animationType) {
            case VDMask.APLHA:
                hideAlpha(duration);
                break;
            case VDMask.TRANSLATE:
                if (VDMask.BOTTOM.equals(position)) {
                    hide(duration);
                } else if (VDMask.TOP.equals(position)) {
                    translateHide(duration);
                }
                break;
            case VDMask.SCALE:
                hideInCenter(duration);
                break;
        }
    }

    private void translateHide(long duration) {
        if (isAnimating) {
            return;
        }
        if (mTranslateTopHide == null) {
            mTranslateTopHide = AnimationUtils.loadAnimation(getContext(), R.anim.anim_topout);
        }
        mTranslateTopHide.setDuration(duration);
        mTranslateTopHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
                mCurrentStatus = STATUS_HIDE;
                hideParent();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        getHostView().startAnimation(mTranslateTopHide);
    }

    private void hideAlpha(long duration) {
        if (isAnimating) {
            return;
        }
        if (mAlphaHide == null) {
            mAlphaHide = AnimationUtils.loadAnimation(getContext(), R.anim.anim_alhpa_hide);
        }
        mAlphaHide.setDuration(duration);
        mAlphaHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
                mCurrentStatus = STATUS_HIDE;
                hideParent();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        getHostView().startAnimation(mAlphaHide);
    }

    private void hideInCenter(long duration) {
        if (isAnimating) {
            return;
        }
        if (mCenterHide == null) {
            mCenterHide = AnimationUtils.loadAnimation(getContext(), R.anim.anim_reduce);
        }
        mCenterHide.setDuration(duration);
        mCenterHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("animation", "centerhide");
                isAnimating = false;
                mCurrentStatus = STATUS_HIDE;
                hideParent();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        getHostView().startAnimation(mCenterHide);
    }

    void hide(long duration) {
        if (mHideAnimation == null) {
//            getAnimationHeight();
            mHideAnimation = AnimationUtils.loadAnimation(getContext(),R.anim.anim_bottom_out);
        }
        mHideAnimation.setDuration(duration);
        mHideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
                mCurrentStatus = STATUS_HIDE;
                hideParent();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        getHostView().startAnimation(mHideAnimation);
//        mHideAnimation.setTarget(mHost);
//        mHideAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mHost.setTranslationY((float) animation.getAnimatedValue());
//            }
//        });
//        mHideAnimation.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                isAnimating = true;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                isAnimating = false;
//                mCurrentStatus = STATUS_HIDE;
//                hideParent();
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        mHideAnimation.start();
    }

    private void hideParent() {
        WXVContainer parent = getParent();
        if (parent instanceof VDMask) {
            VDMask vdMask = (VDMask) parent;
            vdMask.hideSelf();
        }
    }

}
