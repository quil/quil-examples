(ns quil-sketches.mouse-wheel
  (:require [quil.core :as q]
            [quil.helpers.drawing :refer [line-join-points]]))

(def radius 100)

(defn setup []
  (q/background 255)
  (q/set-state! :zoom (atom 1))
  (q/stroke 0))

(defn draw []
  (q/background 255)
  (q/translate (/ (q/width) 2.0) (/ (q/height) 2.0))
  (q/scale @(q/state :zoom))
  (q/rotate-y (* (q/mouse-x) 0.02))
  (q/rotate-x (* (q/mouse-y) 0.02))
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

(defn mouse-wheel [rotation]
  (swap! (q/state :zoom) #(max 1 (- % (* 0.1 rotation)))))

(q/defsketch gen-art-29
  :title "Spiral Sphere"
  :setup setup
  :draw draw
  :size [500 300]
  :renderer :p3d
  :mouse-wheel mouse-wheel)

(defn -main [& args])

