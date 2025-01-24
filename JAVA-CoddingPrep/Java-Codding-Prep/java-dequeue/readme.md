In computer science, a double-ended queue (dequeue, often abbreviated to deque, pronounced deck) is an abstract data type that generalizes a queue, for which elements can be added to or removed from either the front (head) or back (tail).

Deque interfaces can be implemented using various types of collections such as LinkedList or ArrayDeque classes. For example, deque can be declared as:

```java
Deque deque = new LinkedList<>();

Deque deque = new ArrayDeque<>();
```
# Problem Statement
In this problem, you are given  integers. You need to find the maximum number of unique integers among all the possible contiguous subarrays of size .

Note: Time limit is  second for this problem.

#### Input Format

The first line of input contains two integers  and : representing the total number of integers and the size of the subarray, respectively. The next line contains  space separated integers.

#### Constraints

* 1<= N <=10000
* 1<= M <=10000
* M <= N

The numbers in the array will range between [0,10000]

#### Output Format

Print the maximum number of unique integers among all possible contiguous subarrays of size .

#### Sample Input

```shell
6 3
5 3 5 2 3 2
```

#### Sample Output
```shell
3
```
#### Explanation
In the sample testcase, there are 4 subarrays of contiguous numbers.
* s1 = (5,3,5) - Has 2 unique numbers.
* s2 = (3,5,2) - Has 3 unique numbers.
* s3 = (5,2,3) - Has 3 unique numbers.
* s4 = (2,3,2) - Has 2 unique numbers.

In these subarrays, there are  2,3,3,2 unique numbers, respectively. The maximum amount of unique numbers among all possible contiguous subarrays is 3.