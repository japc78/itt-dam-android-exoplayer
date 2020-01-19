package itt.dam.android.actividad03_exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay  = findViewById(R.id.btnPlay);
        Button btnDel  = findViewById(R.id.btnDel);

        url = findViewById(R.id.urlId);
//        url.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        btnPlay.setOnClickListener(this);
        btnDel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                String getUrl = url.getText().toString().trim();

                if (getUrl.isEmpty()) {
                    message("Url no puede estar vacia");

                } else if (!checkUrl(getUrl)){
                    message("Url no valida");

                } else {
                    startActivity(new Intent(this, PlayerActivity.class)
                            .putExtra("url", getUrl));
                    url.setText("");
                }

                break;
            case R.id.btnDel:
                url.setText("");
        }
    }


    private boolean checkUrl (String url) {
        if ((url.toLowerCase().endsWith(".mp4") || url.toLowerCase().endsWith(".mp3")) && (url.startsWith("http://") || url.startsWith("https://")))
            return true;
        else
            return false;
    }

    private void message (String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,48);
        toast.show();
    }
}
