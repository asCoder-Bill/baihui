package com.ksl.baihuichuanglian.baihui.fragmemts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksl.baihuichuanglian.baihui.R;

/**
 * 作者：Bill on 2017/2/17 14:49
 * 备注：
 */
public class CartFragment extends Fragment {
    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        return rootView;
    }
}
