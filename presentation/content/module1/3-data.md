# Data

We need to retrieve some data for our application. Since we are focused on NLP, lets grab some text. We will use text that is complex because many of the contrived datasets that are available always yield pretty good results. Non cultivated data yields less rosy results and presents real world challenges our application must overcome.

We will use 2 sets of data:

* US Regulations: US Code of Federal Regulations (CFR) 21, the Food & Drug Administration (FDA) regs
* SK Regulations: Medical device act

Government regulations are usually available in some form electronically. They always have issues:

* Published formats are inconsistent or have errors.
* Regulations are in different languages and words do not match.
* Regulations often refer to other regulations and their stated inclusion means they must also be pulled into NLP processing.
  * For example, some FDA regs pull in several ISO standards, which are not freely, electronically available.

Usually, data fetch is handled in a scripting language like python but any language that can perform a fetch and some initial data wrangling is suffcient. Tensorflow also has an abstraction, a very functional abstraction, to provide datasets more easily to practitioners. Most common datasets especially those used in benchmarks such as GLUE, SUPERGLUE or SentEval are available in different formats. Each major NLP toolkit uses a different format.



# Data Fetch

We will use:

* dotty: fetch US regs, filter, output CSV file
* ammonite: process a provided South Korea regulatory XML file into a CSV file.
  * The content has been translated into English. You could use various services todo this such as Amazon Translate.  Many of the translation services seem to produce about the same quality of translation but they do differ. You must test the translations to see if they are good enough for your application.



# USRegs



US Regs notables:

* All US Regs are electronically availabel as https://www.govinfo.gov
  * You have to search down into the CFR area and look for Title which are the FDA regulations.
* Most are in XML format translated from other authoritative sources. The official regulation is always the published version, i.e., the PDF version.
* They are continuously updated with fixes.
* Published 4 times a year, different titles are updated at different times.
* In between major updates, you can retrieve minor updates.
* The Federal Registar (FR) is different than CFR. FR documents are daily publishing of content "read into" the registrar through various legistrative processes. They hold, among other things, drafts of regulations prior to becoming law. We will not be dealing with FR.
* 

# South Korea Regs



South Korea Regs notables:

* Published by Ministry of Food and Drug Safety at [http://www.law.go.kr/lsSc.do?tabMenuId=tab18&query=%EC%9D%98%EB%A3%8C%EA%B8%B0%EA%B8%B0#undefined](http://www.law.go.kr/lsSc.do?tabMenuId=tab18&query=의료기기#undefined)
* We will use a slighly older version of the act.
* Not published in XML, official versions are in PDF written in Korean using a Korean market specific word processor.
* Google translate in your browser should create a pretty good translation.



# Data Fetch Scripting

The fetch development files are located in `modules/module1/30_regs`.

We will use the dev container for execution.

```sh
cd module/modules1/30_regs

docker build -t dev -f ../10_containers/Dockerfile

# use this to run the first time it assumes the container is not already running
docker run -v ./:/data --env-host --rm -it -w /data dev:latest

# if using podman, need :z
podman run -v ./:/data:z --env-host --rm -it /data dev:latest
```

If you exit out of the shell the container will be destroyed and cannot be restarted, which is fine for this task.

You should be at a shell prompt ready for commands.

# Development

To cover the development of the fetch scripts, shift to the actual code in your checkout folder.

After building and running per the README.md, let's check for updated data. It is possible that the CFR 2020 published version is available at this time. 

In the container, try running

```sh
$ mill usregs.run 2020 title-21
```

If its not available, you will see an exception thrown. For a script in the workshop, this is fine, but we were writing a real downloader, we would want to be more robust in the error handling. Generally, once you start adding alot of error handling to code, tools like python become more difficult to manage. We could use something like `zio` to manage the process and handle errors more robustly.

We also see that we are downloading all of Title-21. If we run counts:

```sh
wc -l usregs.csv
# 8,xxx regs

wc -l skregs.csv
# 71 regs
```

Lets keep only US medical devices regs. 

To do that, go to the govinfo site, navigate to "Browse>Category>Regulatory Information>Code of Federal Regulations", https://www.govinfo.gov/app/collection/cfr. From here, go to 2019 and look for Title 21 FDA. The medical device regs are in part "8XX" so lets filter on those.

```scala
- @main def download_usregs(year: Int, entry_filter: String) =
+ @main def download_usregs(year: Int, entry_filter: String, part_filter: String) =

    val url = ...
+   val part_r = matching.Regex(part_filter)


      def run = parse(
               zip_entry_path,
               new InputStreamReader(zfile.getInputStream(entry), cs),
               ofile,
+              part_r.findFirstIn(_).isDefined
             )

```

And apply the filter:

```scala
- def parse(src: String, in: Reader, out: PrintWriter): Unit = 
+ def parse(src: String, in: Reader, out: PrintWriter, ifilter: String=>Boolean): Unit = 

-      index.foreach{ i =>
-         val subject = (reg \ "SUBJECT").text pipe clean_subject
-      	  val content = reg.child.filter(_.label == "P").map(_.text).mkString("\n") pipe clean
-      	  out.println(s"""${title}$i,"$subject","$content"""")
-      }

+      index.foreach{ i =>
+ 	     if ifilter(i) then
+      	   val subject = (reg \ "SUBJECT").text pipe clean_subject
+      	   val content = reg.child.filter(_.label == "P").map(_.text).mkString("\n") pipe clean
+      	  out.println(s"""${title}$i,"$subject","$content"""")
+      }}
```

Now we can filter via:

```sh
mill -w usregs.scala 2019 title-21 '8\\d\\d\\.'
```

