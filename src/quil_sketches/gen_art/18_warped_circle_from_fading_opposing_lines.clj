(ns quil-sketches.gen-art.18-warped-circle-from-fading-opposing-lines
  (:require [quil.core :as q]
            [quil.helpers.seqs :refer [range-incl steps]]
            [quil.helpers.calc :refer [mul-add]]))

;; Example 18 - Warped Circle from Fading Opposing Lines
;; Taken from Section 4.2, p79 (Figure 4.13)

;; void setup() {
;;   size(500,300);
;;   background(255);
;;   smooth();
;;   float radius;
;;   int centX = 250;
;;   int centY = 150;
;;   float x1, y1, x2, y2;
;;   float lastx = -999;
;;   float lasty = -999;
;;   int strokeCol = 255;
;;   float radiusNoise = random(10);
;;   for (float ang = 0; ang <= 360; ang += 1) {
;;     float rad = radians(ang);
;;     radius = (noise(radiusNoise) * 400) + 1;
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
;;     radiusNoise += 0.005;
;;   }
;; }

(defn setup []
  (q/background 255)
  (q/stroke-weight 0.5)
  (q/smooth)
  (q/stroke 20 50 70)
  (let [cent-x    250
        cent-y    150
        angles    (range-incl 0 360)
        rads      (map q/radians angles)
        opp-rads  (map + rads (repeat q/PI))
        colours   (cycle (range-incl 255 0 -1))
        rad-noise (steps (q/random 10) 0.005)
        radii     (map q/noise rad-noise)
        radii     (mul-add radii 400 1)
        x1s       (map (fn [radius rad] (mul-add (q/cos rad) radius cent-x)) radii rads)
        y1s       (map (fn [radius rad] (mul-add (q/sin rad) radius cent-y)) radii rads)
        x2s       (map (fn [radius rad] (mul-add (q/cos rad) radius cent-x)) radii opp-rads)
        y2s       (map (fn [radius rad] (mul-add (q/sin rad) radius cent-y)) radii opp-rads)]
    (doall (map (fn [x1 y1 x2 y2 col]
                  (q/stroke col)
                  (q/line x1 y1 x2 y2))
                x1s y1s x2s y2s colours))))

(q/defsketch gen-art-18
  :title "Warped Circle from Fading Opposing Lines"
  :setup setup
  :size [500 300])

(defn -main [& args])
