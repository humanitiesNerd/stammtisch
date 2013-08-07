# stammtisch

This is a words counter. It just reads a large text file and counts words in it. The file is supposed to be larger than the available ram on the machine it´s running on.

This was an exercise for a meeting (in German: Stammtisch) about functional languages, held in Berlin in the winter of 2013 [this one] (http://www.meetup.com/thefunclub/events/104441382/).

At august 2013, it counts words in 2 different ways.

A "vanilla" way, with a plain simple java reader reading the file sequentially.

Then the so called "Meikel´s version" based on the reducers library.

I used [iota] (https://github.com/thebusby/iota#readme) to represent the file as a vector-like structure so that the reducers library can be used on it.

I called it "Meikel´s version" because [Meikel Brandmeyer] (https://twitter.com/kotarak) suggested me the right way to call fold on the clojure mailing list.

## Usage

Usage:
load this namespace ( (use 'stammtisch.core) ) and then type either one of these lines
* (-main "path/to/the/file.txt" "vanilla")
* (-main "path/to/the/file.txt" "meikel")
* (-main "path/to/the/file.txt" "vanilla" "-b")
* (-main "path/to/the/file.txt" "meikel" "-b")

Of course you can build a jar with lein and call that.

## License

Copyright © 2013 Catonano catonano@gmail.com

Distributed under the Eclipse Public License, the same as Clojure.
