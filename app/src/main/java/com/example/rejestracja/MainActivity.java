package com.example.rejestracja;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText inputEmail,inputPassword;
    private Button buttonReister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        buttonReister = findViewById(R.id.buttonRegister);




        buttonReister.setOnClickListener(v->{
            String haslo = inputPassword.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();

            if(czyEmailPoprawny(email)){

            }else{
                Toast.makeText(MainActivity.this,"Email jest zły jest złe",Toast.LENGTH_SHORT).show();
            }
            if(czyPoprawneHaslo(haslo)){
                //Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this,"Haslo jest złe",Toast.LENGTH_SHORT).show();
            }
            czyPoprawneHaslo(haslo);
        });

    }

    private static Boolean czyEmailPoprawny(String email){
        for(int i = 0 ;i<email.length();i++){
            if(email.charAt(i) == '@'){
                return  true;
            }
        }
        return false;
    }
    private static Boolean czyPoprawneHaslo(String haslo){
        boolean czyMamMinZnakow = false,czyMaDuzeLitery = false,czyMaZnakiSpecjalne = false,czyMaLiczby=false;

        char[] znakiSpecjalne = {'`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '{', '}', '[', ']', ';', ':', '<', '>', '.', '/', '?', '|'};

        if(haslo.length() >= 16){
            czyMamMinZnakow = true;
        }

        for(int i =0 ;i<haslo.length();i++){
            if(Character.isUpperCase(haslo.charAt(i))){
                czyMaDuzeLitery = true;
            }
        }


        for(int i = 0;i<znakiSpecjalne.length;i++){
            for(int j = 0;j<haslo.length();j++){
                if(haslo.toCharArray()[j] == znakiSpecjalne[i]){
                    czyMaZnakiSpecjalne = true;
                }
            }
        }

        for (int i = 0; i < haslo.length(); i++) {
            if (Character.isDigit(haslo.charAt(i))) {
                czyMaLiczby = true;
            }
        }

        if(czyMamMinZnakow && czyMaDuzeLitery && czyMaZnakiSpecjalne && czyMaLiczby){
            return true;
        }

        System.out.println(czyMamMinZnakow+","+czyMaDuzeLitery+","+czyMaZnakiSpecjalne+","+czyMaLiczby);

        return false;
    }
}