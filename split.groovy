// @Grab("org.codehaus.groovy:groovy-json")
import groovy.json.JsonSlurper
import groovy.io.FileType

def getFeatures(String directory) {
    def dir = new File(directory)
    def list = []
    dir.eachFileRecurse (FileType.FILES) { file ->
      list << file
    }
    def features = []
    for (f in list) {
        if (f.size() > 8) {
            if (f.toString().endsWith(".feature")) {
                features.add(f.toString())
            }
        }
    }
    return features
}

def dryrun(ArrayList languages) {
    outputs = []
    if ("Ruby" in languages) {
        outputs.add("cmd /c cucumber --dry-run --format json".execute().text)
    }
    if ("Python" in languages) {
        tmp = "cmd /c behave --dry-run --format json".execute().text
        println(fix_behave_json(tmp))
    }
    return outputs
}

def fix_behave_json(String json) {
    def ret = (json =~ /\d features passed, \d failed, \d skipped, \d untested\n\d scenarios passed, \d failed, \d skipped, \d untested\n\d steps passed, \d failed, \d skipped, \d undefined, \d untested\nTook \dm\d\.\d+s/).replaceFirst("")
    return ret
}

def getScenarios(String txt) {
    def slurper = new JsonSlurper()
    def jsonText = slurper.parseText(txt)

    def scenarios = []
    for (feature in jsonText) {
        feature = feature.uri
        println feature
        for (scenario in feature.elements) {
            scenarios.add(scenario)
        }    
    }
    return scenarios
}

// json_data = dryrun(["Ruby", "Python"])




// input results json, set
def parse_to_set(json, current, language) {
    for (feature in json) {
        for (scenario in feature.elements) {
            tmp = feature.uri.toString() + ":" + scenario.line.toString()
            defined = true
            for (step in scenario.steps) {
                if (step.result.status == "undefined") {
                    defined = false
                }
            }
            if (defined & current.containsKey(tmp)) {
                current[tmp].add(language)
            } else if (defined) {
                current[tmp] = [language]
            } else {
                current[tmp] = [null]
            }
        }
    }
    return current
}



String data = new File('out.json').text
def slurper = new JsonSlurper()
def jsonText = slurper.parseText(data)

String data2 = new File('out2.json').text
def json2 = slurper.parseText(data)

scenario_languages = [:]
scenario_languages2 = parse_to_set(jsonText, scenario_languages, "Ruby")
scenario_languages3 = parse_to_set(json2, scenario_languages, "Python")

println scenario_languages3