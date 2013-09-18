package com.nibdev.otrtav2.view.fragments;

import java.util.Map;
import java.util.Set;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.nibdev.otrtav2.R;
import com.nibdev.otrtav2.model.jdata.Code;
import com.nibdev.otrtav2.service.OTRTAService;

public class FragmentVirtualController extends Fragment {

    Map<String, Code> mButtons;

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
            b.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Code c = mButtons.get(key);
                    OTRTAService.getInstance().getIrSender().sendCode(c.getData(), false);
                }
            });
            gl.addView(b);
        }

        return v;
    }
}
