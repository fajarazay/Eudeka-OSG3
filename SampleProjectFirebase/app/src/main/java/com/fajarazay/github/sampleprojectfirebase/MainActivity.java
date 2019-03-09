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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.ClickMenuListener {
    private AlertDialog.Builder dialog;
    private LayoutInflater inflater;
    private View dialogView;

    private RecyclerView recyclerViewUser;
    private FloatingActionButton fabAddUser;

    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<>();
    private List<String> listKey = new ArrayList<>();
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private User user;
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

        addUserEventListener();
    }

    private void initView() {
        recyclerViewUser = findViewById(R.id.recyclerView);
        fabAddUser = findViewById(R.id.fabAddUser);
        fabAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddUser(false, 0);
            }
        });
    }

    private void showDialogAddUser(final boolean isUpdate, final int positionData) {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.add_data_user, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        final EditText editTextPhone = dialogView.findViewById(R.id.editTextPhone);
        Button buttonSave = dialogView.findViewById(R.id.buttonSave);

        if (isUpdate) {
            editTextName.setText(user.getName());
            editTextEmail.setText(user.getEmail());
            editTextPhone.setText(user.getPhone());
            dialog.setTitle("Update User");
        }
        dialog.setTitle("Add User");
        final AlertDialog alertDialog = dialog.show();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdate) {
                    updateUser(listKey.get(positionData),
                            editTextName.getText().toString().trim(),
                            editTextEmail.getText().toString().trim(),
                            editTextPhone.getText().toString().trim());
                } else {
                    createUser(editTextName.getText().toString().trim(),
                            editTextEmail.getText().toString().trim(),
                            editTextPhone.getText().toString().trim());
                }
                alertDialog.dismiss();
                addUserEventListener();
            }
        });
        alertDialog.show();
    }

    private void initAdapter() {
        userAdapter = new UserAdapter(userList, this);
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

    private void addUserEventListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                //Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                emptyDataList();

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    User user = childSnapshot.getValue(User.class);
                    String userKey = childSnapshot.getKey();

                    userList.add(user);
                    listKey.add(userKey);
                }

                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getMessage());
            }
        });

    }

    private void emptyDataList() {
        if (!userList.isEmpty()) {
            userList.clear();
        }

        if (!listKey.isEmpty()) {
            listKey.clear();
        }
    }

    private void updateUser(String keyUser, String name, String email, String phone) {
        databaseReference.child(keyUser).child("name").setValue(name);
        databaseReference.child(keyUser).child("email").setValue(email);
        databaseReference.child(keyUser).child("phone").setValue(phone);
    }

    private void deleteUser(String userKey) {
        databaseReference.child(userKey).removeValue();
        addUserEventListener();
    }

    @Override
    public void onClickMenu(ImageView view, final int position) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.update:
                        user = userList.get(position);
                        showDialogAddUser(true, position);
                        break;
                    case R.id.delete:
                        deleteUser(listKey.get(position));
                        break;
                }

                return true;
            }
        });

        popupMenu.show();
    }
}