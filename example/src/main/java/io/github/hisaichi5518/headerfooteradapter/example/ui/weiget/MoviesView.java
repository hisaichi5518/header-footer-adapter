package io.github.hisaichi5518.headerfooteradapter.example.ui.weiget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.hisaichi5518.headerfooteradapter.HeaderFooterAdapter;
import io.github.hisaichi5518.headerfooteradapter.example.R;

public class MoviesView extends RecyclerView {

    public MoviesView(Context context) {
        this(context, null);
    }

    public MoviesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoviesView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(context));

        MovieAdapter adapter = new MovieAdapter(layoutInflater);
        adapter.setHeaderView(layoutInflater.inflate(R.layout.movies_view_header, this, false));
        adapter.setFooterView(layoutInflater.inflate(R.layout.movies_view_footer, this, false));
        setAdapter(adapter);
    }

    public void setMovies(List<String> movies) {
        ((MovieAdapter) getAdapter()).setMovies(movies);
    }

    class MovieAdapter extends HeaderFooterAdapter<ItemViewHolder> {
        private List<String> movies;
        private LayoutInflater layoutInflater;

        MovieAdapter(LayoutInflater layoutInflater) {
            this.layoutInflater = layoutInflater;
        }

        void setMovies(List<String> movies) {
            this.movies = movies;
            notifyDataSetChanged();
        }

        @Override
        protected ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(
                    this.layoutInflater.inflate(R.layout.movies_view_item, parent, false));
        }

        @Override
        protected void onBindItemViewHolder(ItemViewHolder holder, int position) {
            holder.setName(this.movies.get(position));
        }

        @Override
        protected int getAdapterItemCount() {
            return movies.size();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movies_view_item_name)
        TextView movieName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setName(String name) {
            movieName.setText(name);
        }
    }
}

