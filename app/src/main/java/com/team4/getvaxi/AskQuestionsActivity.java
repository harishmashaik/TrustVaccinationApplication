package com.team4.getvaxi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team4.getvaxi.models.Message;
import com.team4.getvaxi.recycle.QuestionsAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AskQuestionsActivity extends AppCompatActivity {

  public static final String TAG = "AskQuestionsActivity";

  EditText txtMessage;
  Button buttonSend;

  Message messageSender = new Message();
  Message currentMessage = new Message();
  Boolean isAdmin = false;
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

    Intent i = getIntent();
    if (i.hasExtra("FROMCLC")) {
      Bundle data = i.getExtras();
      currentMessage = (Message) data.getSerializable("FROMCLC");
      isAdmin = true;
      ArrayList<Message> message = new ArrayList<>();
      message.add(currentMessage);
      System.out.println("The current mesg is " + currentMessage.toString());
      questionsAdapter.setMessagesList(message);

    } else {
      loaduserMessages();
    }

    buttonSend.setOnClickListener(v -> sendMessage());
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private void sendMessage() {
    if (isAdmin) {
      uploadMessages(currentMessage.getUserId(), "ADMIN", "ADMIN");
    } else {
      int index = mAuth.getCurrentUser().getEmail().indexOf("@gmail.com");
      String sName = (mAuth.getCurrentUser().getEmail().substring(0, index));
      uploadMessages(mAuth.getCurrentUser().getUid(), "USER", sName);
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private void uploadMessages(String uid, String type, String sendername) {

    messageSender.setUserId(uid);
    messageSender.setMessage(txtMessage.getText().toString());
    messageSender.setUserType(type);
    if (isAdmin) {
      messageSender.setMessageRepliedDateTime(LocalDateTime.now().toString());
      messageSender.setMessageDateTime(currentMessage.getMessageDateTime());
    } else {
      messageSender.setMessageDateTime(LocalDateTime.now().toString());
    }

    messageSender.setSenderName(sendername);

    db.collection("questions")
        .add(messageSender)
        .addOnSuccessListener(
            documentReference -> {
              Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
              postMessageSend();
            })
        .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
  }

  private void postMessageSend() {
    txtMessage.setText("");

    loaduserMessages();
  }

  private void loadAdminMessages() {
    ArrayList<Message> userMessageList = new ArrayList<>();
    db.collection("questions")
        .whereEqualTo("userId", mAuth.getCurrentUser().getUid())
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<QuerySnapshot>() {
              @RequiresApi(api = Build.VERSION_CODES.O)
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {
                    String tempID = document.getId();
                    Message m = document.toObject(Message.class);

                    Log.i(TAG, document.getId() + " => " + m.toString());
                    // Log.i(TAG, "temp is " + " => " + temp);

                    userMessageList.add(m);
                  }
                  questionsAdapter.setMessagesList(userMessageList);
                } else {
                  Log.i(TAG, "Error getting documents: ", task.getException());
                }
              }
            });
  }

  private void loaduserMessages() {
    String tempUUid;

    if (mAuth.getCurrentUser() != null) {
      tempUUid = mAuth.getCurrentUser().getUid();
    } else {
      tempUUid = currentMessage.getUserId();
    }

    ArrayList<Message> userMessageList = new ArrayList<>();
    db.collection("questions")
        .whereEqualTo("userId", tempUUid)
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<QuerySnapshot>() {
              @RequiresApi(api = Build.VERSION_CODES.O)
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {
                    String tempID = document.getId();
                    Message m = document.toObject(Message.class);

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
