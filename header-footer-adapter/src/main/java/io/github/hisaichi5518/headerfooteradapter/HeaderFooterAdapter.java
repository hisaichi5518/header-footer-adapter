package io.github.hisaichi5518.headerfooteradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class HeaderFooterAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    public static final int ITEM_VIEW_TYPE_ITEM = 0;
    public static final int ITEM_VIEW_TYPE_HEADER = 1;
    public static final int ITEM_VIEW_TYPE_FOOTER = 2;

    private View headerView;
    private View footerView;

    protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindItemViewHolder(VH holder, int position);

    protected abstract int getAdapterItemCount();

    class HeaderFooterHolder extends RecyclerView.ViewHolder {
        public HeaderFooterHolder(View itemView) {
            super(itemView);
        }
    }

    public void setHeaderView(View view) {
        headerView = view;
    }

    public void setFooterView(View view) {
        footerView = view;
    }

    public boolean isHeaderOrFooter(int position) {
        return isHeader(position) || isFooter(position);
    }

    public boolean isHeader(int position) {
        return getItemViewType(position) == ITEM_VIEW_TYPE_HEADER;
    }

    public boolean isFooter(int position) {
        return getItemViewType(position) == ITEM_VIEW_TYPE_FOOTER;
    }

    public int getHeadersCount() {
        return headerView != null ? 1 : 0;
    }

    public int getFootersCount() {
        return footerView != null ? 1 : 0;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_HEADER:
                return new HeaderFooterHolder(headerView);
            case ITEM_VIEW_TYPE_FOOTER:
                return new HeaderFooterHolder(footerView);
            case ITEM_VIEW_TYPE_ITEM:
                return onCreateItemViewHolder(parent, viewType);
        }
        return null;
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_VIEW_TYPE_ITEM) {
            onBindItemViewHolder((VH) holder, position - getHeadersCount());
        }
    }

    @Override
    public final int getItemCount() {
        return getHeadersCount() + getFootersCount() + getAdapterItemCount();
    }

    @Override
    public final int getItemViewType(int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return ITEM_VIEW_TYPE_HEADER;
        }

        if (position - numHeaders < getAdapterItemCount()) {
            return ITEM_VIEW_TYPE_ITEM;
        }

        return ITEM_VIEW_TYPE_FOOTER;
    }


}
