# mouseandkeylogger

A Processing.org library for logging global mouse and key events even when the sketch is not in focus.

This library is basically a wrapper for the [jnativehook](https://github.com/kwhat/jnativehook) project.

## dependencies

* [Processing.org](https://github.com/processing)
* [jnativehook](https://github.com/kwhat/jnativehook)

## versions

### 003 20150225

* added a windows x86 version of the native library ( thx to Håvard Lundberg from CIID )

### 002 20150222

* added a method to read the pixel color at a certain position ( via java.awt.Robot )
* added methods to get the actual screen width and height of the host machine ( via java.awt.Robot )

### 001 20150220

* added distribution shell scripts
* added examples
* initial release
