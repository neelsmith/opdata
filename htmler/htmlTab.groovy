
import edu.harvard.chs.cite.CtsUrn

File f = new File(args[0])

String imgBase = '"http://beta.hpcc.uh.edu/tomcat/hmtcite/images?request=GetBinaryImage&w=9000&urn='

String currentVersion = ""
println "<table>"
f.eachLine { l ->
  cols = l.split(/,/)
  if (cols.size() == 8) {
    String base = cols[3].replaceFirst('"', imgBase)

    def img = base + "," + cols[4..6].join(",")
    CtsUrn cts

    try {
      cts = new CtsUrn(cols[1])
      String vers = cts.getVersion()
      if (vers != currentVersion) {
	if (currentVersion != "") {
	  println "</tr>"
	}

	currentVersion = vers
	println "<tr><th>${currentVersion}</th>"
      }
      println "<td><img src="+ img + "/></td>"

    } catch (Exception e) {
      System.err.println "Could not parse CTS URN " + cols[0]
    }
  }

}
println "</tr>\n</table>"