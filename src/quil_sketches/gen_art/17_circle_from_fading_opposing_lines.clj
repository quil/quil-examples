(ns quil-sketches.gen-art.17-circle-from-fading-opposing-lines
  (:require [quil.core :as q]
            [quil.helpers.seqs :refer [range-incl]]
            [quil.helpers.calc :refer [mul-add]]))

;; Example 17 - Circle from Fading Opposing Lines
;; Taken from Section 4.2, p79 (Figure 4.12)

;; void setup() {
;;   size(500,300);
;;   background(255);
;;   strokeWeight(0.5);
;;   smooth();
;;   float radius = 130;
;;   int centX = 250;
;;   int centY = 150;
;;   float x1, y1, x2, y2;
;;   float lastx = -999;
;;   float lasty = -999;
;;   int strokeCol = 255;
;;   for (float ang = 0; ang <= 360; ang += 1) {
;;     float rad = radians(ang);
;;     x1 = centX + (radius * cos(rad));
;;     y1 = centY + (radius * sin(rad));
;;     float opprad = rad + PI;
;;
;;     x2 = centX + (radius * cos(opprad));
;;     y2 = centY + (radius * sin(opprad));
;;     strokeCol -= 1;
;;     if (strokeCol < 0) {
;;       strokeCol = 255;
;;     }
;;     stroke(strokeCol);
;;
;;     line(x1,y1, x2, y2);
;;   }
;; }


(defn setup []
  (q/background 255)
  (q/stroke-weight 0.5)
  (q/smooth)
  (q/stroke 20 50 70)
  (let [radius   130
        cent-x   250
        cent-y   150
        angles   (range-incl 0 360)
        rads     (map q/radians angles)
        opp-rads (map + rads (repeat q/PI))
        colours  (cycle (range-incl 255 0 -1))
        x1s      (map #(mul-add (q/cos %) radius cent-x) rads)
        y1s      (map #(mul-add (q/sin %) radius cent-y) rads)
        x2s      (map #(mul-add (q/cos %) radius cent-x) opp-rads)
        y2s      (map #(mul-add (q/sin %) radius cent-y) opp-rads)]
    (doall (map (fn [x1 y1 x2 y2 col]
                  (q/stroke col)
                  (q/line x1 y1 x2 y2))
                x1s y1s x2s y2s colours))))

(q/defsketch gen-art-17
  :title "Circle from Fading Opposing Lines"
  :setup setup
  :size [500 300])

(defn -main [& args])
