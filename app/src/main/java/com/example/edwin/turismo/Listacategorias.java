package com.example.edwin.turismo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Listacategorias extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    private String url = "";
    private RequestQueue rq;
    private JsonRequest jrq;

    /*array para traer los datos*/
    private List<Integer> id_cate = new ArrayList<Integer>();
    private List<String> fecha_promo = new ArrayList<String>();
    private List<Double> decuento_promo = new ArrayList<Double>();
    private List<String> titulo = new ArrayList<String>();
    private List<String> descripcion = new ArrayList<String>();
    private List<Bitmap> imagen = new ArrayList<Bitmap>();
    private List<String> nombre_contacto = new ArrayList<String>();
    private List<String> email = new ArrayList<String>();
    private List<String> telefono = new ArrayList<String>();
    private List<String> nombre_comercio = new ArrayList<String>();
    private List<String> coordenadas=new ArrayList<String>();
    private String mensaje = "";
private int catSeleccionado=0;
private String nameCateg=null;
private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacategorias);
        /*para aser la fecha a atras */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*para cambiarle el titulo a la barra */
this.setTitle(R.string.regresar);
Bundle bundle=getIntent().getExtras();
lista=findViewById(R.id.listPromo);
/*para las categorias seleccionadas */
switch (bundle.getString("accion")){
    case "1":
        nameCateg="Volcanes";
        catSeleccionado=1;
        break;
    case "2":
        nameCateg="Playas";
        catSeleccionado=2;
        break;
    case "3":
        nameCateg="Pueblos coloniales";
        catSeleccionado=3;
        break;
    case "4":
        nameCateg="Restaurantes";
        catSeleccionado=4;
        break;
    case "5":
        nameCateg="Hoteles";
        catSeleccionado=5;
        break;
    case "6":
        nameCateg="Recreacion";
        catSeleccionado=6;
        break;
}
/*para aser las peticiones*/
rq= Volley.newRequestQueue(getApplicationContext());
/*para seleccionar la categoria*/
selectCategoria(catSeleccionado);
lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent detalle=new Intent(getApplicationContext(),DetalleCateg.class);
        detalle.putExtra("id",id_cate.get(i));
        detalle.putExtra("titulo",titulo.get(i));
        detalle.putExtra("fecha",fecha_promo.get(i));
        detalle.putExtra("descuento",decuento_promo.get(i));
        detalle.putExtra("descripcion",descripcion.get(i));
detalle.putExtra("contacto",nombre_contacto.get(i));
detalle.putExtra("telefono",telefono.get(i));
detalle.putExtra("comercio",nombre_comercio.get(i));
detalle.putExtra("email",email.get(i));
detalle.putExtra("ubicacion",coordenadas.get(i));

detalle.putExtra("img",imagen.get(i));
        startActivity(detalle);
        finish();

      //  Toast.makeText(getApplicationContext(),"telefono "+imagen.get(i),Toast.LENGTH_SHORT).show();
    }
});
    }

/*metodo para aser salir la aplicacion */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Intent pp=new Intent(getApplicationContext(),MenuPrincipal.class);
        startActivity(pp);
        finish();
        return false;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"Conexion demasiada lenta ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        //Toast.makeText(getApplicationContext(),"cantidad "+jsonArray.length(),Toast.LENGTH_LONG).show();


       try {
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

        /*llenando los array*/
                id_cate.add(jsonObject.optInt("id_promo"));
                fecha_promo.add(jsonObject.optString("fecha_promocion"));
                decuento_promo.add(jsonObject.optDouble("descuento"));
                titulo.add(jsonObject.optString("titulo_img"));
                descripcion.add(jsonObject.optString("descripcion_img"));
/*para convertir la img a bitmap*/
                String img = jsonObject.optString("imagen");
                byte[] bytedate = android.util.Base64.decode(img, android.util.Base64.DEFAULT);
                Bitmap bitmapimg = BitmapFactory.decodeByteArray(bytedate, 0, bytedate.length);
/*-----------------------------------------------------------------*/
                imagen.add(bitmapimg);
                nombre_contacto.add(jsonObject.optString("nombre_Contacto"));
                nombre_comercio.add(jsonObject.optString("nombre_Comercio"));
                telefono.add(jsonObject.optString("telefono_Contacto"));

                email.add(jsonObject.optString("email_Contacto"));
                coordenadas.add(jsonObject.optString("coordenadas"));

            }
llenadoLista();

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    /*metodo para traer  todas las categorias */
    public void selectCategoria(int categoria) {
        switch (categoria) {

            case 1:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=1";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 2:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=2";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 3:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=3";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 4:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=4";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 5:
                url = " https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=5";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 6:

                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=6";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            default:
                mensaje = "No selecciono Categoria";

        }

    }

    /*metodo para llenar la lista */
    public void llenadoLista(){
        AdaptadorCategorias adaptadorCategorias=new AdaptadorCategorias(this,titulo,imagen);
        lista.setAdapter(adaptadorCategorias);
    }
}
