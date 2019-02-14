# New Document# Report for assignment 3

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
#### readNumber \@565 in IterImplForStreaming.java

**Complexity:** 20

**Complexity according to Lizard:** 20

**LOC:** 49

<!-- How clear are the results? -->

The results of the function are hard to grasp at a first glance, but after looking at it a bit further they became easier to understand.

<!-- Where there any exception taken into account in the given measurements? -->

In the beginning of the function there is a while(true) loop, which added to the complexity despite not adding additional branches.

<!-- What is the purpose of the function -->

The function takes a JsonIterator and iterates through it until it hits a character that is not a number, after which it returns a class containing the number of chars iterated through, an array of the numbers and whether or not there was a dot in the number. The function has a high complexity due to the number of characters classified as a number. As the cases are all fall through, this could instead be checked against a string of all the characters instead of one char at a time, reducing the complexity by a lot.

<!-- Is the documentation clear w.r.t all the possible branches? -->

The function is severely lacking in documentation, featuring no inline comments in the code or a javadoc description of the function.


#### readStringSlowPath \@385 in IterImplForStreaming.java

**Complexity:** 27

**Complexity according to Lizard:** 27

**LOC:** 99

<!-- How clear are the results? -->

The results are hard to grasp as they deal with unicode bytecodes, which can be hard to see clearly what they are.

<!-- Where there any exception taken into account in the given measurements? -->

At the start of the function there is a while(true) loop that added complexity.

<!-- What is the purpose of the function -->

The function handles reading strings from a JsonIterator in the case where an escaped character or a unicode multibyte character is encountered while reading the string. The function has a very high complexity due to the way it handles different escaped characters and the large amount of different cases for multibyte characters. Some of this complexity could possibly be reduced, but it is mostly warranted.

<!-- Is the documentation clear w.r.t all the possible branches? -->
The function is lacking in documentation, the only description of its purpose and function is in another function that calls this function for special cases.

#### Parse \@138 in OmitValue.java
**Complexity:** 21

**Complexity according to Lizard:** 21

**LOC:** 57

<!-- How clear are the results? -->
The results are very clear, the method is a sequence of non-nested if statements.
<!-- Where there any exception taken into account in the given measurements? -->
At two of the if statements there was one logical operator of the type '&', which added complexity further.
<!-- What is the purpose of the function -->
This function sets a special code depending on the type of the input data. This code is a string which is later used for logic in which they omit elements of a certain value. The function generates strings of format: "<value to omit> == %s.<getter>()". Later, the %s token is replaced with a certain element on which this check is supposed to be made.
The complexity is high since the type of the value to omit can differ greatly. The author has written one if statement for each and every possible type, these types include but are not limited to ints, Integers, booleans and bytes.
This changes the 'getter' part of the special code. For example a Boolean type omit value will result in the string: "<value to omit> == %s.booleanValue()".
<!-- Is the documentation clear w.r.t all the possible branches? -->
There is no documentation of this function. The purpose of the code is somewhat self explanatory, but only with extensive digging in other parts of the code base. However it is clear as to why there are so many branches as the if statements are relatively simple.

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
