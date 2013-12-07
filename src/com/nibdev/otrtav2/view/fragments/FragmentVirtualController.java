package com.nibdev.otrtav2.view.fragments;

import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Fragment;
import android.os.Bundle;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.nibdev.otrtav2.R;
import com.nibdev.otrtav2.model.jdata.Code;
import com.nibdev.otrtav2.service.OTRTAService;

public class FragmentVirtualController extends Fragment {

    Map<String, List<Code>> mButtons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mButtons = OTRTAService.getInstance().getLocalDb().getCodesForModel(getArguments().getInt("ID"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_virtual_controller, null);
        GridLayout gl = (GridLayout) v.findViewById(R.id.gl_controller);
        Set<String> keys = mButtons.keySet();
        for (final String key : keys) {
            Button b = new Button(getActivity());
            b.setText(key);
            b.setOnTouchListener(new OnTouchListener() {
                
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            Code c = mButtons.get(key).get(0);
                            OTRTAService.getInstance().getIrSender().sendCode(c.getData(), true);
                            v.getBackground().setColorFilter(0xFF33B5E5, PorterDuff.Mode.SRC_ATOP);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            OTRTAService.getInstance().getIrSender().cancelCode();
                            v.getBackground().clearColorFilter();
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_OUTSIDE: {
                            OTRTAService.getInstance().getIrSender().cancelCode();
                            v.getBackground().clearColorFilter();
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL: {
                            OTRTAService.getInstance().getIrSender().cancelCode();
                            v.getBackground().clearColorFilter();
                            v.invalidate();
                            break;
                        }

                        }
                        return true;
                }
            });
            gl.addView(b);
        }

        return v;
    }
}
