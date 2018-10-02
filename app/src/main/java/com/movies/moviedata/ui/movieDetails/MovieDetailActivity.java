package com.movies.moviedata.ui.movieDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.movies.moviedata.BaseActivity;
import com.movies.moviedata.R;
import com.movies.moviedata.data.Genre;
import com.movies.moviedata.data.Movie;
import com.movies.moviedata.Properties;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This Activity displays the details of the movie selected.
 * Movie data is passed via a {@link Bundle} and from its
 * {@link MovieDetailContract.Presenter}. It then renders the movie data and its details.
 */
public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.View {

    @BindView(R.id.imageView)
    ImageView ivMovieImage;
    @BindView(R.id.tv_name)
    TextView tvMovieName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_delivery_fee)
    TextView tvDeliveryFee;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_genre)
    LinearLayout llGenre;

    private Movie movie;
    MovieDetailsComponent movieDetailsComponent;
    @Inject
    MovieDetailContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMovieDetailsDaggerComponent();
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        movie = getIntent().getParcelableExtra(Properties.MOVIE);
        getSupportActionBar().setTitle(movie.getName());
        presenter.onCreate(movie);
    }

    private void initMovieDetailsDaggerComponent() {
        super.initDaggerComponent();
        if (movieDetailsComponent == null) {
            movieDetailsComponent = DaggerMovieDetailsComponent.builder()
                    .appComponent(appComponent)
                    .movieDetailsModule(new MovieDetailsModule(this))
                    .build();

            movieDetailsComponent.inject(this);
        }
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Unable to Load Movie Data", Toast.LENGTH_LONG).show();
        this.finish();
    }

    @Override
    public void setProgressBar(boolean show) {

    }

    @Override
    public void showMovieImage(String url) {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+url).placeholder(
                R.drawable.drawable_placeholder).error(
                R.drawable.drawable_placeholder).into(ivMovieImage);
    }

    @Override
    public void setResName(String name) {
        tvMovieName.setText(name);
    }

    @Override
    public void setResPhone(String phone) {
        tvPhone.setText(phone);
    }

    @Override
    public void setResDeliveryFee(List<Genre> genreList) {
        int i = 0;
        for (Genre g:genreList)
        {
            TextView tv = new TextView(this);
            tv.setText(g.getName());
            tv.setId(i + 5);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 10, 0);
            tv.setPadding(15,15,15,15);
            tv.setTextColor(getResources().getColor(R.color.colorWhite));
            tv.setBackgroundResource(R.drawable.rounded_corner);
            llGenre.addView(tv, layoutParams);
            i++;
        }

    }
}
