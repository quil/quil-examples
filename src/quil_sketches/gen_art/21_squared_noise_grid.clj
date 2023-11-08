(ns quil-sketches.gen-art.21-squared-noise-grid
  (:require
   [quil.core :as q]
   [quil.helpers.calc :refer [mul-add]]
   [quil.helpers.seqs :refer [range-incl]]))

;; Example 21 - Squared 2D Noise Grid
;; Taken from Listing 5.2, p86

;; float xstart, xnoise, ynoise;
;;
;; void setup() {
;;   size(300, 300);
;;   smooth();
;;   background(255);
;;   xstart = random(10);
;;   xnoise = xstart;
;;   ynoise = random(10);
;;   for(int y = 0; y <= height; y+=5) {
;;     ynoise += 0.1;
;;     xnoise = xstart;
;;     for(int x = 0; x <= width; x+=5) {
;;       xnoise += 0.1;
;;       drawPoint(x, y, noise(xnoise, ynoise));
;;     }
;;   }
;; }

;; void drawPoint(float x, float y, float noiseFactor) {
;;   float len = 10 * noiseFactor;
;;   rect(x, y, len, len);
;; }

(defn draw-point
  [x y noise-factor]
  (let [len (* 10 noise-factor)]
    (q/rect x y len len)))

(defn draw-squares
  [x-start y-start]
  (dorun
   (for [y (range-incl 0 (q/height) 5)
         x (range-incl 0 (q/width) 5)]
     (let [x-noise (mul-add x 0.01 x-start)
           y-noise (mul-add y 0.01 y-start)]
       (draw-point x y (q/noise x-noise y-noise))))))

(defn setup []
  (q/smooth)
  (q/background 255)
  (draw-squares (q/random 10) (q/random 10)))

(q/defsketch gen-art-21
  :title "Squared 2D Noise Grid"
  :setup setup
  :size [300 300])

(defn -main [& args])
