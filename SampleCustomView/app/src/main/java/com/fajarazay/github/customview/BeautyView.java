package com.fajarazay.github.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Fajar Septian on 2019-03-05.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
public class BeautyView extends LinearLayout {
    public BeautyView(Context context) {
        super(context);
        init(context, null);
    }

    public BeautyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BeautyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BeautyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet set) {
        View view = inflate(context, R.layout.beauty_item, this);
        ImageView imageView = view.findViewById(R.id.foto);
        TextView textView = view.findViewById(R.id.nama);

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.BeautyView);

        imageView.setImageDrawable(typedArray.getDrawable(R.styleable.BeautyView_foto));
        textView.setText(typedArray.getString(R.styleable.BeautyView_nama));

        typedArray.recycle();
    }
}
