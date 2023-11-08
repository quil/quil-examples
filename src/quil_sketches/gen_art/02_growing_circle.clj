(ns quil-sketches.gen-art.02-growing-circle
  (:require [quil.core :as q]
            [quil.helpers.seqs :refer [seq->stream range-incl]]))

;; Example 2 - Growing Circle
;; Taken from Listing 2.1, p28

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
;;   strokeWeight(5);
;;   fill(255, 50);
;; }

;; void draw() {
;;   if(diam <= 400) {
;;     background(180);
;;     ellipse(centX, centY, diam, diam);
;;     diam += 10;
;;   }
;; }

(defn setup []
  (q/frame-rate 24)
  (q/smooth)
  (q/background 180)
  (q/stroke 0)
  (q/stroke-weight 5)
  (q/fill 255 25)
  (let [diams (range-incl 10 400 10)]
    (q/set-state! :diam (seq->stream diams)
                  :cent-x (/ (q/width) 2)
                  :cent-y (/ (q/height) 2))))

(defn draw []
  (let [cent-x (q/state :cent-x)
        cent-y (q/state :cent-y)
        diam   ((q/state :diam))]
    (when diam
      (q/background 180)
      (q/ellipse cent-x cent-y diam diam))))

(q/defsketch gen-art-2
  :title "Growing circle"
  :setup setup
  :draw draw
  :size [500 300]
  :keep-on-top true)

 (defn -main [& args])
