(defproject quil-sketches "0.1.0-SNAPSHOT"
  :description "Examples of using Quil library"
  :url "https://github.com/quil/quil-examples"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
		  [quil "1.7.0"]]
;; put the namespace of the example You wish to run after :main in next line 
   :main quil_sketches.gen-art.01-cross-with-circle)
