# Risky Operations

Compare different styles to handle calls to external services (aka Risky Operations™).

## This is not production code

It is meant as a starting point for discussions. Play around with the code and talk to team members how it makes you feel.

## RiskyService

Offers four different methods, which return "nasty" results:
 - null
 - throw a RuntimeException
 - java.util.Optional.empty()
 - io.vavr.control.Either.right()
 
## OurService

Needs to handle the results, transform the data and pass it along to its client. 

## OurServiceTest

Checks that nasty results haven been handled and repackaged properly.

