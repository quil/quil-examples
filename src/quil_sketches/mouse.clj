(ns quil-sketches.mouse
  (:require [quil.core :as q]))

(defn setup []
  (q/smooth)
  (q/no-stroke)
  (q/set-state! :mouse-position (atom [0 0])))

(defn draw
  []
  (q/background 125)
  (q/stroke-weight 20)
  (q/with-stroke 10
    (let [[x y] @(q/state :mouse-position)]
      (q/point x y))))

(defn mouse-moved []
  (let [x (q/mouse-x)
        y (q/mouse-y)]
    (reset! (q/state :mouse-position) [x y])))

(q/defsketch mouse-example
  :title "Mouse example."
  :size [200 200]
  :setup setup
  :draw draw
  :mouse-moved mouse-moved)

(defn -main [& args])
