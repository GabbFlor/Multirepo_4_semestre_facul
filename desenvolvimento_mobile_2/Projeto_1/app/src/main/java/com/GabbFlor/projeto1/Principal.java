package com.GabbFlor.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_ola_android = findViewById(R.id.bt_ola_android);
        Button btn_tela_ciclo = findViewById(R.id.bt_tela_ciclo);
        Button btn_contador = findViewById(R.id.bt_contador);

        btn_ola_android.setOnClickListener(v -> {
            Intent roteHelloWorld = new Intent(Principal.this, MainActivity.class);
            startActivity(roteHelloWorld);
        });

        btn_contador.setOnClickListener(v -> {
            Intent roteSorteio = new Intent(Principal.this, Sorteio_top.class);
            startActivity(roteSorteio);
        });

        btn_tela_ciclo.setOnClickListener(v -> {
            Intent roteCiclo = new Intent(Principal.this, Ciclo.class);
            startActivity(roteCiclo);
        });
    }
}