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

#### (METHOD NAME)
1. What is the complexity?
   * Did all tools/methods get the same result?
   * Are the results clear?
2. Are the functions just complex, or also long?
3. What is the purpose of the functions?
4. Are exceptions taken into account in the given measurements?
5. Is the documentation clear w.r.t. all the possible outcomes?

#### updateBindings \@394 in Config.java
**Complexity:** 18

**Complexity according to Lizard:** 18

**LOC:** 58
<!-- How clear are the results? -->
The function itself does not have a return value, but it sets the bindings so that the mapping to/from a field can be changed. Since this is are only names, the results are hard to grasp in some way.
<!-- Where there any exception taken into account in the given measurements? -->
This function mainly consists of one big `for` loop that iterates over all possible bindings. One difficuluty whilst computing the complexity is to get the exact complexity of an `if` statement, since `&&` statements increment the complexity by 1.
<!-- What is the purpose of the function -->
The main purpose of the function is as the name says to update the bindings from/to a JSON field, which is done by iterating over all bindings. First, it is checked whether this binding shall be ignored. After that, the function checks for several annotations of the current binding and changes the binding accordingly. The complexity of this function mainly seems to be predefined by the complexity of the problem itself. It might be possible to reduce the complexity of the function itself by splitting it into multiple subfunction that change the binding if a certain annotation is given.
<!-- Is the documentation clear w.r.t all the possible branches? -->
The documentation of this function is surprisingly better than the documentation of most of the other functions in this project. But compared to many standards, there is actually no direct documentation besides some inline comments and a one-liner on the documentation web site.

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
