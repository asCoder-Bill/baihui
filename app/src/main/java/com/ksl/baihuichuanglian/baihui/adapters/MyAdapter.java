package com.ksl.baihuichuanglian.baihui.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ksl.baihuichuanglian.baihui.R;
import com.ksl.baihuichuanglian.baihui.entity.resulte.StoreInfo;
import com.ksl.baihuichuanglian.baihui.entity.resulte.SubjectStore;
import com.ksl.baihuichuanglian.baihui.fragmemts.HomePageFragment;
import com.ksl.baihuichuanglian.baihui.interfaces.OnRecyclerItemClickLitener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private OnRecyclerItemClickLitener mOnRecyclerItemClickLitener;
    public void setOnRecyclerItemClickLitener(OnRecyclerItemClickLitener mOnRecyclerItemClickLitener) {
        this.mOnRecyclerItemClickLitener = mOnRecyclerItemClickLitener;
    }

    private Activity mActivity;
    private SubjectStore subjectStore;
    private List<StoreInfo> mDatas;

    private ImageLoader imageLoader;

    public MyAdapter(Activity mActivity, ImageLoader imageLoader) {
        this.mActivity = mActivity;
        this.imageLoader = imageLoader;
    }

    public List<StoreInfo> getmDatas() {
        return mDatas;
    }

    public void setDatas(SubjectStore subjectStore) {
        this.subjectStore = subjectStore;
        this.mDatas = subjectStore.getList();
        notifyDataSetChanged();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootLayout =
                LayoutInflater.from(mActivity).inflate(R.layout.recy_list_item, parent, false);
        return new ViewHolder(rootLayout);
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, int position) {
        List<StoreInfo> storeInfos = subjectStore.getList();
        StoreInfo data = storeInfos.get(position);
        holder.tvStoreName.setText(data.getStore_name());
        holder.tvDistance.setText(data.getDistance() + "km");
        holder.tvAddress.setText(data.getContact_address());
        holder.tvSalesVolume.setText(data.getSales());
//        holder.tvScore.setText(String.valueOf(data.getScore()) + "分");
        double dd = 4.2;
        holder.ratingBar.setRating((float) dd);

        imageLoader.displayImage(data.getLogo_img(), holder.ivStoreImg);


        if(mOnRecyclerItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnRecyclerItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnRecyclerItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivStoreImg;
        TextView tvStoreName, tvDistance, tvSalesVolume, tvAddress;

        //        TextView tvScore;  //评分
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivStoreImg = (ImageView) itemView.findViewById(R.id.iv_index_store);
            this.tvStoreName = (TextView) itemView.findViewById(R.id.tv_index_storeName);
            this.tvDistance = (TextView) itemView.findViewById(R.id.tv_index_distance);
            this.tvSalesVolume = (TextView) itemView.findViewById(R.id.tv_index_salesVolume);
            this.tvAddress = (TextView) itemView.findViewById(R.id.tv_index_store_address);

//            this.tvScore = (TextView) itemView.findViewById(R.id.SeekBar);
            ratingBar = (RatingBar) itemView.findViewById(R.id.app_ratingbar);
        }
    }
}
