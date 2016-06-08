package com.xiaoer.xiaoer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.entity.ImageEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Brioal on 2016/6/5.
 */
public class GridViewAdapterHome extends BaseAdapter {
    private List<ImageEntity> mList;
    private Context mContext;

    public GridViewAdapterHome(Context mContext, List<ImageEntity> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_grid_image, parent, false);
            holder = new ImageViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ImageViewHolder) convertView.getTag();
        }
        holder.mImageView.setImageResource(R.mipmap.ic_launcher);
        String url = mList.get(position).getmPicUrl(mContext);
        holder.mImageView.setImageResource(R.mipmap.ic_launcher);
        final ImageViewHolder finalHolder = holder;
        Glide.with(mContext).load(url).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                finalHolder.mImageView.setImageDrawable(glideDrawable);
            }
        });
        return convertView;
    }


    static class ImageViewHolder {
        @Bind(R.id.item_home_grid_imageView)
        ImageView mImageView;

        ImageViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
