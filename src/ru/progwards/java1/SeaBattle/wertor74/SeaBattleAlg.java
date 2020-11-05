package ru.progwards.java1.SeaBattle.wertor74;

import ru.progwards.java1.SeaBattle.SeaBattle;

import java.util.Arrays;

public class SeaBattleAlg {
    // Тестовое поле создаётся конструктором
    //     SeaBattle seaBattle = new SeaBattle(true);
    //
    // Обычное поле создаётся конструктором по умолчанию:
    //     SeaBattle seaBattle = new SeaBattle();
    //     SeaBattle seaBattle = new SeaBattle(false);
    //
    // Посомтреть результаты стрельбы можно в любой момент,
    // выведя объект класса SeaBattle на консоль. Например так:
    //     System.out.println(seaBattle);
    //
    //
    // Вид тестового поля:
    //
    //           0 1 2 3 4 5 6 7 8 9    координата x
    //         0|.|.|.|.|.|.|.|X|.|.|
    //         1|.|.|.|.|.|X|.|.|.|.|
    //         2|X|X|.|.|.|.|.|.|.|.|
    //         3|.|.|.|.|.|.|.|X|X|X|
    //         4|.|.|.|.|X|.|.|.|.|.|
    //         5|.|.|.|.|X|.|.|Х|.|.|
    //         6|.|.|.|.|.|.|.|Х|.|X|
    //         7|X|.|X|.|.|.|.|Х|.|X|
    //         8|X|.|.|.|.|.|.|X|.|.|
    //         9|X|.|.|.|X|.|.|.|.|.|
    static char [][] field; // своё поле для отмечания выстрелов

    static void init (SeaBattle seaBattle) {
        field = new char[seaBattle.getSizeY()][seaBattle.getSizeX()];
        for (int i = 0; i < seaBattle.getSizeY(); i++) {
            Arrays.fill(field[i], '0');
        }
    }
    public static void encircleDestroyed (int x, int y) { //обводим убитые корабли
        //System.out.println("x = " + x + ", y = " + y);
        if (y < 9) field[x][y + 1] = '.';
        if (x < 9) field[x + 1][y] = '.';
    }

    public void battleAlgorithm(SeaBattle seaBattle) {
        // пример алгоритма:
        // стрельба по всем квадратам поля полным перебором
        int hits = 0; // считаем количество подбитых клеток
        for (int y = 0; y < seaBattle.getSizeX(); y++) {
            for (int x = 0; x < seaBattle.getSizeY(); x++) {
                // проверяем пустая ячейка, или нет. Если не пустая стреляем в следующую
                if (field[x][y] != '0') continue;
                SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
                System.out.println(fireResult);
                switch (fireResult) {
                    case MISS:
                        field[x][y] = '*';
                        break;
                    case HIT:
                        field[x][y] = 'X';
                        hits++;
                        break;
                    case DESTROYED:
                        field[x][y] = 'X';
                        encircleDestroyed(x, y);
                        hits++;
                        //System.out.println("hits = " + hits);
                        if (hits >= 20) return;
                }
            }
        }
    }

    // функция для отладки
    public static void main(String[] args) {
        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        init(seaBattle);
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        System.out.println(seaBattle);
        for (int k = 0; k <= 9; k++) {
            for (int l = 0; l <= 9; l++) {
                if (l < 9) {
                    System.out.print(field[l][k]);
                } else {
                    System.out.println(field[l][k]);
                }
            }
        }

        System.out.println(seaBattle.getResult());
    }
}