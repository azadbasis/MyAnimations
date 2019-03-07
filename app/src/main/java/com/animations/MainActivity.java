package com.animations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabWidget;

public class MainActivity extends AppCompatActivity {


    private View container;
    private View welcome;
    private View profilePic;
    private View signIn;
    private boolean playAnimations = true;

    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        welcome = findViewById(R.id.welcome);
        profilePic = findViewById(R.id.profile_pic);
        signIn = findViewById(R.id.sign_in);

        username = (EditText) findViewById(R.id.username);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus && username.getText().toString().equals("anna")) {
                    changeProfilePic();
                }
            }
        });
    }

    private void changeProfilePic() {

        profilePic.animate()
                .rotationY(90)
                .setDuration(750)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ((ImageView) profilePic).setImageResource(R.drawable.photo1);
                        profilePic.animate()
                                .rotationY(0)
                                .setDuration(750)
                                .setInterpolator(new OvershootInterpolator());
                    }
                });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && playAnimations) {
            showContainer();
            playAnimations = false;
        }
    }

    private void showContainer() {
        container.animate().alpha(1f).setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        showOtherItem();
                    }
                })
        ;
    }

    private void showOtherItem() {

        float startXWelcome = 0 - welcome.getWidth();
        float endXWelcome = welcome.getX();

        ObjectAnimator animWelcome = ObjectAnimator.ofFloat(welcome, View.X, startXWelcome, endXWelcome);
        animWelcome.setDuration(1500);
        animWelcome.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        welcome.setVisibility(View.VISIBLE);
                    }
                });

        PropertyValuesHolder scaleXHolder =
                PropertyValuesHolder.ofFloat(View.SCALE_X, 1f);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f);

        ObjectAnimator animProfile =
                ObjectAnimator.ofPropertyValuesHolder(profilePic, scaleXHolder, scaleYHolder);
        animProfile.setDuration(1500);

        ObjectAnimator animSignIn =
                (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.sign_in_animator);
        animSignIn.setTarget(signIn);


        AnimatorSet set = new AnimatorSet();
        set.play(animWelcome).after(animProfile);
        set.play(animWelcome).before(animSignIn);
        set.start();

    }
}
