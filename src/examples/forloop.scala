package examples

object forloop {

    def queens(n: Int): List[List[Int]] = {
        def placeQueens(k: Int): List[List[Int]] =
            if (k == 0) List(List())
            else for {
                queens <- placeQueens(k - 1)
                column <- List.range(1, n + 1)
                if isSafe(column, queens, 1)
            } yield column :: queens
        placeQueens(n)
    }

    def isSafe(col: Int, queens: List[Int], delta: Int): Boolean =
        false

    case class Book(title: String, authors: List[String])
    val books: List[Book] = List(
        Book("Structure and Interpretation of Computer Programs", List("Abelson, Harold", "Sussman, Gerald J.")),
        Book("Principles of Compiler Design", List("Aho, Alfred", "Ullman, Jeffrey")),
        Book("Programming in Modula-2", List("Wirth, Niklaus")),
        Book("Introduction to Functional Programming", List("Bird, Richard")),
        Book("The Java Language Specification", List("Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad")))
    def main(args: Array[String]) {
        for (b <- books; a <- b.authors if a startsWith "Ullman") yield b.title
        for (b <- books if (b.title indexOf "Program") >= 0) yield b.title
        for (
            b1 <- books; b2 <- books if b1 != b2;
            a1 <- b1.authors; a2 <- b2.authors if a1 == a2
        ) yield a1
    }
}