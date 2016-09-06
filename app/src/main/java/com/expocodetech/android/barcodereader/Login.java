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
import java.util.ArrayList;
import java.util.List;

public class Login extends Activity implements OnClickListener {

	public static final String USER_NAME = "USERNAME";
	String username, password;
	private EditText editTextUserName, editTextPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		editTextUserName = (EditText) findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	public void invokeLogin(View view){
		username = editTextUserName.getText().toString();
		password = editTextPassword.getText().toString();

		login(username,password);

	}

	public void invokeRegistrar(View view){
		Intent intent = new Intent(Login.this, Registrar.class);
		//intent.putExtra(USER_NAME, username);
		//finish();
		startActivity(intent);

	}



	private void login(final String username, String password) {

		class LoginAsync extends AsyncTask<String, Void, String> {

			private Dialog loadingDialog;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loadingDialog = ProgressDialog.show(Login.this, "Conectando con el servidor", "Cargando...");
			}

			@Override
			protected String doInBackground(String... params) {
				String uname = params[0];
				String pass = params[1];

				InputStream is = null;
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("username", uname));
				nameValuePairs.add(new BasicNameValuePair("password", pass));
				String result = null;

				try{
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(
							"http://www.intoleran-ts.esy.es/login.php");
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					HttpResponse response = httpClient.execute(httpPost);

					HttpEntity entity = response.getEntity();

					is = entity.getContent();

					BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
					StringBuilder sb = new StringBuilder();

					String line = null;
					while ((line = reader.readLine()) != null)
					{
						sb.append(line + "\n");
					}
					result = sb.toString();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;
			}

			@Override
			protected void onPostExecute(String result){
				String s = result.trim();
				System.out.println("Resultado1" +result);
				System.out.println("Resultado2" + s);

				loadingDialog.dismiss();
				if(s.equalsIgnoreCase("success")){
					Intent intent = new Intent(Login.this, MainActivity.class);
					intent.putExtra(USER_NAME, username);
					finish();
					startActivity(intent);
				}else {
					Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
				}
			}
		}

		LoginAsync la = new LoginAsync();
		la.execute(username, password);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}


