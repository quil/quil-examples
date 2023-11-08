(ns quil-sketches.gen-art.04-fading-horizontal-lines
  (:require [quil.core :as q]))

;; Example 4 - Fading Horizontal Lines
;; Taken from Section 2.4.3, p 39

;; void setup() {
;;   size(500, 300);
;;   background(180);
;;   strokeWeight(4);
;;   strokeCap(SQUARE);
;;   for(int h = 10; h <= (height - 15); h+=10){
;;     stroke(0, 255-h);
;;     line(10, h, width - 20, h);
;;     stroke(255, h);
;;     line(10, h+4, width - 20, h+4);
;;   }
;; }

(defn draw-line
  "Draws a horizontal line on the canvas at height h"
  [h]
  (q/stroke 0 (- 255 h))
  (q/line 10 h (- (q/width) 20) h)
  (q/stroke 255 h)
  (q/line 10 (+ h 4) (- (q/width) 20) (+ h 4)))

(defn setup []
  (q/background 180)
  (q/stroke-weight 4)
  (q/stroke-cap :square)
  (let [line-heights (range 10 (- (q/height) 15) 10)]
    (dorun (map draw-line line-heights))))

(q/defsketch example-4
  :title "Fading Horizontal Lines"
  :setup setup
  :size [500 300])

(defn -main [& args])
