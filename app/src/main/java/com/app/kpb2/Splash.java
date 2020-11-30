package com.app.kpb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.kpb2.features.users.login.Login;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Splash extends AppCompatActivity {

    ImageView fromUp;
    TextView fromBottom;
    Animation animUp, animBottom;
    private static int SPLASH_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        fromUp = findViewById(R.id.up);
        fromBottom = findViewById(R.id.bottom);
        animUp = AnimationUtils.loadAnimation(this, R.anim.transition);
        fromUp.startAnimation(animUp);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, Login.class));
                Animatoo.animateSlideUp(Splash.this);
            }
        }, SPLASH_SCREEN);
    }
}
