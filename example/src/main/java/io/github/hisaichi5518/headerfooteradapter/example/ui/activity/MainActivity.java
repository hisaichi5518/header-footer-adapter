package io.github.hisaichi5518.headerfooteradapter.example.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.hisaichi5518.headerfooteradapter.example.R;
import io.github.hisaichi5518.headerfooteradapter.example.ui.weiget.MoviesView;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_movies)
    MoviesView moviesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String[] movies = getResources().getStringArray(R.array.yuriko_movies);
        moviesView.setMovies(Arrays.asList(movies));
    }
}
