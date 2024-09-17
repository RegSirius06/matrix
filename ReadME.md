# Documentation:

This program is a matrix calculator that allows you to perform operations on matrices and numbers.

## Commands:

* matrix - creates and manipulates matrices.
  * new zeros [rows] [columns] initAs [name] - creates a new matrix with zeros of the specified dimensions and assigns it the given name.
  * new ones [rows] [columns] initAs [name] - creates a new matrix with ones of the specified dimensions and assigns it the given name.
  * new sameNumbers [rows] [columns] [number] initAs [name] - creates a new matrix with all elements set to the specified number and assigns it the given name.
  * new identity [size] initAs [name] - creates a new identity matrix of the specified size and assigns it the given name.
  * new [rows] [columns] initAs [name] - creates a new matrix by prompting the user to enter elements and assigns it the given name.
  * [matrixName] putIn [newName] - creates a copy of the specified matrix and assigns it the new name.
  * [matrixName] determinant putIn [newName] - calculates the determinant of the specified matrix and assigns it the new name.
  * [matrixName] multiplyBy [matrixName2] putIn [newName] - multiplies the two specified matrices and assigns the result to the new name.
  * [matrixName] multiplyByDigit [numberName] putIn [newName] - multiplies the specified matrix by the specified number and assigns the result to the new name.
  * [matrixName] get [row] [column] putIn [newName] - retrieves the element at the specified row and column from the matrix and assigns it the new name.
  * [matrixName] getRows putIn [newName] - retrieves the number of rows in the specified matrix and assigns it the new name.
  * [matrixName] getColumns putIn [newName] - retrieves the number of columns in the specified matrix and assigns it the new name.
  * [matrixName] set [row] [column] [numberName] - sets the element at the specified row and column in the matrix to the specified number.
  * [matrixName] - displays the elements of the specified matrix.

* number - creates and manipulates numbers.
  * new [number] initAs [name] - creates a new number and assigns it the given name.
  * [numberName] putIn [newName] - creates a copy of the specified number and assigns it the new name.
  * [numberName] - displays the value of the specified number.

* names - displays a list of all matrices and numbers stored in the program.

* forget - deletes a matrix or number.
    * matrix [name] - deletes the matrix with the specified name.
    * number [name] - deletes the number with the specified name.

* exit - exits the program.

* help - displays a list of available commands and examples.

## Example Commands:

```bash
matrix new zeros 3 3 initAs A
number new 5.2 initAs myNumber
A multiplyByDigit myNumber putIn C
matrix C
number myNumber
names
forget C
exit
```

## Notes:

* Matrix names and number names can be any string without spaces.
* You can use the name `__buffer__` to store temporary results.
* Numbers can be entered as decimals or fractions (e.g., 3.14 or 1/2).

