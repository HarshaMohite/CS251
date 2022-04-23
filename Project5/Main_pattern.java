public class Main_pattern {

    /**
     * Finds a word in a matrix.
     * @param crossword
     * @param target
     * @return an array of length 3.
     */
    public static int[] find(final char[][] crossword, final String target) {
        /*
            get row/column/diagonal
            for each:
                concatenate two times
                search
                reverse
                search
                find index from returned value
         */

        // for rows
        for (int i = 0; i < crossword.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < crossword.length; j++) {
                sb.append(crossword[i][j]);
            }
            sb.append(sb);
            // search this

            sb.reverse();
            // search this
        }

        // for columns
        for (int i = 0; i < crossword.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < crossword.length; j++) {
                sb.append(crossword[j][i]); // add the column's values
            }
            sb.append(sb);
            // search this

            sb.reverse();
            // search this
        }

        // top left, bottom right diagonals
        for (int i = 0; i < crossword.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < crossword.length; j++) {
                sb.append(crossword[((i + j) % crossword.length)][j]); // rows, then columns
            }
            sb.append(sb);
            // search this

            sb.reverse();
            // search this
        }

        // top right, bottom left diagonals
        for (int i = 0; i < crossword.length; i++) {
            StringBuilder sb = new StringBuilder();
            int offset = 0;
            for (int j = crossword.length - 1; j >= 0; j--) {
                sb.append(crossword[j][((i + offset) % crossword.length)]);
                offset++;
            }
            sb.append(sb);
            // search this

            sb.reverse();
            // search this
        }

        return null;
    }
}
