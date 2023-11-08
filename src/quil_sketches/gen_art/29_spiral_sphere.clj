(ns quil-sketches.gen-art.29-spiral-sphere
  (:require
   [quil.core :as q]
   [quil.helpers.drawing :refer [line-join-points]]))

;; Example 29 - Spiral Sphere
;; Taken from Listing 5.7, p100

;; import processing.opengl.*;

;; int radius = 100;

;; void setup() {
;;   size(500, 300, OPENGL);
;;   background(255);
;;   stroke(0);
;; }

;; void draw() {
;;   background(255);

;;   translate(width/2, height/2, 0);
;;   rotateY(frameCount * 0.03);
;;   rotateX(frameCount * 0.04);

;;   float s = 0;
;;   float t = 0;
;;   float lastx = 0;
;;   float lasty = 0;
;;   float lastz = 0;

;;   while(t < 180) {
;;     s+= 18;
;;     t+= 1;
;;     float radianS = radians(s);
;;     float radianT = radians(t);

;;     float thisx = 0 + (radius * cos(radianS) * sin(radianT));
;;     float thisy = 0 + (radius * sin(radianS) * sin(radianT));
;;     float thisz = 0 + (radius * cos(radianT));

;;     if (lastx != 0){
;;       line(thisx, thisy, thisz, lastx, lasty, lastz);
;;     }

;;     lastx = thisx;
;;     lasty = thisy;
;;     lastz = thisz;
;;   }
;; }

(def radius 100)

(defn setup []
  (q/background 255)
  (q/stroke 0))

(defn draw []
  (q/background 255)
  (q/translate (/ (q/width) 2) (/ (q/height) 2) 0)
  (q/rotate-y (* (q/frame-count) 0.03))
  (q/rotate-x (* (q/frame-count) 0.04))
  (let [line-args (for [t (range 0 180)]
                    (let [s        (* t 18)
                          radian-s (q/radians s)
                          radian-t (q/radians t)
                          x (* radius (q/cos radian-s) (q/sin radian-t))
                          y (* radius (q/sin radian-s) (q/sin radian-t))
                          z (* radius (q/cos radian-t))]
                      [x y z]))]
    (dorun
     (map #(apply q/line %) (line-join-points line-args)))))


(q/defsketch gen-art-29
  :title "Spiral Sphere"
  :setup setup
  :draw draw
  :size [500 300]
  :renderer :opengl)

(defn -main [& args])
