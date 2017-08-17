package com.qiuchenly.weixinplatform.weixin.BaseUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qiuchenly.weixinplatform.weixin.R;

/**
 * Author: QiuChenluoye
 * Time: 2017/08/10,下午 08:54
 * Func: RecyclerView基础适配器
 * Using: 无解释
 */

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.VH> {

    Context c;

    public BaseRecyclerViewAdapter(Context c) {
        this.c = c;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH v = new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitems, parent, false));
        return v;
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        Glide.with(c).
                load("http://i.imgur.com/idojSYm.png").
                into(holder.iv);
    }

    

    @Override
    public int getItemCount() {
        return 100;
    }

    class VH extends RecyclerView.ViewHolder {
        ImageView iv;

        public VH(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.mImageBack);
        }
    }


}
