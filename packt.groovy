/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
