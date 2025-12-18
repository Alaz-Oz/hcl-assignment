# String vs StringBuilder vs StringBuffer 

## String
- It is immutable string object.
- It can't be modified.
- Only a new copy is created for the operations applied on it.

## StringBuilder
- It is a construct to represent modifiable string objects.
- It is not thread safe.
- It can grow in size as per the requirements.

## StringBuffer
- It is a thread safe implementation of `StringBuilder`.
- It could be used on multiple threads and won't cause any issue.
- It has higher performance cost than `StringBuilder`.
