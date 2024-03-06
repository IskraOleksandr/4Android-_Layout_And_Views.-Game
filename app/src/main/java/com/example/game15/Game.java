package com.example.game15;

import java.util.Random;

public class Game {
    private int[][] gameField = {//було так
            {1, 2, 3, 4},//        1,2,3,4
            {5, 6, 7, 8},//      5, 6, 7, 8
            {9, 10, 11, 12},//   9, 10, 0, 12
            {13, 14, 15, 0}// 13, 14, 11, 15
    };

    private int[][] winGameField = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    public static class Coord {

        public int x;
        public int y;

        public Coord() {
            this(-1, -1);
        }

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isValid() {

            return x > -1 && x < 4 && y > -1 && y < 4;
        }
    }


    public Game() {

        init();
    }

    public void mixArray() {
        Random rand = new Random();
        int dir;
        int i = 0;
        int j = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (gameField[i][j] == 0) break;
            }
            if (j == 4) j--;
            if (gameField[i][j] == 0 && j < 4) {
                break;
            }
        }
        for (int cycle = 200; cycle > 0; cycle--) {
            dir = rand.nextInt(4) + 1;
            if (dir == 1) {
                if (i >= 0 && i < 3) {
                    gameField[i][j] = gameField[i + 1][j];
                    gameField[i + 1][j] = 0;
                    i++;
                } else continue;
            } else if (dir == 2) {
                if (i <= 3 && i > 0) {
                    gameField[i][j] = gameField[i - 1][j];
                    gameField[i - 1][j] = 0;
                    i--;
                } else continue;
            } else if (dir == 3) {
                if (j >= 0 && j < 3) {
                    gameField[i][j] = gameField[i][j + 1];
                    gameField[i][j + 1] = 0;
                    j++;
                } else continue;
            } else if (dir == 4) {
                if (j <= 3 && j > 0) {
                    gameField[i][j] = gameField[i][j - 1];
                    gameField[i][j - 1] = 0;
                    j--;
                } else continue;
            }
        }
    }

    public void init() {
        Random rand = new Random();
//криво
        for (int i = gameField.length - 1; i > 0; i--) {
            for (int j = gameField[i].length - 1; j > 0; j--) {
                int rand_i = rand.nextInt(i + 1);
                int rand_j = rand.nextInt(j + 1);

                int temp = gameField[i][j];
                gameField[i][j] = gameField[rand_i][rand_j];
                gameField[rand_i][rand_j] = temp;
            }
        }
        //TO DO: Home Work
    }

    public boolean isWin() {//TO DO: Home Work
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (gameField[i][j] != winGameField[i][j])
                    return false;
            }
        }
        return true;
    }

    public int getValue(int x, int y) {

        return gameField[y][x];
    }

    public Coord findValue(int value) {

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (gameField[j][i] == value) {

                    return new Coord(j, i);
                }
            }
        }
        return new Coord();
    }

    public Coord go(int value) {

        Coord zeroCoord = findValue(0);
        Coord coord = findValue(value);

        if (Math.abs(zeroCoord.x - coord.x) + Math.abs(zeroCoord.y - coord.y) != 1) {

            return new Coord();
        }
        gameField[zeroCoord.x][zeroCoord.y] = value;
        gameField[coord.x][coord.y] = 0;

        return zeroCoord;
    }
}