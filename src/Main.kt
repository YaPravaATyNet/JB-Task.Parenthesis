fun main() {
    val converter = Converter()
    println(converter.addBrackets("abcde"))
    println(converter.addBrackets("abcde", outParen = true))
    println(converter.addBrackets("abcdef"))
    println(converter.addBrackets("abcdef", parenInMiddle = true))
    println(converter.addBrackets("abcdefgh", true, true, Bracket.PARENTHESIS, Bracket.BRACKET, Bracket.BRACE))
}