package io.github.anywhere.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.anywhere.R; ;

/**
 * Created by PCPC on 2017-06-05.
 */

public class LoginFragmentActivity extends Fragment {
    EditText pswd,usrusr;
    TextView sup,lin,lin2,lin3;
    Context context;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_login_fragmnet, container, false);
        context = getActivity();
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        GradientDrawable drawable= (GradientDrawable) context.getDrawable(R.drawable.background_rounding);


        imageView.setBackground(drawable);
        imageView.setClipToOutline(true);


        lin = (TextView) view.findViewById(R.id.lin);
        //lin2 = (TextView) findViewById(R.id.lin2);
        // lin3 = (TextView) findViewById(R.id.lin3);
        usrusr = (EditText) view.findViewById(R.id.usrusr);
        pswd = (EditText) view.findViewById(R.id.pswrdd);
        sup = (TextView) view.findViewById(R.id.sup);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LatoRegular.ttf");
        lin.setTypeface(custom_font1);
        sup.setTypeface(custom_font);
        usrusr.setTypeface(custom_font);
        pswd.setTypeface(custom_font);
        sup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              //  Intent it = new Intent(LoginFragmentActivity.this, JoinFragmentActivity);
               // startActivity(it);
            }
        });

        return view;
    }
}
