package com.GabbFlor.projeto1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ciclo extends AppCompatActivity {

    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ciclo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("CICLO", "onCreate");
        Button btnSomar = findViewById(R.id.bt_contar);
        TextView txtResult = findViewById(R.id.txt_somar);

        btnSomar.setOnClickListener(v -> {
            contador++;
            txtResult.setText(String.valueOf(contador));
        });
    }

    // essa porra aqui salva no bundle, ou sej , mudanças de estados na tela (tipo mudar para horizontal) não perdem o valor salvo nesse método
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("contador", contador);
    }

    // aqui é o contrário do de cima, ele puxa do bundle o valor que foi salvo
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contador = savedInstanceState.getInt("contador");

        // atualiza o componente de tela com o valor que foi recuperado do bundle
        TextView txtResult = findViewById(R.id.txt_somar);
        txtResult.setText(String.valueOf(contador));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CICLOx", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CICLOx", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CICLOx", "onStop");
    }
}