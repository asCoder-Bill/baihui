package com.ksl.baihuichuanglian.baihui.interfaces;

import android.view.View;

/**
 * 作者：Bill on 2017/2/21 14:11
 * 备注：
 */
public interface OnRecyclerItemClickLitener {
    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
