package com.expocodetech.android.barcodereader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class InsertarProducto extends Activity implements OnClickListener {
	private EditText codigodebarras;
	private EditText nombre;
	private EditText composicion;
	private Button insertar;
	//Insertar en la Base de datos
	private ImageView photo;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
		setContentView(R.layout.insertar);
		codigodebarras=(EditText)findViewById(R.id.codigodebarras);
		nombre=(EditText)findViewById(R.id.editTextPassword);
		composicion=(EditText)findViewById(R.id.composicion);
		Button button = (Button) findViewById(R.id.button);
		//Insertamos los datos del producto.
		insertar=(Button)findViewById(R.id.Insertar);	
		insertar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!codigodebarras.getText().toString().trim().equalsIgnoreCase("")&&
				   !nombre.getText().toString().trim().equalsIgnoreCase("")&&
				   !composicion.getText().toString().trim().equalsIgnoreCase(""))	
					
					new Insertar(InsertarProducto.this).execute();		
				
				else
					Toast.makeText(InsertarProducto.this, "Hay informaci�n por rellenar", Toast.LENGTH_LONG).show();
			}
			
		});
		 button.setOnClickListener(new OnClickListener() {
	         public void onClick(View v) {
	            finish();
	         }
	      });	
	}

	//Inserta los datos de las Personas en el servidor.
	private boolean insertar(){	 
		HttpClient httpclient;
		List<NameValuePair> nameValuePairs;
		HttpPost httppost;
        httpclient=new DefaultHttpClient();
        httppost= new HttpPost("http://manolait.ddns.net//insert.php"); // Url del Servidor      
        //A�adimos nuestros datos

        nameValuePairs = new ArrayList<NameValuePair>(4);
        nameValuePairs.add(new BasicNameValuePair("codigodebarras",codigodebarras.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("nombre",nombre.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("composicion",composicion.getText().toString().trim()));
		try {
		   httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	       httpclient.execute(httppost);
	       return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}	
	//AsyncTask para insertar Producto
	class Insertar extends AsyncTask<String,String,String>{
		
		private Activity context;	
		Insertar(Activity context){
			this.context=context;
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(insertar())
				context.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub							
						Toast.makeText(context, "Producto insertado con �xito", Toast.LENGTH_LONG).show();
						codigodebarras.setText("");
						nombre.setText("");
						composicion.setText("");
					}						
				});
			else
				context.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub							
						Toast.makeText(context, "Producto, no insertado con �xito", Toast.LENGTH_LONG).show();
					}					
				});
		      return null;
		}			
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
