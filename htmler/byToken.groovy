
// Convert csv source data to data maps allowing access
// by paleographic token.

import au.com.bytecode.opencsv.CSVReader
import edu.harvard.chs.cite.CtsUrn

Integer debug = 0




def msOrder = ["msA", "e3", "msB", "e4", "ge", "vgf64"]
def titles = [
  "msA": "Venetus A (10th c.)", 
  "e3": "Esc. Upsilon 1.1 (11th c.)",
  "msB": "Venetus B (11th c.)",
  "e4": "Esc. Omega 1.12 (11th c.)",
  "ge": "Gen. 44 (13th c.)",
  "vgf64": "Leiden Voss. 64 (15th c.)"
]



String imgBase = "http://beta.hpcc.uh.edu/tomcat/hmtcite/images?request=GetBinaryImage&w=9000&urn="


File csvFile = new File (args[0])

def allData = []

CSVReader reader = new CSVReader(new FileReader(csvFile))
reader.readAll().each { ln ->
  def record = []
  ln.each { val ->
    record.add(val)
  }
  try {
    CtsUrn cts = new CtsUrn(record[1])
    def triple = [cts.getVersion(), record[3], record[4]]
    allData.add(triple)
  } catch (Exception e) {
    if (debug > 0) {
      System.err.println "Not a urn: " + record[1]
    }
  }
}


// find by triple[2], group by triple[0], img value is triple[1]
def featureSet = allData.groupBy { it[2] }




def dataSrcs = ["alpha", "delta", "epsilon", "epsilon-pi ligature", "omega"]
println "<table>\n<tr><th>Feature</th>"
msOrder.each { ms ->
print "<th>${titles[ms]}</th>"
}
println "\n</tr>"

dataSrcs.each { src ->
  println "<tr><th>${src}</th>"
  if (featureSet.keySet().contains(src)) {
	def data = featureSet[src]
	def dataByMss = data.groupBy{ it[0] }
	msOrder.each { k ->
	  print "\t<td>"
	  dataByMss[k].each { trip ->
	    if (trip[1] != "") {
	      print '<img src ="' + imgBase + trip[1] + '"/>'
	    }
	  }
	  println "\t</td>"
	}
  } else {
    println "NO DATA FOR " + src + " in " + feaureSet.keySet()
  }
}

println "</table>"



