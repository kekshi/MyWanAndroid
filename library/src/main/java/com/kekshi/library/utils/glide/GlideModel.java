package com.kekshi.library.utils.glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 没有这个类，会报 GlideApp 找不到的错
 */

@GlideModule
public class GlideModel extends AppGlideModule {
}
