package com.expocodetech.android.barcodereader;

import android.app.Activity;

import android.content.Intent;
import android.os.Handler;
import android.app.Activity;
import android.os.Bundle;
 
 
public class SplashScreen extends Activity {
 
	// Temporizador para la pantalla de bienvenida
	private static int SPLASH_TIEMPO = 3000;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
 
		new Handler().postDelayed(new Runnable() {
 
			/*
			* Mostramos la pantalla de bienvenida con un temporizador.
			* De esta forma se puede mostrar el logo de la app
			* durante unos segundos.
			*/
 
			@Override
			public void run() {
				Intent i = new Intent(SplashScreen.this, Login.class);
				startActivity(i);
 
				// Cerramos esta activity
				finish();
			}
		}, SPLASH_TIEMPO);
	}
}