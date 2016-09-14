package com.expocodetech.android.barcodereader;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductosAdapter extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Productos2> items;

    public ProductosAdapter(Activity activity, ArrayList<Productos2> items) {
        this.activity = activity;
        this.items = items;
    }
 
    @Override
    public int getCount() {
        return items.size();
    }
    
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
    
   
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
         
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.list_item_productos, null);
        }
         
        Productos2 productos2 = items.get(position);
         
        ImageView image = (ImageView) vi.findViewById(R.id.imageView1);
        image.setImageBitmap(productos2.getPhoto());
             
        TextView nombre = (TextView) vi.findViewById(R.id.editTextPassword);
        nombre.setText(productos2.getName());
        
        TextView composicion = (TextView) vi.findViewById(R.id.composicion);
        composicion.setText(productos2.getTelefono());
        
        TextView codigodebarras = (TextView) vi.findViewById(R.id.codigodebarras);
        codigodebarras.setText(productos2.getcodigodebarras());
        
        return vi;
    }

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}