# Clean code exercise guide

1. Identify bits of this code that are NOT clean code

| Class        | Issue                     | Description                                                                                                  |
|--------------|---------------------------|--------------------------------------------------------------------------------------------------------------|
| `hltchk`     | Class name                | The class name does not reveal the intention of the class                                                    |
| `hltchkTest` | Class name                | The class name does not reveal the intention of the class                                                    |
| `hltchkTest` | `aHltchkTest`             | The test is not testing anything related with the class. The test name is useless                            |
| `hltchk`     | Poorly formatted code     | Poorly formatted code makes it difficult to understand                                                       |
| `hltchk`     | Useless comments          | There are a lot of comments in the code                                                                      |
| `hltchk`     | Variable names            | Variables `c`,`skipper`,`x`, `jeff` do not express the intention of the variable                             |
| `hltchk`     | Unclear method name `cmp` | What is this `cmp` doing? Compute, compare?                                                                  |
| `hltchk`     | `{`                       | Useless code blocks separation                                                                               |
| `hltchk`     | `public static`           | Missing encapsulation, there are `public static` variables                                                   |
| `hltchk`     | Repetition detected       | ```c.substring(c.indexOf(' ', 1 + c.indexOf(' ')) + skipper, c.length())``` This piece of code appears twice |

2. Understand the intention of the code

Read the code and perform exploratory tests to ensure we have a testing harness and we can modify the code
without losing its features. First of all, we should re-format the code with IntelliJ and create a failing unit test
while we do the refactoring:

```java
@Test
public void failingTest(){
    Assert.fail("this will fail until we have done the refactor");
    }
```

If for whatever reason we commit this code to the CI/CD, the pipeline will fail until we remove this test.

As we read the code we see that:

- If the passed argument is not a string it should return 0, write a test for that:

```java
@Test
public void shouldReturnZeroIfNoString(){
    Assert.assertEquals(0,Hltchk.cmp(0));
    Assert.assertEquals(0,Hltchk.cmp(0f));
    Assert.assertEquals(0,Hltchk.cmp(0d));
    }
```

- `c` and `skipper` variables are not used outside `cmp` method. So, let's embed them inside
- We can remove useless comments without affecting the functionalities
- IntelliJ detects some repetition, so we can extract a common variable easily without changing the behaviour
- We can refactor `c` variable for something more relevant, maybe `inputText`?
- Now that we have the condition to return zero if not string, we can change the code a bit and extract a method that
  will do that:

```java
public static int cmp(Object argument){
    int skipper=1;
    String inputText;

    if(notString(argument)){
    return 0;
    }
    inputText=(String)argument
```

```java
private static boolean notString(Object argument){
    return!(argument instanceof String);
    }
```

- We can perform method overloading and define a `cmp` method with `String` parameter.
- Inside the `cmp` method we see that it search for the index of `''` character many times, and sometimes it parses the
  integer value of it. So, the initial idea for exploratory tests is to provide a text with numbers and whitespaces.
  Note: we can extract a common variable for that character:

```java
final char whiteSpaceChar=' ';
```

- Now, let's see what happens with some cases:

```java
@Test
public void exploratoryTest(){
    explore("1");
    }

private void explore(String input){
    System.out.println(input+" => "+Hltchk.cmp(input));
    }
```

This crashes with the following exception:
`java.lang.StringIndexOutOfBoundsException: begin 0, end -1, length 1`.

- We need to have some whitespaces:

```java
@Test
public void exploratoryTest(){
    explore("1 1");
    }
```

Now it crashes with the following exception:
`java.lang.StringIndexOutOfBoundsException: begin 2, end -1, length 3`.

Knowing that, we can mark those tests are expected exceptions

```java
@Test(expected = StringIndexOutOfBoundsException.class)
public void shouldThrowWithoutWhitespace(){
    explore("1");
    }

@Test(expected = StringIndexOutOfBoundsException.class)
public void shouldThrowWithOneWhitespace(){
    explore("1 1");
    }
```

- At this point we know that the algorithm requires something more than two numbers separated with a whitespace.
  If we try to read the code a bit more, we see that it tries to parse a string and do comparison with `+`, `-` and `*`,
  so it looks like it's implementing some sort of calculator, so, let's prepare test cases for those operations:

```java

@Test
public void shouldSum(){
    Assert.assertEquals(4,Hltchk.cmp("2 2 +"));
    }

@Test
public void shouldMultiply(){
    Assert.assertEquals(4,Hltchk.cmp("2 2 *"));
    }

@Test
public void shouldSubtract(){
    Assert.assertEquals(0,Hltchk.cmp("2 2 -"));
    }
```

- Now we can change the names of the classes to `Calculator` and `cmp` to `calculate`
- Now the code has some clear separation between the logic that parse the operand and the operation and the logic that
  performs the operation. To fulfil the `Single Responsability Principle (SRP)`, we must separate that logic into
  a `OperationParser` and `Operation` and create different tests for the parser and the logic that performs the
  operation
- The logic to perform the operation could as well be embed in the `Operation` class

What we have achieved at this point?

- The code is much more readable
- The code is much more modular: we can change the way we compute the operation without affecting the parsing and
  vice-versa
- We have tests that cover all features. We can refactor even further without losing any feature