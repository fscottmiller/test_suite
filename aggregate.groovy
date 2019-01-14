import groovy.json.*

// merges jsons to one feature array
// inputs must match cucumber output 
def merge_scenarios(scenarios) {
    output = []
    features_seen = []
    for (f in scenarios) {
        scenario = f[0]
        name = scenario.uri
        
        println scenario.elements[0].toString() + "\n\n\n"
        
        if (name in features_seen) {
            // append scenario to feature
            index = features_seen.findIndexOf { it == name }
            output[index].elements.add(scenario.elements[0])
        } else {
            // enter new feature
            features_seen.add(name)
            output.add(scenario)
        }
        
        println(output.toString() + "\n\n\n")
    }
}

def translate_to_cucumber(scenario, language) {
    switch (language) {
        case "Ruby": 
            return scenario
        case "Python":
            println "need to translate"
            return scenario
    }
    return -1
}

//temp hardcoded input
String rawjson1 = new File('c.json').text
String rawjson2 = new File('d.json').text

def slurper = new JsonSlurper()
def json1 = slurper.parseText(rawjson1)
def json2 = slurper.parseText(rawjson2)

ret = merge_scenarios([json1, json2])

for (i in ret) {
    println JsonOutput.toJson(i)
}