# IMPORTANT NOTE: 

Please remove all java files under the src/main/java/de/tudresden/codegenerator/autogen folder

## REQUIREMENTS
In runtime and execution of tests, Java 17 is necessary.
Annotations and annotation processor have been compiled with Java Version: 11
Ganache Version: ganache v7.9.0 (@ganache/cli: 0.10.0, @ganache/core: 0.10.0) - (Not ganache-cli)
The following properties might be changed
```bash 
nvm list
nvm use v12.22.12
node --version
v12.22.12

java --version
JDK 21

solc --versıon
0.8.13
```

In order to run general-purpose programming language test (JAVA) 
integrated with smart contract-oriented programming language (Solidity), 
you need to type the following commands, respectively: 

## SOLIDITY VERSION REQUIREMENTS

In the folder ~/.web3j/solc folder should be the version of solc 0.8.13

Download from the following link:

```bash
https://github.com/argotorg/solidity/releases?page=2
```

And change the solc windows, macos or linux app under the folder. Last version of this solidity does not work.

```bash
mvn clean compile
mvn test 
```
The following command will generate the source code from SOLMAS
framework in java language so that one can test the Java code
with mvn test command.

```bash
mvn clean compile
```

Install ganache not ganache-cli and run the following command (This is a necessary step). Start Ganache with deterministic accounts:

```bash
ganache -m "world reopen cute forward vintage okay drink margin piano buffalo autumn awful"
```

or 

```bash
npx ganache -m "world reopen cute forward vintage okay drink margin piano buffalo autumn awful"
``` 


Ganache version: 

- ganache v7.9.0 (@ganache/cli: 0.10.0, @ganache/core: 0.10.0)


Create solidity source codes in java with the following command


```bash
mvn web3j:generate-sources
```

Compile all classes for target jar that can be used for annotation processing in an external project. Annotation metamodels 
and templates will be compiled


```bash
mvn clean install -DskipTests
```

Update your dependencies

```bash
mvn dependency:resolve
```

### INFORMATION REGARDING GANACHE TEST SUITE (Ethereum)


Mnemonic can be used for generating deterministic private keys. There is no way to get all private keys with a script 
and Ganache-Cli or GUI changes private key at each opening from scratch. Use the following code snippet

Don't use the following limitation in ganache-cli 

```bash
-l 9000000
```



## IMPORTANT NOTE ## 

SocialRbSC Project will be in the the following Github repository due to the contractual reason.


```bash
https://github.com/zointblackbriar/SocialRBSC.git
```


### DOCUMENTATION SPECIFIC INFORMATION

### REPORTING

In order to create reporting with Finbbugs, please use the following
command:

(@Deutsch: Um einen Bericht mit Findbugs zu erstellen, bitte verwenden Sie den folgenden
Befehl)

```bash
mvn clean compile site 
```

The report consists of dependency info, index, licenses, plugin-management info,
project-info, source code management, and summary.

(@Deutsch: Der Bericht besteht aus Abhängigkeitsinformationen, Index,
Lizenzen, Plugin-Verwaltungsinformation, Projektinformationen, Info bezügliche Quellcodeverwaltung,
und Zusammenfassung)

### STATIC CODE ANALYZER

If you use the following command, it is going to generate
a static analysis analyzer report to /reports/pmd folder.

(@Deutsch: Wenn Sie folgenden Befehl verwenden, wird Folgendes erzeugt
einen Bericht zur statistischen Analyse im Ordner /reports/pmd)

```bash
mvn pmd:pmd 
```

### CODE STYLE (CHECKSTYLE)

To generate checkstyle report as standalone, please type the following
command:

```bash
mvn checkstyle:checkstyle
```

This is going to save an XML file to /reports/checkstyle folder. If you type the command as follows:

```bash
mvn clean compile site
```

It is going to generate findbugs and checkstyle html documentation together
under the /target/site folder.

### JAVA Documentation

You can geneate javadoc with the following command

```bash 
mvn javadoc:javadoc
```

Or, basically you can use the script as follows:

```bash 
sh generatereport.sh
```


You can find the javadocs documentation under the /site/apidocs

## DOCKER COMPOSE TOOL FOR GANACHE

You can type the following commands: 

```bash 
docker-compose build
docker-compose up
```

## DEPENDENCY INFO

THe following dependency should be inserted to the pom.xml file in order to 
activate the code generator

```
<dependency>
    <groupId>de.tudresden.mas</groupId>
    <artifactId>SocialRBSC</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

then you can update the maven dependencies in the sample project, with the following
command: 

```
mvn dependency:resolve
```

## MANUAL SUBPROJECT CREATIONG 

The manual way to create a sub project as follows (For windows): 

```bash
cmd.exe /K mkdir applicationfolder
cmd.exe /K cd .\applicationfolder\
cmd.exe /K mvn archetype:generate "-DgroupId=de.tudresden.sampleprojectsocialagent" "-DartifactId=solmasframeworkapplication" "-DarchetypeArtifactId=maven-archetype-quickstart" "-DinteractiveMode=false"
```

For ubuntu and macosx

```bash
mkdir applicationfolder
cd .\applicationfolder\
mvn archetype:generate -DgroupId=de.tudresden.sampleprojectsocialagent -DartifactId=solmasframeworkapplication -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Sphinx Documentation 

This documentation can be found under the folder named as Sphinx-documentation/SocialRbSC

## Security Analysis

For the security analysis of bytecode of Solidity contracts, [Mythril](https://github.com/Consensys/mythril) has been used. 


### Solidity Web3j Version Info

Under the folder ~/.web3j/solc/

you need to download solidity version from the following website

```bash
https://github.com/ethereum/solidity/releases?page=2
```

Then change the name as solc.exe

## Test scripts

```bash
test-fast.bat

test-all.bat skip-docker

test-all.bat skip-docker integration
```