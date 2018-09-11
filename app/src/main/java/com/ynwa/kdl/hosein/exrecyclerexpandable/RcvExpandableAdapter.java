package com.ynwa.kdl.hosein.exrecyclerexpandable;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RcvExpandableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<Carpet> list;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public RcvExpandableAdapter(List<Carpet> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            expandState.append(i, false);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        //parent item
        if (viewType == 0){
            View view = LayoutInflater.from(context).inflate(R.layout.rcv_parent_item, parent, false);
            return new CarpetParentViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.rcv_child_item, parent, false);
            return new CarpetChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType())
        {
            case 0:
            {
                CarpetParentViewHolder viewHolder = (CarpetParentViewHolder) holder;
                Carpet carpet = list.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.tvParentTitle.setText(carpet.getTitle());
            }
            break;

            case 1:
            {
                final CarpetChildViewHolder viewHolder = (CarpetChildViewHolder) holder;
                Carpet carpet = list.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.tvChildTitle.setText(carpet.getTitle());
                viewHolder.tvChildSubTitle.setText(carpet.getSubTitle());

                Picasso.get()
                        .load(carpet.getImageUrl())
                        .error(R.drawable.ic_image)
                        .into(viewHolder.ivChildImage);

                viewHolder.expandableLayout.setInRecyclerView(true);
                viewHolder.expandableLayout.setExpanded(expandState.get(position));
                viewHolder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {

                    @Override
                    public void onPreOpen() {

                        changeRotate(viewHolder.layoutExpand, 0f, 180f).start();
                        expandState.put(position, true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.layoutExpand, 180f, 0f).start();
                        expandState.put(position, false);
                    }
                });

                viewHolder.layoutExpand.setRotation(expandState.get(position) ? 180f : 0f );
                viewHolder.layoutExpand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.expandableLayout.toggle();
                    }
                });
            }
            break;

            default:
                break;
        }
    }

    private ObjectAnimator changeRotate(RelativeLayout ivExpand, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivExpand, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (list.get(position).isExpandable())
            return 1;
        else
            return 0;
    }

    class CarpetParentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_child)
        TextView tvParentTitle;

        public CarpetParentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CarpetChildViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_child)
        TextView tvChildTitle;
        @BindView(R.id.tv_sub_title_child)
        TextView tvChildSubTitle;
        @BindView(R.id.iv_image_child)
        ImageView ivChildImage;
        @BindView(R.id.relative_button)
        RelativeLayout layoutExpand;
        @BindView(R.id.expandable_layout)
        ExpandableLinearLayout expandableLayout;



        public CarpetChildViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
