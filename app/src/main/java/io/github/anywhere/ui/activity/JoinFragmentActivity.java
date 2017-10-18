package io.github.anywhere.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import io.github.anywhere.R; ;

/**
 * Created by PCPC on 2017-06-05.
 */

public class JoinFragmentActivity extends Fragment {
    EditText mail,mophone,pswd,usrusr;
    TextView lin,sup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_join_fragment, container, false);

        sup = (TextView) view.findViewById(R.id.sup);
        lin = (TextView) view.findViewById(R.id.lin);
        usrusr = (EditText) view.findViewById(R.id.usrusr);
        pswd = (EditText) view.findViewById(R.id.pswrdd);
        mail = (EditText) view.findViewById(R.id.mail);
        mophone = (EditText) view.findViewById(R.id.mobphone);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LatoRegular.ttf");
        mophone.setTypeface(custom_font);
        sup.setTypeface(custom_font1);
        pswd.setTypeface(custom_font);
        lin.setTypeface(custom_font);
        usrusr.setTypeface(custom_font);
        mail.setTypeface(custom_font);
        lin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              //  Intent it = new Intent(signup.this,login.class);
                //startActivity(it);
            }
        });
        return view;
    }
}
