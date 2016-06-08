package com.xiaoer.xiaoer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.xiaoer.lib.gridview.MyGridView;
import com.xiaoer.lib.util.ToastUtils;
import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.entity.ClassifyEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Brioal on 2016/6/5.
 */
public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ClassifyViewHolder> {

    private Context mContext;
    private List<ClassifyEntity> mTitles; //显示大的分类
    private List<List<ClassifyEntity>> mDetails;

    public ClassifyAdapter(Context mContext, List<ClassifyEntity> mList) {
        this.mContext = mContext;
        mTitles = new ArrayList<>();
        mDetails = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            ClassifyEntity entity = mList.get(i);
            if (entity.getmParent() == null||entity.getmParent().isEmpty()) {
                mTitles.add(entity);
            }
        }
        for (int i = 0; i < mTitles.size(); i++) {
            mDetails.add(new ArrayList<ClassifyEntity>());
            String objectId = mTitles.get(i).getObjectId();
            for (int j = 0; j < mList.size(); j++) {

                if (mList.get(j).getmParent()!=null&&mList.get(j).getmParent().equals(objectId)) {
                    mDetails.get(i).add(mList.get(j));
                }
            }
        }
    }

    @Override
    public ClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_classify, parent, false);
        return new ClassifyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClassifyViewHolder holder, int position) {
        ClassifyEntity title = mTitles.get(position);
        final List<ClassifyEntity> details = mDetails.get(position);
        holder.mTitle.setText(title.getmContent());
        holder.mGridView.setAdapter(new ClassifyDetailAdapter(mContext, details));
        holder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(mContext, details.get(position).getmContent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    class ClassifyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_classify_title)
        TextView mTitle;
        @Bind(R.id.item_classify_gridView)
        MyGridView mGridView;


        public ClassifyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
