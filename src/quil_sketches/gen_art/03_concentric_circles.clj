(ns quil-sketches.gen-art.03-concentric-circles
  (:require [quil.core :as q]))

;; Example 3 - Concentric circles drawn using traces
;; Taken from Listing 2.3, p37

;; int diam = 10;
;; float centX, centY;

;; void setup() {
;;   size(500, 300);
;;   frameRate(24);
;;   smooth();
;;   background(180);
;;   centX = width/2;
;;   centY = height/2;
;;   stroke(0);
;;   strokeWeight(1);
;;   noFill();
;; }

;; void draw() {
;;   if(diam <= 400) {
;;     ellipse(centX, centY, diam, diam);
;;     diam += 10;
;;   }
;; }

(defn setup []
  (q/frame-rate 24)
  (q/smooth)
  (q/background 180)
  (q/stroke 0)
  (q/stroke-weight 1)
  (q/no-fill)
  (q/set-state! :diam (atom 10)
                :cent-x (/ (q/width) 2)
                :cent-y (/ (q/height) 2)))

(defn draw []
  (let [cent-x (q/state :cent-x)
        cent-y (q/state :cent-y)
        diam   (q/state :diam)]
    (when (<= @diam 400)
      (q/ellipse cent-x cent-y @diam @diam)
      (swap! diam + 10))))

(q/defsketch gen-art-3
  :title "Concentric Circles"
  :setup setup
  :draw draw
  :size [500 300])

(defn -main [& args])
