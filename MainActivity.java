package com.group4.joseph.firebasereadingdata1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private ArrayList<String> mUsernames = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();
    private ListView userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = (ListView)findViewById(R.id.userList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsernames);
        userList.setAdapter(arrayAdapter);


        mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = mDatabase.getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {     //This is called when the activity is created
                String value = dataSnapshot.getValue(String.class);     //The DB values will be stored in 'value'
                mUsernames.add(value);                  //Add the value to the list

                String key = dataSnapshot.getKey();
                mKeys.add(key);

                arrayAdapter.notifyDataSetChanged();    //Notify the adapter that the list has changed
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {   //This will run when any of the data in the DB gets changed
                String changed_value = dataSnapshot.getValue(String.class);     //Get value of changed item
                String key = dataSnapshot.getKey();     //Get key of changed item

                int index = mKeys.indexOf(key);         //Get index of this key

                mUsernames.set(index, changed_value);           //Update the username at this index with the new value

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {     //Runs when data is removed from the DB

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {     //Runs when data is moved from the DB

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {      //Runs when there is an error

            }
        });

    }
}
