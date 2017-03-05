package in.ac.mjcet.mjconnect.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.ac.mjcet.mjconnect.R;

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.menu_view)
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        view.startAnimation(animationFadeIn);
    }

    @OnClick(R.id.adsophos_image_view)
    public void adsophosImageViewClicked(View button){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, button , "menu");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(this, UploadActivity.class), options.toBundle());
            overridePendingTransition(R.anim.fade_out,0);
        }else{
            startActivity(new Intent(this, UploadActivity.class));
        }
    }

    @OnClick(R.id.qr_SIV)
    public void qrSIVClicked(View button){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, button , "menu");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(this, UploadActivity.class), options.toBundle());
        }else{
            startActivity(new Intent(this, UploadActivity.class));
        }
    }

    @OnClick(R.id.syllabus_SIV)
    public void syllabusButtonClicked(View button){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, button , "menu");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(this, SyllabusActivity.class), options.toBundle());
        }else{
            startActivity(new Intent(this, SyllabusActivity.class));
        }
    }
    @OnClick(R.id.time_table_SIV)
    public void timeTableButtonClicked(View button){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, button , "menu");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(this, TimeTableActivity.class), options.toBundle());
        }else{
            startActivity(new Intent(this, TimeTableActivity.class));
        }
    }
    @OnClick(R.id.about_SIV)
    public void aboutButtonClicked(View button){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, button , "menu");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(this, AboutActivity.class), options.toBundle());
        }else{
            startActivity(new Intent(this, AboutActivity.class));
        }
    }
    @OnClick(R.id.previous_papers_SIV)
    public void previousPaperButtonClicked(View button){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, button , "menu");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(this, UploadActivity.class), options.toBundle());
        }else{
            startActivity(new Intent(this, UploadActivity.class));
        }
    }

    @OnClick(R.id.bug_SIV)
    public void uploadButtonClicked(View button){
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, button , "menu");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(new Intent(this, UploadActivity.class), options.toBundle());
        }else{
            startActivity(new Intent(this, UploadActivity.class));
        }
    }
}
