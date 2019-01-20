package com.marc.et.pelotarebotante;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int width;
    private int height;
    private ArrayList<Bola> arrayBolas = new ArrayList<>();

    //Para utilizar siempre las mismas variables en el TimerTask y no crear nuevas en cada iteración
    private Bola b1 = null;
    private ImageView imgViewBola = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl = findViewById(R.id.relativeLayout);

        //Guardar la imagen de la bola en un Bitmap
        Bitmap imgBola = BitmapFactory.decodeResource(getResources(), R.drawable.bola);
        //Redimensionar la imagen
        imgBola = Bitmap.createScaledBitmap(imgBola, 60,60, false);
        int posX = 0;

        for (int i=0; i < 5; i++){
            final ImageView imgViewBola = new ImageView(getApplicationContext());
            imgViewBola.setImageBitmap(imgBola);
            final Bola b1 = new Bola(posX,0, 5*(i+1), 5*(i+1), imgViewBola);
            arrayBolas.add(b1);
            //Añadimos la bola al RelativeLayout
            rl.addView(imgViewBola);
            posX+=60;
        }

        //Obtenemos el tamaño de la barra de navegación para restarlo a la altura de la pantalla
        int nav_bar_height = 0;
        Resources resources = getApplicationContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            nav_bar_height = resources.getDimensionPixelSize(resourceId);
        }

        //Obtenemos las dimensiones de la pantalla
        DisplayMetrics display = this.getBaseContext().getResources().getDisplayMetrics();
        width = display.widthPixels;
        height = display.heightPixels-nav_bar_height;

        final int length = arrayBolas.size();

        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i=0; i < length; i++) {
                    b1 = arrayBolas.get(i);
                    imgViewBola = arrayBolas.get(i).getImageView();
                    //Cambiar la posición de X
                    if (width > b1.getPosX() && b1.getPosX() >= 0.0) {
                        if (b1.getPosX() >= width) {
                            b1.setvX(b1.getvX() * -1);
                            b1.setPosX(b1.getPosX() + b1.getvX());
                        } else {
                            //incrementar la 'x' del ImageView
                            imgViewBola.setX(b1.getPosX() + b1.getvX());
                            //incrementar la 'y' del objeto Bola
                            b1.setPosX(b1.getPosX() + b1.getvX());
                        }
                    } else if (width <= b1.getPosX()) {
                        //cambiar la velocidad 'x' a negativa para que la pelota rebote
                        b1.setvX(b1.getvX() * -1);
                        b1.setPosX(b1.getPosX() + b1.getvX());
                    } else if (b1.getPosX() < 0.0) {
                        //cambiar la velocidad 'x' a negativa para que la pelota rebote
                        b1.setvX(b1.getvX() * -1);
                        b1.setPosX(b1.getPosX() + b1.getvX());
                    }

                    //Cambiar la posición de Y
                    if (height > b1.getPosy() && b1.getPosy() >= 0.0) {
                        //incrementar la 'y' del ImageView
                        imgViewBola.setY(b1.getPosy() + b1.getVy());
                        //incrementar la 'y' del objeto Bola
                        b1.setPosy(b1.getPosy() + b1.getVy());
                    } else if (height <= b1.getPosy()) {
                        //cambiar la velocidad 'y' a negativa para que la pelota rebote
                        b1.setVy(b1.getVy() * -1);
                        b1.setPosy(b1.getPosy() + b1.getVy());
                    } else if (b1.getPosy() < 0.0) {
                        //cambiar la velocidad 'y' a negativa para que la pelota rebote
                        b1.setVy(b1.getVy() * -1);
                        b1.setPosy(b1.getPosy() + b1.getVy());
                    }
                }
            }
        };
        t.schedule(timerTask, 0, 10);
    }
}
