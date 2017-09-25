package com.raythinks.poesia.ui.anim

import android.support.v4.view.ViewPager.PageTransformer
import android.view.View

class ZoomOutPagerAnim : PageTransformer {

    override fun transformPage(view: View, position: Float) {
        //		int pageWidth = view.getWidth();
        //		int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.alpha = 0f
            view.translationX = 0f
        } else if (position <= 1) { // [-1,1]
            //									// Modify the default slide transition to
            //									// shrink the page as well
            val scaleFactor = Math.max(MIN_ALPHA, 1 - Math.abs(position))
            //			float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            //			float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            //			if (position < 0) {
            //				view.setTranslationX(horzMargin - vertMargin / 2);
            //			} else {
            //				view.setTranslationX(-horzMargin + vertMargin / 2);
            //			}
            // Scale the page down (between MIN_SCALE and 1)
            // Fade the page relative to its size.
            view.alpha = scaleFactor
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.alpha = 0f
            view.translationX = 0f
        }
    }

    companion object {
        private val MIN_SCALE = 0.85f

        private val MIN_ALPHA = 0.5f
    }
}