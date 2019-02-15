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

#### createEncoder \@245 in GsonCompatibilityMode.java
**Complexity:** 17

**Complexity according to Lizard:** 17

**LOC:** 84

<!-- How clear are the results? -->
The results are somewhat clear. There is many different decision statements intermixed. However manually counting was not hard and Lizard provided the same result.
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

#### genReadOp() \@195 in CodeGenImplNative.java
**Complexity:** 23

**Complexity according to Lizard:** 23

**LOC:** 73

<!-- How clear are the results? -->
The results are quite clear. The conditions consist of simple if statements without any && or || operators, which made them quite easy to count.
<!-- Where there any exception taken into account in the given measurements? -->
There were exceptions, but no try statements, so I didn't take them into account for the cyclomatic complexity.
<!-- What is the purpose of the function -->
The purpose of the function is to decide which decoder is to be used based on the Type object parameter, that can be set to either float, double, boolean, byte, short, int, char, long, Big_Decimal, Big_Integer, String, Object or Any, although this function doesn't handle *all* of these types. The function essentially returns a string containing the function call to be made to get to the appropriate decoder for the current data type, which is in another class. The high complexity can be attributed to all of the code duplication needed for all of the different data types; for every data type there were if statements which made the function long and (cyclomatically) complex.
<!-- Is the documentation clear w.r.t all the possible branches? -->
The documentation isn't very clear, there are a few line comments at the beginning though.

#### createDecoder() \@335 in GsonCompatibilityMode.java
**Complexity:** 24

**Complexity according to Lizard:** 24

**LOC:** 111
<!-- How clear are the results? -->
There was a conditional assignment statement that was quite hard to spot, other than that, the results were very clear.
<!-- Where there any exception taken into account in the given measurements? -->
There were 22 if (and/or else-if) statements and one catch statement. The if statements were simple; no && or || operators had to be taken into account for the cyclomatic complexity. There were no for or while loops.
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
