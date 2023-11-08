(ns quil-sketches.gen-art.16-circle-from-opposing-lines
  (:require [quil.core :as q]
            [quil.helpers.seqs :refer [range-incl]]
            [quil.helpers.calc :refer [mul-add]]))

;; Example 16 - Circle from Opposing Lines
;; Taken from Section 4.2, p76

;; void setup() {
;;   size(500,300);
;;   background(255);
;;   strokeWeight(0.5);
;;   smooth();
;;   float radius = 130;
;;   int centX = 250;
;;   int centY = 150;
;;   stroke(20, 50, 70);
;;   float x1, y1, x2, y2, opprad;
;;   float lastx = -999;
;;   float lasty = -999;
;;   for (float ang = 0; ang <= 360; ang += 1) {
;;     float rad = radians(ang);
;;     x1 = centX + (radius * cos(rad));
;;     y1 = centY + (radius * sin(rad));
;;     opprad = rad + PI;
;;
;;     x2 = centX + (radius * cos(opprad));
;;     y2 = centY + (radius * sin(opprad));
;;     line(x1,y1, x2, y2);
;;   }
;; }

(defn setup []
  (q/background 255)
  (q/stroke-weight 0.5)
  (q/smooth)
  (q/no-fill)
  (q/stroke 20 50 70)
  (let [radius   130
        cent-x   250
        cent-y   150
        angles   (range-incl 0 360)
        rads     (map q/radians angles)
        opp-rads (map + rads (repeat q/PI))
        x1s      (map #(mul-add (q/cos %) radius cent-x) rads)
        y1s      (map #(mul-add (q/sin %) radius cent-y) rads)
        x2s      (map #(mul-add (q/cos %) radius cent-x) opp-rads)
        y2s      (map #(mul-add (q/sin %) radius cent-y) opp-rads)]
    (doall (map q/line x1s y1s x2s y2s))))


(q/defsketch gen-art-16
  :title "Circle from Opposing Lines"
  :setup setup
  :size [500 300])

(defn -main [& args])
