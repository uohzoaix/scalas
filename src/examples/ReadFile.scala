package examples

import scala.reflect.io.File
import java.util.Scanner

object ReadFile {

  def withScanner(f: File, op: Scanner => Unit) = {
    val scanner = new Scanner(f.bufferedReader)
    try {
      while (scanner.hasNext()) {
        op(scanner)
      }
    } finally {
      scanner.close()
    }
  }

  def split(str: => String) = {
    val strs = str.split("\\|");
    for (item <- strs) {
      println(item)
    }
  }

  def main(args: Array[String]) {
    withScanner(File("test.txt"),
      scanner => split(scanner.next()))
  }
}