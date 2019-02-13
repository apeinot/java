# Report for assignment 3

This is a template for your report. You are free to modify it as needed.
It is not required to use markdown for your report either, but the report
has to be delivered in a standard, cross-platform format.

[![Build Status](https://travis-ci.org/apeinot/java.svg?branch=lab3)](https://travis-ci.org/apeinot/java)
[![codecov](https://codecov.io/gh/apeinot/java/branch/lab3/graph/badge.svg)](https://codecov.io/gh/apeinot/java/)

## Project

Name: Json iterator

URL: https://github.com/json-iterator/java

This project is a fast Json parser for Java and Go. We are working on the Java version of the project.

## Onboarding experience

Did it build as documented?

The documentation of the project is essentially a website that explains how to use the library in another project.
There is no documentation on how to build but as there is a pom.xml, we deduced that it is a maven project.
We can build it easily using `mvn test` (This downloads the useful components, compiles and runs the tests).
We noticed that the project works on Java 8 but not on Java 10.

## Complexity
### 10 Function Of Very High complexity
In the code base there exists methods of high complexity. Here follows ten function of very high relative complexity.

#### getEncoder \@245 in GsonCompatibilityMode.java
**Complexity:** 17

**Complexity according to Lizard:** 17

**LOC:** 84

<!-- How clear are the results? -->
The results are somewhat clear. There is many is many different decision statements intermixed. However manually counting was not hard and Lizard provided the same result.
<!-- Where there any exception taken into account in the given measurements? -->
There where two cases of if statements with the OR operator. This gave the function a slightly higher complexity.
<!-- What is the purpose of the function -->
The function returns one of two classes. These classes are written inline which leads to the high complexity of the function.
The two classes are encoders, one of them handles dates and one handles Strings. The class that encodes strings is far more complicated than the one that handles dates. The string encoder loops over the entire string and encodes each char in a specific way. How the char is encoded is very much dependent on the value of the char.
This leads to a myriad of if statements and branches to determine the correct way of encoding.
For example the char is checked if it's greater than or lesser than many different thresholds. Additionally the char is explicitly checked against specific values.
All this leads to a very high number of if/else if/ for statements and thus a high CC.
<!-- Is the documentation clear w.r.t all the possible branches? -->
This function has no documentation. This was problematic since it seems many of the rules checked were due to special rules regarding JSONs in general. The reader had no way of knowing this without previous knowledge about JSONs. However the code could be somewhat understood without the need to dig around the code base.

### Manually counting complexity
For this assignment the complexity of five different functions was manually counted .
For each of those functions, two group members independently calculated the complexity of the function.
This way eventual errors in the calculations can be caught and recalculated.
#### Results
| Function | Class | Lines Of Code |  Cyclomatic Complexity |
|----------|-------|---------------|------------------------|
|parse|Parsed|56|21|
|skip|IterImplSkip|35|18|
|readNumber|IterImplForStreaming|49|20|
|genReadOp|CodegenImplNative|80|23|
|updateBindings|Config|62|18|

## Coverage

### Tools

Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment?

### DYI

Show a patch that show the instrumented code in main (or the unit
test setup), and the ten methods where branch coverage is measured.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

### Evaluation

Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

git diff ...

## Refactoring

Plan for refactoring complex code:

Carried out refactoring (optional)

git diff ...

## Effort spent

For each team member, how much time was spent in

1. plenary discussions/meetings;

2. discussions within parts of the group;

3. reading documentation;

4. configuration;

5. analyzing code/output;

6. writing documentation;

7. writing code;

8. running code?

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
