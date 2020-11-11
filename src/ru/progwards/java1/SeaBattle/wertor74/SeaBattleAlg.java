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

    public void battleAlgorithm(SeaBattle seaBattle) {
        // своё поле для отмечания выстрелов
        char [][] field = new char[seaBattle.getSizeY()][seaBattle.getSizeX()];
        for (int i = 0; i < seaBattle.getSizeY(); i++) {
            Arrays.fill(field[i], '0');
        }
        class Miss { // Локальный класс для обработки "мимо"
            Miss(int x, int y) {
                field[x][y] = '*';
            }
        }
        class Destroyed { // Локальный класс для обработки "убит"
            Destroyed(int x, int y, int len, boolean isVertical, boolean isHorizontal) {
                field[x][y] = 'X';
                len++;
                if (len == 0) { // обводим однопалубный
                    if (y < 9) {
                        switch (x) {
                            case 0:
                                field[x + 1][y] = '.';
                                field[x + 1][y +1] = '.';
                                field[x][y + 1] = '.';
                                break;
                            case 9:
                                field[x][y + 1] = '.';
                                field[x - 1][y + 1] = '.';
                                break;
                            default:
                                field[x + 1][y] = '.';
                                field[x + 1][y + 1] = '.';
                                field[x][y + 1] = '.';
                                field[x - 1][y + 1] = '.';
                        }
                    } else {
                        if (x < 9) field[x + 1][y] = '.';
                    }
                }
                if (isHorizontal == true) { // многопалубный, если горизонтальный
                    switch (y) {
                        case 9:
                            if (x < 9) field[x + 1][y] = '.';
                            break;
                        default:
                            if (x == 9) {
                                for (int i = 0; i <= len; i++) {
                                    if (field[x - i][y + 1] == '0')field[x - i][y + 1] = '.';
                                }
                            } else if (x - (len - 1) == 0) {
                                field[x + 1][y] = '.';
                                for (int i = 0; i <= len; i++) {
                                    if (field[i][y + 1] == '0') field[i][y + 1] = '.';
                                }
                            } else {
                                field[x + 1][y] = '.';
                                field[x + 1][y + 1] = '.';
                                for (int i = 0; i <= len; i++) {
                                    if (field[x - i][y + 1] == '0') field[x - i][y + 1] = '.';
                                }
                            }
                    }
                } else { // многопалубный, если вертикальный
                    switch (y) {
                        case 9:
                            if (x == 0) {
                                for (int i = 0; i <= (len - 1); i++) {
                                    field[x + 1][y - i] = '.';
                                }
                            } else if (x == 9) {
                                for (int i = 0; i <= (len - 1); i++) {
                                    if (field[x - 1][y - i] == '0') field[x - 1][y - i] = '.';
                                }
                            } else {
                                for (int i = 0; i <= (len - 1); i++) {
                                    if (field[x - 1][y - i] == '0') field[x - 1][y - i] = '.';
                                    if (field[x + 1][y - i] == '0') field[x + 1][y - i] = '.';
                                }
                            }
                            break;
                        default:
                            if (x == 0) {
                                if (field[x][y + 1] == '0') field[x][y + 1] = '.';
                                if (field[x + 1][y + 1] == '0') field[x + 1][y + 1] = '.';
                                for (int i = 0; i <= (len - 1); i++) {
                                    if (field[x + 1][y - i] == '0') field[x + 1][y - i] = '.';
                                }
                            } else if (x == 9) {
                                if (field[x][y + 1] == '0') field[x][y + 1] = '.';
                                if (field[x - 1][y + 1] == '0') field[x - 1][y + 1] = '.';
                                for (int i = 0; i <= (len - 1); i++) {
                                    if (field[x - 1][y - i] == '0') field[x - 1][y - i] = '.';
                                }
                            } else {
                                if (field[x - 1][y + 1] == '0') field[x - 1][y + 1] = '.';
                                if (field[x][y + 1] == '0') field[x][y + 1] = '.';
                                if (field[x + 1][y +1] == '0') field[x + 1][y +1] = '.';
                                for (int i = 0; i <= (len - 1); i++) {
                                    if (field[x - 1][y - i] == '0') field[x - 1][y - i] = '.';
                                    if (field[x + 1][y - i] == '0') field[x + 1][y - i] = '.';
                                }
                            }
                    }
                }
            }
        }
        class Hit { // Локальный класс для обработки "ранил"
            Hit(int x, int y, int len, boolean isVertical, boolean isHorizontal) {
                field[x][y] = 'X';
                len ++;
                if (y < 9) {
                    switch (seaBattle.fire(x, y + 1)) {
                        case MISS:
                            field[x][y + 1] = '*';
                            isHorizontal = true;
                            int i = 1;
                            while (seaBattle.fire(x + i, y) == SeaBattle.FireResult.HIT) {
                                field[x + i][y] = 'X';
                                len++;
                                i++;
                            }
                            new Destroyed(x + i, y, len, isVertical, isHorizontal);
                            break;
                        case HIT:
                            field[x][y + 1] = 'X';
                            len++;
                            isVertical = true;
                            int j = 2;
                            while (seaBattle.fire(x, y + j) == SeaBattle.FireResult.HIT) {
                                field[x][y + j] = 'X';
                                len++;
                                j++;
                            }
                                new Destroyed(x, y + j, len, isVertical, isHorizontal);
                            break;
                        case DESTROYED:
                            new Destroyed(x, y + 1, len, isVertical, isHorizontal);
                    }
                } else {
                    isHorizontal = true;
                    int i = 1;
                    while (seaBattle.fire(x + i, y) == SeaBattle.FireResult.HIT) {
                        field[x + i][y] = 'X';
                        len++;
                        i++;
                    }
                    new Destroyed(x + i, y, len, isVertical, isHorizontal);
                }
            }
        }
        int hits = 0; // считаем количество подбитых кораблей
        int len = 0; // длинна корабля
        boolean isVertical = false; // положение раненного корабля (вертикальный)
        boolean isHorizontal = false; // положение корабля (горизонтальный)
        // пример алгоритма:
        // стрельба по всем квадратам поля полным перебором
        for (int y = 0; y < seaBattle.getSizeX(); y++) {
            for (int x = 0; x < seaBattle.getSizeY(); x++) {
                // проверяем пустая ячейка, или нет. Если не пустая стреляем в следующую
                if (field[x][y] != '0') continue;
                SeaBattle.FireResult fireResult = seaBattle.fire(x, y);
                System.out.println(seaBattle);
                switch (fireResult) {
                    case MISS:
                        new Miss(x, y);
                        break;
                    case HIT:
                        hits++;
                        new Hit(x, y, len, isVertical, isHorizontal);
                        // проверяем количество подбитых кораблей
                        if (hits >= 10) return;
                        break;
                    case DESTROYED:
                        hits++;
                        // проверяем количество подбитых кораблей
                        new Destroyed(x, y, len, isVertical, isHorizontal);
                        if (hits >= 10) return;
                }
            }
        }
    }

    // функция для отладки
    public static void main(String[] args) {
        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle();
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        System.out.println(seaBattle.getResult());
    }
}