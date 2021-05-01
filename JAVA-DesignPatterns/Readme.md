# Design Patterns 

* [Decorator Design pattern](#decorator-design-pattern)


## Decorator Design pattern
The Decorator Pattern proposes a solution to modify an object in run time, applying independent features or behaviors which can be combined and accumulated in any order.
It’s an elegant solution to avoid procedural code and use SOLID concepts, resulting in scalable, maintainable and clean implementation.
###### When to use decorator pattern
decorator pattern is most sutited for the following scenarios

* Several conditions to apply different behaviors/features to an object (usually done by utility imperative methods).
* Class explosion being used to combine variations of an object. For Example : When performing text formating, instead of having  TextBold, TextItalic, TextUnderline, TextBoldAndItalic, TextBoldAndUnderline, TextBoldAndItalicAndUnderline method have a decorator pattern implemented 
* One or more attributes that during a process are modified in a chained or accumulative way.
* An object with Boolean flags being used to apply modifications on the object itself.