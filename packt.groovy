package learningspringboot

@Grab('org.jsoup:jsoup:1.6.1')

import org.jsoup.nodes.Document
import org.jsoup.Jsoup

import groovy.util.logging.*
import groovy.xml.*

@Slf4j
class ParsePackt implements CommandLineRunner {

    void run(String[] args) {
        log.info("Getting ready to parse PACKT styles...")
        
        Document doc = Jsoup.parse(new File("packt_template.fodt"), "UTF-8", "")
        
        doc.outputSettings().prettyPrint(false)
        
        new File("packt.odt.styles").withWriter { out ->
        
            [doc.select("office|settings"),
                doc.select("office|scripts"),
                doc.select("office|font-face-decls"),
                doc.select("office|styles"),
                doc.select("office|automatic-styles"),
                doc.select("office|master-styles")].each { style ->
                style.toString().eachLine { line ->
                   out.writeLine(line)
                }
            }
            
        }
        

    }

}
