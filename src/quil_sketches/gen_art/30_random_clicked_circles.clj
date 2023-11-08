(ns quil-sketches.gen-art.30-random-clicked-circles
  (:require [quil.core :as q]))

;; Example 30 - Random Clicked Circles
;; Taken from Listing 6.1, p113

;; int _num = 10;

;; void setup() {
;;   size(500, 300);
;;   background(255);
;;   smooth();
;;   strokeWeight(1);
;;   fill(150, 50);
;;   drawCircles();
;; }

;; void draw() {
;; }

;; void mouseReleased() {
;;   drawCircles();
;; }

;; void drawCircles() {
;;   for(int i = 0; i < _num; i++) {
;;     float x = random(width);
;;     float y = random(height);
;;     float radius = random(100) + 10;
;;     noStroke();
;;     ellipse(x, y, radius * 2, radius * 2);
;;     stroke(0, 150);
;;     ellipse(x, y, 10, 10);
;;   }
;; }

(def num-circles 10)

(defn draw-circles []
  (dorun
   (for [_ (range 0 num-circles)]
     (let [x      (q/random (q/width))
           y      (q/random (q/height))
           radius (+ (q/random 100) 10)]
       (q/no-stroke)
       (q/ellipse x y (* 2 radius) (* 2 radius))
       (q/stroke 0 150)
       (q/ellipse x y 10 10)))))

(defn setup []
  (q/background 255)
  (q/smooth)
  (q/stroke-weight 1)
  (q/fill 150 50)
  (draw-circles))

(q/defsketch gen-art-30
  :title "Random Clicked Circles"
  :setup setup
  :mouse-released draw-circles
  :size [500 300])

(defn -main [& args])
