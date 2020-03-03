// script to download a very narrow section of government regs
// for fda, args: 2019 title-21
package usregs
import ammonite.ops._
import java.nio.{file=> jnf}
import java.util._, zip._
import java.io._
import scala.jdk.CollectionConverters._
import scala.util._, chaining._
import scala.{xml => sx}
import scala.language.implicitConversions

val output_reg_file = os.pwd / "usregs.csv"
val cs = java.nio.charset.StandardCharsets.UTF_8
val base = "https://www.govinfo.gov/bulkdata"


// CFRs only published 4 times a year so for today, grab 2019's version.
// Perhaps 2020 is available.
@main def download_usregs(year: Int, entry_filter: String, part_filter: scala.util.matching.Regex) =
  val target_file = s"CFR-$year.zip"
  val target_path = os.pwd / target_file
  val url = s"$base/CFR/$year/$target_file"
  
  if !jnf.Files.exists(target_path.toNIO)
    println(s"Downloading $url")
    os.write(target_path, requests.get.stream(url))

  Using.Manager { use =>
    val zfile = use(new ZipFile(target_path.toIO, cs))
    val ofile = use(new PrintWriter(jnf.Files.newBufferedWriter(output_reg_file.toNIO)))
    zfile.entries.asScala.foreach { entry =>
      val zip_entry_path = entry.getName
      def run = parse(
          zip_entry_path,
          new InputStreamReader(zfile.getInputStream(entry), cs),
          ofile
        )
      if zip_entry_path.contains(entry_filter) then
        println(s"Processing $zip_entry_path")
        run
    }
  } match
    case Failure(_) => 1
    case _ => 0

val bad = "\u00a7\u2009\u2014\u201c"
def clean(i: String) = i.filterNot(bad contains _ ).trim.replaceAll("\""," ")
  .stripPrefix("\n").replaceAll("\n", "\\n").stripLineEnd
val valid_section_chars = """(\w|-|\.)+""".r
def clean_section(i: String) = valid_section_chars.findFirstIn(i)
def clean_subject(i: String) = i pipe clean

def title_from_doc(e: sx.Elem) =
  Option(e \\ "TITLENUM")
    .headOption
    .flatMap(n => "\\d+".r.findFirstIn(n.text))

def parse(src: String, in: Reader, out: PrintWriter): Unit = 
  println(s"Processing $src")
  val content = sx.XML.load(in)
  val title = title_from_doc(content).map(t => s"$t.").getOrElse("")
  // get all Section entries, these hold the regs
  out.println("section,subject,content")
  (content \\ "SECTION").foreach { reg =>
    val index = (reg \ "SECTNO").text pipe clean_section
     if index matches part_filter then
      val subject = (reg \ "SUBJECT").text pipe clean_subject
      val content = reg.child.filter(_.label == "P").map(_.text).mkString("\n") pipe clean
      index.foreach(i => out.println(s"""${title}$i,"$subject","$content""""))    
  }

