package com.expocodetech.android.barcodereader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Productos2 {
    protected String codigodebarras;
    protected String nombre;
    private String composicion;
    protected String data;
    protected Bitmap photo;
   
    
    
    public Productos2(String codigodebarras, String nombre, String composicion) {
        this.codigodebarras = codigodebarras;
        this.nombre = nombre;
        this.composicion = composicion;
    }
    
    public String getcodigodebarras() {
        return codigodebarras;
    }
    
    public void setcodigodebarras(String codigodebarras) {
        this.codigodebarras = codigodebarras;
    }
    
    public String getName() {
        return nombre;
    }
    
    public void setName(String nombre) {
        this.nombre = nombre;
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