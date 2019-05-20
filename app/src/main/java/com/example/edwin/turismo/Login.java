package com.example.edwin.turismo;
//holahola
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
private Button btnRegistrarse,btnLogin;
private EditText editLoginEmail,editLoginPass;
private ProgressBar progressBar;
private FirebaseAuth firebaseAuth;
private FirebaseAuth.AuthStateListener authStateListener;
private FirebaseUser user;
private ImageButton salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*para quitar la barra superior*/
        getSupportActionBar().hide();
        /**/
        /*asignacion de variables*/
        btnRegistrarse=findViewById(R.id.btnRegistrar);
        btnLogin=findViewById(R.id.btnLoguin);
        editLoginEmail=findViewById(R.id.edituser);
        editLoginPass=findViewById(R.id.editPassword);
        progressBar=findViewById(R.id.ProgresBarLogin);
        progressBar.setVisibility(View.INVISIBLE);
        salir=findViewById(R.id.ImgBtnSalir);
        /**/
        /*FIREBASE*/
        FirebaseApp.initializeApp(this);
        firebaseAuth=FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();



authStateListener= new FirebaseAuth.AuthStateListener() {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

    }
};
        /*-------*/
        /*acccion de boton de registrar usuario*/
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarCampos();
                Intent registrar = new Intent(getApplicationContext(),RegistroUser.class);
                startActivity(registrar);
            }
        });
salir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"Regrese Pronto....",Toast.LENGTH_SHORT).show();
        finish();
    }
});
        /*inisio de sesion*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validacion_campos_vacios validarCampos=new Validacion_campos_vacios();
                if(validarCampos.validarVaciosLogin(editLoginEmail,editLoginPass)){
                    editLoginPass.requestFocus();
                    editLoginPass.setError("Password requerido");
                    editLoginEmail.requestFocus();
                    editLoginEmail.setError("Email requerido");

                }else if(validarCampos.validarCorreo(editLoginEmail)){
                    btnRegistrarse.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                   inicioLogin();
                }else {
                    editLoginEmail.requestFocus();
                    editLoginEmail.setError("Formato de correo invalido");
                }
            }
        });
    }
public void inicioLogin(){
    firebaseAuth.signInWithEmailAndPassword(editLoginEmail.getText().toString(), editLoginPass.getText().toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
/*inisio correcto*/
                        Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
Intent inicio=new Intent(getApplicationContext(),MenuPrincipal.class);
startActivity(inicio);
finish();
                    } else {
   /*autenticacion fallida*/
if(user.getEmail().equals(editLoginEmail.getText().toString())){
    btnLogin.setVisibility(View.VISIBLE);
    btnRegistrarse.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.INVISIBLE);
    Toast.makeText(getApplicationContext(),"Password  Incorrecto",Toast.LENGTH_LONG).show();
editLoginPass.setText("");
}else{
    progressBar.setVisibility(View.INVISIBLE);
    btnRegistrarse.setVisibility(View.VISIBLE);
    Toast.makeText(getApplicationContext(),"No esta registrado",Toast.LENGTH_LONG).show();
    limpiarCampos();
}
                    }
                }
            });
}
    @Override
    protected void onStart() {
        super.onStart();
firebaseAuth.addAuthStateListener(authStateListener);
    }
    public void limpiarCampos(){
    editLoginEmail.setText("");
    editLoginPass.setText("");

    }

    @Override
    protected void onResume() {
        SharedPreferences obj= PreferenceManager.getDefaultSharedPreferences(this);
        if(obj.getBoolean("loginData",true)){
editLoginEmail.setText(obj.getString("UserPreference","User"));
editLoginPass.setText(obj.getString("PassPreference","Pass"));
        }
        super.onResume();
    }
}
