import java.lang.StringBuilder

class Converter {
    private var str = ""
    private var sequenceOfBkt = arrayOf(Bracket.PARENTHESIS)
    private var ind = 0

    private fun init(str: String, vararg sequenceOfBkt: Bracket) {
        ind = 0
        this.str = str
        this.sequenceOfBkt = if (sequenceOfBkt.isEmpty()) {
            arrayOf(Bracket.PARENTHESIS)
        } else {
            arrayOf(*sequenceOfBkt)
        }
    }

    fun addBrackets(
        str: String,
        outParen: Boolean = false,
        parenInMiddle: Boolean = false,
        vararg sequenceOfBkt: Bracket
    ): String {
        init(str, *sequenceOfBkt)
        val builder = StringBuilder()
        var middle = (str.length - 1) / 2
        if (outParen) {
            builder.append(openBkt())
        }
        for (i in 0 until middle) {
            builder.append(str[i])
            builder.append(openBkt())
        }
        builder.append(str[middle])
        if (str.length % 2 == 0) {
            if (parenInMiddle) {
                builder.append(openBkt())
            } else {
                middle++
                builder.append(str[middle])
            }
        }
        decreaseIndex()
        for (i in middle + 1 until str.length) {
            builder.append(closeBkt())
            builder.append(str[i])
        }
        if (outParen) {
            builder.append(closeBkt())
        }
        return builder.toString()
    }

    private fun openBkt(): String {
        val res = matchOpenBkt(sequenceOfBkt[ind])
        increaseIndex()
        return res
    }

    private fun closeBkt(): String {
        val res = matchCloseBkt(sequenceOfBkt[ind])
        decreaseIndex()
        return res
    }

    private fun matchOpenBkt(bracket: Bracket): String {
        return when (bracket) {
            Bracket.PARENTHESIS -> "("
            Bracket.BRACKET -> "["
            Bracket.BRACE -> "{"
        }
    }

    private fun matchCloseBkt(bracket: Bracket): String {
        return when (bracket) {
            Bracket.PARENTHESIS -> ")"
            Bracket.BRACKET -> "]"
            Bracket.BRACE -> "}"
        }
    }

    private fun increaseIndex() {
        ind = (ind + 1) % sequenceOfBkt.size
    }

    private fun decreaseIndex() {
        ind = (ind - 1 + sequenceOfBkt.size) % sequenceOfBkt.size
    }
}