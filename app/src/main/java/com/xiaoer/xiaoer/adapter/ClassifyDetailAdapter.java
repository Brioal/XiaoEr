package com.xiaoer.xiaoer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiaoer.lib.view.CircleImageView;
import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.entity.ClassifyEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Brioal on 2016/6/5.
 */
public class ClassifyDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<ClassifyEntity> mLists;

    public ClassifyDetailAdapter(Context mContext, List<ClassifyEntity> mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassifyEntity entity = mLists.get(position);
        DetailViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_classify_detail, parent, false);
            holder = new DetailViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DetailViewHolder) convertView.getTag();
        }
        Log.i("ClassifyDetailInfo", "getView: "+entity.getmContent());
        holder.mTitle.setText(entity.getmContent());
        Glide.with(mContext).load(entity.getmPicUrl(mContext)).into(holder.mPic);
        return convertView;
    }

    static class DetailViewHolder {
        @Bind(R.id.item_classify_detail_pic)
        CircleImageView mPic;
        @Bind(R.id.item_classify_detail_title)
        TextView mTitle;

        DetailViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
