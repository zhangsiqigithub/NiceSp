# NiceSp
A quickly useful Shared preferences tool

## Advantage
This tool can instantiates a sharedpreference file, you don't need to maintain the sp table names and the sp keys.

## Instructions
1. Use @SpTable annotate an interface to create a sharedpreference file.
2. Define some method in this interface, the method name is the default sharedpreference key or you can annotate the method
with @SpKey to modify the key.
3. All the methods in your interface should return an Option with the generic paradigm of some type supported by shared preference
4. The Option object can get and set the sp value.
5. Use SpManager.provideInstance() to create an instance of your sharedprefenrence table object.


[![](https://jitpack.io/v/helen-x/JitpackReleaseDemo.svg)](https://jitpack.io/#helen-x/JitpackReleaseDemo)
