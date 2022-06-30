package com.una.bartendercliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAnalytics mFirebaseAnalytics;
    private DatabaseReference Mesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Button mesa1 = findViewById(R.id.mesa1);
        Button mesa2 = findViewById(R.id.mesa2);
        Button mesa3 = findViewById(R.id.mesa3);
        Button mesa4 = findViewById(R.id.mesa4);
        Button mesa5 = findViewById(R.id.mesa5);
        Button mesa6 = findViewById(R.id.mesa6);
        Button mesa7 = findViewById(R.id.mesa7);
        Button mesa8 = findViewById(R.id.mesa8);
        mesa1.setOnClickListener(this);
        mesa2.setOnClickListener(this);
        mesa3.setOnClickListener(this);
        mesa4.setOnClickListener(this);
        mesa5.setOnClickListener(this);
        mesa6.setOnClickListener(this);
        mesa7.setOnClickListener(this);
        mesa8.setOnClickListener(this);
        Mesas = FirebaseDatabase.getInstance().getReference("Mesas");
        Mesas.child("Mesa").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    Mesa mesa = userDataSnapshot.getValue(Mesa.class);
                    Boolean state = mesa.getEstado();
                    System.out.println(mesa.getEstado());
                    int[] btnMesa = new int[]{R.id.mesa1,R.id.mesa2,R.id.mesa3,R.id.mesa4,R.id.mesa5,R.id.mesa6,R.id.mesa7,R.id.mesa8};
                    if (state) {
                        mesa.getNumero();
                        Button boton = (Button)findViewById(btnMesa[mesa.getNumero()-1]);
                        boton.setEnabled(true);
                    }else{
                        mesa.getNumero();
                        Button boton = (Button)findViewById(btnMesa[mesa.getNumero()-1]);
                        boton.setEnabled(false);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        });

        }

    @Override
    public void onClick(View v) {
        Mesas = FirebaseDatabase.getInstance().getReference("Mesas");
        Integer numero;
        Button boton;
        Mesa mesa;
        switch (v.getId()) {
            case R.id.mesa1:
                boton = (Button)findViewById(R.id.mesa1);
                numero = Integer.parseInt(boton.getText().toString());
                mesa = new Mesa(numero, false);
                boton.setEnabled(false);
                Mesas.child("Mesa").child(numero.toString()).setValue(mesa);
                Toast.makeText(this, "¡Has reservado la mesa "+numero+" !",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.mesa2:
                boton = (Button)findViewById(R.id.mesa2);
                    numero = Integer.parseInt(boton.getText().toString());
                    mesa = new Mesa(numero, false);
                    boton.setEnabled(false);
                    Mesas.child("Mesa").child(numero.toString()).setValue(mesa);
                    Toast.makeText(this, "¡Has reservado la mesa "+numero+" !",
                            Toast.LENGTH_LONG).show();
                break;
            case R.id.mesa3:
                boton = (Button)findViewById(R.id.mesa3);
                numero = Integer.parseInt(boton.getText().toString());
                mesa = new Mesa(numero, false);
                boton.setEnabled(false);
                Mesas.child("Mesa").child(numero.toString()).setValue(mesa);
                Toast.makeText(this, "¡Has reservado la mesa "+numero+" !",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.mesa4:
                boton = (Button)findViewById(R.id.mesa4);
                numero = Integer.parseInt(boton.getText().toString());
                mesa = new Mesa(numero, false);
                boton.setEnabled(false);
                Mesas.child("Mesa").child(numero.toString()).setValue(mesa);
                Toast.makeText(this, "¡Has reservado la mesa "+numero+" !",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.mesa5:
                boton = (Button)findViewById(R.id.mesa5);
                numero = Integer.parseInt(boton.getText().toString());
                mesa = new Mesa(numero, false);
                boton.setEnabled(false);
                Mesas.child("Mesa").child(numero.toString()).setValue(mesa);
                Toast.makeText(this, "¡Has reservado la mesa "+numero+" !",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.mesa6:
                boton = (Button)findViewById(R.id.mesa6);
                numero = Integer.parseInt(boton.getText().toString());
                mesa = new Mesa(numero, false);
                boton.setEnabled(false);
                Mesas.child("Mesa").child(numero.toString()).setValue(mesa);
                Toast.makeText(this, "¡Has reservado la mesa "+numero+" !",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.mesa7:
                boton = (Button)findViewById(R.id.mesa7);
                numero = Integer.parseInt(boton.getText().toString());
                mesa = new Mesa(numero, false);
                boton.setEnabled(false);
                Mesas.child("Mesa").child(numero.toString()).setValue(mesa);
                Toast.makeText(this, "¡Has reservado la mesa "+numero+" !",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.mesa8:
                boton = (Button)findViewById(R.id.mesa8);
                numero = Integer.parseInt(boton.getText().toString());
                mesa = new Mesa(numero, false);
                boton.setEnabled(false);
                Mesas.child("Mesa").child(numero.toString()).setValue(mesa);
                Toast.makeText(this, "¡Has reservado la mesa "+numero+" !",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

}