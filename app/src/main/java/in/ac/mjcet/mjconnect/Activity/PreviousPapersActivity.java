package in.ac.mjcet.mjconnect.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.ac.mjcet.mjconnect.Adapters.EventAdapter;
import in.ac.mjcet.mjconnect.R;
import in.ac.mjcet.mjconnect.Utils.ExpandableHeightGridView;

public class PreviousPapersActivity extends AppCompatActivity   {

    @BindView(R.id.gridview)
    ExpandableHeightGridView gridView;

    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_papers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("questionpapers");
//        storageReference.


    }

    @OnClick(R.id.back_image_button)
    public void backImageButtonClicked(View view){
        finish();
    }

}
