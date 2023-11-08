(ns quil-sketches.gen-art.08-sine-wave
  (:require
   [quil.core :as q]
   [quil.helpers.calc :refer [mul-add]]
   [quil.helpers.drawing :refer [line-join-points]]
   [quil.helpers.seqs :refer [range-incl]]))

;; Example 8 - Sine Wave
;; Taken from Listing 3.2, p60

;; void setup() {
;;  size(500, 100);
;;  background(255);
;;  strokeWeight(5);
;;  smooth();
;;  stroke(0, 30);
;;  line(20, 50, 480, 50);
;;  stroke(20, 50, 70);

;;  float xstep = 1;
;;  float lastx = -999;
;;  float lasty = -999;
;;  float angle = 0;
;;  float y = 50;
;;  for(int x=20; x<=480; x+=xstep){
;;    float rad = radians(angle);
;;    y = 50 + (sin(rad) * 40);
;;    if(lastx > -999) {
;;      line(x, y, lastx, lasty);
;;    }
;;    lastx = x;
;;    lasty = y;
;;    angle++;
;;  }
;; }


(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)
  (q/stroke 0 30)
  (q/line 20 50 480 50)
  (q/stroke 20 50 70)

  (let [xs        (range-incl 20 480 1)
        rads      (map q/radians (range))
        ys        (map q/sin rads)
        scaled-ys (mul-add ys 40 50)
        line-args (line-join-points xs scaled-ys)]
    (dorun (map #(apply q/line %) line-args))))

(q/defsketch gen-art-8
  :title "Sine Wave"
  :setup setup
  :size [500 100])

(defn -main [& args])
