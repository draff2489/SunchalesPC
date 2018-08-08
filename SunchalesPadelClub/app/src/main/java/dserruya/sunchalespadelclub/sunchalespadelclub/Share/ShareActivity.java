package dserruya.sunchalespadelclub.sunchalespadelclub.Share;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dserruya.sunchalespadelclub.sunchalespadelclub.R;

public class ShareActivity extends AppCompatActivity{
    private static final String TAG = "ShareActivity";

    //constants
    private static final int ACTIVITY_NUM = 2;

    private Context mContext = ShareActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        init();

    }

    private void init(){
        Log.d(TAG, "init: inflating gallery fragment" );

        GalleryFragment fragment = new GalleryFragment();
        FragmentTransaction transaction = ShareActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack("profile_gallery");
        transaction.commit();
    }
}
