package com.expocodetech.android.barcodereader;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	private Button scanBtn;
	private TextView formatTxt, contentTxt, contentTxt2;
	private Button dialogButton;
	private Button BT_Dialog_Gluten;
	private Button Lista_Alimentos;
	private Button insertarproducto;
	private Button BT_Dialog_Marisco;
	private static final String TAG = "MyTag";
	private EditText codigodebarras;
	private EditText nombre;
	private EditText composicion;
	private Button mostrar;
	private Button buscar2;
	private ImageButton buscar;
	private ImageButton CatalogoProductos;
	private ImageButton mas;
	private ImageButton menos;
	private int posicion = 0;
	private List<Productos> listaProductos;
	private Productos productos;
	@SuppressLint("InlinedApi")
	private CheckBox checkBoxlactosa;
	private CheckBox checkBoxmarisco;
	private CheckBox checkBoxgluten;
	CheckBox cbPulsame;
	TextView lblCheckBox;
	TextView lblCheckBoxMarisco;
	TextView lblCheckBoxGluten;
	private List<Lactosa> listaLactosa;
	private Lactosa alergenos;
	private EditText Lactosa;
	private List<Marisco> listaMarisco;
	private Marisco marisco;
	private List<Gluten> listaGluten;
	private Gluten gluten;	
	private EditText Marisco;
	private EditText Gluten;
	private ImageView imageView1;
	private EditText editText1;
	String Marisco2;
	ProgressDialog pBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
		setContentView(R.layout.activity_main);
		new Mostrar().execute();
		new RecuperarLactosa().execute();
		new RecuperarMarisco().execute();
		new RecuperarGluten().execute();
		
	
		// Se Instancia el bot�n de Scan
		scanBtn = (Button) findViewById(R.id.scan_button);
		// Se Instancia el Campo de Texto para el nombre del formato de c�digo
		// de barra
		formatTxt = (TextView) findViewById(R.id.scan_format);
		// Se Instancia el Campo de Texto para el contenido del c�digo de barra
		contentTxt = (TextView) findViewById(R.id.scan_content);

		contentTxt2 = (TextView) findViewById(R.id.textView5);
		// Se agrega la clase MainActivity.java como Listener del evento click
		// del bot�n de Scan
		scanBtn.setOnClickListener(this);

		dialogButton = (Button) findViewById(R.id.DialogButton);
		BT_Dialog_Gluten = (Button) findViewById(R.id.BT_Dialog_Gluten);
		BT_Dialog_Marisco = (Button) findViewById(R.id.BT_Dialog_Marisco);
		
		codigodebarras = (EditText) findViewById(R.id.codigodebarras);

		checkBoxlactosa = (CheckBox) findViewById(R.id.checkBoxlactosa);
		checkBoxmarisco = (CheckBox) findViewById(R.id.checkBoxmarisco);
		checkBoxgluten = (CheckBox) findViewById(R.id.checkBoxgluten);
		
		dialogButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mostrarAlertDialogoLactosa();
			}
		});
		
		
		BT_Dialog_Gluten.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mostrarAlertDialogoGluten();
			}
		});
		
		
		BT_Dialog_Marisco.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mostrarAlertDialogoMarisco();
			}
		});

		listaProductos = new ArrayList<Productos>();
		codigodebarras = (EditText) findViewById(R.id.codigodebarras);
		nombre = (EditText) findViewById(R.id.editTextPassword);
		composicion = (EditText) findViewById(R.id.composicion);
		editText1 = (EditText) findViewById(R.id.editText1);
		imageView1= (ImageView)findViewById(R.id.imageView1);
		Lactosa = (EditText) findViewById(R.id.Lactosa);
		Marisco = (EditText) findViewById(R.id.Marisco);
		System.out.println("Marisco2" + Marisco);
		Gluten = (EditText) findViewById(R.id.Gluten);
		listaLactosa=new ArrayList<Lactosa>();
		listaMarisco=new ArrayList<Marisco>();
		listaGluten=new ArrayList<Gluten>();
		
		
		//--------------------------------------CheckboxLactosa-----------------------------

		checkBoxlactosa = (CheckBox) findViewById(R.id.checkBoxlactosa);

		lblCheckBox = (TextView) findViewById(R.id.lblCheckBox);

		checkBoxlactosa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (checkBoxlactosa.isChecked())
					lblCheckBox.setText("CheckBox marcado");
				else
					lblCheckBox.setText("CheckBox no marcado");
			}
		});
		//--------------------------------------CheckboxMarisco-----------------------------
		checkBoxmarisco = (CheckBox) findViewById(R.id.checkBoxmarisco);

		lblCheckBoxMarisco = (TextView) findViewById(R.id.lblCheckBoxMarisco);

		checkBoxmarisco.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (checkBoxmarisco.isChecked())
					lblCheckBoxMarisco.setText("CheckBox marcado");
				else
					lblCheckBoxMarisco.setText("CheckBox no marcado");
			}
		});
		//--------------------------------------CheckboxGluten-----------------------------

		checkBoxgluten = (CheckBox) findViewById(R.id.checkBoxgluten);

		lblCheckBoxGluten = (TextView) findViewById(R.id.lblCheckBoxGluten);

		checkBoxgluten.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						if (checkBoxgluten.isChecked())
							lblCheckBoxGluten.setText("CheckBox marcado");
						else
							lblCheckBoxGluten.setText("CheckBox no marcado");
					}
				});

		buscar = (ImageButton) findViewById(R.id.buscar);

		buscar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!codigodebarras.getText().toString().trim().equalsIgnoreCase("")
						|| !nombre.getText().toString().trim().equalsIgnoreCase("")
						|| !composicion.getText().toString().trim().equalsIgnoreCase("")) {
					if (compruebaProductos()){
					Intent intent = new Intent(MainActivity.this, Activity1.class);
					String text = composicion.getText().toString();
					String textNombre = nombre.getText().toString();
					String text2 = Lactosa.getText().toString();
					String textMarisco = Marisco.getText().toString();
					String textCheckLactosa = lblCheckBox.getText().toString();
					String textCheckMarisco= lblCheckBoxMarisco.getText().toString();
					String textCheckGluten= lblCheckBoxGluten.getText().toString();
					String ImagenCodificada2= editText1.getText().toString();
					String textGluten = Gluten.getText().toString();
					intent.putExtra("Recuperar2", text2);// pasamos el check
					intent.putExtra("RecuperarCheckLactosa", textCheckLactosa);
					intent.putExtra("RecuperarMarisco", textMarisco);// pasamos el check
					intent.putExtra("RecuperarCheckMarisco", textCheckMarisco);
					intent.putExtra("RecuperarGluten", textGluten);
					intent.putExtra("RecuperarCheckGluten", textCheckGluten);
					intent.putExtra("Recuperar3", textNombre);
					intent.putExtra("Enviarfoto", ImagenCodificada2);
					intent.putExtra("Recuperar", text);
		
					Bundle extras = new Bundle();
					Bitmap foto = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();
					intent.putExtra("bitmap",foto);
					intent.putExtras(extras);
					startActivity(intent);
				}
					else{Toast.makeText(MainActivity.this,
							"Producto no disponible en la base de datos", Toast.LENGTH_LONG)
							.show();
					}
				} else{
					Toast.makeText(MainActivity.this,
							"Debe introducir un Código  de Barras", Toast.LENGTH_LONG)
							.show();
					}
				}
		});
		
		Lista_Alimentos = (Button) findViewById(R.id.Lista_Alimentos);
		//CatalogoProductos = (ImageButton) findViewById(R.id.CatalogoProductos);
		Lista_Alimentos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						CatalogoProductos.class);
				String textGluten = Gluten.getText().toString();
				String text2 = Lactosa.getText().toString();
				System.out.println("Recuperar lactosa" + text2);
				String textMarisco = Marisco.getText().toString();
				intent.putExtra("Recuperar2", text2);
				intent.putExtra("RecuperarMarisco", textMarisco);
				intent.putExtra("RecuperarGluten", textGluten);// pasamos el check seleccionado
				startActivity(intent);
			}
		});
		
		
		insertarproducto = (Button) findViewById(R.id.insertarproducto);
		insertarproducto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						InsertarProducto.class);
				startActivity(intent);
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// Se responde al evento click
		if (v.getId() == R.id.scan_button) {
			// Se instancia un objeto de la clase IntentIntegrator
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			// Se procede con el proceso de scaneo
			scanIntegrator.initiateScan();
		}
	}

	private void mostrarAlertDialogoLactosa() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);

		dialog.setMessage("Conservantes:"
				+ "\n"
				+ "E101 Riboflavina o Lactoflavina,"
				+ "\n"
				+ "E 270 Acido l�ctico"
				+ "\n"
				+ "E 325 Lactato s�dico"
				+ "\n"
				+ "E 326 Lactato pot�sico"
				+ "\n"
				+ "E 327 Lactato c�lcico"
				+ "\n"
				+ "E 328 Lactato de amonio"
				+ "\n"
				+ "E 329 Lactato de magnesio"
				+ "\n"
				+ "E 472 b Esteres l�cticos de los mono y diglic�ridos de los �cidos grasos"
				+ "\n" + "E 481 Estearoil-2-lactilato s�dico" + "\n"
				+ "E 482 Estearoil-2-lactilato c�lcico" + "\n"
				+ "E 575 Glucono delta lactona" + "\n"
				+ "E 585 Lactato ferroso" + "\n" + "E 966 Lactitol");
		dialog.setCancelable(false);
		dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		dialog.show();
	}
	
	//dialog Gluten
	private void mostrarAlertDialogoGluten() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);

		dialog.setMessage("Conservantes:"
				+ "\n"
				+ "E-1404 al E-1451"
				+ "\n"
				+ "E-1404 (Almid�n oxidado)"
				+ "\n"
				+ "E-1412 (Fosfato de dialmid�n)"
				+ "\n"
				+ "E-1414 (Fosfato acetilado de almid�n)"
				+ "\n"
				+ "E-1422 (Adipato acetilado de dialmid�n)"
				+ "\n"
				+ "E-1442 (Fosfato de hidroxipropil dialmid�n)"
				+ "\n"
				+ "E-1410 (Fosfato de monoalmid�n)"
				+ "\n"
				+ "E-1413 (Fosfato fosfatado de almid�n)"
				+ "\n"
				+ "E-1420 (Almid�n acetilado)"
				+ "\n"
				+ "E-1440 (Hidroxipropil almid�n)"
				+ "\n"
				+ "E-1450 (Octenil succinato de almid�n)");
		dialog.setCancelable(false);
		dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		dialog.show();
	}
	
	//dialog Marisco
		private void mostrarAlertDialogoMarisco() {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);

			dialog.setMessage("Conservantes:"
					+ "\n"
					+ "No encontramos conservantes");
			dialog.setCancelable(false);
			dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			dialog.show();
		}
		
		
		private void mostrarAlertDialogoErrorConectar() {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);

			dialog.setMessage("Error:"
					+ "\n"
					+ "Error al Conectar con la base de datos, vuelva a abrir la aplicaci�n.");
			dialog.setCancelable(false);
			dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			dialog.show();
		}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// Se obtiene el resultado del proceso de scaneo y se parsea
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			contentTxt.setText("Contenido: " + scanContent);
			String scanContent2 = scanningResult.getContents();
			contentTxt2.setText(scanContent2);
			codigodebarras.setText(scanContent);
			String scanFormat = scanningResult.getFormatName();
			formatTxt.setText("Formato: " + scanFormat);
		} else {
			// Quiere decir que NO se obtuvo resultado
			Toast toast = Toast.makeText(getApplicationContext(),
					"No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	// una vez hemos leido del webService y tenemos todo guardado en la variable
	// resultado vamos al metodo filtrarDatos()

	// Este m�todo obtendr� el objeto String del m�todo mostrar() y lo filtrar�
	// por diferentes clases JSON rellenando objetos de tipo Productos que
	// almacenaremos en un ArrayList. Parece complejo, pero no lo es, ya lo
	// ver�is.
	private boolean filtrarDatos() {
		Conexiones Conec = new Conexiones();
		listaProductos.clear();// limpiamos nuestro arraylist de productos por si tubiera algo de informacion
		String data = Conec.mostrar();// guardaremos la variable obtenido al conectar es decir la reultado
		//System.out.println("ahora si que si222222222222" + data);
		if (!data.equalsIgnoreCase("")) {// comprobamos si la variable data con
											// el resultado de la consulta esta
											// vacia para que de false
			JSONObject json;
			try {
				json = new JSONObject(data);// creamos un objeto de tipo Json
											// con el valor de data
				JSONArray jsonArray = json.optJSONArray("productos"); 
				for (int i = 0; i < jsonArray.length(); i++) {
					productos = new Productos(); 
					JSONObject jsonArrayChild = jsonArray.getJSONObject(i);
					productos.setDni(jsonArrayChild.optString("codigodebarras"));
					productos.setNombre(jsonArrayChild.optString("nombre"));
					productos.setTelefono(jsonArrayChild.optString("composicion"));
					productos.setData(jsonArrayChild.optString("photo"));
					listaProductos.add(productos);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	// Nos cargar� y mostrar� los datos de las productos almacenadas en
	// determinadas posiciones de nuestro ArrayList.
	private void mostrarMensajeConexion() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"Datos Cargados Correctamente", Toast.LENGTH_LONG)
						.show();
				
			}
		});
	}

	private boolean compruebaProductos() {
		for (int i = 0; i < listaProductos.size(); i++) {
			Productos p = listaProductos.get(i);
			System.out.println("Mostraremos la lista de productos" + p);
			if (codigodebarras.getText().toString().trim()
					.equalsIgnoreCase(p.getDni().toString().trim()))
				
			{
				
				nombre.setText(p.getNombre());
				composicion.setText(p.getTelefono());
				imageView1.setImageBitmap(p.getPhoto());
				//Toast.makeText(MainActivity.this, "El registro existe",
						//Toast.LENGTH_LONG).show();
				return true;
				//break;

			} else {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						/*Toast.makeText(MainActivity.this,
						"El registro NO existe", Toast.LENGTH_LONG).show();*/
						
					}
				});
				
			
			}

		}

		return false;
	}

	class Mostrar extends AsyncTask<String, String, String> {
		@Override
		
		protected void onPreExecute() {
	        // TODO Auto-generated method stub
	        super.onPreExecute();
	        pBar = new ProgressDialog(MainActivity.this);
	        pBar.setTitle("Cargando Alimentos");
	        pBar.setMessage("Cargando Lista");
	        pBar.setCancelable(false);
	        pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pBar.show();
	      
	    }
		
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try {Thread.sleep(300); 
				if (filtrarDatos())
					mostrarMensajeConexion();}
            catch (InterruptedException e) {}
			return "Terminado";
			
		}
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
	        //list.setAdapter(result);
			System.out.println("Esto es lo que tiene la variable" + result + "y esta la variable pBat" + pBar);
			if((pBar != null) && (pBar.isShowing())){
	        pBar.dismiss();
			}
			System.out.println("esto" + productos);
			
			if (productos == null){
				System.out.println("esto es un mensaje de datos no cargados");
				mostrarAlertDialogoErrorConectar();
				
			}
		}
		
	}

	// AsyncTask para Actualizar Productos
	class Update extends AsyncTask<String, String, String> {

		private Activity context;

		Update(Activity context) {
			this.context = context;
		}

		@Override
		protected String doInBackground(String... params) {
			
			return null;
		}
	}
	
	
	//------------------------------------------------------------Recuperar el JSON de LACTOSA---------------
	
	//una vez hemos leido del webService y tenemos todo guardado en la variable resultado vamos al metodo FiltrarRecuperarLactosa()
	
	//Este m�todo obtendr� el objeto String del m�todo mostrar() y lo filtrar� por diferentes clases JSON rellenando objetos de tipo Lactosa que almacenaremos en un ArrayList. Parece complejo, pero no lo es, ya lo ver�is.
	private boolean FiltrarRecuperarLactosa(){
		Conexiones Conec = new Conexiones();
		listaLactosa.clear();//limpiamos nuestro arraylist de alergenos por si tubiera algo de informacion
		String data=Conec.AlergiasJSON();//guardaremos la variable obtenido al conectar es decir la reultado el formato de la pagina web
		System.out.println("ahora si que si222222222222" + data);
		if(!data.equalsIgnoreCase("")){//comprobamos si la variable data con el resultado de la consulta esta vacia para que de false
			JSONObject json;
			try {
				json = new JSONObject(data);//creamos un objeto de tipo Json con el valor de data 
	            JSONArray jsonArray = json.optJSONArray("alergenos");            //pasamos de Json objeto a Json array pasandole el array de alergenos (esta vacio creo yo el per) asies como le hemos llamado en PHP
	            System.out.println("ahora si que SI33333333" + jsonArray);
	            for (int i = 0; i < jsonArray.length(); i++) {//con esto tendr�amos almacenado en JSONArray diferentes objetos JSON, cada uno con la informaci�n de cada fila de nuestra tabla, por lo que para ir recorriendo cada uno deber�amos utilizar un for, ya que en su esencia, lo que vamos a hacer es recorrer un array. 
	            	alergenos=new Lactosa(); //es decir una fila de Manuel, Otra de Juan, Otra de Jose Antonio ,,,, creamos un objeto del tipo persona
	                JSONObject jsonArrayChild = jsonArray.getJSONObject(i);
		            alergenos.setTipo(jsonArrayChild.optString("tipo"));
		            System.out.println("ahora si que SI444444444444" + jsonArrayChild);
		            listaLactosa.add(alergenos);
		            System.out.println("ahora si que SI555555" + alergenos.toString());

	            }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	//Nos cargar� y mostrar� los datos de las alergenos almacenadas en determinadas posiciones de nuestro ArrayList.
	private void mostrarLactosa(final int posicion){
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				
				Lactosa alergenos=listaLactosa.get(posicion);
				Lactosa.setText(alergenos.getTipo());
					
				
			}			
		});
	}
	class RecuperarLactosa extends AsyncTask<String,String,String>{
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(FiltrarRecuperarLactosa())mostrarLactosa(posicion);	
			return null;
		}			
	}

	class BarraProgreso extends AsyncTask<Void,Integer,Void>{
	      @Override
	      protected void onPreExecute() {
	         pBar.setMessage("Cargando...");
	         pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	         pBar.setProgress(0);
	         pBar.setMax(100);
	         pBar.show();
	      }
	 
	      @Override
	      protected Void doInBackground(Void... params) {
	         for(int i = 0; i < 10 ; i++)
	         {
	            try { Thread.sleep(1000); }
	            catch (InterruptedException e) {}
	            publishProgress(10);
	         }
	 
	         return null;
	      }
	 
	      @Override
	      protected void onProgressUpdate(Integer... values) {
	         pBar.incrementProgressBy(values[0]);
	      }
	 
	      @Override
	      protected void onPostExecute(Void aVoid) {
	         pBar.dismiss();
	         Toast.makeText(MainActivity.this,"Progreso Finalizado",Toast.LENGTH_SHORT).show();
	      }
	   }

	//------------------------------------------------------------Recuperar el JSON de MARISCO---------------
	
		//una vez hemos leido del webService y tenemos todo guardado en la variable resultado vamos al metodo FiltrarRecuperarLactosa()
		
		//Este m�todo obtendr� el objeto String del m�todo mostrar() y lo filtrar� por diferentes clases JSON rellenando objetos de tipo Lactosa que almacenaremos en un ArrayList. Parece complejo, pero no lo es, ya lo ver�is.
		private boolean FiltrarRecuperarMarisco(){
			Conexiones Conec = new Conexiones();
			listaMarisco.clear();//limpiamos nuestro arraylist de alergenos por si tubiera algo de informacion
			String data=Conec.MariscoJSON();//guardaremos la variable obtenido al conectar es decir la reultado el formato de la pagina web
			System.out.println("ahora si que si222222222222" + data);
			if(!data.equalsIgnoreCase("")){//comprobamos si la variable data con el resultado de la consulta esta vacia para que de false
				JSONObject json;
				try {
					json = new JSONObject(data);//creamos un objeto de tipo Json con el valor de data 
		            JSONArray jsonArray = json.optJSONArray("marisco");            //pasamos de Json objeto a Json array pasandole el array de alergenos (esta vacio creo yo el per) asies como le hemos llamado en PHP
		            System.out.println("ahora si que SI33333333" + jsonArray);
		            for (int i = 0; i < jsonArray.length(); i++) {//con esto tendr�amos almacenado en JSONArray diferentes objetos JSON, cada uno con la informaci�n de cada fila de nuestra tabla, por lo que para ir recorriendo cada uno deber�amos utilizar un for, ya que en su esencia, lo que vamos a hacer es recorrer un array. 
		            	marisco=new Marisco(); //es decir una fila de Manuel, Otra de Juan, Otra de Jose Antonio ,,,, creamos un objeto del tipo persona
		                JSONObject jsonArrayChild = jsonArray.getJSONObject(i);
		                marisco.setTipo(jsonArrayChild.optString("tipo"));
			            System.out.println("ahora si que SI444444444444" + jsonArrayChild);
			            listaMarisco.add(marisco);
			            System.out.println("ahora si que SI555555" + marisco.toString());
			            //mostramos el array de los alergenos
			            //System.out.println("Ste es el array con los datos" + listaLactosa.toString());
		            }
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			return false;
		}
		
		//Nos cargar� y mostrar� los datos de las alergenos almacenadas en determinadas posiciones de nuestro ArrayList.
		private void mostrarMarisco(final int posicion){
			runOnUiThread(new Runnable(){
				@Override
				public void run() {
					
					Marisco marisco=listaMarisco.get(posicion);
					Marisco.setText(marisco.getTipo());
					System.out.println("Marisco" + Marisco);
					
						
					
				}			
			});
		}
		class RecuperarMarisco extends AsyncTask<String,String,String>{
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				if(FiltrarRecuperarMarisco())mostrarMarisco(posicion);	
				return null;
			}			
		}
		
		
		
		
		//------------------------------------------------------------Recuperar el JSON de Gluten---------------
		
			//una vez hemos leido del webService y tenemos todo guardado en la variable resultado vamos al metodo FiltrarRecuperarLactosa()
			
			//Este m�todo obtendr� el objeto String del m�todo mostrar() y lo filtrar� por diferentes clases JSON rellenando objetos de tipo Lactosa que almacenaremos en un ArrayList. Parece complejo, pero no lo es, ya lo ver�is.
			private boolean FiltrarRecuperarGluten(){
				Conexiones Conec = new Conexiones();
				listaGluten.clear();//limpiamos nuestro arraylist de alergenos por si tubiera algo de informacion
				String data=Conec.GlutenJSON();//guardaremos la variable obtenido al conectar es decir la reultado el formato de la pagina web
				
				if(!data.equalsIgnoreCase("")){//comprobamos si la variable data con el resultado de la consulta esta vacia para que de false
					JSONObject json;
					try {
						json = new JSONObject(data);//creamos un objeto de tipo Json con el valor de data 
			            JSONArray jsonArray = json.optJSONArray("gluten");            //pasamos de Json objeto a Json array pasandole el array de alergenos (esta vacio creo yo el per) asies como le hemos llamado en PHP
			            
			            for (int i = 0; i < jsonArray.length(); i++) {//con esto tendr�amos almacenado en JSONArray diferentes objetos JSON, cada uno con la informaci�n de cada fila de nuestra tabla, por lo que para ir recorriendo cada uno deber�amos utilizar un for, ya que en su esencia, lo que vamos a hacer es recorrer un array. 
			            	gluten=new Gluten(); //es decir una fila de Manuel, Otra de Juan, Otra de Jose Antonio ,,,, creamos un objeto del tipo persona
			                JSONObject jsonArrayChild = jsonArray.getJSONObject(i);
			                gluten.setTipo(jsonArrayChild.optString("tipo"));
			                System.out.println("ahora si que SI444444444444" + jsonArrayChild);
				            listaGluten.add(gluten);
				            System.out.println("ahora si que SI555555" + gluten.toString());
				            
			            }
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
			
			//Nos cargar� y mostrar� los datos de las alergenos almacenadas en determinadas posiciones de nuestro ArrayList.
			private void mostrarGluten(final int posicion){
				runOnUiThread(new Runnable(){
					@Override
					public void run() {
						
						Gluten gluten=listaGluten.get(posicion);
						Gluten.setText(gluten.getTipo());
											
					}			
				});
			}
			class RecuperarGluten extends AsyncTask<String,String,String>{
				@Override
				protected String doInBackground(String... params) {
					// TODO Auto-generated method stub
					if(FiltrarRecuperarGluten())mostrarGluten(posicion);	
					return null;
				}			
			}
		
		

}
