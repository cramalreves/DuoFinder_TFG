package com.example.duofinder_tfg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener{
    private SwipeStack swipeStack;
    private SwipeAdapter adapter;
    private ImageView fav, clear;
    private ArrayList<Usuario> users;
    private TextView textView;
    private BottomBar bottomBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //swipeStack=getView().findViewById(R.id.swipeStack);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        swipeStack=(SwipeStack) rootView.findViewById(R.id.swipeStack);
        fav = (ImageView) rootView.findViewById(R.id.imageButton4);
        clear = (ImageView) rootView.findViewById(R.id.imageButton2);
        textView = (TextView) rootView.findViewById(R.id.hola);
        users=getUsers();
        adapter= new SwipeAdapter(this.getActivity(), users);
        swipeStack.setAdapter(adapter);

        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                clear.setImageResource(R.drawable.ic_clear_red);
                textView.setText("adios");
            }

            @Override
            public void onViewSwipedToRight(int position) {

            }

            @Override
            public void onStackEmpty() {

            }
        });

       // Inflate the layout for this fragment
        return rootView;
    }

    private ArrayList <Usuario> getUsers(){
        ArrayList<Usuario> list = new ArrayList<>();
        list.add(new Usuario(R.drawable.icon8,"Stellaa37", "Jungle", "Challenger", "Vi", "Kha'Zix", "Camille", false));
        list.add(new Usuario(R.drawable.icon3,"ZeKroX24", "Mid", "Iron 4", "Ekko", "Sylas", "Fizz", false));
        list.add(new Usuario(R.drawable.icon2,"SKT Faker", "Mid", "Challenger", "Zed", "Twisted Fate", "Kassadin", false));
        list.add(new Usuario(R.drawable.icon1,"G2 Perkz", "ADCarry", "Challenger", "Kai'Sa", "Xayah", "Aphelios", false));
        list.add(new Usuario(R.drawable.icon3,"G2 Ibai", "Top", "Master", "Renekton", "Rumble", "Riven", false));
        return list;
    }

    public void like( View view){
        swipeStack.onViewSwipedToRight();
    }

    public void dislike (View view){
        swipeStack.onViewSwipedToLeft();
    }

    @Override
    public void onSwipeStart(int position) {

    }

    @Override
    public void onSwipeProgress(int position, float progress) {

    }

    @Override
    public void onSwipeEnd(int position) {

    }

    @Override
    public void onViewSwipedToLeft(int position) {

    }

    @Override
    public void onViewSwipedToRight(int position) {

    }

    @Override
    public void onStackEmpty() {

    }
}
