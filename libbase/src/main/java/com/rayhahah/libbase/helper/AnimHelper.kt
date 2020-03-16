package com.rayhahah.libbase.helper

import android.animation.*
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.google.android.material.appbar.CollapsingToolbarLayout


/**
 * ┌───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│ │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│ ┌┐    ┌┐    ┌┐
 * └───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘ └┘    └┘    └┘
 * ┌──┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐┌───┬───┬───┐┌───┬───┬───┬───┐
 * │~`│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp ││Ins│Hom│PUp││N L│ / │ * │ - │
 * ├──┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤├───┼───┼───┤├───┼───┼───┼───┤
 * │Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ ││Del│End│PDn││ 7 │ 8 │ 9 │   │
 * ├────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤└───┴───┴───┘├───┼───┼───┤ + │
 * │Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │             │ 4 │ 5 │ 6 │   │
 * ├─────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤    ┌───┐    ├───┼───┼───┼───┤
 * │Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │    │ ↑ │    │ 1 │ 2 │ 3 │   │
 * ├────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤┌───┼───┼───┐├───┴───┼───┤ E││
 * │Ctrl│Ray │Alt │         Space         │ Alt│code│fuck│Ctrl││ ← │ ↓ │ → ││   0   │ . │←─┘│
 * └────┴────┴────┴───────────────────────┴────┴────┴────┴────┘└───┴───┴───┘└───────┴───┴───┘
 *
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2018/6/28
 * @tips 这个类是Object的子类
 * @fuction
 */
object AnimHelper {

    val ALPHA = "alpha"
    val TRANSLATION_X = "translationX"
    val TRANSLATION_Y = "translationY"
    val X = "x"
    val Y = "Y"
    val ROTATION = "rotation"
    val ROTATION_X = "rotationX"
    val ROTATION_Y = "rotationY"
    val SCALE_X = "scaleX"
    val SCALE_Y = "scaleY"

    fun createSet(duration: Long, interpolator: Interpolator = LinearInterpolator(),
                  vararg animators: Animator): AnimatorSet {
        val set = AnimatorSet()
        set.interpolator = interpolator
        set.duration = duration
        set.playTogether(*animators)
        return set
    }

    fun createScale(view: View, vararg values: Float): ObjectAnimator {
        val scaleX = PropertyValuesHolder.ofFloat(SCALE_X, *values)
        val scaleY = PropertyValuesHolder.ofFloat(SCALE_Y, *values)
        return ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY)
    }

    fun createAlphaInTranIn(view: View, vararg values: Float): ObjectAnimator {
        val alpha = PropertyValuesHolder.ofFloat(ALPHA, 0f, 1f)
        val transY = PropertyValuesHolder.ofFloat(TRANSLATION_Y, *values)
        return ObjectAnimator.ofPropertyValuesHolder(view, alpha, transY)
    }

    fun createAlphaOutTranOut(view: View, vararg values: Float): ObjectAnimator {
        val alpha = PropertyValuesHolder.ofFloat(ALPHA, 1f, 0f)
        val transY = PropertyValuesHolder.ofFloat(TRANSLATION_Y, *values)
        return ObjectAnimator.ofPropertyValuesHolder(view, alpha, transY)
    }

    fun createHeight(view: View, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(ViewWrapper(view), "height", *values)
    }

    fun createScaleY(view: View, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, SCALE_Y, *values)
    }


    fun createTransX(view: View, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, TRANSLATION_X, *values)
    }

    fun createTransY(view: View, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, *values)
    }

    fun createAlpha(view: View, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, ALPHA, *values)
    }

    fun obtainOption(objectAnimator: ObjectAnimator, duration: Long,
                     timeInterpolator: TimeInterpolator = LinearInterpolator(),
                     repeatCount: Int = 0, repeatMode: Int = ValueAnimator.RESTART): ObjectAnimator {
        objectAnimator.duration = duration
        objectAnimator.interpolator = timeInterpolator
        objectAnimator.repeatCount = repeatCount
        objectAnimator.repeatMode = repeatMode
        return objectAnimator
    }

    open class AnimListener : Animator.AnimatorListener {
        override fun onAnimationRepeat(animator: Animator) {
        }

        override fun onAnimationEnd(animator: Animator) {
        }

        override fun onAnimationCancel(animator: Animator) {
        }

        override fun onAnimationStart(animator: Animator) {
        }

    }

    class ViewWrapper(val view: View) {

        fun setHeight(height: Float) {
            val lp = view.layoutParams
            lp.height = height.toInt()
            view.layoutParams = lp
        }
    }

    open class ToolbarWrapper(val toolbarLayout: CollapsingToolbarLayout,
                              val originTop: Int,
                              val originStart: Int,
                              val targetTop: Int,
                              val targetStart: Int) {
        fun setTrans(trans: Float) {
            toolbarLayout.expandedTitleMarginStart = originStart + ((targetStart - originStart) * trans).toInt()
            toolbarLayout.expandedTitleMarginTop = originTop + ((targetTop - originTop) * trans).toInt()
        }
    }
}
