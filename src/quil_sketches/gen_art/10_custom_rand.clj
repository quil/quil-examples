(ns quil-sketches.gen-art.10-custom-rand
  (:require [quil.core :as q]
            [quil.helpers.drawing :refer [line-join-points]]
            [quil.helpers.seqs :refer [range-incl]]
            [quil.helpers.calc :refer [mul-add]]))

;; Example 10 - Custome Random Function
;; Taken from Listing 3.2, p60

;; float customRandom() {
;;   float retValue = 1 - pow(random(1), 5); return retValue;
;; }

;; void setup() {
;;  size(500, 100);
;;  background(255);
;;  strokeWeight(5);
;;  smooth();
;;  stroke(0, 30);
;;  line(20, 50, 480, 50);
;;  stroke(20, 50, 70);

;;  float xstep = 5;
;;  float lastx = -999;
;;  float lasty = -999;
;;  float angle = 0;
;;  float y = 50;
;;  for(int x=20; x<=480; x+=xstep){
;;    float rad = radians(angle);
;;    y = 20 + (customRandom() * 60);
;;    if(lastx > -999) {
;;      line(x, y, lastx, lasty);
;;    }
;;    lastx = x;
;;    lasty = y;
;;    angle++;
;;  }
;; }

(defn custom-rand
  []
  (- 1 (q/pow (q/random 1) 5)))

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)
  (q/stroke 0 30)
  (q/line 20 50 480 50)
  (q/stroke 20 50 70)

  (let [xs        (range-incl 20 480 5)
        ys        (repeatedly custom-rand)
        scaled-ys (mul-add ys 60 20)
        line-args (line-join-points xs scaled-ys)]

    (dorun (map #(apply q/line %) line-args))))

(q/defsketch gen-art-10
  :title "Custom Random Function"
  :setup setup
  :size [500 100])

(defn -main [& args])
