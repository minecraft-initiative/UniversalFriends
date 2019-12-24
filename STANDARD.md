# Universal Friends standard

## Definitions
##### Friends file
The file where all data regarding friends is stored. This file must be called `friends.json`, and is assumed to be in the process' current working directory.

##### Friend list
A list of persons, typically defined within the friends file.

##### Person
An entry to the friend list. A person must be accompanied by the following attributes:

|Attribute name|Type|Description|
|--------------|----|-----------|
|`name`|Text|This person's name.|
|`id`|UUID|A unique identifier for this person.|
|`value`|Double-precision 64-bit floating point. (Java type `double`)|The friendliness value assigned to this person. See [friendliness values](#friendliness-values)|

## Files
The standard file format is JSON.

> `friends.json`

This is the friends file. It has the following structure:
* Root (_JSON Object_)
  * `bounds` (_JSON Object_)
    * `minimum` (_JSON Number_) A number equal to `-2`, as defined by the minimum value of friendliness.
    * `maximum` (_JSON Number_) A number equal to `2`, as defined by the maximum value of friendliness.
  * `list` (_JSON Array_)
    * A person (_JSON Object_)
      * `name` (_JSON String_) This person's `name` attribute
      * `id` (_JSON String_) This person's `id` attribute, in string form.
      * `value` (_JSON Number_) This person's `value` attribute
    * ...

### Deterministic (de)serialisation
Any system implementing this standard must make sure both deserialisation and serialisation of the friends file is deterministic.

## Friendliness values
How much of a friend or enemy a person is, is defined by their friendliness value.
The friendliness value can be placed on a scale from `-2` to, and with, `2`

### Standard values
|Value|Description|
|-----|-----------|
|-2   |The standardised minimum value for friendliness|
|-1   |The standardised value for an enemy|
|0    |The standardised value for a neutral person|
|1    |The standardised value for a friend|
|2    |The standardised maximum value for friendliness|

#### Conversions
The following conversions must be made when converting persons from a boolean-based system:

##### Enemies
|Enemy|Friendliness|
|-----|--------|
|false|0       |
|true |-1      |

##### Friends
|Friend|Friendliness|
|------|------------|
|false |0           |
|true  |1           |