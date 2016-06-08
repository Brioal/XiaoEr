package com.xiaoer.xiaoer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.xiaoer.lib.bgabanner.BGABanner;
import com.xiaoer.lib.gridview.MyGridView;
import com.xiaoer.lib.util.DateFormat;
import com.xiaoer.lib.util.NetWorkUtil;
import com.xiaoer.lib.view.CircleImageView;
import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.entity.BannerEntity;
import com.xiaoer.xiaoer.entity.ContentEntity;
import com.xiaoer.xiaoer.entity.ImageEntity;
import com.xiaoer.xiaoer.entity.User;
import com.xiaoer.xiaoer.interfaces.OnLoaderMoreListener;
import com.xiaoer.xiaoer.util.Constants;
import com.xiaoer.xiaoer.util.DataQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by Brioal on 2016/6/5.
 */
public class HomeContentAdapter extends RecyclerView.Adapter {

    private int TYPE_BANNER = 0; //轮播
    private int TYPE_LOAD_MORE = 1;//加载更多
    private int TYPE_NO_MORE = 2;//没有更多
    private int TYPE_CONTENT = 3;//内容
    private String TAG = "ContextAdapter";

    private Context mContext;
    private List<BannerEntity> mBanners;
    private List<ContentEntity> mContents;
    private OnLoaderMoreListener loaderMoreListener;

    public HomeContentAdapter(Context mContext, List<BannerEntity> mBanners, List<ContentEntity> mContents) {
        this.mContext = mContext;
        this.mBanners = mBanners;
        this.mContents = mContents;
    }


    public void setLoaderMoreListener(OnLoaderMoreListener loaderMoreListener) {
        this.loaderMoreListener = loaderMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) { // 轮播布局
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_banner, parent, false);
            return new BannerViewHolder(itemView);
        } else if (viewType == TYPE_CONTENT) { //内容布局
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_content, parent, false);
            return new ContentViewHolder(itemView);
        } else if (viewType == TYPE_LOAD_MORE) { //加载更多布局
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_load_more, parent, false);
            return new LoaderMoreViewHolder(itemView);
        } else if (viewType == TYPE_NO_MORE) { //没有更多布局
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_no_more, parent, false);
            return new NoMoreViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BannerViewHolder) { //轮播布局
            if (mBanners.size() > 3) {
                List<View> views = new ArrayList<>();
                List<String> tips = new ArrayList<>();
                for (int i = 0; i < mBanners.size(); i++) {
                    final BannerEntity model = mBanners.get(i);
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(model.getmPicUrl(mContext)).into(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                    views.add(imageView);
                    tips.add(model.getmTip());
                }

                ((BannerViewHolder) holder).mBanner.setViews(views);
                ((BannerViewHolder) holder).mBanner.setTips(tips);
                ((BannerViewHolder) holder).mBanner.setPageChangeDuration(2000);
            }

        } else if (holder instanceof ContentViewHolder) { //内容布局
            final ContentEntity entity = mContents.get(position - 1);

            if (entity.getmUserName() == null) { //来自网络的数据
                DataQuery<User> query = new DataQuery<>();
                query.getData(mContext, entity.getmUserId(), new GetListener<User>() {
                    @Override
                    public void onSuccess(User user) {
                        Log.i(TAG, "onSuccess: 查询用户成功");
                        Glide.with(mContext).load(user.getmHeadUrl(mContext)).into(((ContentViewHolder) holder).mHead);
                        ((ContentViewHolder) holder).mName.setText(user.getUsername());
                        entity.setmUserName(user.getUsername());
                        entity.setmUserHeadUrl(user.getmHeadUrl(mContext));
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.i(TAG, "onFailure: 查询用户失败" + s);
                    }
                });
            } else {
                Glide.with(mContext).load(entity.getmUserHeadUrl()).into(((ContentViewHolder) holder).mHead);
                ((ContentViewHolder) holder).mName.setText(entity.getmUserName());
            }
            ((ContentViewHolder) holder).mTime.setText(DateFormat.format(entity.getmTime()));
            ((ContentViewHolder) holder).mRead.setText(entity.getmRead() + "");
            ((ContentViewHolder) holder).mContent.setText(entity.getmDesc());
            ((ContentViewHolder) holder).mLocation.setText(entity.getmLocation());
            List<ImageEntity> list = Constants.getmDataLoader(mContext).getImageLocal(entity.getObjectId());
            if (list.size() > 0) {
                ((ContentViewHolder) holder).mGridView.setAdapter(new GridViewAdapterHome(mContext, list));
            }
            if (NetWorkUtil.isNetworkConnected(mContext)) {
                Constants.getmDataLoader(mContext).getImageNet(entity.getObjectId(), new FindListener<ImageEntity>() {
                    @Override
                    public void onSuccess(final List<ImageEntity> list) {
                        Log.i(TAG, "onSuccess: 图片加载成功" + list.size());
                        ((ContentViewHolder) holder).mGridView.post(new Runnable() {
                            @Override
                            public void run() {
                                ((ContentViewHolder) holder).mGridView.setAdapter(new GridViewAdapterHome(mContext, list));
                            }
                        });

//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Constants.getmDataLoader(mContext).saveImageNetLocal(entity.getObjectId(), list);
//                            }
//                        }).start();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.i(TAG, "onError: 加载图片失败" + s);
                    }
                });
            }

            if (entity.getmOldPrice() == -1) {
                ((ContentViewHolder) holder).mOldPrice.setVisibility(View.INVISIBLE);

            } else {
                ((ContentViewHolder) holder).mOldPrice.setVisibility(View.VISIBLE);
                ((ContentViewHolder) holder).mOldPrice.setText("¥" + entity.getmOldPrice());

            }
            ((ContentViewHolder) holder).mNowPrice.setText("¥" + entity.getmNowPrice());
            ((ContentViewHolder) holder).mCollect.setText(entity.getmCollect() + "");

            // TODO: 2016/6/5 收藏处理
            ((ContentViewHolder) holder).mLocation.setText(entity.getmLocation());
            ((ContentViewHolder) holder).mPraise.setText(entity.getmPraise() + "");
            ((ContentViewHolder) holder).mComment.setText(entity.getmComment() + "");
        } else if (holder instanceof LoaderMoreViewHolder) { //加载更多布局
            if (NetWorkUtil.isNetworkConnected(mContext)) {
                loaderMoreListener.loadMore();
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = mContents.size();
        if (mBanners != null) { //banner存在,则多一个
            count += 1;
        }
        if (mContents.size() >= 7) {//还有数据,或者数据大小合适,应该显示底部信息
            count += 1;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (mContents != null && position == 0) {
            return TYPE_BANNER;
        } else if (position == getItemCount() - 1) {
            if (!NetWorkUtil.isNetworkConnected(mContext)) {
                return TYPE_NO_MORE; //没有更多布局
            } else {
                if (mContents.size() % 15 == 0) {
                    return TYPE_LOAD_MORE; //加载更多布局
                } else if (mContents.size() >= 7) {
                    return TYPE_NO_MORE; //没有更多布局
                } else {
                    return TYPE_CONTENT; //内容布局
                }
            }
        }
        return TYPE_CONTENT;
    }

    //轮播布局
    class BannerViewHolder extends RecyclerView.ViewHolder {
        BGABanner mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mBanner = (BGABanner) itemView.findViewById(R.id.item_banner);
        }
    }

    //内容布局
    class ContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_home_content_head)
        CircleImageView mHead;
        @Bind(R.id.item_home_content_name)
        TextView mName;
        @Bind(R.id.item_home_content_time)
        TextView mTime;
        @Bind(R.id.item_home_content_red)
        TextView mRead;
        @Bind(R.id.item_home_content_content)
        TextView mContent;
        @Bind(R.id.item_home_content_gridView)
        MyGridView mGridView;
        @Bind(R.id.item_home_content_nowPrice)
        TextView mNowPrice;
        @Bind(R.id.item_home_content_oldPrice)
        TextView mOldPrice;
        @Bind(R.id.item_home_content_collect)
        CheckBox mCollect;
        @Bind(R.id.item_home_content_location)
        Button mLocation;
        @Bind(R.id.item_home_content_praise)
        TextView mPraise;
        @Bind(R.id.item_home_content_comment)
        TextView mComment;

        View itemView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }

    //加载更多布局
    class LoaderMoreViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoaderMoreViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.item_load_more_progressBar);
            DoubleBounce doubleBounce = new DoubleBounce();
            progressBar.setIndeterminateDrawable(doubleBounce);
        }
    }

    //没有更多的布局
    class NoMoreViewHolder extends RecyclerView.ViewHolder {

        public NoMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
