package com.aula.muralfilmes.model;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Filme {
    //ATENÇÃO: liberar comentario apos implementar o firebase

    @DocumentId
    private String id;

    private String titulo;
    private String genero;
    private String indicadoPor;
    private long votos;

    //ATENÇÃO: liberar comentario apos implementar o firebase
    @ServerTimestamp
    private Date criadoEm;


    public Filme() {}

    // construtor com a porra toda menos id pq vem do firebases
    public Filme(String titulo, String genero, String indicadoPor, long votos) {
        this.titulo = titulo;
        this.genero = genero;
        this.indicadoPor = indicadoPor;
        this.votos = votos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getIndicadoPor() {
        return indicadoPor;
    }

    public long getVotos() {
        return votos;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setVotos(long votos) {
        this.votos = votos;
    }

    public String getId() {
        return id;
    }
}
