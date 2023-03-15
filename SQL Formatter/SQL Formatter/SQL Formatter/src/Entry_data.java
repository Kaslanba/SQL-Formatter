import java.util.Arrays;
import java.util.List;

public class Entry_data {


    final List<String> sql_commands_top =
            Arrays.asList(
                    "create",
                    "create Table",
                    "union",
                    "intersect",
                    "if",
                    "then",
                    "else",
                    "ADD",
                    "EXCEPT",
                    "FROM",
                    "HAVING",
                    "INSERT",
                    "LIMIT",
                    "SELECT",
                    "SET",
                    "UPDATE",
                    "VALUES",
                    "WHERE",
                    "ALTER COLUMN",
                    "ALTER TABLE",
                    "DELETE FROM",
                    "GROUP BY",
                    "INSERT INTO",
                    "ORDER BY");


    final List<String> sql_new_line =
            Arrays.asList(
                    "table",
                    "any",
                    "as",
                    "min",
                    "max",
                    "index",
                    "primary key",
                    "values",
                    "like",
                    "check",
                    "AND",
                    "ELSE",
                    "OR",
                    "WHEN",
                    // joins
                    "JOIN",
                    "INNER JOIN",
                    "LEFT JOIN",
                    "LEFT OUTER JOIN",
                    "RIGHT JOIN",
                    "RIGHT OUTER JOIN",
                    "CROSS JOIN",
                    "NATURAL JOIN",
                    // non-standard joins
                    "STRAIGHT_JOIN",
                    "NATURAL LEFT JOIN",
                    "NATURAL LEFT OUTER JOIN",
                    "NATURAL RIGHT JOIN",
                    "NATURAL RIGHT OUTER JOIN");



    public int control_commands_top(String word) {
        for (String s : sql_commands_top) {
            if (s.equalsIgnoreCase(word))
                return 1;
            if (s.toLowerCase().contains(word.toLowerCase()))
                return 2;
        }

        return 0;
    }

    public int control_sql_new_line(String word) {
        for (String s : sql_new_line) {
            if (s.equalsIgnoreCase(word))
                return 1;
            if (s.toLowerCase().contains(word.toLowerCase()))
                return 2;
        }

        return 0;
    }





}
