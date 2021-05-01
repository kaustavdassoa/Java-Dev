# Design Patterns 

* [Decorator Design pattern](#decorator-design-pattern)
* [Strategy Design pattern](#strategy-design-pattern)

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


##Observer Design pattern

##### When to use Observer pattern

####Refered Link

[Christopher Okhravi - Design Patterns in Object Oriented Programming](#https://www.youtube.com/playlist?list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc)

[refactoring.guru](#https://refactoring.guru/design-patterns)