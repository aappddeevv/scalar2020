// devel: amm --watch sk.sc mda.xml
import scala.util.chaining._
import scala.util._
import java.io._
import java.nio.file._

val ofile = "skregs.csv"
val cs = java.nio.charset.StandardCharsets.UTF_8
def clean(i: String) = i.trim.stripPrefix("\n")
def clean_reg(i: String) = i.trim.replaceAll("\""," ").replaceAll("\n", "\\n")
    .trim.stripLineEnd.stripPrefix("\n")

@main def parse_xml(file: String): Unit = {
    println(s"Converting $file => csv.")
    val toplevel = scala.xml.XML.load(file)
    val act = (toplevel \ "title" \ "number").text pipe clean
    Using(new PrintWriter(Files.newBufferedWriter(Paths.get(ofile)))) { out =>
        out.println("section,subject,content")
        (toplevel \\ "article").foreach{ node =>
            val index = (node \ "num").text pipe clean
            val subject = (node \ "subject").text pipe clean
            val reg = (node \ "content").text pipe clean_reg
            out.println(s"""${act}.${index},"${subject}","${reg}"""")
        }
    }
}
