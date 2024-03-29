package com.example.kimparkjoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterAccept_main extends AppCompatActivity {

    private FirebaseDatabase userDatabase, getDatabase, setDatabase, friendDatabase;
    private DatabaseReference userDatabaseReference, getReference, setReference, friendDatabaseReference;
    private String uid, profile, name, message, email, path;
    private int number = 0;
    private static ArrayList<AchieveItemList> arrayList = new ArrayList<AchieveItemList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_accept_main);

        //현재 유저의 uid 받아오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            name = Register_main.name;
        }

        message = "null";
        profile = "https://firebasestorage.googleapis.com/v0/b/memorymate-d8aa5.appspot.com/o/profile_sample.png?alt=media&token=b2428a1a-fcd2-4e9e-a229-13dcecd709ff";

        path = MainActivity.encodeUserEmail(email);

        friendDatabaseReference = friendDatabase.getInstance().getReference();
        userDatabaseReference = userDatabase.getInstance().getReference();
        setReference = setDatabase.getInstance().getReference();

        friendDatabaseReference.child("user").child(path).child("friendNum").setValue(number);

        PersonItemList myProf = new PersonItemList(profile, name, message);
        userDatabaseReference.child("user").child(path).child("profile").setValue(myProf);

        arrayList = new ArrayList<>();

        getDatabase = FirebaseDatabase.getInstance();
        getReference = getDatabase.getReference("achieve");
        getReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    String temp = Integer.toString(i);

                    AchieveItemList achieveList = dataSnapshot.getValue(AchieveItemList.class); // 만들어뒀던 User 객체에 데이터를 담는다.

                    String achievement = achieveList.getAchievement();
                    Boolean check = achieveList.getCheck();

                    System.out.println("achieve is " + achievement + "check is " + check);

                    AchieveItemList achieve = new AchieveItemList(achievement, check);

                    setReference.child("user").child(path).child("achievement").child(temp).setValue(achieve);
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("TestActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        Button accept_button = (Button) findViewById(R.id.btn_register_accept);

        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login_main.class);

                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        //배경 클릭시 꺼지는거 막음
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        //안드로이드 백버튼 막음
        return ;
    }
}