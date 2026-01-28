package com.example.project1;

import android.content.Intent;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import eightbitlab.com.blurview.BlurView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        blurEffect(R.id.BlurView1);
        blurEffect(R.id.BlurView2);
        blurEffect(R.id.BlurView3);
        blurEffect(R.id.BlurView4);
        contacts();

    }

    private void contacts() {
        ImageButton call = findViewById(R.id.call);
        call.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0123456789"));
            startActivity(intent);
        });
        ImageButton email = findViewById(R.id.mail);
        email.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"unknown@gmail.com"});
            startActivity(Intent.createChooser(intent, "Send Email"));
        });
        ImageButton linkedin = findViewById(R.id.linkedin);
        linkedin.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.linkedin.com"));
            startActivity(intent);
        });
    }

    private void blurEffect(int viewId) {
        BlurView blurView = findViewById(viewId);
        View decorView = getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(R.id.main1);

        blurView.setupWith(rootView)
                .setBlurRadius(20f)
                .setBlurAutoUpdate(true);
        blurView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 40f);
            }
        });
        blurView.setClipToOutline(true);
    }
}