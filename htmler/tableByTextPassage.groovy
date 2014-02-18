// Create HTML <table> elements from csv source sorted in document
// order.
// 
// Usage: groovy htmlTab.groovy <FILENAME>
//

import edu.harvard.chs.cite.CtsUrn


String imgBase = '"http://beta.hpcc.uh.edu/tomcat/hmtcite/images?request=GetBinaryImage&w=9000&urn='

def msOrder = ["msA", "e3", "msB", "e4", "ge", "vgf64"]
def titles = [
  "msA": "Venetus A (10th c.)", 
  "e3": "Esc. Upsilon 1.1 (11th c.)",
  "msB": "Venetus B (11th c.)",
  "e4": "Esc. Omega 1.12 (11th c.)",
  "ge": "Gen. 44 (13th c.)",
  "vgf64": "Leiden Voss. 64 (15th c.)"
]


File f = new File(args[0])

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
	println "<tr><th>${titles[currentVersion]}</th>"
      }
      println "<td><img src="+ img + "/></td>"

    } catch (Exception e) {
      System.err.println "Could not parse CTS URN " + cols[0]
    }
  }

}
println "</tr>\n</table>"