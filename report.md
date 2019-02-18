# Report for assignment 3

This is a template for your report. You are free to modify it as needed.
It is not required to use markdown for your report either, but the report
has to be delivered in a standard, cross-platform format.

[![Build Status](https://travis-ci.org/apeinot/java.svg?branch=lab3)](https://travis-ci.org/apeinot/java)
[![codecov](https://codecov.io/gh/apeinot/java/branch/lab3/graph/badge.svg)](https://codecov.io/gh/apeinot/java/)

**Branch:**
* [Lab3](https://github.com/apeinot/java/tree/lab3): main branch for the lab (include new tests and report)
* [Lab3_refactoring](https://github.com/apeinot/java/tree/lab3_refactoring): lab3 + some refactoring done on 5 functions
* [coverage](https://github.com/apeinot/java/tree/coverage): ad-hoc branch coverage on the original project
* [coverage2](https://github.com/apeinot/java/tree/coverage2): ad-hoc branch coverage on the lab3 which include new tests

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

#### chooseImpl() \@124 in Codegen.java
**Complexity:** 18

**Complexity according to Lizard:** 18

**LOC:** 60
<!-- How clear are the results? -->
The results are quite clear. In fact, the Type returned by the function is clear while reading the function. However, the way used to reach each return is quite complicated and it is hard given a particular input to know directly the result.
<!-- Where there any exception taken into account in the given measurements? -->
No exceptions are taken into account in the given measurements. In fact, the function throws some exceptions but there is no try/catch.
<!-- What is the purpose of the function -->
This function is used in the method gen() of the class Codegen. Understanding the gen() method helps to understand this function. gen() creates a decoder according to a cacheKey and a given type. The chooseImpl() function takes this type (often modified) during the execution of gen().  
The role of chooseImpl is to return a Type from the type given in argument. The newly return Type depends mainly on the fact that the original Type is parametrized or not. The result also depends on the fact that the conversion in class of the given Type can be used to create a collection or a map.  
The complexity of the function is needed because of the variety of possible Type. However, there are some small duplicates in the code.
<!-- Is the documentation clear w.r.t all the possible branches? -->
There is no documentation at all on this function. Everything should be deduced from the code.

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

#### skip \@19 in IterImplSkip.java
**Complexity:** 18

**Complexity according to Lizard:** 18

**LOC:** 36
<!-- How clear are the results? -->
The results of the function are extremely clear. According to the `token` the next action is decided or an error message is launched. This is done via a `switch` statement.
<!-- Where there any exception taken into account in the given measurements? -->
This function only contains one large 'switch' statement, which makes it easy to understand the function. One has to be aware that the `default` statement is not counted as contribution to the overall complexity.
<!-- What is the purpose of the function -->
The purpose of the 'skip' function is to call the correct function for the current `token` so that this 'token' gets correctly skipped, e.g., if it is an array or a number. The `token` is accessed via an iterator, which is passed throughout an argument. The complexity of this function is somehow naturally given (one must go differentiate between all this cases). But one could solve this with a lambda expression. So dynamically the right function that should be executed on the `token` will be found.
<!-- Is the documentation clear w.r.t all the possible branches? -->
The documentation for this function is nonexistent.

#### updateBindings \@394 in Config.java
**Complexity:** 18

**Complexity according to Lizard:** 18

**LOC:** 58
<!-- How clear are the results? -->
The function itself does not have a return value, but it sets the bindings so that the mapping to/from a field can be changed. Since these are only strings, the results are hard to grasp in some way.
<!-- Where there any exception taken into account in the given measurements? -->
This function mainly consists of one big `for` loop that iterates over all possible bindings. One difficulty whilst computing the complexity is to get the exact complexity of an `if` statement, since `&&` statements increment the complexity by 1.
<!-- What is the purpose of the function -->
The main purpose of the function is as the name says to update the bindings from/to a JSON field, which is done by iterating over all bindings. First, it is checked whether this binding shall be ignored. After that, the function checks for several annotations of the current binding and changes the binding accordingly. The complexity of this function mainly seems to be predefined by the complexity of the problem itself. It might be possible to reduce the complexity of the function itself by splitting it into multiple subfunctions that change the binding if a certain annotation is given.
<!-- Is the documentation clear w.r.t all the possible branches? -->
The documentation of this function is surprisingly good and better than the documentation of most of the other functions in this project. But compared to many standards, there is actually no direct documentation besides some inline comments and a one-liner on the documentation web site.

#### readStringSlowPath \@217 in IterImpl.java

**Complexity:** 28

**Complexity according to Lizard:** 28

**LOC:** 105

<!-- How clear are the results? -->

The results are not really clear because there are a lot of possible outcomes due to the different unicodes analysis.

<!-- Where there any exception taken into account in the given measurements? -->

There is a try/catch on the whole process that increase the complexity.

<!-- What is the purpose of the function -->
The function reads a string contained in a JsonIterator. This string is stored as a buffer of bytes representing characters. The function has a high complexity because it differentiates a lot of different escaped characters and a lot of possible unicodes. Maybe the complexity could be reduced a little bit but it would be difficult and there are not a lot of possibilities because we can't neglect any implemented case.

<!-- Is the documentation clear w.r.t all the possible branches? -->
There is no documentation except the descriptions of the returned errors. The function IterImplString::parse that call this function is more documented and can help to understand it a little bit more.

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

## Questions for Part 1

### 1. What are your results? Did everyone get the same result? Is there something that is unclear? If you have a tool, is its result the same as yours?
Initially, it was a bit unclear how we were supposed to manually count the cyclomatic complexity. We got different results than the lizard tool, but we eventually settled on counting if and else-if statements (not else), as well as for and while loops. We also counted catch statements. We realised that the default CCN should be one, not zero like we initially thought.

Consequently everyone got the same results as the lizard tool.

### 2. Are the functions/methods with high CC also very long?
Usually, but not necessarily. IterImplSkip::skip is quite complex, although it only spans 35 lines, while Config::updateBindings() is equally complex, but is 28 lines longer.

### 3. What is the purpose of these functions? Is it related to the high CC?
Answered individually for all functions above.

### 4. If your programming language uses exceptions: Are they taken into account in the CC count? If you think of an exception as another possible branch (to the catch block or the end of the function), how is the CC affected?
Yes, all of the catch statements were counted, not the try or finally statements however, since they are always executed, whether there is an exception or not. Each catch statement increases the CCN by 1. 

### 5. Is the documentation of the function clear about the different possible outcomes induced by different branches taken?
Also answered above in the individual accounts of the functions.

## Coverage

### Tools

We have used [Cobertura](https://cobertura.github.io/cobertura/) for computing the coverage of all the project.  
It is very well documented and can be integrated easily in a maven build (the maven plugin was already included in the original project). To launch the coverage report generation from the command line, run: `mvn cobertura:cobertura`

The report is generated at the end of each Travis build and available on Codecov for all branches of our project:
[![codecov](https://codecov.io/gh/apeinot/java/branch/lab3/graph/badge.svg)](https://codecov.io/gh/apeinot/java/)

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

### readNumber() ([old](https://github.com/apeinot/java/blob/lab3/src/main/java/com/jsoniter/IterImplForStreaming.java#L565)/[refactored](https://github.com/apeinot/java/blob/lab3_refactoring/src/main/java/com/jsoniter/IterImplForStreaming.java#L565)) in IterImplForStreaming.java

#### 1. Is the complexity necessary?
The complexity of 20 is mostly unnecessary, as it comes from a large switch statement where most cases are fall through with no code being executed for the case, aside from the final case and the three characters classified as dots.

#### 2. Is it possible to split up the code into smaller units to reduce complexity?
While it would be possible to split up this function into smaller subfunctions, a better way to reduce complexity would be to make changes to the switch statement.

#### 3. If so, how would you go about this?
Instead of the large switch statement, the byte could instead be matched against a string containing all the characters classified as number, for example using the String.contains() function. This would reduce the complexity greatly, without altering how the code works.

### skip() ([old](https://github.com/apeinot/java/blob/lab3/src/main/java/com/jsoniter/IterImplSkip.java#L19)/[refactored](https://github.com/apeinot/java/blob/lab3_refactoring/src/main/java/com/jsoniter/IterImplSkip.java#L19)) in IterImplSkip.java

#### 1. Is the complexity necessary?
At the first glance, the complexity seems necessary, since all the different cases need to be covered by the switch statement. But by looking more carefully at the code one can see that there is a huge fallthrough case which results in the same action, namely calling the function `skipUntilBreak()`. One could condense these cases to one case, which does not make the complexity warranted anymore.

#### 2. Is it possible to split up the code into smaller units to reduce complexity?
Yes, it is possible to split up the function in the smaller subfunctions, but it is also possible to handle several cases in a smarter way to save complexity.

#### 3. If so, how would you go about this?
The ten cases '0' to '9' can be condensed to one test case. This can be achieved by applying bit modifications to the byte `c`, which leads to the fact that only one check `if(c < 9)` has to be applied. Therefore, the complexity will be reduced by 9 resulting in a overall reduction of 50%.

### createDecoder() ([old](https://github.com/apeinot/java/blob/lab3/src/main/java/com/jsoniter/extra/GsonCompatibilityMode.java#L335)/[refactored](https://github.com/apeinot/java/blob/lab3_refactoring/src/main/java/com/jsoniter/extra/GsonCompatibilityMode.java#L442)) in GsonCompatibilityMode.java

#### 1. Is the complexity necessary?
On one hand, there are a lot of data types to decode in this function, which warrants the high complexity, but on the other hand it's not really necessary to have all the classes inside the function. The Decoder classes are all written inline, which gives rise to the high complexity of the function. However, you could just put them outside the function to decrease complexity.

#### 2. Is it possible to split up the code into smaller units to reduce complexity?
Yes, very much so.

#### 3. If so, how would you go about this?
There is no reason for the decoders not to be outline. Instead of returning new instances of the classes, we could then simply just return pre-saved decoder classes. According to my calculations, this should reduce the CCN from the current 24 to the new 7, which is a approximate 71 % reduction.

### readStringSlowPath() ([old](https://github.com/apeinot/java/blob/lab3/src/main/java/com/jsoniter/IterImpl.java#L217)/[refactored](https://github.com/apeinot/java/blob/lab3_refactoring/src/main/java/com/jsoniter/IterImpl.java#L219)) in IterImpl.java

#### 1. Is the complexity necessary?
The complexity of this function (28) is necessary because of the huge number of cases to deal with. However, we can put part of the code in smaller methods in order to reduce the complexity a lot.

#### 2. Is it possible to split up the code into smaller units to reduce complexity?
Exactly.

#### 3. If so, how would you go about this?
To do the refactoring, I plan to create 3 new methods to externalize some part of the code:
* readEscape(JsonIterator iter, int i) that will contain the big switch about escaped code in the string
* readUnicode(JsonIterator iter, int i, int j) that will deal with the else if of the current function
* appendToReusableChars(JsonIterator iter, int j) that will append character in the reusableChars array (this code is currently duplicate 3 times  

With all this modifications, the complexity of readStringSlowPath should be reduced to 6 (against 28 currently) which is a reduction of approximatly 78%.

### parse() ([old](https://github.com/apeinot/java/blob/lab3/src/main/java/com/jsoniter/spi/OmitValue.java#L138)/[refactored](https://github.com/apeinot/java/blob/lab3_refactoring/src/main/java/com/jsoniter/spi/OmitValue.java#L138)) in OmitValue.java

#### 1. Is the complexity necessary?
The complexity of this function (21) is somewhat necessary since we need to check many different types.
We want one case per type the input can be.

#### 2. Is it possible to split up the code into smaller units to reduce complexity?
Yes, the function is essentially a long chain of if statements. This chain can easily be split and reduce the complexity of parse() greatly. However there are also smarter ways to acquire the correct string. The complexity can possibly be reduced greatly with a HashMap.
#### 3. If so, how would you go about this?
The functions if-chain would be split and move into eight separate functions.
These function would each implement a pair of the if statements, one for the object type, for example 'Boolean' and one the primitive type, 'boolean'.
If the type is not a boolean (in this case), the function would return null.
Then we can try each of the eight functions separately in parse() and see if they returned correctly. If they do, we return that returned value.
Seven of the eight function would each result in a decrease in CC of one in parse(). The function that parses characters would result in a decrease of 3, as those if statements are more complex. in total the CC would decrease by 11, a ~50% reduction.

### Results of refactoring

| CCN | Old  | Refactored | Reduction |
|--:|--:|---|---|
| readNumber()         | 20 | 7 | 65 % |
| skip()               | 18 | 9 | 50 % |
| createDecoder()      | 24 | 8 | 67 % |
| readStringSlowPath() | 28 | 7 | 75 % |
| parse()              | 21 | 11| 48 % |

Links to the old files and the refactored files can be found above in the title of each refactoring description.

## Effort spent

For each team member, how many hours was spent in

|  | Alexandre | Emil |  Franz | Jonathan | Samuel|
|--|-----------|------|--------|----------|-------|
|plenary discussions/meetings||||||
|discussions within parts of the group||||||
|Reading documentation||||||
|configuration||||||
|analyzing code/output||||||
|writing documentation||||||
|writing code||||||
|running code||||||


## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
