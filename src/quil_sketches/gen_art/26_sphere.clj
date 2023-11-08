(ns quil-sketches.gen-art.26-sphere
  (:require [quil.core :as q]))

;; Example 26 - 3D Sphere
;; Taken from Section 5.3.1, p94

;; import processing.opengl.*;

;; void setup() {
;;   size(500, 300, OPENGL);
;;   sphereDetail(40);

;;   translate(width/2, height/2.0);
;;   sphere(100);
;; }

(defn draw []
  (q/smooth)
  (q/sphere-detail 40)
  (q/translate (/ (q/width) 2) (/ (q/height) 2))
  (q/sphere 100))

(q/defsketch gen-art-26
  :title "3D Sphere"
  :draw draw
  :size [500 300]
  :renderer :opengl)

(defn -main [& args])
