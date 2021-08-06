package com.team4.getvaxi;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.team4.getvaxi.models.Message;
import com.team4.getvaxi.recycle.CLCQuestionsAdapter;

import java.util.ArrayList;

public class CLCQuestionsActivity extends AppCompatActivity {

    public static final String TAG = "CLCQuestionsActivity";

    RecyclerView listOfQuestions;
    final CLCQuestionsAdapter questionsAdapterCLC = new CLCQuestionsAdapter();
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_l_c_questions);
        db = FirebaseFirestore.getInstance();

        listOfQuestions = findViewById(R.id.clc_questionsList);
        listOfQuestions.setHasFixedSize(false);
        listOfQuestions.setLayoutManager(new LinearLayoutManager(this));
        listOfQuestions.setAdapter(questionsAdapterCLC);
        
        loadAllNewQuestions();


    }

    private void loadAllNewQuestions() {

        ArrayList<Message> userMessageList = new ArrayList<>();
        db.collection("questions")
                .whereEqualTo("userType", "USER")
                .get()
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String tempID = document.getId();
                                    Message m = document.toObject(Message.class);//
                                    Log.i(TAG, document.getId() + " => " + m.toString());
                                    userMessageList.add(m);
                                }
                                questionsAdapterCLC.setMessages(userMessageList);
                            } else {
                                Log.i(TAG, "Error getting documents: ", task.getException());
                            }
                        });
    }
}