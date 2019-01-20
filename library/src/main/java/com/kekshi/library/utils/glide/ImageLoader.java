package com.kekshi.library.utils.glide;

import android.content.Context;
import android.widget.ImageView;


public class ImageLoader {

    public static void loadImageFromUrlThumb(Context context, String url, Float thumbnail, ImageView imageView) {
        GlideApp.with(context).load(url).simple().thumbnail(thumbnail).into(imageView);

    }
}
