/**
 * Author: dnj, Hank Huang
 * Date: March 7, 2009
 * 6.005 Elements of Software Construction
 * (c) 2007-2009, MIT 6.005 Staff
 */
package sudoku;

import java.io.IOException;

import sat.env.Environment;
import sat.env.Variable;
import sat.formula.Formula;
import utils.FileUtils;

/**
 * Sudoku is an immutable abstract datatype representing instances of Sudoku.
 * Each object is a partially completed Sudoku puzzle.
 */
public class Sudoku {
    // dimension: standard puzzle has dim 3
    private final int dim;
    // number of rows and columns: standard puzzle has size 9
    private final int size;
    // known values: square[i][j] represents the square in the ith row and jth
    // column,
    // contains 0 if the digit is not present, else k (1 <= k <= size) to represent
    // the digit k
    private final int[][] square;
    // occupies [i,j,k] means that kth (1 <= k <= size) symbol occupies entry in
    // row i, column j
    private final Variable[][][] occupies;

    // Rep invariants:
    //		(1) dim*dim == size
    //			the number of rows and columns are: dim*dim
    //		(2) 2 <= dim <= 3
    //			the puzzle has at least dim of 2 and at most 3
    //		(3) 0 <= square[i][j] <= 9
    //			the puzzle is either filled with digits 1-9 or
    //			left blank
    //
    // Abstract Function:
    //		The slot square[i][j] represents the ith row and jth column in the
    //		sudoku puzzle,
    //			square[i][j] == 0 if the slot is empty, else
    //			square[i][j] == k (1 <= k <= 9) to represent the digit k
    //
    //		For example, if the sudoku puzzle contains
    //
    //				+---+---+---+---+
    //				|   |   |   |   |
    //				+---+---+---+---+
    //				| 3 |   |   |   |
    //				+---+---+---+---+
    //				|   |   | 4 |   |
    //				+---+---+---+---+
    //				|   | 2 | 3 |   |
    //				+---+---+---+---+
    //
    //		then, the corresponding two dimensional array is
    //
    //			  { { 0, 0, 0, 0 },
    //			    { 3, 0, 0, 0 },
    //			    { 0, 0, 4, 0 },
    //			    { 0, 2, 3, 0 } }
    //		
    private void checkRep() {
    	assert (this.dim * this.dim) == size :
    		"SudokuPuzzle, Rep invariant: the number of rows and columns "
    		+ "are: dim*dim";
    	assert ((this.dim >= 2) && (this.dim <= 3)) :
    		"SudokuPuzzle, Rep invariant: the puzzle has at least dim of 2 "
    		+ "and at most dim of 3";
    	for (int i = 0; i < size; i = i + 1) {
    		for (int j = 0; j < size; j = j + 1) {
    			assert ((square[i][j] >= 0) && (square[i][j] <= 9)) :
    	    		"SudokuPuzzle, Rep invariant: the slots are either filled "
    	    		+ "with digits 1-9 or left blank";
    		}
    	}
    }

    /**
     * create an empty Sudoku puzzle of dimension dim.
     * 
     * @param dim
     *            size of one block of the puzzle. For example, new Sudoku(3)
     *            makes a standard Sudoku puzzle with a 9x9 grid.
     */
    public Sudoku(int dim) {
    	this.dim = dim;
    	size = dim * dim;
        int[][] sq = new int[size][size];
        for (int i = 0; i < size; i = i + 1) {
        	for (int j = 0; j < size; j = j + 1) {
        		sq[i][j] = 0;
        	}
        }
        this.square = sq;
        this.occupies = initializeVariables(size);
        checkRep();
    }

    /**
     * create Sudoku puzzle
     * 
     * @param square
     *            digits or blanks of the Sudoku grid. square[i][j] represents
     *            the square in the ith row and jth column, contains 0 for a
     *            blank, else i to represent the digit i. So { { 0, 0, 0, 1 }, {
     *            2, 3, 0, 4 }, { 0, 0, 0, 3 }, { 4, 1, 0, 2 } } represents the
     *            dimension-2 Sudoku grid: 
     *            
     *            ...1 
     *            23.4 
     *            ...3
     *            41.2
     * 
     * @param dim
     *            dimension of puzzle Requires that dim*dim == square.length ==
     *            square[i].length for 0<=i<(dim^2 - 1).
     */
    public Sudoku(int dim, int[][] square) {
        this.dim = dim;
        size = dim * dim;
        this.square = square;
        this.occupies = initializeVariables(size);
        checkRep();
    }
    
    // initialize the occupies[i][j][k] variables in Sudoku's representation
    private Variable[][][] initializeVariables(int size) {
    	Variable[][][] vars = new Variable[size][size][size];
    	for (int i = 0; i < size; i = i + 1) {
        	for (int j = 0; j < size; j = j + 1) {
        		for (int k = 0; k < size; k = k + 1) {
        			Variable var = new Variable(
        					"occupies(" + i + "," + j + "," + k + ")");
        			vars[i][j][k] = var;
        		}
        	}
        }
    	return vars;
    }

    /**
     * Reads in a file containing a Sudoku puzzle.
     * 
     * @param dim
     *            Dimension of puzzle. Requires: at most dim of 3, because
     *            otherwise need different file format
     * @param filename
     *            of file containing puzzle. The file should contain one line
     *            per row, with each square in the row represented by a digit,
     *            if known, and a period otherwise. With dimension dim, the file
     *            should contain dim*dim rows, and each row should contain
     *            dim*dim characters.
     * @return Sudoku object corresponding to file contents
     * @throws IOException
     *             if file reading encounters an error
     * @throws ParseException
     *             if file has error in its format
     */
    public static Sudoku fromFile(int dim, String filename) throws IOException,
            ParseException {
    	String fileContents = FileUtils.fetch(filename);
    	int size = dim * dim;
    	char[][] charMatrix = convertStringToCharMatrix(fileContents);
    	int[][] digitMatrix = new int[size][size];
    	for (int i = 0; i < size; i = i + 1) {
    		for (int j = 0; j < size; j = j + 1) {
    			if (charMatrix[i][j] == '.') {
    				digitMatrix[i][j] = 0;
    			} else {
    				assert (Character.getNumericValue(charMatrix[i][j]) >= 1) &&
    					(Character.getNumericValue(charMatrix[i][j]) <= 9) :
    						"fromFile: only digits 1-9, '.' and '\\n' are legal"
    						+ " in the text representation of Sudoku puzzle.";
    				digitMatrix[i][j] = Character.getNumericValue(charMatrix[i][j]);
    			}
    		}
    	}
    	return new Sudoku(dim, digitMatrix);
    }
    
    // convert a string into a matrix of characters
    // each row in the matrix corresponds to a substring partitioned
    // by the character '\n'
    private static char[][] convertStringToCharMatrix(String text) {
    	String[] lines = text.split("\\n");
    	int size = lines.length;
    	// System.out.println("lines: " + size);
    	char[][] charMatrix = new char[size][size];
    	for (int i = 0; i < size; i = i + 1) {
    		for (int j = 0; j < size; j = j + 1) {
    			charMatrix[i][j] = lines[i].charAt(j);
    		}
    	}
    	return charMatrix;
    }

    /**
     * Exception used for signaling grammatical errors in Sudoku puzzle files
     */
    @SuppressWarnings("serial")
    public static class ParseException extends Exception {
        public ParseException(String msg) {
            super(msg);
        }
    }

    /**
     * Produce readable string representation of this sudoku grid, e.g. for a 4
     * x 4 sudoku problem: 
     *   12.4 
     *   3412 
     *   2.43 
     *   4321
     * 
     * @return a string corresponding to this grid
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i = i + 1) {
        	for (int j = 0; j < size; j = j + 1) {
        		if (square[i][j] == 0) {
        			sb.append('.');
        		} else {
        			checkRep(); // assert that 1 <= square[i][j] <= 9
        			sb.append(square[i][j]);
        		}
        	}
        	sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * @return a SAT problem corresponding to the puzzle, using variables with
     *         names of the form occupies(i,j,k) to indicate that the kth symbol
     *         occupies the entry in row i, column j
     */
    public Formula getProblem() {

        // TODO: implement this.
        throw new RuntimeException("not yet implemented.");
    }

    /**
     * Interpret the solved SAT problem as a filled-in grid.
     * 
     * @param e
     *            Assignment of variables to values that solves this puzzle.
     *            Requires that e came from a solution to this.getProblem().
     * @return a new Sudoku grid containing the solution to the puzzle, with no
     *         blank entries.
     */
    public Sudoku interpretSolution(Environment e) {

        // TODO: implement this.
        throw new RuntimeException("not yet implemented.");
    }

}
