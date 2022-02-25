import java.util.ArrayList;

public class TesterHelp {
    public static ArrayList<String> getTrees() {
        ArrayList<String> trees = new ArrayList<>();
        trees.add("\\-- a\n");
        trees.add("\\-- +\n    |-- a\n    \\-- b\n");
        trees.add("\\-- +\n    |-- a\n    \\-- b\n");
        trees.add("\\-- +\n    |-- a\n    \\-- *\n        |-- b\n        \\-- z\n");
        trees.add(new StringBuilder().append("\\-- +\n").append("    |-- a\n").append("    \\-- +\n").append(
                "        |-- +\n").append("        |   |-- a\n").append("        |   \\-- *\n").append(
                "        |       |-- b\n").append("        |       \\-- z\n").append("        \\-- +\n").append(
                "            |-- %\n").append("            |   |-- x\n").append("            |   \\-- u\n").append(
                "            \\-- *\n").append("                |-- p\n").append(
                "                \\-- x\n").toString());
        trees.add(new StringBuilder().append("\\-- +\n").append("    |-- a\n").append("    \\-- +\n").append(
                "        |-- +\n").append("        |   |-- +\n").append("        |   |   |-- a\n").append(
                "        |   |   \\-- *\n").append("        |   |       |-- b\n").append(
                "        |   |       \\-- z\n").append("        |   \\-- *\n").append("        |       |-- x\n").append(
                "        |       \\-- u\n").append("        \\-- *\n").append("            |-- p\n").append(
                "            \\-- x\n").toString());
        return trees;
    }

    public static void main(String[] args) {
        ArrayList<String> trees = getTrees();
        for (String x : trees) {
            System.out.println(x);
        }
    }
}
