package com.example.philipphiri.homelessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * user profile activity
 */
public class UserProfileActivity extends AppCompatActivity {
    //private Button claimsButton;
    //private ImageView userPicture;
    private TextView userName;
    private TextView userEmail;
    //private TextView residence;
    private TextView curresidence;
    //private TextView claims;
    private TextView numClaims;
    //private TextView religion;
    private TextView religionIs;
    //private static DatabaseReference userData;
    private final User u = MainPageActivity.getCurrentUser();

    //private FirebaseAuth user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Button claimsButton;
//        ImageView userPicture;
//        TextView residence;
//        TextView claims;
//        TextView religion;

        claimsButton = findViewById(R.id.releaseButton);
        //userPicture = findViewById(R.id.userImageView); //make button for image setting later
        userName = findViewById(R.id.nameTextView);
        userEmail = findViewById(R.id.emailTextView);
        //residence = findViewById(R.id.residencyTextView);
        //claims = findViewById(R.id.numClaims);
        curresidence = findViewById(R.id.residencySetTextView);
        numClaims = findViewById(R.id.claimsSetTextView);
        //religion = findViewById(R.id.religionTextView);
        religionIs = findViewById(R.id.religionSetTextView);

        //release claims and set residence back to None
        final DatabaseReference userData;
        userData = FirebaseDatabase.getInstance().getReference().child("Users");

        claimsButton.setOnClickListener(new View.OnClickListener() {
            //String user_id = user.getCurrentUser().getUid();
            final DatabaseReference current_user
                    = userData.child(WelcomePageActivity.getCurrentUser());

            @Override
            public void onClick(View view) {
                current_user.child("ShelterRegistered").setValue("none");
                current_user.child("NumberClaimed").setValue("0");
                //releasing crashes when emulator is restarted and the first thing you do is try to
                // release a previous claim
                //but is fine in between logins during same emulator usage

                //ShelterListActivity.release(MainPageActivity.getCurrentUser().getNumClaims());
                ShelterListActivity.release(u.getNumClaims());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        curresidence.setText(u.getUserResidence());
        userName.setText(u.getUserName());
        numClaims.setText(u.getNumClaims());
        userEmail.setText(u.getUserEmail());
        userName.setText(u.getUserName());
        religionIs.setText(u.getUserReligion());
    }
}
