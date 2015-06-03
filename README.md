# BibleVerses

This is my first Clojure program.

The idea is to use the web search engine API to get the number of web search results for each Verse from the Holy Bible.

I'm using Bing Search API because it has more reasonable free usage offer (5000 queries / month).
I've already used my first month's limit and some results are included (exactly Verses from 1 to 5868 at the time of writing).
~~In the following months I plan to add more results and then generate report.~~
[The generated report is now available.](https://raw.githubusercontent.com/PawelStroinski/BibleVerses/master/BibleVerses.txt)

The translation of the Holy Bible I'm using is the King James Version [downloaded from Zefania project](http://sourceforge.net/projects/zefania-sharp) as KJV is still the most poplar translation in use in the English internet.

## Installation

Download this repository [as a zip file](https://github.com/PawelStroinski/BibleVerses/archive/master.zip) and extract it. You will also need [Leiningen](http://leiningen.org/) installed.

## Usage

The first thing you will need to do is to update `src/BibleVerses/query.clj` file to replace `<cut>` in this line

    (def bingSearchAPIAccountKey "<cut>")

with your Bing Search API Key.

Then if you go to the folder with extracted files in command prompt and type `lein run` you will get the following options:

    Please use one of the following commands:
    lein query <VerseNumberStart> <VerseNumberStop>
    lein report

If you type `lein query 5869 5870` this will display output and save it into a file `Verses5869-5870`.
The output is a map of Verses from 5869 to 5870 as keys and numbers of web search results as values.
The numbering of Verses used is just as in a single list from the KJV.

Then if you type `lein report` it will look for files produced by `lein query`, merge, sort them decrementally and save into a file `BibleVerses.txt`. The first line in the report is a Verse, then number of web search results in the next line, and this repeated for each Verse.

## License

Copyright © 2014 Paweł Stroiński

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
