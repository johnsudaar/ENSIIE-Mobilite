package fr.johnsudaar.moviie.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.adapters.FriendAdapter;
import fr.johnsudaar.moviie.models.User;

public class FriendsActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        TextView noFriends = (TextView) findViewById(R.id.no_friends);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler_view);

        User me = Configuration.get().getLoggedInUser();
        if(me.getFriends().length == 0 ){
            noFriends.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            noFriends.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new FriendAdapter(Configuration.get().getLoggedInUser().getFriends()));
        }
    }
}
