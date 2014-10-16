package calculator;

/*
 * Legitimate tokens for a calculator parser:
 * 
 * 	'6', '6.25', ... (any positive number)
 * 	'pt'	(point)
 * 	'in'	(inch)
 * 	'+'		(plus)
 * 	'-'		(minus)
 * 	'*'		(multiply)
 * 	'/'		(divide)
 * 	'('		(open parentheses)
 * 	')'		(close parentheses)
 */

/**
 * Token types:
 * 
 * 	SCALAR,			// any positive number
 * 	POINT, 			// unit 'pt'
 *	INCH,			// unit 'in'
 *	PLUS,			// arithmetic operator '+'
 *	MINUS,			// arithmetic operator '-'
 *	MULTIPLY,		// arithmetic operator '*'
 *	DIVIDE,			// arithmetic operator '/'
 *	OPEN_PAREN,		// open parentheses '('
 *	CLOSE_PAREN		// close parentheses ')'
 *
 */
enum Type {
	SCALAR,
	POINT,
	INCH,
	PLUS,
	MINUS,
	MULTIPLY,
	DIVIDE,
	OPEN_PAREN,
	CLOSE_PAREN,
}