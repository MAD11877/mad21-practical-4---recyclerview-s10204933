package sg.edu.np.practical4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private ArrayList<User> userList = initUserList();
    private RecyclerView recyclerView;
    private UserAdapter.RecyclerViewClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        setOnClickListener();
        UserAdapter mAdapter = new UserAdapter(userList, clickListener);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    private void setOnClickListener() {
        clickListener = new UserAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                ProfileAlertDialog(userList.get(position));
            }
        };
    }

    private void ProfileAlertDialog(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage("MADness");
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProfilePageIntent(user);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void ProfilePageIntent(User user) {
        Intent intent = new Intent(ListActivity.this, ProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private ArrayList<User> initUserList() {
        Random rand = new Random();
        ArrayList<User> userList = new ArrayList<User>();
        for (int i = 0; i < 20; ++i) {
            User user = new User("Name-" + rand.nextInt(10000000),
                    "Description-" + rand.nextInt(10000000),
                    rand.nextBoolean());
            userList.add(user);
        }
        return userList;
    }
}