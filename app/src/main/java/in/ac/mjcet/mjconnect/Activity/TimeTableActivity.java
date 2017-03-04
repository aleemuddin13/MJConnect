package in.ac.mjcet.mjconnect.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.ac.mjcet.mjconnect.Constants.StringConstants;
import in.ac.mjcet.mjconnect.R;
import in.ac.mjcet.mjconnect.Utils.TouchImageView;

public class TimeTableActivity extends AppCompatActivity {

    @BindView(R.id.branch_spinner)
    Spinner branchSpinner;

    @BindView(R.id.year_spinner)
    Spinner yearSpinner;

    @BindView(R.id.sec_spinner)
    Spinner secSpinner;

    @BindView(R.id.image_view)
    TouchImageView imageView;

    ArrayAdapter branchAdapter;
    ArrayAdapter secAdapter;
    ArrayAdapter yearAdapter;

    String selectedYear = "4";
    String selectedBranch = "cse";
    String selectedSec = "SEC-A";
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("timetables");

        branchAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, StringConstants.branches);
        branchAdapter.setDropDownViewResource(R.layout.spinner_row);

        secAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, StringConstants.sec);
        secAdapter.setDropDownViewResource(R.layout.spinner_row);

        yearAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, StringConstants.year);
        yearAdapter.setDropDownViewResource(R.layout.spinner_row);

        branchSpinner.setAdapter(branchAdapter);
        secSpinner.setAdapter(secAdapter);
        yearSpinner.setAdapter(yearAdapter);

        branchSpinner.setSelection(1);
        secSpinner.setSelection(1);
        yearSpinner.setSelection(3);

        initializeSpinnerListners();

        updateImageView();
    }

    public void updateImageView(){
        String sec = "A";
        if(selectedSec.contentEquals(StringConstants.sec[1])){
            sec = "B";
        }
        imageView.resetZoom();
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(storageReference.child(selectedBranch.toUpperCase()+selectedYear+sec+".jpg"))
                .placeholder(R.drawable.syllabus)
                .error(R.drawable.timetable)
                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }



    public void initializeSpinnerListners(){
        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBranch = branchSpinner.getSelectedItem().toString();
                updateImageView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = yearSpinner.getSelectedItem().toString();
                updateImageView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        secSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSec = secSpinner.getSelectedItem().toString();
                updateImageView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
