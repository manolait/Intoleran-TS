package com.expocodetech.android.barcodereader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Productos {
	
	private String nombre;
	private String codigodebarras;
	private String composicion;

    protected String data;
    protected Bitmap photo;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return codigodebarras;
	}
	public void setDni(String codigodebarras) {
		this.codigodebarras = codigodebarras;
	}
	public String getTelefono() {
		return composicion;
	}
	public void setTelefono(String composicion) {
		this.composicion = composicion;
	}
	public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        try {
            byte[] byteData = Base64.decode(data, Base64.DEFAULT);
            this.photo = BitmapFactory.decodeByteArray( byteData, 0, 
                byteData.length);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getPhoto() {
        return photo;
    }
	
	
   
}
