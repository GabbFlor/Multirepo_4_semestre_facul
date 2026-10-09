package com.aula.muralfilmes.data;

import com.aula.muralfilmes.model.Filme;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class FilmeRepository {
    public ListenerRegistration lerRealTime(EventListener<QuerySnapshot> callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Filmes")
                .orderBy("criadoEm", Query.Direction.DESCENDING)
                .addSnapshotListener(callback);
    }

    public Task<DocumentReference> salvar(Filme filme) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Filmes").add(filme);
    }

    public Task<Void> excluir(Filme filme) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Filmes")
                .document(filme.getId())
                .delete();
    }

    public Task<Void> votar(Filme filme) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Filmes")
                .document(filme.getId())
                .update("votos", FieldValue.increment(1));
    }
}
