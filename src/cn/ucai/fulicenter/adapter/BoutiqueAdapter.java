package cn.ucai.fulicenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.ucai.fulicenter.I;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.bean.BoutiqueBean;
import cn.ucai.fulicenter.utils.ImageUtils;
import cn.ucai.fulicenter.view.FooterViewHolder;

/**
 * Created by Administrator on 2016/8/1.
 */
public class BoutiqueAdapter extends RecyclerView.Adapter<ViewHolder>{
    Context mContext;
    List<BoutiqueBean> mBoutiquelist;
    BoutiqueViewHolder mBoutiqueViewHolder;
    FooterViewHolder mFooterViewHolder;
    boolean isMore;
    String footerString;
    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public void setFooterString(String footerString) {
        this.footerString = footerString;
        notifyDataSetChanged();
    }

    public BoutiqueAdapter(Context context, List<BoutiqueBean> list) {
        mContext = context;
        mBoutiquelist = new ArrayList<BoutiqueBean>();
        mBoutiquelist.addAll(list);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder holder = null;
        switch (viewType) {
            case I.TYPE_FOOTER:
                holder = new FooterViewHolder(inflater.inflate(R.layout.item_footer, parent, false));
                break;
            case I.TYPE_ITEM:
                holder = new BoutiqueViewHolder(inflater.inflate(R.layout.item_boutique, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BoutiqueViewHolder) {
            mBoutiqueViewHolder = (BoutiqueViewHolder) holder;
            final BoutiqueBean boutique = mBoutiquelist.get(position);
            ImageUtils.setGoodImage(mContext, mBoutiqueViewHolder.ivBoutiqueThumb, boutique.getImageurl());
            mBoutiqueViewHolder.tvTitle.setText(boutique.getTitle());
            mBoutiqueViewHolder.tvName.setText(boutique.getName());
            mBoutiqueViewHolder.tvDesc.setText(boutique.getDescription());

            /*mBoutiqueViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, BoutiquesDetailActivity.class)
                            .putExtra(D.Boutique.KEY_GOODS_ID, boutique.getGoodsId()));
                }
            });*/
        }
        if (holder instanceof FooterViewHolder) {
            mFooterViewHolder = (FooterViewHolder) holder;
            mFooterViewHolder.tvFooter.setText(footerString);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return I.TYPE_FOOTER;
        } else {
            return I.TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mBoutiquelist != null ? mBoutiquelist.size() + 1 : 1;
    }

    public void initItem(ArrayList<BoutiqueBean> list) {
        if (mBoutiquelist != null) {
            mBoutiquelist.clear();
        }
        mBoutiquelist.addAll(list);
        notifyDataSetChanged();
    }

    public void addItem(ArrayList<BoutiqueBean> list) {
        mBoutiquelist.addAll(list);
        notifyDataSetChanged();
    }

    class BoutiqueViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        ImageView ivBoutiqueThumb;
        TextView tvTitle;
        TextView tvName;
        TextView tvDesc;
        public BoutiqueViewHolder(View itemView) {
            super(itemView);
            layout = (RelativeLayout) itemView.findViewById(R.id.layout_boutique_item);
            ivBoutiqueThumb = (ImageView) itemView.findViewById(R.id.ivBoutiqueImg);
            tvTitle = (TextView) itemView.findViewById(R.id.tvBoutiqueTitle);
            tvName = (TextView) itemView.findViewById(R.id.tvBoutiqueName);
            tvDesc = (TextView) itemView.findViewById(R.id.tvBoutiqueDescription);
        }
    }

}