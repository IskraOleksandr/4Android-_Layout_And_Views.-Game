package com.example.game15;


import static com.google.android.material.internal.ContextUtils.getActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


@SuppressLint("MissingInflatedId")
public class MainActivity extends AppCompatActivity {

    Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.game_grid);
        if(grid==null) return;

        grid.removeAllViews();
        //game.init();
game.mixArray();
        for(int i = 0; i < 4; ++i){
            for(int j = 0; j < 4; ++j){

                int value = game.getValue(j, i);
                if(value == 0) continue;

                Button btn = new Button(this);
                btn.setText(Integer.toString(value));
                btn.setHeight(300);

                GridLayout.Spec row = GridLayout.spec(i);
                GridLayout.Spec col = GridLayout.spec(j);

                GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                lp.setMargins(10, 10, 10, 10);
                grid.addView(btn, lp);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Button btn = (Button) view;
                        int value = Integer.parseInt((String)btn.getText());

                        Game.Coord coord = game.go(value);

                        if(coord.isValid()){

                            GridLayout.Spec row = GridLayout.spec(coord.x);
                            GridLayout.Spec col = GridLayout.spec(coord.y);

                            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                            btn.setLayoutParams(lp);

                            if(game.isWin()){
                                CustomDialogFragment dialog = new CustomDialogFragment();
                                Bundle args = new Bundle();
                                args.putString("win", "Вы победили");
                                dialog.setArguments(args);
                                dialog.show(getSupportFragmentManager(), "custom");
                            }
                        }

                    }
                });
            }
        }
    }

}