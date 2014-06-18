class CleanUpBootConsoleOutput implements CommandLineRunner {

    void run(String[] args) {
        println "[[Remove the stuff above]]"
        System.in.eachLine() { line ->
            def m = line =~ /([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9\.]+) (.+) (: .+)/ 
            if (m) {
                def revised = "${m[0][1]} ... ${m[0][3]}"
                if (revised.size() > 80) {
                    println "${revised[0..77]}..."
                } else {
                    println revised
                }
            } else {
    
                if (line.size() > 80) {
                    println "${line[0..77]}..."
                } else {
                    println line
                }
            }
        }
        println "[[Remove the stuff below]]"
    }

}
