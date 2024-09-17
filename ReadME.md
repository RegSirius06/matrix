# Documentation:

This program is a matrix calculator that allows you to perform operations on matrices and numbers.

##  Matrix Calculator - User Manual

This program allows you to perform various operations on matrices and numbers.

###  Commands:

**1.  Matrix Operations:**

  * **`matrix new [zeros|ones|sameNumbers|identity|...] <parameters> [initAs <name>]`:** Creates a new matrix.
    *  **`zeros`:** Creates a matrix filled with zeros.
    *  **`ones`:** Creates a matrix filled with ones.
    *  **`sameNumbers <number>`:** Creates a matrix filled with a specific number.
    *  **`identity <size>`:** Creates an identity matrix of the given size.
    *  **`...`:** Creates a matrix by inputting its elements.
    *  **`<parameters>`:** For `zeros`, `ones`, `sameNumbers`: `rows columns`. For `identity`: `size`. For inputting elements: `rows columns`
    *  **`initAs <name>`:** Assigns a name to the created matrix.  If omitted, the matrix is assigned the temporary name "__buffer__".

  * **`matrix <name> [determinant|addTo|multiplyBy|multiplyByDigit|getRows|getColumns|power|transposition|inverse|getWithoutRow|getWithoutColumn|minor|algebraicComplements|get|set|...] [<parameters>] [putIn <name>]`** : Performs operations on an existing matrix.

    *  **`determinant`:** Calculates the determinant of the matrix.
    *  **`addTo <matrix name>`:** Adds the given matrix to the current matrix.
    *  **`multiplyBy <matrix name>`:** Multiplies the current matrix by the given matrix.
    *  **`multiplyByDigit <number name>`:** Multiplies the current matrix by the given number.
    *  **`getRows`:** Returns the number of rows in the matrix.
    *  **`getColumns`:** Returns the number of columns in the matrix.
    *  **`power <power>`:** Raises the matrix to the given power.
    *  **`transposition`:** Returns the transposed matrix.
    *  **`inverse`:** Returns the inverse of the matrix.
    *  **`getWithoutRow <row number>`:** Returns a new matrix without the specified row.
    *  **`getWithoutColumn <column number>`:** Returns a new matrix without the specified column.
    *  **`minor <row number> <column number>`:** Calculates the minor of the matrix at the given row and column.
    *  **`algebraicComplements <row number> <column number>`:** Calculates the algebraic complement of the matrix at the given row and column.
    *  **`get <row number> <column number>`:** Returns the element at the given row and column.
    *  **`set <row number> <column number> <number name>`:** Sets the element at the given row and column to the specified number.
    *  **`<parameters>`:**  Depends on the specific operation (e.g., `matrix name` for `addTo`, `power` for `power`, etc.).
    *  **`putIn <name>`:** Assigns the result of the operation to the given name.

  * **`matrix <name>`:** Prints the matrix with its current name.

**2.  Number Operations:**

  * **`number new <number> [initAs <name>]`:** Creates a new number.

    * **`<number>`:** The number to be stored. Can be a decimal or fraction.
    * **`initAs <name>`:** Assigns a name to the created number.  If omitted, the number is assigned the temporary name "__buffer__".

  * **`number <name> [putIn <name>]`:**  Copies a number to another name.

  * **`putIn <name>`:** Assigns the copied number to the given name.

  * **`number <name>`:** Prints the number with its current name.

**3.  General Commands:**

*   **`help`:** Displays a list of available commands.
*   **`names`:** Prints a list of currently stored matrices and numbers.
*   **`forget <matrix|number> <name>`:** Removes the specified matrix or number.
*   **`exit`:** Exits the program.

**Example Usage:**

```
> matrix new zeros 2 3 initAs A
> matrix new ones 3 3 initAs B
> matrix A multiplyBy B putIn C
> matrix C
> number new 2.5 putIn x
> matrix C multiplyByDigit x
> number new 1/2 initAs frac
> number frac
> forget matrix C
> exit
```

**Note:** The program uses a case-insensitive approach to command and parameter names.

## Notes:

* Matrix names and number names can be any string without spaces.
* You can use the name `__buffer__` to store or get temporary results.
* Numbers can be entered as decimals or fractions (e.g., 3.14 or 1/2).
