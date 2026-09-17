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
    private EditText inputEmail,inputPassword,inputName,inputSurname;
    private Button buttonReister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputName = findViewById(R.id.inputName);
        inputSurname = findViewById(R.id.inputSurname);

        buttonReister = findViewById(R.id.buttonRegister);




        buttonReister.setOnClickListener(v->{
            String haslo = inputPassword.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String imie = inputName.getText().toString().trim();
            String nazwisko = inputSurname.getText().toString().trim();
            if(czyPolaSaPuste(imie,nazwisko,email,haslo)){
                Toast.makeText(MainActivity.this,"Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
                return;
            }else{
                if(!czyEmailPoprawny(email)){
                    Toast.makeText(MainActivity.this,"Podaj poprawny adres email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!czyPoprawneHaslo(haslo)){
                    return;
                }
            }

            if(czyPolaSaPuste(imie,nazwisko,email,haslo) == false && czyEmailPoprawny(email) && czyPoprawneHaslo(haslo)){
                Toast.makeText(MainActivity.this,"Dane są poprawne",Toast.LENGTH_SHORT).show();
                inputName.setText("");
                inputSurname.setText("");
                inputEmail.setText("");
                inputPassword.setText("");
            }

        });

    }

    private static Boolean czyPolaSaPuste(String imie,String nazwisko,String email,String haslo){
        if(imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty()){
            return true;
        }
        return false;
    }
    private static Boolean czyEmailPoprawny(String email){
        if(email.contains("@") && email.contains(".")){
            return  true;
        }
        return false;
    }
    private Boolean czyPoprawneHaslo(String haslo){
        boolean czyMamMinZnakow = false,czyMaDuzeLitery = false,czyMaZnakiSpecjalne = false,czyMaMaleLitery=false;

        char[] znakiSpecjalne = {'`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '{', '}', '[', ']', ';', ':', '<', '>', '.', '/', '?', '|'};

        if(haslo.length() >= 8){
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

        for(int i =0 ;i<haslo.length();i++){
            if(Character.isLowerCase(haslo.charAt(i))){
                czyMaMaleLitery = true;
            }
        }

        if(czyMamMinZnakow && czyMaDuzeLitery && czyMaZnakiSpecjalne && czyMaMaleLitery){
            return true;
        } else if (czyMamMinZnakow == false && czyMaDuzeLitery == false && czyMaZnakiSpecjalne == false && czyMaMaleLitery == false ) {
            Toast.makeText(MainActivity.this,"Hasło musi miec conajmniej 8 znaków,Dużą/Malą lieterę,znak specjalny",Toast.LENGTH_SHORT).show();
        } else if (czyMamMinZnakow == false) {
            Toast.makeText(MainActivity.this,"Hasło musi miec conajmniej 8 znaków",Toast.LENGTH_SHORT).show();
        } else if (czyMaDuzeLitery == false) {
            Toast.makeText(MainActivity.this,"Hasło musi miec conajmniej jedną dużą literę",Toast.LENGTH_SHORT).show();
        }else if (czyMaZnakiSpecjalne == false) {
            Toast.makeText(MainActivity.this,"Hasło musi miec conajmniej jednen znak specjalny",Toast.LENGTH_SHORT).show();
        }else if (czyMaMaleLitery == false) {
            Toast.makeText(MainActivity.this,"Hasło musi miec conajmniej jedną małą litere",Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}