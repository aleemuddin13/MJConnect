package in.ac.mjcet.mjconnect.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.ac.mjcet.mjconnect.R;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

//    @OnClick(R.id.syllabusButton)
//    public void syllabusButtonClicked(ImageButton button){
//        startActivity(new Intent(this, SyllabusActivity.class));
//    }
//    @OnClick(R.id.timetableButton)
//    public void timeTableButtonClicked(ImageButton button){
//        startActivity(new Intent(this, TimeTableActivity.class));
//    }
//    @OnClick(R.id.aboutButton)
//    public void aboutButtonClicked(ImageButton button){
//        startActivity(new Intent(this, AboutActivity.class));
//    }
//    @OnClick(R.id.previouspaperButton)
//    public void previousPaperButtonClicked(ImageButton button){
//        startActivity(new Intent(this, PreviousPapersActivity.class));
//    }
//
//    @OnClick(R.id.uploadButton)
//    public void uploadButtonClicked(ImageButton button){
//        startActivity(new Intent(this, UploadActivity.class));
//    }
}
