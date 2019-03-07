package com.animations;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private View container;
    private View welcome;
    private View profilePic;
    private boolean playAnimations = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        welcome = findViewById(R.id.welcome);
        profilePic = findViewById(R.id.profile_pic);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && playAnimations) {
            showContainer();
            showOtherItem();
            playAnimations = false;
        }
    }

    private void showContainer() {
        container.animate().alpha(1f).setDuration(1000);
    }

    private void showOtherItem(){

        float startXWelcome = 0-welcome.getWidth();
        float endXWelcome = welcome.getX();

        ObjectAnimator animWelcome=ObjectAnimator.ofFloat(welcome,View.X,startXWelcome,endXWelcome);
        animWelcome.setDuration(1500);
        welcome.setVisibility(View.VISIBLE);
        animWelcome.start();

        PropertyValuesHolder scaleXHolder =
                        PropertyValuesHolder.ofFloat(View.SCALE_X,1f);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y,1f);

        ObjectAnimator animProfile=
                     ObjectAnimator.ofPropertyValuesHolder(profilePic,scaleXHolder,scaleYHolder);
        animProfile.setDuration(1500);
        animProfile.start();

    }
}
