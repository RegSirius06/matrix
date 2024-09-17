package net.regsirius06.matrix.ui;

import net.regsirius06.matrix.core.Matrix;
import net.regsirius06.matrix.utils.Parsers;
import net.regsirius06.matrix.utils.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class UI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Matrix> matrixMap = new HashMap<>(){{
        put("__buffer__", null);
    }};
    private static final Map<String, Double> doubleMap = new HashMap<>(){{
        put("__buffer__", null);
    }};
    private static final String done = TextFormatter.GREEN.format("Done!");

    private UI() {
    }

    public static @NotNull Matrix inputMatrix() {
        TextFormatter.outConsole(
                "Enter the matrix dimensions (e.g. 3x4; \"x\" is the English letter X): ",
                TextFormatter.GREEN
        );
        return inputMatrix(Parsers.dimensionParser(scanner.next()));
    }

    public static @NotNull Matrix inputMatrix(@NotNull Pair<Integer, Integer> dimension) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= dimension.first(); i++) {
            TextFormatter.outConsole(
                    "Enter a space-separated " + dimension.second() + " line elements " + i +
                            " (e.g. \"" + "1 ".repeat(dimension.second() - 1) + "1"
                            + "\"): ",
                    TextFormatter.GREEN
            );
            builder.append(scanner.nextLine()).append("\n");
        }
        return Parsers.matrixParser(builder.toString());
    }

    public static void input() {
        TextFormatter.outConsole("Hello! Let's start :)", TextFormatter.BLUE);
        loop:
        while (true) {
            System.out.print("> ");
            String[] data = Parsers.commandParser(scanner.nextLine());
            try {
                switch (data[0]) {
                    case "exit":
                        TextFormatter.outConsole("Goodbye!\n(Input anything to exit...)", TextFormatter.BLUE);
                        scanner.nextLine();
                        break loop;
                    case "help":
                        printHelp();
                        continue;
                    case "names":
                        TextFormatter.outConsole(getNames());
                        TextFormatter.outConsole(done);
                        continue;
                    case "forget":
                        if (Objects.equals(data[1], "matrix")) {
                            matrixMap.remove(data[2]);
                        } else if (Objects.equals(data[1], "number")) {
                            doubleMap.remove(data[1]);
                        } else {
                            break;
                        }
                        TextFormatter.outConsole(done);
                        continue;
                    case "matrix":
                        if (Objects.equals(data[1], "new")) {
                            if (Objects.equals(data[2], "zeros")) {
                                Pair<Integer, Integer> p = Parsers.dimensionParser(data[3]);
                                String name;
                                if (data.length == 6 && Objects.equals(data[4], "initAs")) {
                                    name = data[5];
                                } else {
                                    name = "__buffer__";
                                }
                                matrixMap.put(name, Matrix.zeros(p.first(), p.second()));
                            } else if (Objects.equals(data[2], "ones")) {
                                Pair<Integer, Integer> p = Parsers.dimensionParser(data[3]);
                                String name;
                                if (data.length == 6 && Objects.equals(data[4], "initAs")) {
                                    name = data[5];
                                } else {
                                    name = "__buffer__";
                                }
                                matrixMap.put(name, Matrix.ones(p.first(), p.second()));
                            } else if (Objects.equals(data[2], "sameNumbers")) {
                                Pair<Integer, Integer> p = Parsers.dimensionParser(data[3]);
                                String name;
                                if (data.length == 7 && Objects.equals(data[5], "initAs")) {
                                    name = data[6];
                                } else {
                                    name = "__buffer__";
                                }
                                matrixMap.put(name, Matrix.sameNumbers(p.first(), p.second(), Double.parseDouble(data[4])));
                            } else if (Objects.equals(data[2], "identity")) {
                                int p = Integer.parseInt(data[3]);
                                String name;
                                if (data.length == 6 && Objects.equals(data[4], "initAs")) {
                                    name = data[5];
                                } else {
                                    name = "__buffer__";
                                }
                                matrixMap.put(name, Matrix.identityMatrix(p));
                            } else {
                                Pair<Integer, Integer> p = Parsers.dimensionParser(data[2]);
                                String name;
                                if (data.length == 5 && Objects.equals(data[3], "initAs")) {
                                    name = data[4];
                                } else {
                                    name = "__buffer__";
                                }
                                matrixMap.put(name, inputMatrix(p));
                            }
                        } else {
                            if (data.length == 4 && Objects.equals(data[2], "putIn")) {
                                matrixMap.put(data[3], matrixMap.get(data[1]).copy());
                            } else if (data.length > 2) {
                                if (Objects.equals(data[2], "determinant")) {
                                    double det = matrixMap.get(data[1]).determinant();
                                    if (data.length == 5 && Objects.equals(data[3], "putIn")) {
                                        doubleMap.put(data[4], det);
                                    }
                                    TextFormatter.outConsole(
                                            det,
                                            TextFormatter.BLUE
                                    );
                                } else if (Objects.equals(data[2], "multiplyBy")) {
                                    Matrix m = matrixMap.get(data[1]).multiplyBy(matrixMap.get(data[3]));
                                    if (data.length == 6 && Objects.equals(data[4], "putIn")) {
                                        matrixMap.put(data[5], m);
                                    }
                                    TextFormatter.outConsole(
                                            m.view(),
                                            TextFormatter.BLUE
                                    );
                                } else if (Objects.equals(data[2], "multiplyByDigit")) {
                                    Matrix m = matrixMap.get(data[1]).multiplyBy(doubleMap.get(data[3]));
                                    if (data.length == 6 && Objects.equals(data[4], "putIn")) {
                                        matrixMap.put(data[5], m);
                                    }
                                    TextFormatter.outConsole(
                                            m.view(),
                                            TextFormatter.BLUE
                                    );
                                } else if (Objects.equals(data[2], "get")) {
                                    double x = matrixMap.get(data[1]).get(Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                                    if (data.length == 7 && Objects.equals(data[5], "putIn")) {
                                        doubleMap.put(data[6], x);
                                    }
                                    TextFormatter.outConsole(
                                            x,
                                            TextFormatter.BLUE
                                    );
                                } else if (Objects.equals(data[2], "getRows")) {
                                    int x = matrixMap.get(data[1]).getRows();
                                    if (data.length == 5 && Objects.equals(data[3], "putIn")) {
                                        doubleMap.put(data[4], (double) x);
                                    }
                                    TextFormatter.outConsole(
                                            x,
                                            TextFormatter.BLUE
                                    );
                                } else if (Objects.equals(data[2], "getColumns")) {
                                    int x = matrixMap.get(data[1]).getColumns();
                                    if (data.length == 5 && Objects.equals(data[3], "putIn")) {
                                        doubleMap.put(data[4], (double) x);
                                    }
                                    TextFormatter.outConsole(
                                            x,
                                            TextFormatter.BLUE
                                    );
                                } else if (Objects.equals(data[2], "set")) {
                                    matrixMap.get(data[1]).set(
                                            Integer.parseInt(data[3]),
                                            Integer.parseInt(data[4]),
                                            doubleMap.get(data[5])
                                    );
                                } else {
                                    break;
                                }
                            } else {
                                TextFormatter.outConsole(
                                        data[1] + ":\n" + matrixMap.get(data[1]),
                                        TextFormatter.BLUE
                                );
                            }
                        }
                        TextFormatter.outConsole(done);
                        continue;
                    case "number":
                        if (Objects.equals(data[1], "new")) {
                            String name;
                            if (data.length == 5 && Objects.equals(data[3], "initAs")) {
                                name = data[4];
                            } else {
                                name = "__buffer__";
                            }
                            try {
                                doubleMap.put(name, Double.parseDouble(data[2]));
                            } catch (Exception e) {
                                doubleMap.put(name, Parsers.fractionsParser(data[2]));
                            }
                        } else {
                            if (data.length == 4 && Objects.equals(data[2], "putIn")) {
                                doubleMap.put(data[3], doubleMap.get(data[1]));
                            } else {
                                TextFormatter.outConsole(
                                        data[1] + ":\n" + doubleMap.get(data[1]),
                                        TextFormatter.BLUE
                                );
                            }
                        }
                        TextFormatter.outConsole(done);
                        continue;
                }
            } catch (NullPointerException e) {
                TextFormatter.outConsole(
                        "I can't solve this!",
                        TextFormatter.RED);
                continue;
            } catch (NoSuchElementException | IllegalArgumentException e) {
                TextFormatter.outConsole(
                        "I can't solve this! (" + e.getMessage() + ")",
                        TextFormatter.RED);
                continue;
            } catch (Exception ignored) {
                /*TextFormatter.outConsole(
                        ignored.getMessage(),
                        TextFormatter.RED);
                continue;*/
            }
            TextFormatter.outConsole(
                    "Sorry, I don't understand you...\n" +
                            "(You can ask for 'help'!)",
                    TextFormatter.YELLOW
            );
        }
    }

    private static @NotNull String getNames() {
        StringBuilder builder = new StringBuilder();
        builder.append(TextFormatter.PURPLE.format("\nMatrices:\n"));
        for (String i : matrixMap.keySet()) {
            builder.append(TextFormatter.WHITE.format(i + "\n"));
        }
        builder.append(TextFormatter.PURPLE.format("\nNumbers:\n"));
        for (String i : doubleMap.keySet()) {
            builder.append(TextFormatter.WHITE.format(i + "\n"));
        }
        return builder.toString();
    }

    private static void printHelp() {
        TextFormatter.outConsole("\n---Available commands:---", TextFormatter.BLUE);
        TextFormatter.outConsole("**matrix**", TextFormatter.WHITE);
        TextFormatter.outConsole("   * new zeros [rows] [columns] initAs [name] - create a new matrix with zeros", TextFormatter.WHITE);
        TextFormatter.outConsole("   * new ones [rows] [columns] initAs [name] - create a new matrix with ones", TextFormatter.WHITE);
        TextFormatter.outConsole("   * new sameNumbers [rows] [columns] [number] initAs [name] - create a new matrix with given number", TextFormatter.WHITE);
        TextFormatter.outConsole("   * new identity [size] initAs [name] - create a new identity matrix", TextFormatter.WHITE);
        TextFormatter.outConsole("   * new [rows] [columns] initAs [name] - create a new matrix by entering numbers", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] putIn [newName] - copy matrix", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] determinant putIn [newName] - calculate determinant", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] multiplyBy [matrixName2] putIn [newName] - multiply two matrices", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] multiplyByDigit [numberName] putIn [newName] - multiply matrix by number", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] get [row] [column] putIn [newName] - get element from matrix", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] getRows putIn [newName] - get number of rows", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] getColumns putIn [newName] - get number of columns", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] set [row] [column] [numberName] - set element in matrix", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [matrixName] - show matrix", TextFormatter.WHITE);
        TextFormatter.outConsole("**number**", TextFormatter.WHITE);
        TextFormatter.outConsole("   * new [number] initAs [name] - create a new number", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [numberName] putIn [newName] - copy number", TextFormatter.WHITE);
        TextFormatter.outConsole("   * [numberName] - show number", TextFormatter.WHITE);
        TextFormatter.outConsole("**names**", TextFormatter.WHITE);
        TextFormatter.outConsole("   * show all names of matrices and numbers", TextFormatter.WHITE);
        TextFormatter.outConsole("**forget**", TextFormatter.WHITE);
        TextFormatter.outConsole("   * matrix [name] - delete matrix", TextFormatter.WHITE);
        TextFormatter.outConsole("   * name [name] - delete number", TextFormatter.WHITE);
        TextFormatter.outConsole("**exit**", TextFormatter.WHITE);
        TextFormatter.outConsole("   * exit the program", TextFormatter.WHITE);
        TextFormatter.outConsole("**help**", TextFormatter.WHITE);
        TextFormatter.outConsole("   * show this help", TextFormatter.WHITE);
        TextFormatter.outConsole("\n---Example commands:---", TextFormatter.BLUE);
        TextFormatter.outConsole("matrix new zeros 3 3 initAs A", TextFormatter.WHITE);
        TextFormatter.outConsole("number new 5.2 initAs myNumber", TextFormatter.WHITE);
        TextFormatter.outConsole("A multiplyByDigit myNumber putIn C", TextFormatter.WHITE);
        TextFormatter.outConsole("C", TextFormatter.WHITE);
        TextFormatter.outConsole("names", TextFormatter.WHITE);
        TextFormatter.outConsole("forget C", TextFormatter.WHITE);
        TextFormatter.outConsole("exit", TextFormatter.WHITE);
        TextFormatter.outConsole("", TextFormatter.BLUE);
    }
}
