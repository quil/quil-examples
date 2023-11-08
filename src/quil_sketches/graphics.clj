(ns quil-sketches.graphics
  (:require [quil.core :as q]
            [quil.helpers.drawing :refer [line-join-points]]
            [quil.helpers.seqs :refer [steps]]))

;; Example of using graphics via create-graphics and with-graphics. On each
;; iteration 1 spiral will be drawn on graphics and then we tile all screen
;; using this graphics. Graphics created in setup funcion and stored in state.
;; Spiral is drawn by draw-spiral function that uses standard draw functions. If
;; draw-spiral function invoked inside 'with-graphics' macro then spiral will be
;; drawn on given graphics, otherwise spiral is drawon on applet.

(def spiral-size 100)

(def cent-x (/ spiral-size 2))
(def cent-y (/ spiral-size 2))


(defn draw-spiral
  "Draws spiral on current surface: on applet or on graphics if inside
  with-graphics macro."
  []
  (q/with-translation [cent-x cent-y]
    (q/with-rotation [(/ (q/frame-count) -5 Math/PI)]
      (q/background 255)
      (q/stroke-weight 2)
      (q/smooth)
      (let [radius 20
            radians (map q/radians (steps 0 5))
            radii (range 5 (/ spiral-size 2) 0.1)
            xs (map (fn [radians radius] (* radius (q/cos radians))) radians radii)
            ys (map (fn [radians radius] (* radius (q/sin radians))) radians radii)
            line-args (line-join-points xs ys)]
        (q/stroke 0 30)
        (q/no-fill)
        (q/ellipse cent-x cent-y (* radius 2) (* radius 2))
        (q/stroke 20 50 70)
        (dorun (map #(apply q/line %) line-args))))))

(defn setup
  "Create graphics in setup and store it in state."
  []
  (let [gr (q/create-graphics spiral-size spiral-size :java2d)]
    (q/set-state! :spiral gr)))

(defn draw []
  (let [gr (q/state :spiral)]
    ;; Draw spiral on graphics.
    (q/with-graphics gr
      (draw-spiral))
    ;; Tile screen with spirals using graphics.
    (doseq [x (range 0 (q/width) spiral-size)
            y (range 0 (q/height) spiral-size)]
      (q/image gr x y))))

(q/defsketch graphics
  :title "Graphics"
  :setup setup
  :draw draw
  :size [500 500])

(defn -main [& args])
