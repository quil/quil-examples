(ns quil-sketches.gen-art.06-rand-walk-scribble
  (:require [quil.core :as q]
            [quil.helpers.drawing :refer [line-join-points]]
            [quil.helpers.seqs :refer [range-incl]]))

;; Example 6 - Random Walk Scribble
;; Taken from Section 3.2, p56

;; void setup() {
;;   size(500, 100);
;;   background(255);
;;   strokeWeight(5);
;;   smooth();
;;   stroke(0, 30);
;;   line(20, 50, 480, 50);
;;   stroke(20, 50, 70);

;;   float xstep = 10;
;;   float ystep = 10;
;;   float lastx = 20;
;;   float lasty = 50;
;;   float y = 50;
;;   int borderx = 20;
;;   int bordery = 10;
;;   for(int x = borderx; x <= width - borderx; x += xstep){
;;     ystep = random(20) - 10; //range -10 to 10
;;     y += ystep;
;;     line(x, y, lastx, lasty);
;;     lastx = x;
;;     lasty = y;
;;   }
;; }

(defn rand-walk-ys
  [seed]
  (lazy-seq (cons seed (rand-walk-ys (+ seed (- (rand 20) 10))))))

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)
  (q/stroke 0 30)
  (q/line 20 50 480 50)

  (q/stroke 20 50 70)
  (let [step      10
        border-x  20
        xs        (range-incl border-x (- (q/width) border-x) step)
        ys        (rand-walk-ys (/ (q/height) 2))
        line-args (line-join-points xs ys)]
    (dorun (map #(apply q/line %) line-args))))

(q/defsketch gen-art-6
  :title "Random Walk Scribble"
  :setup setup
  :size [500 100])

 (defn -main [& args])
