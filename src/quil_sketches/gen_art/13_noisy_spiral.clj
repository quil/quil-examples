(ns quil-sketches.gen-art.13-noisy-spiral
  (:require
   [quil.core :as q]
   [quil.helpers.calc :refer [mul-add]]
   [quil.helpers.drawing :refer [line-join-points]]
   [quil.helpers.seqs :refer [range-incl steps]]))

;; Example 13 - Noisy Spiral
;; Taken from Listing 4.3, p69

;; void setup() {
;;   size(500,300);
;;   background(255);
;;   strokeWeight(5);
;;   smooth();
;;   float radius = 100;
;;   int centX = 250;
;;   int centY = 150;
;;   stroke(0, 30);
;;   noFill();
;;   ellipse(centX,centY,radius*2,radius*2);
;;   stroke(20, 50, 70);

;;   radius = 10;
;;   float x, y;
;;   float lastx = -999;
;;   float lasty = -999;
;;   float radiusNoise = random(10);
;;   for(float ang=0;ang<=1440;ang+=5){
;;     radiusNoise += 0.05;
;;     radius += 0.5;
;;     float thisRadius = radius + (noise(radiusNoise) * 200) - 100;
;;     float rad = radians(ang);
;;     x = centX + (thisRadius * cos(rad));
;;     y = centY + (thisRadius * sin(rad));
;;     if (lastx > -999) {
;;       line(x,y,lastx,lasty);
;;     }
;;     lastx = x;
;;     lasty = y;
;;   }
;; }

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)
  (let [radius    100
        cent-x    250
        cent-y    150
        rad-noise (steps (rand 10) 0.05)
        rad-noise (map #(* 200 (q/noise %)) rad-noise)
        rads      (map q/radians (range-incl 0 1440 5))
        radii     (steps 10 0.5)
        radii     (map (fn [rad noise] (+ rad noise -100)) radii rad-noise)
        xs        (map (fn [rad radius] (mul-add (q/cos rad) radius cent-x)) rads radii)
        ys        (map (fn [rad radius] (mul-add (q/sin rad) radius cent-y)) rads radii)
        line-args (line-join-points xs ys)]
    (q/stroke 0 30)
    (q/no-fill)
    (q/ellipse cent-x cent-y (* radius 2) (* radius 2))
    (q/stroke 20 50 70)
    (dorun (map #(apply q/line %) line-args))))

(q/defsketch gen-art-13
  :title "Noisy Spiral"
  :setup setup
  :size [500 300])

(defn -main [& args])
