(ns quil-sketches.gen-art.27-noise-perspective
  (:require
   [quil.core :as q]
   [quil.helpers.calc :refer [mul-add]]
   [quil.helpers.seqs :refer [indexed-range-incl seq->stream steps]]))

;; Example 27 - 2D Noise from a 3D Perspectivea
;; Taken from Listing 5.5, p95

;; import processing.opengl.*;

;; float xstart, xnoise, ystart, ynoise;

;; void setup() {
;;   size(500, 300, OPENGL);
;;   background(0);
;;   sphereDetail(8);
;;   noStroke();

;;   xstart = random(10);
;;   ystart = random(10);
;; }

;; void draw() {
;;   background(0);
;;   xstart += 0.01;
;;   ystart += 0.01;

;;   xnoise = xstart;
;;   ynoise = ystart;

;;   for(int y = 0; y <= height; y+=5){
;;     ynoise += 0.1;
;;     xnoise = xstart;
;;     for(int x = 0; x <= width; x += 5){
;;       xnoise += 0.1;
;;       drawPoint(x, y, noise(xnoise, ynoise));
;;     }
;;   }
;; }

;; void drawPoint(float x, float y, float noiseFactor){
;;   pushMatrix();
;;   translate(x, 250 - y, -y);
;;   float sphereSize = noiseFactor * 35;
;;   float grey = 150 + (noiseFactor * 120);
;;   float alph = 150 + (noiseFactor * 120);
;;   fill(grey, alph);
;;   sphere(sphereSize);
;;   popMatrix();
;; }


(defn draw-point
  [x y noise-factor]
  (q/push-matrix)
  (q/translate x (- 250 y) (* -1 y))
  (let [sphere-size (* noise-factor 35)
        grey        (mul-add noise-factor 120 150)
        alph        grey]
    (q/fill grey alph)
    (q/sphere sphere-size)
    (q/pop-matrix)))

(defn draw []
  (q/background 0)
  (let [[x-shift y-shift] ((q/state :shifts))]
    (doseq [[x-idx x] (indexed-range-incl 0 (q/width) 5)
            [y-idx y] (indexed-range-incl 0 (q/height) 5)]
      (let [y-noise (mul-add y-idx 0.1 y-shift)
            x-noise (mul-add x-idx 0.1 x-shift)]
        (draw-point x y (q/noise x-noise y-noise))))))

(defn setup []
  (q/smooth)
  (q/background 0)

  (q/sphere-detail 8)
  (q/no-stroke)
  (let [x-shifts (steps (q/random 10) 0.01)
        y-shifts (steps (q/random 10) 0.01)
        shifts   (map list x-shifts y-shifts)]
    (q/set-state! :shifts (seq->stream shifts))))

(q/defsketch gen-art-27
  :title "2D Noise from a 3D Perspective"
  :setup setup
  :draw draw
  :size [500 300]
  :renderer :opengl)

(defn -main [& args])
