package com.bobby.materialdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.bobby.materialdemo.R;
import com.bobby.materialdemo.data.ShopDatasource;
import com.bobby.materialdemo.data.bean.Shop;
import com.bobby.materialdemo.network.RequestManager;
import com.bobby.materialdemo.util.Utils;
import com.bobby.materialdemo.widget.NetworkImageView;
import com.bobby.materialdemo.widget.ShopPower;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bobby on 2015/4/23.
 */
public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int ANIMATED_ITEMS_COUNT = 2;


    public interface OnItemClickListener {
        void onItemClick(View v, Object item);
    }

    private Context context;

    ShopDatasource shopDatasource;
    private List<Shop> shopList = new ArrayList<>(0);

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View v, Object item) {
            Shop shop = (Shop) item;

            Toast.makeText(context, "click "+ shop.getShopname(), Toast.LENGTH_LONG).show();
        }
    };

    public ShopAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(view);
    }

    private void runEnterAnimation(View view, int position) {
//        if (position >= ANIMATED_ITEMS_COUNT - 1) {
//            return;
//        }

        if (position > shopDatasource.getLastPos()) {
            shopDatasource.setLastPos(position);
            view.setTranslationY(Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        //动画
        runEnterAnimation(viewHolder.itemView, position);
        ShopViewHolder holder = (ShopViewHolder) viewHolder;
        final Shop shop = shopList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, shop);
            }
        });
        holder.title.setText(shop.getShopname() + "("+ shop.getBranchname() +")");
        holder.thumb.setImageUrl(shop.getDefaultpic(), RequestManager.getImageLoader());
        holder.address.setText(shop.getAddress());
        holder.power.setPower(shop.getShoppower());
    }


    @Override
    public int getItemCount() {
        return shopList.size();
    }


    public static class ShopViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.thumb)
        NetworkImageView thumb;
        @InjectView(R.id.title)
        TextView title;
        @InjectView(R.id.address)
        TextView address;
        @InjectView(R.id.power)
        ShopPower power;

        public ShopViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }


    }

    public void updateItems(ShopDatasource datasource) {
        shopList = datasource.getShopList();
        shopDatasource = datasource;
        this.notifyDataSetChanged();
    }
}