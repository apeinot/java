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

#### createDecoder() \@335 in GsonCompatibilityMode.java
**Complexity:** 23

**Complexity according to Lizard:** 24

**LOC:** 111
<!-- How clear are the results? -->
As you can see, I'm not getting the same result as lizard for some reason. Apparently lizard also counts exceptions (not the throws, just the try statements), but even when counting them I'm still one off.
<!-- Where there any exception taken into account in the given measurements? -->
There were 21 if (and/or else-if) statements and one try statement. The if statements were simple, so I didn't have to take any logical operators (&& or ||) into account for the cyclomatic complexity. There were no for or while loops.
<!-- What is the purpose of the function -->
The purpose of the function is to create decoders for a range of different input types, including Date, String, boolean and the numeric types. The function returns Decode objects for all of the these data types, containing a function which decodes the data of the according type. These classes/functions are all written inline, which gives rise to the high complexity of this function, since there are also quite many of them. The type of the data is specified via a Type object parameter, which decides what decoder should be returned. In the decoder functions, the data is retrieved from an JsonIterator which is passed to the functions. An attempt will be made to convert the input data to the according data type, but if not possible, an error will be thrown. If the type parameter doesn't match any of the data types, the decoding will be passed to the super class decoder.
<!-- Is the documentation clear w.r.t all the possible branches? -->
There is no documentation of this function at all, not even a single line comment, but it was still quite easy to get the grasp of, since the name (createDecoder) was very descriptive and since essentially the same steps were repeated over and over again, just for different data types.

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
