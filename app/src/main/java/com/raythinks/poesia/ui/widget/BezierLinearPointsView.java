package com.raythinks.poesia.ui.widget;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.raythinks.poesia.R;

/**
 * 功能：指示器<br>
 * 作者：zh<br>
 * 时间： 2017/10/10 0010<br>.
 * 版本：1.2.0
 */
public class BezierLinearPointsView extends View {
    // 绘制圆形贝塞尔曲线控制点的位置
    private static final float C = 0.551915024494f;

    private int mViewWidth = 0, mViewHeight = 0;
    private int mViewLeftPadding = 0, mViewRightPadding = 0,
            mViewTopPadding = 0, mViewButtomPadding = 0;

    // 点数，默认为3
    private int mCount = 3;
    // 选中小点颜色
    private int mSelectedColor = Color.GREEN;
    // 未选中小点颜色
    private int mUnSelectedColor = Color.YELLOW;
    // 小点大小
    private int mPointSize = 50;
    // 画笔宽度
    private int mPaintStrokeWidth = 6;
    // 位移动画时间
    private long mDuration = 500;
    // 恢复动画时间
    private long mDurationRe = 200;
    // 大小抖动动画时间
    private long mDurationSize = 500;
    // 是否循环
    private boolean isLooper = false;
    // 外发光宽度 是 线条宽度的几倍
    private int mBlurRadio = 2;

    // 两个圆的paint
    private Paint mUnselectedPaint, mSelectedPaint;
    private Path mPath = new Path();
    // 存放未选中点的 位置数据
    private ArrayList<int[]> mUnselectedPoints = new ArrayList<int[]>();
    // 当前位置
    private int mCurrentIndex = 0, mToIndex = 0;
    // 两个圆之间圆心距离
    private int mDis = 20;
    // 当前选中点的位置
    private int[] mCurrentPointCenter;
    // 将要选中点的位置
    private int[] mToPointCenter;
    // 动画执行进度,用来控制圆的形变程度
    private float mPercent = 0f;
    // 反弹动画执行进度,用来控制圆的复原形变程度
    private float mRePercent = 0f;
    // 圆圈从未选中到选中抖动动画程度
    private float mSizePercent = 1f;
    // 圆形的控制点与数据点的差值
    private float mDifference;
    // 顺时针记录绘制圆形的四个数据点
    private float[] mData = new float[8];
    // 顺时针记录绘制圆形的八个控制点
    private float[] mCtrl = new float[16];
    // 来标识形变 恢复的时候是改动哪个点。0：两点相同，不变。1：index增加恢复，更改的是圆的左点。-1：index减少恢复，更改圆右点
    private int isIndexInCreate = 0;

    // 位移动画
    private ValueAnimator va;
    // 恢复动画
    private ValueAnimator vaRe;
    // 圆圈从 选中到不选中，粘性动画
    private ValueAnimator vaSize;

    public BezierLinearPointsView(Context context) {
        super(context);
        init(context, null);
    }

    public BezierLinearPointsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BezierLinearPointsView(Context context, AttributeSet attrs,
                                  int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

//	public BezierLinearPointsView(Context context, AttributeSet attrs,
//			int defStyleAttr, int defStyleRes) {
//		super(context, attrs, defStyleAttr, defStyleRes);
//		init(context, attrs);
//	}

    /**
     * 初始化一些数据
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
        //padding
        mViewButtomPadding = getPaddingBottom() + getPaintStrokeWidth()
                * getBlurRadio();
        mViewRightPadding = getPaddingRight() + getPaintStrokeWidth()
                * getBlurRadio();
        mViewLeftPadding = getPaddingLeft() + getPaintStrokeWidth()
                * getBlurRadio();
        mViewTopPadding = getPaddingTop() + getPaintStrokeWidth()
                * getBlurRadio();

        // 在宽度来说，点的最大半径
        int pointSizeWidth = (mViewWidth - mViewLeftPadding - mViewRightPadding)
                / getCount();

        // 在高度来说，点的最大半径
        int pointSizeHeight = mViewHeight - mViewButtomPadding
                - mViewTopPadding;
        // 综合得到的最大半径
        int resultSize = Math.min(pointSizeWidth, pointSizeHeight);
        // 与规定半径相比,得出最小的半径.
        setPointSize(Math.min(getPointSize(), resultSize));
        // 得到圆控制点的距离
        mDifference = getPointSize() / 2 * C;
        // 两两圆点 圆心距离
        mDis = (mViewWidth - mViewLeftPadding - mViewRightPadding
                - getPointSize() - getPaintStrokeWidth())
                / (getCount() - 1);
    }

    @SuppressLint("NewApi")
    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs,
                    R.styleable.linearpointsview);
            setCount(ta.getInt(R.styleable.linearpointsview_count, getCount()));
            mSelectedColor = ta.getColor(R.styleable.linearpointsview_select_color,
                    mSelectedColor);
            setUnSelectedColor(ta.getColor(
                    R.styleable.linearpointsview_unselect_color,
                    getUnSelectedColor()));
            setPointSize(ta.getDimensionPixelOffset(
                    R.styleable.linearpointsview_pointsize, getPointSize()));
            setPaintStrokeWidth(ta.getDimensionPixelOffset(
                    R.styleable.linearpointsview_pointstrokewidth,
                    getPaintStrokeWidth()));
            setDuration(ta.getInt(R.styleable.linearpointsview_anim_move_duration,
                    (int) getDuration()));
            setDurationRe(ta.getInt(
                    R.styleable.linearpointsview_anim_move_duration,
                    (int) getDurationRe()));
            setDurationSize(ta.getInt(
                    R.styleable.linearpointsview_anim_move_duration,
                    (int) geDurationSize()));
            setLooper(ta.getBoolean(R.styleable.linearpointsview_islooper,
                    isLooper()));
            setBlurRadio(ta.getInt(R.styleable.linearpointsview_blur_radio,
                    getBlurRadio()));
            ta.recycle();
        }

        mUnselectedPaint = new Paint();
        mUnselectedPaint.setAntiAlias(true);
        mUnselectedPaint.setDither(true);
        mUnselectedPaint.setStyle(Style.STROKE);
        mUnselectedPaint.setMaskFilter(new EmbossMaskFilter(new float[]{1, 1,
                1}, 0.4f, 6, 3.5f));
        mUnselectedPaint.setStrokeWidth(getPaintStrokeWidth());
        mUnselectedPaint.setColor(getUnSelectedColor());
        //不加这个maskFilter没效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, mUnselectedPaint);
        }

        mSelectedPaint = new Paint();
        mSelectedPaint.setAntiAlias(true);
        mSelectedPaint.setStyle(Style.FILL);
        mSelectedPaint.setMaskFilter(new BlurMaskFilter(getPaintStrokeWidth(),
                Blur.SOLID));
        mSelectedPaint.setColor(mSelectedColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, mSelectedPaint);
        }
    }

    /**
     * 选中此点
     *
     * @param index 选中的索引
     */
    public void selectIndex(int index) {
        if (index < 0 || index >= mUnselectedPoints.size()) {
            return;
        }

        //当上衣个动画没有结束，就不会进行mCurrent改变。主动调用cancel，结束动画，及时改变mCurrent的值
        if (va != null) {
            if (va.isRunning()) {
                va.cancel();
            }
        }
        if (vaRe != null) {
            if (vaRe.isRunning()) {
                vaRe.cancel();
            }
        }

        this.mToIndex = index;

        if (mCurrentIndex == mToIndex) {
            //相等则不变化，可以加一些抖动动画
            mPercent = 0;
            postInvalidate();
        } else {
            //启动选中的圆圈弹性动画(在圆点脱离圆圈时,会有一定的粘性的感觉)
            vaSize = ObjectAnimator.ofFloat(1f, 0.8f, 1f);
            vaSize.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg0) {
                    mSizePercent = arg0.getAnimatedFraction();
                    postInvalidate();
                }
            });
            vaSize.setDuration(geDurationSize());
            vaSize.setInterpolator(new OvershootInterpolator());
            vaSize.start();

            //启动圆点的移动动画
            va = ObjectAnimator.ofFloat(0f, 1f);
            va.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg0) {
                    float f = arg0.getAnimatedFraction();
                    mPercent = f;
                    postInvalidate();
                }
            });
            va.setDuration((long) getDuration());
            va.setInterpolator(new AccelerateDecelerateInterpolator());
            va.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator arg0) {
                }

                @Override
                public void onAnimationRepeat(Animator arg0) {

                }

                /**
                 * 移动动画结束后，启动圆点复原动画，并将选中数据及时赋值
                 */
                @Override
                public void onAnimationEnd(Animator arg0) {
                    //这里isIndexInCreate是用来标示  小圆点移动完成后,复原数据的控制。
                    if (mCurrentIndex == mToIndex) {
                        isIndexInCreate = 0;
                    } else if (mCurrentIndex > mToIndex) {
                        //左移
                        isIndexInCreate = -1;
                    } else {
                        isIndexInCreate = 1;
                    }
                    //及时改变数据
                    mCurrentIndex = mToIndex;
                    //复原动画
                    vaRe = ObjectAnimator.ofFloat(0f, 1f);
                    vaRe.setInterpolator(new OvershootInterpolator());
                    vaRe.addUpdateListener(new AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator arg0) {
                            float f = arg0.getAnimatedFraction();
                            mRePercent = f;
                            postInvalidate();
                        }
                    });
                    vaRe.setDuration(getDurationRe());
                    vaRe.start();
                }

                @Override
                public void onAnimationCancel(Animator arg0) {
                    //这里isIndexInCreate是用来标示 在快速切换时 小圆点移动完成后,复原数据的控制。
                    if (mCurrentIndex == mToIndex) {
                        isIndexInCreate = 0;
                    } else if (mCurrentIndex > mToIndex) {
                        //左移
                        isIndexInCreate = -1;
                    } else {
                        isIndexInCreate = 1;
                    }
                    mCurrentIndex = mToIndex;
                    //postInvalidate();
                }
            });
            va.start();
        }
    }

    /**
     * 选中下一个
     */
    public void selectNext() {
//当上衣个动画没有结束，就不会进行mCurrent改变。主动调用cancel，结束动画，及时改变mCurrent的值
        if (va != null) {
            if (va.isRunning()) {
                va.cancel();
            }
        }
        if (vaRe != null) {
            if (vaRe.isRunning()) {
                vaRe.cancel();
            }
        }
        int toIndex = mCurrentIndex + 1;
        if (isLooper()) {
            if (toIndex >= getCount()) {
                toIndex = 0;
            }
        } else {
            if (toIndex >= getCount()) {
                toIndex = mCurrentIndex;
            }
        }
        selectIndex(toIndex);
    }


    /**
     * 选中上一个
     */
    public void selectPre() {
        if (va != null) {
            if (va.isRunning()) {
                va.cancel();
            }
        }
        if (vaRe != null) {
            if (vaRe.isRunning()) {
                vaRe.cancel();
            }
        }
        int toIndex = mCurrentIndex - 1;
        if (isLooper()) {
            if (toIndex < 0) {
                toIndex = getCount() - 1;
            }
        } else {
            if (toIndex < 0) {
                toIndex = mCurrentIndex;
            }
        }
        selectIndex(toIndex);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnselectedPoints(canvas);
        drawSelectedPoint(canvas);
    }

    /**
     * 画圆点
     *
     * @param canvas
     */
    private void drawSelectedPoint(Canvas canvas) {
        //获取到当前选中的小圆圈 的位置数据
        mCurrentPointCenter = mUnselectedPoints.get(mCurrentIndex);
        //获取到 将要选中的小圆圈 的位置数据
        mToPointCenter = mUnselectedPoints.get(mToIndex);

        //获取小圆点将要移动的总距离
        float disX = mToPointCenter[0] - mCurrentPointCenter[0];
        //根据动画执行进度,获取当前移动了的距离
        float offsetX = disX * mPercent;

        //改变圆点正下方的定点X数据
        mData[0] = mCurrentPointCenter[0] + offsetX;
        //改变圆点正下方的定点Y数据
        mData[1] = mCurrentPointCenter[1] + getPointSize() / 2;
        if (mToIndex > mCurrentIndex) {
            //如果小圆点向右移动，右顶点只是改变移动数据
            mData[2] = mCurrentPointCenter[0] + offsetX + getPointSize() / 2;
        } else if (mToIndex < mCurrentIndex) {
            //如果小圆点向左移动,右顶点还需要算上拉伸的距离
            mData[2] = mCurrentPointCenter[0] + offsetX + getPointSize() / 2
                    + getPointSize() / 2 * mPercent;
        } else {
            //当小圆点移动到位时,开始复原
            if (isIndexInCreate == -1) {
                //如果左移了,则右顶点开始复原
                mData[2] = mCurrentPointCenter[0] + offsetX + getPointSize()
                        - (float) getPointSize() / 2 * mRePercent;
            } else if (isIndexInCreate == 0) {
                //如果没变,则不用复原
                mData[2] = mCurrentPointCenter[0] + offsetX + getPointSize()
                        - getPointSize() / 2;
            }
        }
        mData[3] = mCurrentPointCenter[1];
        mData[4] = mCurrentPointCenter[0] + offsetX;
        mData[5] = mCurrentPointCenter[1] - getPointSize() / 2;
        if (mToIndex > mCurrentIndex) {
            mData[6] = mCurrentPointCenter[0] + offsetX - getPointSize() / 2
                    - getPointSize() / 2 * mPercent;
        } else if (mToIndex < mCurrentIndex) {
            mData[6] = mCurrentPointCenter[0] + offsetX - getPointSize() / 2;
        } else {
            if (isIndexInCreate == 1) {
                //如果右移了,则左顶点开始复原
                mData[6] = mCurrentPointCenter[0] + offsetX - getPointSize()
                        + getPointSize() / 2 * mRePercent;
            } else if (isIndexInCreate == 0) {
                mData[6] = mCurrentPointCenter[0] + offsetX - getPointSize()
                        + getPointSize() / 2;
            }
        }

        mData[7] = mCurrentPointCenter[1];

        //下面的控制点,则只需控制移动数据改变就行.
        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;

        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];

        //画圆点
        mPath.reset();
        mPath.moveTo(mData[0], mData[1]);

        mPath.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mData[2],
                mData[3]);
        mPath.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mData[4],
                mData[5]);
        mPath.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mData[6],
                mData[7]);
        mPath.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mData[0],
                mData[1]);

        canvas.drawPath(mPath, mSelectedPaint);
    }

    /**
     * 画圆圈
     *
     * @param canvas
     */
    private void drawUnselectedPoints(Canvas canvas) {
        int centerY = mViewHeight / 2;
        for (int i = 0; i < getCount(); i++) {
            // 设置要选中的圈 发光
            if (i == mToIndex) {
                mUnselectedPaint.setMaskFilter(new BlurMaskFilter(
                        getPaintStrokeWidth() * getBlurRadio(), Blur.SOLID));
            } else {
                mUnselectedPaint.setMaskFilter(null);
            }
            //计算各个圆圈中心点的位置
            int centerX = mViewLeftPadding + getPointSize() / 2
                    + getPaintStrokeWidth() / 2 + mDis * i;

            if (i == mCurrentIndex) {
                //如果这是选中圈,则还要执行圆圈的抖动动画.
                canvas.drawCircle(centerX, centerY, getPointSize() / 2
                        * mSizePercent, mUnselectedPaint);
            } else {
                canvas.drawCircle(centerX, centerY, getPointSize() / 2,
                        mUnselectedPaint);
            }

            //把圆圈数据保存下来
            int[] ints = new int[]{centerX, centerY};
            mUnselectedPoints.add(ints);
        }
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    public int getUnSelectedColor() {
        return mUnSelectedColor;
    }

    public void setUnSelectedColor(int mUnSelectedColor) {
        this.mUnSelectedColor = mUnSelectedColor;
    }

    public int getPointSize() {
        return mPointSize;
    }

    public void setPointSize(int mPointSize) {
        this.mPointSize = mPointSize;
    }

    public int getPaintStrokeWidth() {
        return mPaintStrokeWidth;
    }

    public void setPaintStrokeWidth(int mPaintStrokeWidth) {
        this.mPaintStrokeWidth = mPaintStrokeWidth;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public long getDurationRe() {
        return mDurationRe;
    }

    public void setDurationRe(long mDurationRe) {
        this.mDurationRe = mDurationRe;
    }

    public long geDurationSize() {
        return mDurationSize;
    }

    public void setDurationSize(long mDurationSize) {
        this.mDurationSize = mDurationSize;
    }

    public boolean isLooper() {
        return isLooper;
    }

    public void setLooper(boolean isLooper) {
        this.isLooper = isLooper;
    }

    public int getBlurRadio() {
        return mBlurRadio;
    }

    public void setBlurRadio(int mBlurRadio) {
        this.mBlurRadio = mBlurRadio;
    }

}