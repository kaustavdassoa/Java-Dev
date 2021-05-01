# Design Patterns 

* [Decorator Design pattern](#decorator-design-pattern)
* [Strategy Design pattern](#strategy-design-pattern)
* [Observer Design pattern](#observer-design-pattern)
* [Factory Desing pattern](#factory-desing-pattern)

## Decorator Design pattern
The Decorator Pattern proposes a solution to modify an object in run time, applying independent features or behaviors which can be combined and accumulated in any order.
It’s an elegant solution to avoid procedural code and use SOLID concepts, resulting in scalable, maintainable and clean implementation.
##### When to use Decorator pattern
Decorator pattern is most sutited for the following scenarios

* Several conditions to apply different behaviors/features to an object (usually done by utility imperative methods).
* Class explosion being used to combine variations of an object. For Example : When performing text formating, instead of having  TextBold, TextItalic, TextUnderline, TextBoldAndItalic, TextBoldAndUnderline, TextBoldAndItalicAndUnderline method have a decorator pattern implemented 
* One or more attributes that during a process are modified in a chained or accumulative way.
* An object with Boolean flags being used to apply modifications on the object itself.


## Strategy Design pattern
In Strategy pattern, a class behavior or its algorithm can be changed at run time. This type of design pattern comes under behavior pattern.

##### Strategy Design pattern
Strategy Desing pattern are suited for the following scenarios during development , desing and in refactoring phase.

* Switch/Elseif structures being used to decide the execution of specific algorithms/responsibility or complex/large code.
* Alternations between several implementations of same interface during a process execution.
* Two or more implementations of same interface used in same process, which is determinate by some external parameter.


## Observer Design pattern
Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.
##### When to use Observer pattern
* Use the Observer pattern when changes to the state of one object may require changing other objects, and the actual set of objects is unknown beforehand or changes dynamically.
* Use the pattern when some objects in your app must observe others, but only for a limited time or in specific cases.



## Factory Desing pattern 
Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
In Factory pattern, objects are created without exposing the creation logic to the client and refer to newly created object using a common interface.
##### When to use Factory pattern
* Use the Factory Method when you don’t know beforehand the exact types and dependencies of the objects your code should work with.
* Use the Factory Method when you want to provide users of your library or framework with a way to extend its internal components.
* Use the Factory Method when you want to save system resources by reusing existing objects instead of rebuilding them each time.


#### Refered Link

<a href="https://www.youtube.com/playlist?list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc" target="_blank">Christopher Okhravi - Design Patterns in Object Oriented Programming</a>

<a href="https://refactoring.guru/design-patterns" target="_blank"> www.refactoring.guru</a>
