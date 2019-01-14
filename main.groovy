import groovy.json.JsonSlurper

// todo
// check if all steps in a scenario are defined
def scenario_is_mapped(scenario, language) {
    mapped = true
    for (step in scenario.steps) {
        mapped = mapped & is_step_mapped(step, language)
    }
    return mapped
}

// check if step is mapped
def is_step_mapped(step, language) {
    switch (language) {
        case "Ruby": 
            if (step.result.status != "undefined") {
                return true
            }
        case "Python":
            if (step.match != null) {
                return true
            }     
    }
    return false
}

// check if an element is a scenario
def is_scenario(scenario, language) {
    if (scenario.type == "scenario") {
        return true
    }
    return false
}

// executes a dry run and returns the json output
def dryrun(language) {
    switch (language) {
        case "Ruby" :
            output = "cmd /c cucumber --dry-run --format json".execute().text
            return output
        case "Python" :
            output = "cmd /c behave --dry-run --format json".execute().text
            return output
    }
    return -1
}

def make_calls(languages) {
    def slurper = new JsonSlurper()
    scenarios = [:]
    for (language in languages){
        txt = dryrun(language)
        def json = slurper.parseText(txt)
        for (feature in json) {
            for (scenario in feature.elements) {
                // println (scenario.toString() + "\n")
                if (is_scenario(scenario, language) && scenario_is_mapped(scenario, language)) {
                    scenario_name = get_scenario_name(language, feature, scenario)
                    if (scenario_name in scenarios.keySet()) {
                        scenarios[scenario_name].add(language)
                    }
                    else {
                        scenarios[scenario_name] = [language]
                    }
                }
            }
        }
    }
    calls = generate_commands(scenarios)
    return calls
}

def get_scenario_name(language, feature, scenario) {
    name = scenario.name
    if (name == "") {
        println "Please name your scenarios."
        return -1
    }
    return name
}

def generate_commands(scenarios) {
    commands = []
    for (scenario in scenarios.keySet()) {
        for (language in scenarios[scenario]) {
            switch (language) {
                case "Ruby":
                    command = "cucumber --name \"" + scenario + "\" --format json"
                    break
                case "Python":
                    command = "behave --name \"" + scenario + "\" --format json"
                    break
            }
            commands.add(command)
        }
    }
    return commands
}


for (i in make_calls(["Ruby", "Python"])) {
    println i
}











