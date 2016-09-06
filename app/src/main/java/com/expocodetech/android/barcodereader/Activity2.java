package com.expocodetech.android.barcodereader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

@SuppressLint("DefaultLocale")
public class Activity2 extends Activity implements OnClickListener {
	
	Bundle datos;
	String temp,Aler_Lactosa_Com,textView2,Comp,temp3, Codigodebarras,Aler_Marisco_Com,temp7, Recuperar_Valor_Check_Lactosa, 
	Recuperar_Valor_Check_Marisco, recuperarfoto, Recuperar_Valor_Check_Gluten, Aler_Gluten_Com;
	private Button Comparar;
	private EditText composicion;
	private EditText nombre;
	private EditText codigodebarras;
	private ImageView ImgFoto;
	private ImageView Imagen_Marisco;
	private ImageView Imagen_sinlactosa;
	private ImageView Imagen_Sin_Marisco;
	private ImageView Imagen_Gluten;
	private ImageView Imagen_Sin_Gulten;
	

	private ImageView imageView1;
	
	
	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.resultados2);
	      composicion = (EditText) findViewById(R.id.composicion);
	      codigodebarras = (EditText) findViewById(R.id.codigodebarras);
	      nombre = (EditText) findViewById(R.id.editTextPassword);
	      Button button = (Button) findViewById(R.id.button);
	      imageView1= (ImageView)findViewById(R.id.imageView1);
	      
	      
	      //esto siempre se hace
	      Bundle datos = this.getIntent().getExtras();
	      temp = datos.getString("Recuperar");// composicion es composicion
	      
	      temp3 = datos.getString("Recuperar3");
	      Codigodebarras = datos.getString("RecuperarCodigoDeBarras");
	      Aler_Marisco_Com = datos.getString("RecuperarMarisco");
	      System.out.println("Vamos a recuperar2" + Aler_Marisco_Com);
	      Aler_Gluten_Com = datos.getString("RecuperarGluten");
	      Aler_Lactosa_Com = datos.getString("Recuperar2");
   		  Recuperar_Valor_Check_Lactosa = "CheckBox marcado";
		  Recuperar_Valor_Check_Marisco = "CheckBox marcado";
		  Recuperar_Valor_Check_Gluten = "CheckBox marcado";
		  composicion.setText (temp);
	      nombre.setText (temp3);
	      codigodebarras.setText(Codigodebarras);
	      
	      String x = "CheckBox marcado"; 
	      String y = "CheckBox marcado";
	      button.setOnClickListener(new OnClickListener() {
		         public void onClick(View v) {
		            finish();
		            codigodebarras.setText("");
					nombre.setText("");
					composicion.setText("");
		         }
		      });
	      
	      
	      Bundle extras = getIntent().getExtras();
	      Bitmap bmp = (Bitmap) extras.getParcelable("bitmap");
	      imageView1.setImageBitmap(bmp);
	     
	      
	      
	      Comp= composicion.getText().toString();
	      System.out.println("com0" + Comp);
	      Comp = Comp.replaceAll(" ", "");
	      Comp = Comp.replaceAll("-", "");
	      String Comp1 = Comp.toLowerCase();
	      System.out.println("com1" + Comp1);
	      String Aler_Lactosa_Com_Min = Aler_Lactosa_Com.toLowerCase();
	      System.out.println("com3" + Aler_Lactosa_Com_Min);
	      Aler_Lactosa_Com_Min = Aler_Lactosa_Com_Min.replaceAll(" ", "");
	      Aler_Lactosa_Com_Min = Aler_Lactosa_Com_Min.replaceAll("-", "");


	      //Realizar la comparacion con Lactosa
	      
	      if (Recuperar_Valor_Check_Lactosa.equalsIgnoreCase(x)){
	    	  
	  		String diaArray[] = Aler_Lactosa_Com_Min.split(",");
	  		System.out.println("Pruebas contiene" + Aler_Lactosa_Com_Min);
	  		System.out.println("Pruebas contiene2" + diaArray.toString());
	  		String ContieneLactosa = "";
	  		for (int i = 0; i < diaArray.length; i++) {
	  			
	  			boolean poss = Comp1.contains(diaArray[i]);
	  			System.out.println("Pruebas44" + poss);

	  			
	  			if(poss == true){
	  	    		ContieneLactosa = "SI";
	  	    		
	  	    	}
	  			
	  		}
	  		if (ContieneLactosa == "SI"){
	  			ImageView img= (ImageView)findViewById(R.id.ImgFoto);
	  		     img.setImageResource(R.drawable.conlactosa);
	  		     
	  			
	  		} 
	  		else {
	  			ImageView img= (ImageView)findViewById(R.id.Imagen_sinlactosa);
	  		      img.setImageResource(R.drawable.sinlactosa);
	  		}
	      }

	      String Aler_Marisco_Com_Min = Aler_Marisco_Com.toLowerCase();
	      System.out.println("com3" + Aler_Marisco_Com_Min);
	      Aler_Marisco_Com_Min = Aler_Marisco_Com_Min.replaceAll(" ", "");
	      Aler_Marisco_Com_Min = Aler_Marisco_Com_Min.replaceAll("-", "");
	      
	      //Realizar la comparacion con Marisco
	      if (Recuperar_Valor_Check_Marisco.equalsIgnoreCase(y)){
	      	
	  		String diaArray[] = Aler_Marisco_Com_Min.split(",");
	  		String ContieneMarisco = "";
	  		for (int i = 0; i < diaArray.length; i++) {
	  			
	  			//int poss = Comp.indexOf(diaArray[i]);
	  			boolean poss = Comp1.contains(diaArray[i]);
	  			System.out.println("Pruebas45" + poss);

	  			//int poss = Comp.indexOf(Aler_Lactosa_Com);
	  	    	/*if(poss != -1){
	  	    		ContieneMarisco = "SI";

	  	    	}*/
	  			
	  			if(poss == true){
	  				ContieneMarisco = "SI";
	  	    		
	  	    	}
	  	    		
	  					
	        }
	  		if (ContieneMarisco == "SI"){
	  			ImageView img_Marisco= (ImageView)findViewById(R.id.Imagen_Marisco);
	  	    	img_Marisco.setImageResource(R.drawable.conmarisco);
	  			
	  		} 
	  		else {
	  			ImageView img= (ImageView)findViewById(R.id.Imagen_Sin_Marisco);
	  		      img.setImageResource(R.drawable.sinmarisco);
	  		}
	      }
	      
	      //Realizar la comparacion con Gluten
	      
	      String Aler_Gluten_Com_Min = Aler_Gluten_Com.toLowerCase();
	      System.out.println("com3" + Aler_Lactosa_Com_Min);
	      Aler_Gluten_Com_Min = Aler_Gluten_Com_Min.replaceAll(" ", "");
	      Aler_Gluten_Com_Min = Aler_Gluten_Com_Min.replaceAll("-", "");

	      if (Recuperar_Valor_Check_Gluten.equalsIgnoreCase(y)){
	    	    	
	  		String diaArray[] = Aler_Gluten_Com_Min.split(",");
	  		String ContieneGluten = "";
	  		for (int i = 0; i < diaArray.length; i++) {
	  			
	  			//int poss = Comp.indexOf(diaArray[i]);
	  			boolean poss = Comp1.contains(diaArray[i]);
	  			System.out.println("Pruebas46" + poss);

	  			/*if(poss != -1){
	  				ContieneGluten = "SI";

	  	    	}*/
	  			
	  			if(poss == true){
	  				ContieneGluten = "SI";
	  	    		
	  	    	}
	  	    		
	  					
	      }
	  		if (ContieneGluten == "SI"){
	  			ImageView img_Gluten= (ImageView)findViewById(R.id.Imagen_Gluten);
	  	    	img_Gluten.setImageResource(R.drawable.congluten);
	  			
	  		} 
	  		else {
	  			ImageView img= (ImageView)findViewById(R.id.Imagen_Sin_Gulten);
	  		      img.setImageResource(R.drawable.singluten);
	  		}
	    }
	      	
	      
	      //acaba el metodo para poner metodos 	
	  			
	  			
	  	    	
	  	   }
	  	   
	  	   
	  	   
	  	@Override
	  	public void onClick(View v) {
	  		// TODO Auto-generated method stub
	  		
	  	}
	  				
	  }


