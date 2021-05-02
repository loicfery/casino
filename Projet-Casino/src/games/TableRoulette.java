package games;

public class TableRoulette {

    public static Cell[][] table;

    static final int WIDTH = 3;
    static final int HEIGHT = 13;

    static public Cell getCell(int row, int col) {
        return TableRoulette.table[row][col];
    }

    static {
        table = new Cell[HEIGHT][WIDTH];

    }

    public static RowColumn getRowColumn(int number) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == 0 && j == 2) //null cell
                    continue;
                if (number == table[i][j].number)
                    return new RowColumn(i, j);
            }
        }
        return null;
    }

    public static RowColumn getRowColumnForm(int number) {
        return new RowColumn(getRowForm(number), getColumnForm(number));
    }

    public static int getRowForm(int number) {
        return number % 3 == 0 ? number / 3 : number / 3 + 1;
    }

    public static int getColumnForm(int number) {
        return number - (3 * (getRowForm(number) - 1)) - 1;
    }

    static public void populateTable() {
        Cell cell0 = new Cell(0, Color.Green);
        Cell cell1 = new Cell(1, Color.Red);
        Cell cell2 = new Cell(2, Color.Black);
        Cell cell3 = new Cell(3, Color.Red);
        Cell cell4 = new Cell(4, Color.Black);
        Cell cell5 = new Cell(5, Color.Red);
        Cell cell6 = new Cell(6, Color.Black);
        Cell cell7 = new Cell(7, Color.Red);
        Cell cell8 = new Cell(8, Color.Black);
        Cell cell9 = new Cell(9, Color.Red);
        Cell cell10 = new Cell(10, Color.Black);
        Cell cell11 = new Cell(11, Color.Black);
        Cell cell12 = new Cell(12, Color.Red);
        Cell cell13 = new Cell(13, Color.Black);
        Cell cell14 = new Cell(14, Color.Red);
        Cell cell15 = new Cell(15, Color.Black);
        Cell cell16 = new Cell(16, Color.Red);
        Cell cell17 = new Cell(17, Color.Black);
        Cell cell18 = new Cell(18, Color.Red);
        Cell cell19 = new Cell(19, Color.Red);
        Cell cell20 = new Cell(20, Color.Black);
        Cell cell21 = new Cell(21, Color.Red);
        Cell cell22 = new Cell(22, Color.Black);
        Cell cell23 = new Cell(23, Color.Red);
        Cell cell24 = new Cell(24, Color.Black);
        Cell cell25 = new Cell(25, Color.Red);
        Cell cell26 = new Cell(26, Color.Black);
        Cell cell27 = new Cell(27, Color.Red);
        Cell cell28 = new Cell(28, Color.Black);
        Cell cell29 = new Cell(29, Color.Black);
        Cell cell30 = new Cell(30, Color.Red);
        Cell cell31 = new Cell(31, Color.Black);
        Cell cell32 = new Cell(32, Color.Red);
        Cell cell33 = new Cell(33, Color.Black);
        Cell cell34 = new Cell(34, Color.Red);
        Cell cell35 = new Cell(35, Color.Black);
        Cell cell36 = new Cell(36, Color.Red);

        TableRoulette.table[0][1] = cell0;
        TableRoulette.table[1][0] = cell1;
        TableRoulette.table[1][1] = cell2;
        TableRoulette.table[1][2] = cell3;
        TableRoulette.table[2][0] = cell4;
        TableRoulette.table[2][1] = cell5;
        TableRoulette.table[2][2] = cell6;
        TableRoulette.table[3][0] = cell7;
        TableRoulette.table[3][1] = cell8;
        TableRoulette.table[3][2] = cell9;
        TableRoulette.table[4][0] = cell10;
        TableRoulette.table[4][1] = cell11;
        TableRoulette.table[4][2] = cell12;
        TableRoulette.table[5][0] = cell13;
        TableRoulette.table[5][1] = cell14;
        TableRoulette.table[5][2] = cell15;
        TableRoulette.table[6][0] = cell16;
        TableRoulette.table[6][1] = cell17;
        TableRoulette.table[6][2] = cell18;
        TableRoulette.table[7][0] = cell19;
        TableRoulette.table[7][1] = cell20;
        TableRoulette.table[7][2] = cell21;
        TableRoulette.table[8][0] = cell22;
        TableRoulette.table[8][1] = cell23;
        TableRoulette.table[8][2] = cell24;
        TableRoulette.table[9][0] = cell25;
        TableRoulette.table[9][1] = cell26;
        TableRoulette.table[9][2] = cell27;
        TableRoulette.table[10][0] = cell28;
        TableRoulette.table[10][1] = cell29;
        TableRoulette.table[10][2] = cell30;
        TableRoulette.table[11][0] = cell31;
        TableRoulette.table[11][1] = cell32;
        TableRoulette.table[11][2] = cell33;
        TableRoulette.table[12][0] = cell34;
        TableRoulette.table[12][1] = cell35;
        TableRoulette.table[12][2] = cell36;
    }

}