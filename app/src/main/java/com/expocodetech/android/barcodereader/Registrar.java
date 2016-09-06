package com.expocodetech.android.barcodereader;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Registrar extends Activity implements OnClickListener {

	private EditText editTextName;
	private EditText editTextUsername;
	private EditText editTextPassword;
	private EditText editTextEmail;

	private Button buttonRegister;

	private static final String REGISTER_URL = "http://www.intoleran-ts.esy.es/Registrar.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar);

		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextUsername = (EditText) findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);

		buttonRegister = (Button) findViewById(R.id.buttonRegister);

		buttonRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == buttonRegister){
			registerUser();
		}

	}


	public void invokeVolver(View view){
		Intent intent = new Intent(Registrar.this, Login.class);

		startActivity(intent);

	}

	private void registerUser() {
		String name = editTextName.getText().toString().trim().toLowerCase();
		String username = editTextUsername.getText().toString().trim().toLowerCase();
		String password = editTextPassword.getText().toString().trim().toLowerCase();
		String email = editTextEmail.getText().toString().trim().toLowerCase();

		register(name,username,password,email);
	}

	private void register(String name, String username, String password, String email) {
		String urlSuffix = "?name="+name+"&username="+username+"&password="+password+"&email="+email;
		class RegisterUser extends AsyncTask<String, Void, String>{

			ProgressDialog loading;


			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loading = ProgressDialog.show(Registrar.this, "Espere....",null, true, true);
			}

			@Override
			protected void onPostExecute(String s) {
				super.onPostExecute(s);
				loading.dismiss();
				Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
			}

			@Override
			protected String doInBackground(String... params) {
				String s = params[0];
				BufferedReader bufferedReader = null;
				try {
					URL url = new URL(REGISTER_URL+s);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

					String result;

					result = bufferedReader.readLine();

					return result;
				}catch(Exception e){
					return null;
				}
			}
		}

		RegisterUser ru = new RegisterUser();
		ru.execute(urlSuffix);
	}


}


