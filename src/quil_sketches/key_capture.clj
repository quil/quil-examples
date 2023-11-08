(ns quil-sketches.key-capture
  (:require [quil.core :as q]))

(def current-text-size (atom 20))

(def params
  {:big-text-size 30
	 :background-color 25
	 :foreground-color 200})

(defn setup []
  (q/smooth)
  (q/no-stroke)
  (q/set-state! :message (atom "Click on screen and type a key")))

(defn draw
  []
  (q/background (params :background-color))
  (q/stroke-weight 20)
  (q/stroke 10)
  (q/fill (params :foreground-color))
  (q/text-size @current-text-size)
  (q/text @(q/state :message) 20 60))

(defn key-press []
	(let [big-text-size (params :big-text-size)]
	  (when (< @current-text-size big-text-size)
      (reset! current-text-size big-text-size))
	  (reset! (q/state :message) (str "Key pressed: " (q/raw-key)))))

(q/defsketch key-listener
  :title "Keyboard listener example"
  :size [400 100]
  :setup setup
  :draw draw
  :key-typed key-press)

(defn -main [& args])
