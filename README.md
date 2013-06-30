# Servlet Test #
This application has 2 servlet and 1 filter. LoginServlet.java take *userName* parameter from request and put it into session. UserInfoServlet.java get *userName* attribute from session and show send message to user. TestFilter.java modify responce if request has *userName* parameter.
Tere are also 2 **junit** test cacshes and 1 **httpUnit** test case.

## How to run test cases ##
To run junit tests, simple run **mvn test**
To run httpUnit test, **mvn -Dtest=HttpUnitTestSkip test**
