(ns quil-sketches.example2
  (:require [quil.core :as q]))

;; here's a function which will be called by Processing's (PApplet)
;; draw method every frame. Place your code here. If you eval it
;; interactively, you can redefine it while the applet is running and
;; see effects immediately

(defn draw
  "Example usage of with-translation and with-rotation."
  []
  (q/background 125)
  (q/stroke 10)
  (q/fill (rand-int 125) (rand-int 125) (rand-int 125))
  (q/with-translation [(/ 200 2) (/ 200 2)]
    (q/with-rotation [q/QUARTER-PI]
      (q/begin-shape)
      (q/vertex -50  50)
      (q/vertex  50  50)
      (q/vertex  50 -50)
      (q/vertex -50 -50)
      (q/end-shape :close)))
  (q/display-filter :invert))

(defn setup
  "Runs once."
  []
  (q/smooth)
  (q/no-stroke)
  (q/fill 226)
  (q/frame-rate 10))

;; Now we just need to define an applet:
(q/defsketch example2
  :title "An example."
  :setup setup
  :draw draw
  :size [200 200])

(defn -main [& args])
