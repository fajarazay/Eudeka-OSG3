package com.fajarazay.github.sampleprojectfirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder dialog;
    private LayoutInflater inflater;
    private View dialogView;

    private RecyclerView recyclerViewUser;
    private FloatingActionButton fabAddUser;

    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<>();

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();

        firebaseDatabase = FirebaseDatabase.getInstance();

        //referensi ke node users
        databaseReference = firebaseDatabase.getReference("users");

        Log.d(MainActivity.class.getSimpleName(), "database: " + databaseReference);
        //menyimpan referensi node app title
        firebaseDatabase.getReference("app_title").setValue("Eudeka Realtime Database");

        addUserListener();
    }

    private void initView() {
        recyclerViewUser = findViewById(R.id.recyclerView);
        fabAddUser = findViewById(R.id.fabAddUser);
        fabAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddUser();
            }
        });
    }

    private void showDialogAddUser() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.add_data_user, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Add User");

        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        final EditText editTextPhone = dialogView.findViewById(R.id.editTextPhone);
        Button buttonSave = dialogView.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser(editTextName.getText().toString().trim(),
                        editTextEmail.getText().toString().trim(),
                        editTextPhone.getText().toString().trim());
            }
        });
        dialog.show();
    }

    private void initAdapter() {
        userAdapter = new UserAdapter(userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewUser.setLayoutManager(layoutManager);
        recyclerViewUser.setAdapter(userAdapter);
    }

    private void createUser(String name, String email, String phone) {
        User user = new User(name, email, phone);
        String key = databaseReference.push().getKey();
        if (key != null) {
            databaseReference.child(key).setValue(user);
        }
    }

    private void addUserListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                if (!userList.isEmpty()) {
                    userList.clear();
                }

                while (iterator.hasNext()) {

                    User user = iterator.next().getValue(User.class);
                    Log.d(MainActivity.class.getSimpleName(), "user name: " + user.getName());
                    userList.add(user);
                }

                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getMessage());
            }
        });

    }

}