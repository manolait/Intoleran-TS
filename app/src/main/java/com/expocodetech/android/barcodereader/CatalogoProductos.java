package com.expocodetech.android.barcodereader;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class CatalogoProductos extends ListActivity {
	
	private ProgressDialog pDialog;
	
	Bundle datos;
	String Aler_Lactosa_Com,textView2,Comp,Aler_Marisco_Com, Recuperar_Valor_Check_Lactosa, 
	Recuperar_Valor_Check_Marisco, recuperarfoto, Recuperar_Valor_Check_Gluten, Aler_Gluten_Com;
	ArrayList<Productos2> productosAvaiable = new ArrayList<Productos2>();
	ArrayList<Productos2> listaFiltrada = new ArrayList<Productos2>();
	private EditText filtro;
	private ListView lv;
	ListAdapter adapter;
		
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.listaproductos);
			Bundle datos = this.getIntent().getExtras();
			Aler_Lactosa_Com = datos.getString("Recuperar2");
			Aler_Marisco_Com = datos.getString("RecuperarMarisco");
		    Aler_Gluten_Com = datos.getString("RecuperarGluten");
		    System.out.println(Aler_Gluten_Com);
		    Recuperar_Valor_Check_Marisco = datos.getString("RecuperarCheckMarisco");
		    Recuperar_Valor_Check_Gluten = datos.getString("RecuperarCheckGluten");
			new GetDatos().execute();
			lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				   @Override
				   public void onItemClick(AdapterView<?> parent, View view, int position, long arg) {
				      
			             Intent intent = new Intent(getApplicationContext(), Activity2.class);
			             String nombre = ((TextView) view.findViewById(R.id.editTextPassword)).getText()
									.toString();
			             String codigodebarras = ((TextView) view.findViewById(R.id.codigodebarras)).getText()
									.toString();
			             String composicion = ((TextView) view.findViewById(R.id.composicion)).getText()
									.toString();
			             ImageView imageView1 = (ImageView)view.findViewById(R.id.imageView1);
			             Bundle extras = new Bundle();
			             intent.putExtra("Recuperar", composicion);
			             intent.putExtra("Recuperar3", nombre);
			             intent.putExtra("RecuperarCodigoDeBarras", codigodebarras);
			             intent.putExtra("Recuperar2", Aler_Lactosa_Com);
						 intent.putExtra("RecuperarMarisco", Aler_Marisco_Com);
						 intent.putExtra("RecuperarGluten", Aler_Gluten_Com);
			             //imageView1.buildDrawingCache();
						 //Bitmap foto= imageView1.getDrawingCache();
						 //extras.putParcelable("bitmap", foto);
						 Bitmap foto = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();
						 //Intent intent = new Intent(CiudadesActivity.this, DetalleCiudadActivity.class);
						 intent.putExtra("bitmap",foto);
						 intent.putExtras(extras);
						 startActivity(intent);
				   }
				});

			filtro = (EditText) findViewById(R.id.filtro);
			filtro.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
					String busqueda = filtro.getText().toString();
					int longBusqueda = busqueda.length();
					listaFiltrada.clear();

					for (int i = 0; i < productosAvaiable.size(); i++) {
						String nombre = productosAvaiable.get(i).getName()
								.toString();
						if (longBusqueda <= nombre.length()) {
							Log.d("Debug", "Nombre: " + nombre + " busqueda: "
									+ busqueda);
							nombre = nombre.toLowerCase();
							busqueda = busqueda.toLowerCase();
							Log.d("Debug2", "Nombre: " + nombre + " busqueda: "
									+ busqueda);
							if (nombre.contains(busqueda)) {
								listaFiltrada.add(productosAvaiable.get(i));
							}
						}
					}
				    ProductosAdapter ProductosAdapter = new ProductosAdapter(CatalogoProductos.this, listaFiltrada);
		            lv.setAdapter(ProductosAdapter);
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}

				@Override
				public void afterTextChanged(Editable s) {
				}
			});
			
		}
		private class GetDatos extends AsyncTask<Void, Void, Void> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// Showing progress dialog
				pDialog = new ProgressDialog(CatalogoProductos.this);
				pDialog.setMessage("Obteniendo datos.");
				pDialog.setCancelable(false);
				pDialog.show();
			}
			@Override
			protected Void doInBackground(Void... arg0) {
				Conexiones Conec = new Conexiones();
				String data = Conec.mostrar();
				if (!data.equalsIgnoreCase("")) {
					JSONObject json;
					try {
						json = new JSONObject(data);
				        JSONObject jsonObject = new JSONObject(data);
				        JSONArray cities = jsonObject.getJSONArray("productos");
				        for(int j = 0; j < cities.length(); j++) {
				            JSONObject productos2 = cities.getJSONObject(j);
				            // Creamos el objeto City
				            Productos2 c = new Productos2(productos2.getString("codigodebarras"), productos2.getString("nombre"), productos2.getString("composicion"));
				            c.setData(productos2.getString("photo"));
				            // Almacenamos el objeto en el array que hemos creado anteriormente
				            productosAvaiable.add(c);				            
					} 
					}catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
				return null;
			}
			//@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				// Dismiss the progress dialog
				if ((pDialog != null) && pDialog.isShowing()) {
					pDialog.dismiss();
				}
				ProductosAdapter ProductosAdapter = new ProductosAdapter(CatalogoProductos.this, productosAvaiable);
	            lv.setAdapter(ProductosAdapter);
			}
			
		}
}





