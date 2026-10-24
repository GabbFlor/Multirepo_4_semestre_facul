package com.aula.fotonuvem;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.preprocess.BitmapDecoder;
import com.cloudinary.android.preprocess.BitmapEncoder;
import com.cloudinary.android.preprocess.DimensionsValidator;
import com.cloudinary.android.preprocess.ImagePreprocessChain;
import com.cloudinary.android.preprocess.Limit;
import com.cloudinary.android.preprocess.Rotate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView imgFoto;
    private Button btnTirarFoto, btnGaleria, btnEnviar;
    private ProgressBar progress;
    private TextView txtStatus;

    private Uri fotoUri;
    private String urlNuvem;

    //configurar camera android
    private ActivityResultLauncher<Uri> cameraAtivar = registerForActivityResult(new ActivityResultContracts.TakePicture(),
                takePicture -> {
                    if (takePicture != null && takePicture) {
                        imgFoto.postDelayed(() -> {
                            // limpa a img que já tem
                            imgFoto.setImageURI(null);

                            imgFoto.setImageURI(fotoUri);

                            txtStatus.setText("Foto tirada com sucesso!");

                            btnEnviar.setEnabled(true);
                        }, 1500);
                    }
                }
            );

    // configurar galeria android
    private ActivityResultLauncher<Intent> galeriaAbrir =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                            public void onActivityResult(ActivityResult result) {
                            if(result.getData() != null) {
                                // carregar a foto
                                fotoUri = result.getData().getData();

                                imgFoto.setImageURI(null);
                                imgFoto.setImageURI(fotoUri);
                                txtStatus.setText("Foto selecionada com sucesso!");
                                btnEnviar.setEnabled(true);
                            }
                        }
                    });

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

        initCloudinary();

        imgFoto = findViewById(R.id.imgFoto);
        btnTirarFoto = findViewById(R.id.btnTirarFoto);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnGaleria = findViewById(R.id.btnGaleria);
        progress = findViewById(R.id.progress);
        txtStatus = findViewById(R.id.txtStatus);

        // recuperar dados quando girar a tela
        if(savedInstanceState != null) {
            fotoUri = Uri.parse(savedInstanceState.getString("fotoUri"));
            imgFoto.setImageURI(fotoUri);

            txtStatus.setText(savedInstanceState.getString("txtStatus"));
        }

        btnTirarFoto.setOnClickListener(v -> {
            tirarFoto();
        });

        btnGaleria.setOnClickListener(v -> {
            abrirGaleria();
        });
        
        btnEnviar.setOnClickListener(v -> {
            enviarNuvem();
        });
    }

    private void enviarNuvem() {
        if (fotoUri == null) return;

        // representatividade visual pq pode demorar para upar para a nuvem
        progress.setVisibility(View.VISIBLE);
        // trava o btn para o usuario n enviar mais de uma vez
        btnEnviar.setEnabled(false);
        txtStatus.setText("Enviando foto para a nuvem...");

        // enviando img
        MediaManager.get()
                .upload(fotoUri)
                .unsigned("nuvem_aula_mobiles")
                .option("folder", "atv_aula_mobiles")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {

                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {

                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        // obtendo a url da foto na nuvem
                        urlNuvem = (String) resultData.get("secure_url");
                        progress.setVisibility(View.GONE);
                        btnEnviar.setEnabled(true);
                        txtStatus.setText(urlNuvem);
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {

                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {

                    }
                })
                .dispatch(this);
    }

    private void abrirGaleria() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galeriaAbrir.launch(galeria);
    }

    private void tirarFoto() {
        // depois q tira a foto, primeiro salva uma versao temporaria para dpps enviar para o cloudnary

        File arquivo = new File(getExternalFilesDir(null), "foto_tmp.jpg");
        fotoUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", arquivo);

        cameraAtivar.launch(fotoUri);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(fotoUri != null) {
            outState.putString("fotoUri", fotoUri.toString());
            outState.putString("txtStatus", txtStatus.getText().toString());
        }
    }

    private void initCloudinary() {
        try {
            Map config = new HashMap();
            config.put("cloud_name", "br4gknks");
            MediaManager.init(this, config);
        } catch (Exception e) {
            Log.e("Cloudinary", "Erro na hora de iniciar o cloudnary" + e.getMessage());
        }
    }
}