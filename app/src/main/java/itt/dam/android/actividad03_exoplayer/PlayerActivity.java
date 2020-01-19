package itt.dam.android.actividad03_exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class PlayerActivity extends AppCompatActivity {
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private String url;
    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Se oculta la barra superior
        getSupportActionBar().hide();

        data = this.getIntent().getExtras();
        url = data.getString("url");
        playerView = findViewById(R.id.explayerId);
    }

    @Override
    protected void onStart() {
        super.onStart();

        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(player);
        DefaultDataSourceFactory dsf = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "ExoPlayer"));
        ExtractorMediaSource fileMedia = new ExtractorMediaSource.Factory(dsf)
                .createMediaSource(Uri.parse(url));
        player.prepare(fileMedia);
        player.setPlayWhenReady(true);

        // Con imagen de fondo y controles siempre visibles para mp3
        if(url.toLowerCase().endsWith(".mp3")) {
            ImageView imgMp3 = findViewById(R.id.imgIconMp3Id);
            playerView.setControllerHideOnTouch(false);
            playerView.setControllerShowTimeoutMs(0);
            imgMp3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        player.release();
        player = null;
    }
}
