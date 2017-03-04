package in.ac.mjcet.mjconnect.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.ac.mjcet.mjconnect.R;

public class UploadActivity extends AppCompatActivity {
    private final static String TAG = "UploadActivity";
    private final int PICK_IMAGE_MULTIPLE =1;
    private LinearLayout lnrImages;
    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        lnrImages = (LinearLayout)findViewById(R.id.lnrImages);


    }

    @OnClick(R.id.add_photos_button)
    public void addPhotosButtonClicked(View view){
        Intent intent = new Intent(UploadActivity.this,CustomGalleryActivity.class);
        startActivityForResult(intent,PICK_IMAGE_MULTIPLE);
    }

    @OnClick(R.id.save_button)
    public void saveButtonClicked(View view){
        if(imagesPathList !=null){
            if(imagesPathList.size()>1) {
                Toast.makeText(UploadActivity.this, imagesPathList.size() + " no of images are selected", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(UploadActivity.this, imagesPathList.size() + " no of image are selected", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(UploadActivity.this," no images are selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == PICK_IMAGE_MULTIPLE){
                imagesPathList = new ArrayList<String>();
                String[] imagesPath = data.getStringExtra("data").split("\\|");
                try{
                    lnrImages.removeAllViews();
                }catch (Throwable e){
                    e.printStackTrace();
                }
                for (int i=0;i<imagesPath.length;i++){
                    imagesPathList.add(imagesPath[i]);
                    yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);
                    ImageView imageView = new ImageView(this);
                    imageView.setImageBitmap(yourbitmap);
                    imageView.setAdjustViewBounds(true);
                    lnrImages.addView(imageView);
                }
            }
        }

    }

}
