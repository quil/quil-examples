(ns quil-sketches.gen-art.05-random-scribble
  (:require [quil.core :as q]
            [quil.helpers.seqs :refer [range-incl]]
            [quil.helpers.drawing :refer [line-join-points]]))

;; Example 5 - Random Scribble
;; Taken from Section 3.2, p55

;; void setup() {
;;   size(500, 100);
;;   background(255);
;;   strokeWeight(5);
;;   smooth();
;;   stroke(0, 30);
;;   line(20, 50, 480, 50);
;;   stroke(20, 50, 70);

;;   int step = 10;
;;   float lastx = -999;
;;   float lasty = -999;
;;   float y = 50;
;;   int borderx = 20;
;;   int bordery = 10;
;;   for(int x = borderx; x <= width - borderx; x += step){
;;     y = bordery + random(height - 2* bordery);
;;     if(lastx > -999) {
;;       line(x, y, lastx, lasty);
;;     }
;;   lastx = x;
;;   lasty = y;
;;   }
;; }

(defn rand-y
  [border-y]
  (+ border-y (rand (- (q/height) (* 2 border-y)))))

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)
  (q/stroke 0 30)
  (q/line 20 50 480 50)

  (q/stroke 20 50 70)
  (let [step      10
        border-x  20
        border-y  10
        xs        (range-incl border-x (- (q/width) border-x) step)
        ys        (repeatedly #(rand-y border-y))
        line-args (line-join-points xs ys)]
    (dorun (map #(apply q/line %) line-args))))


(q/defsketch example-5
  :title "Random Scribble"
  :setup setup
  :size [500 100])

(defn -main [& args])
