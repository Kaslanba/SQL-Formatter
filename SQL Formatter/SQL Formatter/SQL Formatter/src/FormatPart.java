public class FormatPart {
    int i, n = 0, words, space = 0;
    final int KEY_WORD_COUNT_MAX = 4;
    String value = "";
    String[] SQL_string;
    final String NEW_LINE = "newLine";
    final String[] red = {"<>"};
    final String[] blue = {"[", "<=", ">=", "=", "<", ">", "]", "(", ")"};
    final String[] add_new_line = {",", ";"};
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    Entry_data data = new Entry_data();

    private int control_word(String word) {
        int check = data.control_commands_top(word);
        System.out.println(word);
        if (check == 1) {
            if (i > 0) addToValue(NEW_LINE);
            space = 0;
            addToValue(ANSI_PURPLE + word.toUpperCase() + ANSI_RESET);
            addToValue(NEW_LINE);
            space = 2;
//            System.out.println(word);
        }
        if (check == 2 && (i + n + 1) < words && n <= KEY_WORD_COUNT_MAX) {
            n++;
            String newWord = word + " " + SQL_string[i + n];
            check = control_word(newWord);
        }


        return check;
    }


    private void control_word2(String word) {
        int check = data.control_sql_new_line(word);
        if (check == 0) {

            addToValue(word);
            if(value.endsWith("\n"))  space = 2;else space=1;
        }
        if (check == 1) {

            addToValue(ANSI_YELLOW + word.toUpperCase() + ANSI_RESET);

            addToValue(NEW_LINE);
            space = 2;

        }
        if (check == 2 && (i + n + 1) < words && n <= KEY_WORD_COUNT_MAX) {
            n++;
            String newWord = word + " " + SQL_string[i + n];
            control_word2(newWord);
        }
        // return check;
    }


    public String format(String s) {

        for (int r = 0; r < red.length; r++)
            s = s.replace(red[r], " " + red[r] + " ");
        for (int r = 0; r < blue.length; r++) {
            s = s.replace(blue[r], " " + blue[r] + " ");

        }
        for (int r = 0; r < add_new_line.length; r++)
            s = s.replace(add_new_line[r], " "+ add_new_line[r] +" ");
        s = s.replace("<  =", "<=").replace(">  =", ">=").replace("<  >", "<>");
        s = s.replaceAll("\\s+", " ");

        SQL_string = s.split(" ");
        words = SQL_string.length;

        int parenthesis_value = 0;
        for (i = 0; i < SQL_string.length; i++) {

            if (SQL_string[i].isEmpty() || SQL_string[i].equals(" ") || SQL_string[i].equals("\n"))
                continue;
            int c = control_word(SQL_string[i].trim());
            if (c != 1) {
                n = 0;
                control_word2(SQL_string[i].trim());
            }
            i += n;
            n = 0;

            if (SQL_string[i].trim().equals("("))
                parenthesis_value += 1;
            else if (SQL_string[i].trim().equals(")")){
                if (parenthesis_value == 1) {
                    addToValue(NEW_LINE);
                    addToValue("");
                }
                parenthesis_value -= 1;
            }
        }
        return value;
    }


    private void addToValue(String word) {
       String type = word;
        for (int y = 0; y < space; y++)
            value += " ";

        switch (type) {
            case "newLine":
                value += "\n";
                break;

            default:
                if (!type.contains(ANSI_RESET))
                    type = colorWord(type);
                value += type;
                break;

        }
        if (type.contains(",") || type.contains(";")) addToValue(NEW_LINE);
    }

    private String colorWord(String word) {
        //StringBuilder ss = new StringBuilder(word);
        for (int k = 0; k < red.length; k++)
            if (word.contains(red[k]))
                return word.replace(red[k], ANSI_RED + red[k] + ANSI_RESET);
        for (int k = 0; k < blue.length; k++)
            if (word.contains(blue[k]))
                return word.replace(blue[k], ANSI_BLUE + blue[k] + ANSI_RESET);


        return word;
    }

    public String formatParenthesis(String st){
        int parenthesis_count = 0;
        int parenthesis_index = 0;
        int parenthesis_close_count = 0;
        char[] characterArray = st.toCharArray();

        String copyString1 = st.substring(0);

        for (int i = 0; i < characterArray.length - 1; i++){
            if (characterArray[i] == '('){
                parenthesis_count += 1;
                if (parenthesis_count == 1)
                    parenthesis_index = i;
                else if (parenthesis_count > 1)
                    copyString1 = st.substring(0, parenthesis_index) + "\u001B[32m" + "(" + st.substring(parenthesis_index + 1);
            }
            else if (characterArray[i] == ')'){
                parenthesis_close_count += 1;
                if (parenthesis_close_count == 2) {
                    parenthesis_index = i;
                    copyString1 = copyString1.substring(0, parenthesis_index) + "\u001B[32m" + ")" + copyString1.substring(parenthesis_index + 6);
                }
            }
        }
        return copyString1;
    }

}
