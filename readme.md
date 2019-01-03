# Your First Cucumber Specs

## Install Ruby and Bundler on Windows
Find an installation from https://rubyinstaller.org/downloads/ . Use the installer with devkit, version 2.5.3-1 (x86). Also, choose to install the msys2 package (option 1 - msys2 base installation). Ensure that you're using Ruby version >= 2.5

After this is done, run the command 
```bash
ruby -v
```

to ensure the install went well. Then, run the command 

```bash
gem update --system 
```

Finally, install the bundler gem.  
```bash
gem install bundler
```
Note that this may have already been included in the devkit. You should still install the updated version.
    
## Install Required Gems

Navigate to this repository in the command prompt. Then, install all the required gems specified in the gemfile.

```bash
bundle install
```

## Run Your First Spec

In the command prompt, start your very first cucumber command. You'll run the UI 
```bash
cucumber --tags @web
```

## Next Steps

Now it's time to write your own specifications! There are just a few steps to do this.
### 1. Write your gherkin (English specification) in a .feature file. Make sure you give them descriptive tags!
Checkout 'sample.feature' in this file for some examples.
### 2. Write step definitions (the code glued to the English) in Ruby.
Checkout 'step_definition.rb' in the 'steps' directory for some examples.
### 3. Add environment setup in Ruby.
This happens in the 'env.rb' file, which you can find in the 'support' directory. This code will run every time you run the 'cucumber' command.
### 4. Add Cucumber hooks
You can do this in the 'hooks.rb' file, which is in the 'support' directoy. The 'before hooks' run before every scenario, and the 'after hooks' run after - simple! This means that if more than one scenario is linked to one tag, the pieces of code you add here will run before/after every one of them.
### 5. Read more!
I strongly encourage you to check out the following resources:
Writing Better Gherkin - https://automationpanda.com/2017/01/30/bdd-101-writing-good-gherkin/
Cucumber Best Practices - https://saucelabs.com/blog/write-great-cucumber-tests
