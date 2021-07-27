package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Message;
import com.team4.getvaxi.recycle.QuestionsAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AskQuestionsActivity extends AppCompatActivity {

    public static final String TAG = "AskQuestionsActivity";

    EditText txtMessage;
    Button buttonSend;

    Message messageSender = new Message();
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    RecyclerView listOfMessages;
    final QuestionsAdapter questionsAdapter = new QuestionsAdapter();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_questions);

        txtMessage = findViewById(R.id.edit_gchat_message);
        buttonSend = findViewById(R.id.button_gchat_send);

        listOfMessages = findViewById(R.id.recycler_gchat);
        listOfMessages.setHasFixedSize(false);
        listOfMessages.setLayoutManager(new LinearLayoutManager(this));
        listOfMessages.setAdapter(questionsAdapter);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loaduserMessages();



        buttonSend.setOnClickListener(v->sendMessage());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendMessage() {
        messageSender.setUserId(mAuth.getCurrentUser().getUid());
        messageSender.setMessage(txtMessage.getText().toString());
        messageSender.setUserType("USER");
        messageSender.setMessageDateTime(LocalDateTime.now());

        db.collection("questions")
                .add(messageSender)
                .addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                postMessageSend();

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

    }

    private void postMessageSend() {
        txtMessage.setText("");


        loaduserMessages();


    }

    private void loaduserMessages() {

        ArrayList<Message> userMessageList = new ArrayList<>();
        db.collection("questions")
                .whereEqualTo("userId", mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String tempID = document.getId();
                                        //Message b = document.toObject(Message.class);
                                        Message m= new Message();
                                        m.setMessage(document.get("message").toString());
                                        m.setUserId(document.get("userId").toString());
                                        m.setUserType(document.get("userType").toString());

                                     //   b.setFbDocID(tempID);
                                        Log.i(TAG, document.getId() + " => " + m.toString());
                                        userMessageList.add(m);
                                    }
                                    questionsAdapter.setMessagesList(userMessageList);
                                } else {
                                    Log.i(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });

    }
}