(ns quil-sketches.gen-art.07-perlin-noise-scribble
  (:require [quil.core :as q]
            [quil.helpers.drawing :refer [line-join-points]]
            [quil.helpers.seqs :refer [range-incl perlin-noise-seq]]
            [quil.helpers.calc :refer [mul-add]]))

;; Example 7 - Perlin Noise Scribblea
;; Taken from Listing 3.1, p59

;; void setup() {
;;  size(500, 100);
;;  background(255);
;;  strokeWeight(5);
;;  smooth();

;;  stroke(0, 30);
;;  line(20, 50, 480, 50);

;;  stroke(20, 50, 70);
;;  int step = 10;
;;  float lastx = -999;
;;  float lasty = -999;
;;  float ynoise = random(10);
;;  float y;
;;  for (int x = 20 ; x <= 480 ; x += step){
;;    y = 10 + noise(ynoise) * 80;
;;    if(lastx > -999) {
;;      line(x, y, lastx, lasty);
;;    }
;;    lastx = x;
;;    lasty =y;
;;    ynoise += 0.1;
;;  }
;; }

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)

  (q/stroke 0 30)
  (q/line 20 50 480 50)

  (q/stroke 20 50 70)
  (let [step      10
        seed      (rand 10)
        seed-incr 0.1
        y-mul     80
        y-add     10
        border-x  20
        xs        (range-incl border-x (- (q/width) border-x) step)
        ys        (perlin-noise-seq seed seed-incr)
        scaled-ys (mul-add ys y-mul y-add)
        line-args (line-join-points xs scaled-ys)]
    (dorun (map #(apply q/line %) line-args))))

(q/defsketch gen-art-7
  :title "Perlin Noise Scribble"
  :setup setup
  :size [500 100])

(defn -main [& args])
