package com.example.game15;


import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;


@SuppressLint("MissingInflatedId")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDisabledButtons();//Отображаем клетки с числами пятнашек без обработчика клика
                              //для красоти

        Button button = findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                start_game();
            }
        });
    }

    public void start_game() {
        Game game = new Game();
        GridLayout grid = findViewById(R.id.game_grid);
        if (grid == null) return;

        grid.removeAllViews();//
        game.init();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {

                int value = game.getValue(j, i);
                if (value == 0) continue;

                Button btn = createButton(value);

                GridLayout.Spec row = GridLayout.spec(i);
                GridLayout.Spec col = GridLayout.spec(j);

                GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                lp.setMargins(10, 10, 10, 10);
                grid.addView(btn, lp);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Button btn = (Button) view;
                        int value = Integer.parseInt((String) btn.getText());

                        Game.Coord coord = game.go(value);

                        if (coord.isValid()) {

                            GridLayout.Spec row = GridLayout.spec(coord.x);
                            GridLayout.Spec col = GridLayout.spec(coord.y);

                            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                            btn.setLayoutParams(lp);

                            if (game.isWin()) {
                                WinDialogFragment dialog = new WinDialogFragment();
                                dialog.show(getSupportFragmentManager(), "custom");
                                showDisabledButtons();
                            }
                        }
                    }
                });
            }
        }
    }

    private Button createButton(int value) {
        Button btn = new Button(this);
        btn.setText(Integer.toString(value));

        if (getScreenOrientation().hashCode() == "PORTRAIT".hashCode()) {
            btn.setWidth(140);
            btn.setHeight(270);
        } else if (getScreenOrientation().hashCode() == "LANDSCAPE".hashCode()) {
            btn.setWidth(85);
            btn.setHeight(210);
        }
        else {
            btn.setWidth(140);
            btn.setHeight(270);
        }

        return btn;
    }

    private String getScreenOrientation() {//Проверка ориентации устройства
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return "PORTRAIT";
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return "LANDSCAPE";
        return "";
    }

    private void showDisabledButtons(){
        int num = 0;

        GridLayout grid = findViewById(R.id.game_grid);
        if (grid == null) return;
        grid.removeAllViews();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (num != 15) num++;
                else continue;

                Button btn = createButton(num);

                GridLayout.Spec row = GridLayout.spec(i);
                GridLayout.Spec col = GridLayout.spec(j);

                GridLayout.LayoutParams lp = new GridLayout.LayoutParams(row, col);
                lp.setMargins(10, 10, 10, 10);
                grid.addView(btn, lp);
            }
        }
    }


}