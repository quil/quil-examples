Quil examples
=============

Examples of different Quil sketches.

Check `src/quil_sketches` folder and it's subfolders. Each `.clj` file is a separate sketch. Some of them shows particular Quil feature and some of them are simply beatiful animations. You can start by running them one by one and studying their sources.

### How to run examples

Clone repo with examples to you machine:

```shell
git clone https://github.com/quil/quil-examples.git
```

##### Using lein REPL

Start REPL:
```shell
lein repl
```
Require selected sketch to run it:
```clojure
user=> (require 'quil-sketches.gen-art.01-cross-with-circle :reload)
```
Note: 
* you should start sketch namespace with single quote: `'quil-sketches.mouse` 
* `:reload` option is needed if you want to run it several times. Otherwise sketch won't run on second and subsequent calls.


##### Using `lein run`

Use following command to run selected sketch:
```shell
lein run -m quil-sketches.gen-art.01-cross-with-circle
```
When you close sketch JVM won't exit, so you need to do it manually e.g. using `Ctrl+C` combination. 

##### Using LightTable

Start LightTable.  
Open folder with quil-sketches project: `File -> Open folder`.  
Open file containing sketch and evaluate it using `Ctrl+Shift+Enter` combination.

##### Using emacs

Open file with sketch:
```shell
emacs src/quil_sketches/gen_art/01_cross_with_circle.clj
```
Start cider:
```
M-x cider-jack-in
```
Evaluate opened file:
```
C-c C-k
```
Sketch will open. 
You can run other sketches by opening their files and evaluate their contents via `C-c C-k`
