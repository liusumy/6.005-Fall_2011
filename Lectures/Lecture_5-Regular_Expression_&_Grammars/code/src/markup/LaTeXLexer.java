package markup;

public class LaTeXLexer {
	private final String s;
	private int i;
	// s is the string of LaTeX text that we're parsing,
	// and s[i] is the start of the next token to return, or
	// i == s.length means we're at the end of parsing.
		
	public LaTeXLexer(String s) {
		this.s = s;
		this.i = 0;
	}
	
	public Token next() throws SyntaxErrorException {
		if (i >= s.length()) {
			return new Token(Type.EOF, "");
		}
		
		switch (s.charAt(i)) {
		case '{':
			i = i + 1;
			return new Token(Type.OPEN_BRACE, "{");
		case '}':
			i = i + 1;
			return new Token(Type.CLOSE_BRACE, "}");
		case '\\':
			i = i + 1;
			if (s.startsWith("em", i)) {
				i = i + 2;
				return new Token(Type.EM, "}");
			} else {
				throw new SyntaxErrorException("syntax error at" + s.substring(i));
			}
		default:
			// It's a TEXT token, find where it ends.
			int start = i;
			while ((i < s.length()) &&
				   ("{}\\".indexOf(s.charAt(i)) != -1)) {
				i = i + 1;
			}
			int end = i;
			return new Token(Type.TEXT, s.substring(start, end));
		}
	}
}
