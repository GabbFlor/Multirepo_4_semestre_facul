package br.edu.unisenai.rangonaregua.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import br.edu.unisenai.rangonaregua.model.Lugar;

public class LugarRepository {
    public Task<DocumentReference> inserir(Lugar lugar) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Lugares").add(lugar);
    }

    public ListenerRegistration lerRealTime(EventListener<QuerySnapshot> callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // basicamente aqui é montagem de query, então ordena por "votos" de forma decrescente
        // o callback é recebido toda a vez q tem uma mudança no DB ao que eu entendi, é um aviso para mudar a página
        return db.collection("Lugares")
                .orderBy("votos", Query.Direction.DESCENDING)
                .addSnapshotListener(callback);
    }

    public Task<Void> votar (Lugar lugar) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // use metodo q ja existe (FieldValue.increment) para auto incrementar sem precisar fazer aquela porra de lugar.setVotos++
        return db.collection("Lugares")
                .document(lugar.getId())
                .update("votos", FieldValue.increment(1));
    }

    public Task<Void> deletar (Lugar lugar) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // use metodo q ja existe (FieldValue.increment) para auto incrementar sem precisar fazer aquela porra de lugar.setVotos++
        return db.collection("Lugares")
                .document(lugar.getId())
                .delete();
    }

    public Task<Void> restaurar(Lugar lugar) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        return db.collection("Lugares").document(lugar.getId()).set(lugar);
    }
}
