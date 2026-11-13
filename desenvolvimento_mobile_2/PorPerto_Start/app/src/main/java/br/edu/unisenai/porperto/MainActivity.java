package br.edu.unisenai.porperto;

import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;




public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PorPerto";

    private LinearProgressIndicator progresso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Componentes
        progresso = findViewById(R.id.progresso);
        FloatingActionButton fabMinhaPosicao = findViewById(R.id.fabMinhaPosicao);
        ChipGroup chipsCategorias = findViewById(R.id.chipsCategorias);


        fabMinhaPosicao.setOnClickListener(v -> {
            Toast.makeText(this, "Falta ativar o GPS", Toast.LENGTH_SHORT).show();
        });

    }
}
