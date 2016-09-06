package com.expocodetech.android.barcodereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class Conexiones {
	
	//realizamos la conexion con la BD obtenemos un objeto del tipo resultado
			public String mostrar(){
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://www.intoleran-ts.esy.es/Productos.php");
				String resultado="";   
				HttpResponse response;
			    try {
					response = httpclient.execute(httppost);
			        HttpEntity entity = response.getEntity();
		            InputStream instream = entity.getContent();
		            resultado= convertStreamToString(instream);            
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			    return resultado;
			}
			//Este m�todo convertir� objetos de tipo InputStream en objetos de tipo String.
			private String convertStreamToString(InputStream is) throws IOException {
		        if (is != null) {
		                StringBuilder sb = new StringBuilder();
		                String line;
		                try {
		                        BufferedReader reader = new BufferedReader(
		                                        new InputStreamReader(is));
		                        while ((line = reader.readLine()) != null) {
		                                sb.append(line).append("\n");
		                        }
		                }
		                finally {
		                        is.close();
		                }
		                return sb.toString();
		        } else {
		                return "";
		        }
			}
			
			
			
			//Comienza el proceso de recuperar de la base de datos el JSON y comparar con el campo
			//realizamos la conexion con la BD obtenemos un objeto del tipo resultado
					public String AlergiasJSON(){
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.intoleran-ts.esy.es/Lactosa.php");
						String resultado="";   
						HttpResponse response;
					    try {
							response = httpclient.execute(httppost);
					        HttpEntity entity = response.getEntity();
				            InputStream instream = entity.getContent();
				            resultado= convertStreamToString(instream);            
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					    return resultado;
					}
					//Este m�todo convertir� objetos de tipo InputStream en objetos de tipo String.
					private String convertStreamToString2(InputStream is) throws IOException {
				        if (is != null) {
				                StringBuilder sb = new StringBuilder();
				                String line;
				                try {
				                        BufferedReader reader = new BufferedReader(
				                                        new InputStreamReader(is, "UTF-8"));
				                        while ((line = reader.readLine()) != null) {
				                                sb.append(line).append("\n");
				                        }
				                }
				                finally {
				                        is.close();
				                }
				                return sb.toString();
				        } else {
				                return "";
				        }
					}
			
			
					//Comienza el proceso de recuperar de la base de datos el JSON y comparar con el campo
					//realizamos la conexion con la BD obtenemos un objeto del tipo resultado
							public String MariscoJSON(){
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.intoleran-ts.esy.es/Marisco.php");
								String resultado="";   
								HttpResponse response;
							    try {
									response = httpclient.execute(httppost);
							        HttpEntity entity = response.getEntity();
						            InputStream instream = entity.getContent();
						            resultado= convertStreamToString(instream);            
								} catch (ClientProtocolException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							    return resultado;
							}
							//Este m�todo convertir� objetos de tipo InputStream en objetos de tipo String.
							private String convertStreamToString3(InputStream is) throws IOException {
						        if (is != null) {
						                StringBuilder sb = new StringBuilder();
						                String line;
						                try {
						                        BufferedReader reader = new BufferedReader(
						                                        new InputStreamReader(is, "UTF-8"));
						                        while ((line = reader.readLine()) != null) {
						                                sb.append(line).append("\n");
						                        }
						                }
						                finally {
						                        is.close();
						                }
						                return sb.toString();
						        } else {
						                return "";
						        }
							}
							
							
							//Comienza el proceso de recuperar de la base de datos el JSON y comparar con el campo
							//realizamos la conexion con la BD obtenemos un objeto del tipo resultado
									public String GlutenJSON(){
										HttpClient httpclient = new DefaultHttpClient();
										HttpPost httppost = new HttpPost("http://www.intoleran-ts.esy.es/Gluten.php");
										String resultado="";   
										HttpResponse response;
									    try {
											response = httpclient.execute(httppost);
									        HttpEntity entity = response.getEntity();
								            InputStream instream = entity.getContent();
								            resultado= convertStreamToString(instream);            
										} catch (ClientProtocolException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}	
									    return resultado;
									}
									//Este m�todo convertir� objetos de tipo InputStream en objetos de tipo String.
									private String convertStreamToString4(InputStream is) throws IOException {
								        if (is != null) {
								                StringBuilder sb = new StringBuilder();
								                String line;
								                try {
								                        BufferedReader reader = new BufferedReader(
								                                        new InputStreamReader(is, "UTF-8"));
								                        while ((line = reader.readLine()) != null) {
								                                sb.append(line).append("\n");
								                        }
								                }
								                finally {
								                        is.close();
								                }
								                return sb.toString();
								        } else {
								                return "";
								        }
									}
			
			

}
