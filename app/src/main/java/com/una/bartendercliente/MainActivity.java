package com.una.bartendercliente;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAnalytics mFirebaseAnalytics;
    private DatabaseReference Mesas;
    private ArrayList<Mesa> listaMesas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] btnMesa = new int[]{R.id.mesa1,R.id.mesa2,R.id.mesa3,R.id.mesa4,R.id.mesa5,R.id.mesa6,R.id.mesa7,R.id.mesa8};
        listaMesas = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MesaAdapter mesaAdapter = new MesaAdapter(listaMesas);
        recyclerView.setAdapter(mesaAdapter);
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
        Mesas.child("Mesa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaMesas = new ArrayList<>();
                if(listaMesas.size() == 0) {
                    mesaAdapter.setItems(listaMesas);
                    mesaAdapter.notifyDataSetChanged();
                    for (int i = 0; i < 8; i++) {
                        Button boton = (Button) findViewById(btnMesa[i]);
                        boton.setEnabled(true);
                    }
                }
                for (DataSnapshot userDataSnapshot : snapshot.getChildren()) {
                    Mesa mesa = userDataSnapshot.getValue(Mesa.class);
                    Boolean v = true;
                    for (int i = 0; i < 8; i++) {
                        Button boton = (Button) findViewById(btnMesa[i]);
                        boton.setEnabled(true);
                    }
                    for(Mesa m: listaMesas){
                        if(m.getNumero() == mesa.getNumero()){
                            v = false;
                        }
                    }
                    if(v) {
                        listaMesas.add(mesa);
                    }
                    for(Mesa m : listaMesas){
                        System.out.println(m.getNumero());
                        Button boton = (Button) findViewById(btnMesa[m.getNumero() - 1]);
                        boton.setEnabled(mesa.getEstado());
                    }
                    System.out.println("------------");
                    mesaAdapter.setItems(listaMesas);
                    mesaAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                        String key = listaMesas.get(0).getKey();
                        Mesas = FirebaseDatabase.getInstance().getReference("Mesas");
                        listaMesas.remove(0);
                        Mesas.child("Mesa").child(key).removeValue();
                        mesaAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                        int dragFlags = 0;
                        int swipeFlags = 0;
                        swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                        dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

                        if (viewHolder.getAdapterPosition() > 0) {
                            return makeMovementFlags(0, 0);
                        }

                        return makeMovementFlags(dragFlags, swipeFlags);
                    }
                };


        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mesa1:
                agregarMesa(1);
                break;
            case R.id.mesa2:
                agregarMesa(2);
                break;
            case R.id.mesa3:
                agregarMesa(3);
                break;
            case R.id.mesa4:
                agregarMesa(4);
                break;
            case R.id.mesa5:
                agregarMesa(5);
                break;
            case R.id.mesa6:
                agregarMesa(6);
                break;
            case R.id.mesa7:
                agregarMesa(7);
                break;
            case R.id.mesa8:
                agregarMesa(8);
                break;
        }

    }

    public void agregarMesa(Integer n){
        Mesas = FirebaseDatabase.getInstance().getReference("Mesas");
        String key;
        Mesa mesa;
        key = Mesas.push().getKey();
        mesa = new Mesa(key,n, false);
        Mesas.child("Mesa").child(key).setValue(mesa);
        Toast.makeText(this, "¡Has reservado la mesa "+n+" !",
                Toast.LENGTH_LONG).show();
    }

}