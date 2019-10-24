package com.example.realtimeguestlist;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference myDatabaseReference;

    @BindView(R.id.name_edittext)
    public EditText nameEditText;

    @BindView(R.id.type_edittext)
    public EditText typeEditText;

    @BindView(R.id.extras_edittext)
    public EditText extrasEditText;

    @BindView(R.id.results_textview)
    public TextView myInfoTextView;

    @BindView(R.id.add_guest)
    public Button addGuestButton;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        handler = new Handler();

        myDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Guest");
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Guest readGuest = snapShot.getValue(Guest.class);
                    myInfoTextView.setText(myInfoTextView.getText() + "\n" + readGuest.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                TODO: Error handle in here
            }
        });
    }

    @OnClick(R.id.add_guest)
    public void addGuest(View view) {
        Guest newGuest = new Guest();
        newGuest.setName(nameEditText.getText().toString());
        newGuest.setType(typeEditText.getText().toString());
        newGuest.setExtras(Integer.parseInt(extrasEditText.getText().toString()));

        String key = myDatabaseReference.push().getKey();
        myDatabaseReference.child(key).setValue(newGuest);

    }
}
