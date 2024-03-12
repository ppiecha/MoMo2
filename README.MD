# MoMo2

> Midi way to compose your own music in Scala.

MIDI sequencer (console app) written in Scala.\
Implementation using Akka.

Backend uses Java MIDI package \
https://docs.oracle.com/javase/tutorial/sound/overview-MIDI.html

## Main idea
- composition (midi tracks) is stored as a configuration file in HOCON format
- MIDI events (timing, pitches etc.) are defined in this file as separated sequences (iterators) using Scala code
- at runtime composition is parsed. Sequences are compiled, evaluated and zipped to form proper MIDI events 
which are then played by sequencer (design inspired by [SuperCollider](https://supercollider.github.io/))
- app implements set of convenient functions (API available for user at runtime) 
to perform sequence transformations commonly used when composing music (concatenation, loops, randomization etc.). \
This API is crucial to allow user to express ideas concisely without any limits (using power of Scala language). 
API should allow to make composition process as easy and fun as possible.
- any common musical pattern can be defined once and then reused using `HOCON substitution`. 
It should be also possible to extract common patterns to external HOCON file. 
Those patterns can be then imported into any composition using `HOCON include` feature.     



Example of composition
```
name = "Test"
comp.bpm = 1
comp.tracks = [{
    channel = 0
    instrument = 1
    versions = [{
      scale.degrees = [2, 3, 5, 7, 9, 10]
      scale.rootMidiNote = 30
      scaleNote = """
        val p1 = seq(Seq(0, 0, 7, 0, 6, 7, 0, 0))
        val p2 = seq(Seq(2, 2, 4, 4, 2, 0, 0, 0))
        (p1 ++ p2).inf
      """
      timing = "seq(Seq(0)) ++ seq(Seq(8), -1)"
    }]
  }]
```

## TO DO
- finish readme
- actors diagram

## Features
- todo

### Requirements
- jdk >= 17
- sbt >= 1.9.7


### Installation
- todo

### Running
```commandline
todo
```

### Testing (sbt)
Non-audio tests
```bash
test
```
### Code formatting (sbt)
```commandline
scalafmt
```
### Before commit (sbt)
```commandline
;scalafmt ;test
```