package com.example.michel.sqlliteejem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Michel on 21/11/2015.
 */
public class splash extends Activity {
    //set duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //metodo para el timer del splash
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                //inicia la proxima actividad (MainActivity.class)
                Intent myIntent = new Intent().setClass(
                        splash.this, MainActivity.class);
                startActivity(myIntent);

                //cerramos la actividad para que el usuario no se pueda devolver al splash screen
                finish();
            }
        };

        //simulamos el proceso de carga de datos, que se hace al iniciar la app
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

    }

}
