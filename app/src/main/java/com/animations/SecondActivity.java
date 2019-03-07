package com.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {


    private LinearLayout sceneHolder;
    private Scene all;
    private Scene office;
    private Scene gyme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        sceneHolder = (LinearLayout) findViewById(R.id.scenes_holder);
        all = Scene.getSceneForLayout(sceneHolder, R.layout.all_scene, this);
        office = Scene.getSceneForLayout(sceneHolder, R.layout.office_scene, this);
        gyme = Scene.getSceneForLayout(sceneHolder, R.layout.gyme_scene, this);
    }


    public void showAllScene(View view) {
        TransitionManager.go(all, new ChangeBounds());
    }

    public void shoOfficeScene(View view) {
        TransitionManager.go(office, new ChangeBounds());

    }

    public void showGymeScene(View view) {
        TransitionManager.go(gyme, new ChangeBounds());

    }
}
