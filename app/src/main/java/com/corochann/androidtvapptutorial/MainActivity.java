package com.corochann.androidtvapptutorial;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import android.media.MediaPlayer;

public class MainActivity extends Activity {

    private final static int PERMISSIONS = 1;
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private VideoView videoView;
//    protected void checkPermission() {
//        final List<String> missingPermissions = new ArrayList<String>();
//        // check required permission
//        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
//            final int result = ContextCompat.checkSelfPermission(this, permission);
//            if (result != PackageManager.PERMISSION_GRANTED) {
//                missingPermissions.add(permission);
//            }
//        }
//        if (!missingPermissions.isEmpty()) {
//            // request permission
//            final String[] permissions = missingPermissions
//                    .toArray(new String[missingPermissions.size()]);
//            ActivityCompat.requestPermissions(this, permissions, PERMISSIONS);
//        } else {
//            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
//            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
//            onRequestPermissionsResult(PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
//                    grantResults);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(getApplicationContext(), UpdateService.class));

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);

        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        videoView = (VideoView)this.findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        String moviePath = "file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) +"/test.mp4";
        Log.e("movie path", moviePath);

        videoView.setVideoURI(Uri.parse(moviePath));

        videoView.requestFocus();
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                // restart on completion
                videoView.start();
            }
        });
//
//        if (Build.VERSION.SDK_INT >= 23) {
//            checkPermission();
//        } else {
////			next();
//        }
//
//        Movie movie = new Movie();
//        movie.setId(1);
//        movie.setTitle("Title1");
//        movie.setDescription("description1");
//        movie.setCardImageUrl("http://heimkehrend.raindrop.jp/kl-hacker/wp-content/uploads/2014/08/DSC02580.jpg");
////        movie.setVideoUrl("http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4");
//        movie.setVideoUrl("file:///sdcard/test.mp4");
//        movie.setCardImageUrl("http://10.0.2.2/test.jpg");
////        movie.setVideoUrl("http://10.0.2.2/test.mp4");
//
//
//
//        MovieProvider.addMovie(movie);
//
//        Intent intent = new Intent(this, PlaybackOverlayActivity.class);
//        intent.putExtra(DetailsActivity.MOVIE, movie);
//        intent.putExtra(getResources().getString(R.string.should_start), true);
//        startActivity(intent);
//        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
