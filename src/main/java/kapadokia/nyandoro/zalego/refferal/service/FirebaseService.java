package kapadokia.nyandoro.zalego.refferal.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import kapadokia.nyandoro.zalego.refferal.model.AdminUser;
import kapadokia.nyandoro.zalego.refferal.model.Refer;
import kapadokia.nyandoro.zalego.refferal.model.User;
import kapadokia.nyandoro.zalego.refferal.repository.AdminUserRepository;
import kapadokia.nyandoro.zalego.refferal.repository.ReferRepository;
import kapadokia.nyandoro.zalego.refferal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    public static final String COL_NAME="users";
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private ReferRepository referRepository;

    private FirebaseAuth firebaseAuth;

    List<Refer> referList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    public List<User> getUsers() throws ExecutionException, InterruptedException {

        // delete current, to refresh list
        usersRepository.deleteAll();

        Firestore dbFirestore  = FirestoreClient.getFirestore();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            User user;
            user = document.toObject(User.class);
            user.setId(document.getId());
            saveUsers(user);
        }
        return (List<User>) usersRepository.findAll();
    }

    private void saveUsers(User user){
        if(user.getImage().equals("default")){
            user.setHasImage(false);
            userList.add(user);
        }else {
            user.setHasImage(true);
            userList.add(user);
        }
        usersRepository.save(user);

    }


    // refers
    public List<Refer> getRefers() throws ExecutionException, InterruptedException {

        // delete current
        referRepository.deleteAll();

        Firestore dbFirestore  = FirestoreClient.getFirestore();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = dbFirestore.collection("refers").get().get().getDocuments();

        System.out.println("=================================================================================" +
                "documents + " + documents.size() +"  =================================================================");

        for (QueryDocumentSnapshot document : documents) {

            System.out.println("=================================================================================" +
                            "documents + " + document.getId());
            List<QueryDocumentSnapshot> documentSnapshots = dbFirestore.collection("refers").document(document.getId()).collection("refers")
                    .get().get().getDocuments();
            if (documentSnapshots.isEmpty())  System.out.println("Empty..... haha ");
            for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
                Refer refer;
                refer = queryDocumentSnapshot.toObject(Refer.class);
                refer.setId(queryDocumentSnapshot.getId());
                saveRefers(refer);
            }


        }
        return (List<Refer>) referRepository.findAll();
    }
    private void saveRefers(Refer refer){
        referList.add(refer);
        referRepository.save(refer);
    }

    public Optional<Refer> getOne(String id) {
        return referRepository.findById(id);
    }

    public void approve(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore  = FirestoreClient.getFirestore();
        Optional<Refer> refId = referRepository.findById(id);

        //write
        System.out.println("************Sender UId :*********** " + refId.get().getSender_uid());
        ApiFuture<WriteResult> writer = dbFirestore.collection("refers").document(refId.get().getSender_uid()).collection("refers").document(id).update("status", "approved");
        System.out.println("Update time : " + writer.get().getUpdateTime());

    }



}
