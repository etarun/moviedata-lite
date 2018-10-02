package com.movies.moviedata.ui.movies;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doordash.doordashlite.R;
import com.movies.moviedata.data.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder> {

    private Fragment fragment;
    private MoviesContract.Presenter presenter;
    private List<Movie> movies;

    private final OnItemClickListener listener;

    public MoviesRecyclerAdapter(Fragment fragment, MoviesContract.Presenter presenter,
                                      List<Movie> movies, OnItemClickListener listener) {
        this.fragment = fragment;
        this.presenter = presenter;
        this.movies = movies;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.tv_movie_name)
        TextView tvMovieName;
        @BindView(R.id.tv_more_info)
        TextView tvMoreInfo;
        @BindView(R.id.iv_Res_Icon)
        ImageView ivMovieIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public MoviesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvMovieName = view.findViewById(R.id.tv_movie_name);
        viewHolder.tvMoreInfo = view.findViewById(R.id.tv_more_info);
        viewHolder.tvDistance = view.findViewById(R.id.tv_distance);
        viewHolder.ivMovieIcon = view.findViewById(R.id.iv_Res_Icon);

        view.setOnClickListener(v -> listener.onItemClick(movies.get(viewHolder.getAdapterPosition())));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesRecyclerAdapter.ViewHolder viewHolder, int position) {

        Movie movie = movies.get(position);

        viewHolder.tvMovieName.setText(movie.getName());
        StringBuilder s = new StringBuilder();
        s.append(movie.getDescription());
        s.append("\n");
        s.append("("+movie.getRating()+"/10)");
        viewHolder.tvMoreInfo.setText(s.toString());
        viewHolder.tvDistance.setText(movie.getStatus());

        Glide.with(fragment).load("https://image.tmdb.org/t/p/w500/"+movie.getImageURL()).placeholder(
                R.drawable.drawable_placeholder).error(
                R.drawable.drawable_placeholder).into(viewHolder.ivMovieIcon);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void clear() {
        int size = getItemCount();
        //photos.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<Movie> photos) {
        //int prevSize = getItemCount();
        this.movies.addAll(photos);
        notifyDataSetChanged();
        //notifyItemRangeInserted(prevSize, photos.size());
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }
}
