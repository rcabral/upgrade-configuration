# Upgrade Configuration

A version control for the database, so the developer has the power to migrate it easily and with security. Allows database evolutions to be shipped along with the application and run automatically at startup. This facilitates the continuous application delivery process and favors the expansion of the agile development culture and devops.

## Source code

[Link to Source Code] (https://github.com/rcabral/upgrade-configuration/tree/main/source/upgrade-configuration)

## Sample web project

[Link to Sample web project] (https://github.com/rcabral/upgrade-configuration/tree/main/source/sample-web-project)

## Documentation

Project presented to the Programa de Pós–graduação em Informática, do Departamento de Informática da PUC-Rio in partial fulfillment of the requirements for the degree of Mestre em Informática.

[Link to Documentation] (https://github.com/rcabral/upgrade-configuration/raw/main/source/upgrade-configuration/docs/raphael-cabral-programming-conclusion-project.pdf)

# User Manual

## Prerequisites

List:

-   JDK 8 or greater;

-   JPA 2 or greater;

-   Maven 3 or greater.

## How to Configure

Upgrade Configuration uses Maven to manage its dependencies. So, to add
Upgrade Configuration to your project, all you need to do is add the
following dependency:

    <dependency>
        <groupId>br.puc-rio</groupId>
        <artifactId>upgrade-configuration</artifactId>
        <version>1.0</version>
    </dependency>

Maven will take care of downloading all required dependencies.

## How to Use

### Creating your first Build

By convention, to manage your Builds, just create the file
upgrade-configuration.xml in the root of your project. The XML code, also present at
[https://github.com/rcabral/upgrade-configuration/raw/main/source/upgrade-configuration/demo/first-build/upgrade-configuration.xml](https://github.com/rcabral/upgrade-configuration/blob/main/source/upgrade-configuration/demo/first-build/upgrade-configuration.xml),
presents an example of creating your first Build. As can be seen, this
Build is composed of 2 steps, with an upgrade and a downgrade action.

``` {#first-build .xml language="XML" caption="First Build" label="first-build"}
<?xml version="1.0" encoding="UTF-8"?>
<UpgradeConfiguration xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="upgrade-configuration.xsd">
<!-- List of Builds -->
<build majorVersion="1" minorVersion="0" releaseVersion="0" buildNumber="151122" buildSequence="0">
	<!-- List of Steps -->
    <steps>
      <Step number="0">
        <!-- Upgrade Action -->
        <upgrade class="br.puc.rio.model.RunSQLAction">
          <sql>CREATE TABLE MY_FIRST_TABLE(id int(4) AUTO_INCREMENT,name varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
        </upgrade>
         <!-- Downgrade Action -->
        <downgrade class="br.puc.rio.model.RunSQLAction">
          <sql>DROP TABLE MY_FIRST_TABLE;</sql>
        </downgrade>
      </Step>
      <Step number="1">
      	<!-- Upgrade Action -->
        <upgrade class="br.puc.rio.model.RunSQLAction">
          <sql>CREATE TABLE MY_SECOND_TABLE(id int(4) AUTO_INCREMENT,name varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
        </upgrade>
      	<!-- Downgrade Action -->
        <downgrade class="br.puc.rio.model.RunSQLAction">
          <sql>DROP TABLE MY_SECOND_TABLE;</sql>
        </downgrade>
      </Step>
    </steps>
    <message>UpgradeConfiguration version 1.0.0.151122</message>
  </build>
</UpgradeConfiguration>
```

One note is that the downgrade action is not mandatory, only upgrade.

### Running The Upgrade Configuration

To run the upgrade configuration and synchronize your builds declared in
the XML file with the database, just run the method named execute, from
the br.puc.rio.controller.UpgradeConfigurationController class.

It is recommended that this method be executed at server startup, so
that every build change is synchronized. The code presents an example of creating a component with Spring Boot, so that the method is executed at server startup.

``` {#startup-component .java language="Java" caption="Running The Upgrade Configuration on Startup" label="startup-component"}
package br.puc.rio;

import br.puc.rio.controller.UpgradeConfigurationController;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class StartupComponent {

	@PostConstruct
	private void init() throws FileNotFoundException {
		UpgradeConfigurationController.execute();
	}

}
```

### Upgrade your Build

To create a new build and synchronize with your database. Add a new
build to the upgrade-configuration.xml file and run the upgrade.

An example of creating a new build in the upgrade-configuration.xml file can be seen in XML code. And it is also available on GitHub at [https://github.com/rcabral/upgrade-configuration/raw/main/source/upgrade-configuration/demo/upgrade-build/upgrade-configuration.xml](https://github.com/rcabral/upgrade-configuration/blob/main/source/upgrade-configuration/demo/upgrade-build/upgrade-configuration.xml).
Note that the creation of the new build starts from line 35.

``` {#upgrade-build .xml language="XML" caption="Upgrade Build" label="upgrade-build"}
<?xml version="1.0" encoding="UTF-8"?>
<UpgradeConfiguration
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:noNamespaceSchemaLocation="upgrade-configuration.xsd">
	<!-- List of Builds -->
	<build majorVersion="1" minorVersion="0" releaseVersion="0"
		buildNumber="151122" buildSequence="0">
		<!-- List of Steps -->
		<steps>
			<Step number="0">
				<!-- Upgrade Action -->
				<upgrade class="br.puc.rio.model.RunSQLAction">
					<sql>CREATE TABLE MY_FIRST_TABLE(id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
				</upgrade>
				<!-- Downgrade Action -->
				<downgrade class="br.puc.rio.model.RunSQLAction">
					<sql>DROP TABLE MY_FIRST_TABLE;</sql>
				</downgrade>
			</Step>
			<Step number="1">
				<!-- Upgrade Action -->
				<upgrade class="br.puc.rio.model.RunSQLAction">
					<sql>CREATE TABLE MY_SECOND_TABLE(id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
				</upgrade>
				<!-- Downgrade Action -->
				<downgrade class="br.puc.rio.model.RunSQLAction">
					<sql>DROP TABLE MY_SECOND_TABLE;</sql>
				</downgrade>
			</Step>
		</steps>
		<message>UpgradeConfiguration version 1.0.0.151122</message>
	</build>
	<!-- New Build declaration -->
	<build majorVersion="2" minorVersion="0" releaseVersion="0"
		buildNumber="161122" buildSequence="0">
		<!-- List of Steps -->
		<steps>
			<Step number="0">
				<!-- Upgrade Action -->
				<upgrade class="br.puc.rio.model.RunSQLAction">
					<sql>CREATE TABLE MY_THIRD_TABLE(id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
				</upgrade>
				<!-- Downgrade Action -->
				<downgrade class="br.puc.rio.model.RunSQLAction">
					<sql>DROP TABLE MY_THIRD_TABLE;</sql>
				</downgrade>
			</Step>
			<Step number="1">
				<!-- Upgrade Action -->
				<upgrade class="br.puc.rio.model.RunSQLAction">
					<sql>CREATE TABLE FOURTH (id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
				</upgrade>
				<!-- Downgrade Action -->
				<downgrade class="br.puc.rio.model.RunSQLAction">
					<sql>DROP TABLE FOURTH ;</sql>
				</downgrade>
			</Step>
		</steps>
		<message>UpgradeConfiguration version 2.0.0.161122</message>
	</build>
</UpgradeConfiguration>
```

### Downgrade your Build

In some cases it may be valuable to downgrade your database to a point earlier than the current one. In these cases, choose a previous build
and set the downgrade attribute to true. All later builds will be regressed.

The XML code shows an example where the Build
2.0.0.161122 is being downgraded to Build version 1.0.0.151122. It´s
also available on GitHub at
[https://github.com/rcabral/upgrade-configuration/raw/main/source/upgrade-configuration/demo/downgrade-build/upgrade-configuration.xml](https://github.com/rcabral/upgrade-configuration/blob/main/source/upgrade-configuration/demo/downgrade-build/upgrade-configuration.xml).
Note that in line 7 the downgrade attribute is set to true.

``` {#downgrade-build .xml language="XML" caption="Downgrade Build" label="downgrade-build"}
<?xml version="1.0" encoding="UTF-8"?>
<UpgradeConfiguration
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:noNamespaceSchemaLocation="upgrade-configuration.xsd">
	<!-- List of Builds -->
	<build majorVersion="1" minorVersion="0" releaseVersion="0"
		buildNumber="151122" buildSequence="0" downgrade="true">
		<!-- List of Steps -->
		<steps>
			<Step number="0">
				<!-- Upgrade Action -->
				<upgrade class="br.puc.rio.model.RunSQLAction">
					<sql>CREATE TABLE MY_FIRST_TABLE(id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
				</upgrade>
				<!-- Downgrade Action -->
				<downgrade class="br.puc.rio.model.RunSQLAction">
					<sql>DROP TABLE MY_FIRST_TABLE;</sql>
				</downgrade>
			</Step>
			<Step number="1">
				<!-- Upgrade Action -->
				<upgrade class="br.puc.rio.model.RunSQLAction">
					<sql>CREATE TABLE MY_SECOND_TABLE(id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
				</upgrade>
				<!-- Downgrade Action -->
				<downgrade class="br.puc.rio.model.RunSQLAction">
					<sql>DROP TABLE MY_SECOND_TABLE;</sql>
				</downgrade>
			</Step>
		</steps>
		<message>UpgradeConfiguration version 1.0.0.151122</message>
	</build>
	<!-- New Build declaration -->
	<build majorVersion="2" minorVersion="0" releaseVersion="0"
		buildNumber="161122" buildSequence="0">
		<!-- List of Steps -->
		<steps>
			<Step number="0">
				<!-- Upgrade Action -->
				<upgrade class="br.puc.rio.model.RunSQLAction">
					<sql>CREATE TABLE MY_THIRD_TABLE(id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
				</upgrade>
				<!-- Downgrade Action -->
				<downgrade class="br.puc.rio.model.RunSQLAction">
					<sql>DROP TABLE MY_THIRD_TABLE;</sql>
				</downgrade>
			</Step>
			<Step number="1">
				<!-- Upgrade Action -->
				<upgrade class="br.puc.rio.model.RunSQLAction">
					<sql>CREATE TABLE FOURTH (id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
				</upgrade>
				<!-- Downgrade Action -->
				<downgrade class="br.puc.rio.model.RunSQLAction">
					<sql>DROP TABLE FOURTH ;</sql>
				</downgrade>
			</Step>
		</steps>
		<message>UpgradeConfiguration version 2.0.0.161122</message>
	</build>
</UpgradeConfiguration>
```

### Execution Log

The execution log is available through the upgrade.log file at the root
of your project. An example of the Log can be seen in Figure.

![Execution Log](source/upgrade-configuration/docs/pictures/execution-log.png)

### Checking synchronized Builds in the Database

To check the Builds applied to your Database, just check the contents of
the BuildInformation table in your database. This table is created
automatically on the first run of Upgrade Configuration.

An example can be seen in Figure (#fig:execution-log).

![Sample of content in BuildInformation
table](source/upgrade-configuration/docs/pictures/build-information.png){#fig:execution-log width="14cm"}

## Detailing the structure of a Build

A build is composed of a list of Steps. As can be seen in the XML code.

### Detailing the structure of a Step

A step is composed of the number attribute and an upgrade action and
downgrade action. The number attribute informs the step sequence. The
upgrade action executes scripts in order to regress the database. The
downgrade action executes scripts in order to regress the database. As
can be seen in the XML code.

Note that the upgrade action is mandatory for every step, but the
downgrade action is optional.

### Types of Action

The types of actions available for Upgrade or Downgrade are just
implementations of the Action interface.

This subsection presents the RunSQLAction, RunSQLFileAction
implementations available for use. And an example of creating a custom
action.

#### RunSQLAction action type

To use the RunSQLAction action type, set the class attribute of the
upgrade, or downgrade tags with the value
\"br.puc.rio.model.RunSQLAction\". And fill the sql tag, with the sql
script to be executed. As can be seen in the XML code.

``` {#RunSQLAction .xml language="XML" caption="RunSQLAction sample" label="RunSQLAction"}
<!-- Upgrade Action -->
<upgrade class="br.puc.rio.model.RunSQLAction">
    <sql>CREATE TABLE MY_FIRST_TABLE(id int(4) AUTO_INCREMENT,name
						varchar(30) NOT NULL,PRIMARY KEY (id));</sql>
</upgrade>
```

#### RunSQLFileAction action type

To use the RunSQLFileAction action type, set the class attribute of the
upgrade, or downgrade tags with the value
\"br.puc.rio.model.RunSQLFileAction\". And fill the path tag, with the
path to the sql file that has the content to be executed. As can be seen
in the XML code.

``` {#RunSQLFileAction .xml language="XML" caption="RunSQLFileAction sample" label="RunSQLFileAction"}
<!-- Upgrade Action -->
<<upgrade class="br.puc.rio.model.RunSQLFileAction">
    <path>demo/upgrade-script.sql</path>
</upgrade>
```

#### Build example with custom action

To use a Custon action type, set the class attribute of the upgrade, or
downgrade tags with complete name of your custom class. As can be seen
in the XML code.

Your Custom Class just need to implements the interface Action, how the
sample code. Also available on GitHub at
<https://github.com/rcabral/upgrade-configuration/blob/main/source/upgrade-configuration/src/main/java/br/puc/rio/sample/HelloCustomAction.java>.

``` {#CustomAction .xml language="XML" caption="CustomAction sample" label="CustomAction"}
<!-- Upgrade Action -->
<upgrade class="br.puc.rio.sample.HelloCustomAction"/>
```

``` {#HelloCustomAction .java language="Java" caption="HelloCustomAction sample" label="HelloCustomAction"}
package br.puc.rio.sample;

import javax.persistence.EntityManager;

import br.puc.rio.model.Action;

/**
 * A sample Class of a CustomAction implementation.
 */
public class HelloCustomAction implements Action {
	
	@Override
	public void execute(EntityManager entityManager) throws Exception {
		System.out.println("Hello Custom Action! Here you can put your arbitrary code.");
	}

}
```

## XSD with upgrade-configuration.xml structure details

The XSD file with details of the upgrade-configuration.xml structure is
available on GitHub at
<https://github.com/rcabral/upgrade-configuration/blob/main/source/upgrade-configuration/upgrade-configuration.xsd>.

## Sample Web Project

A Sample Web Project is available on GitHub at
<https://github.com/rcabral/upgrade-configuration/tree/main/source/sample-web-project>,
for free use and modification.





