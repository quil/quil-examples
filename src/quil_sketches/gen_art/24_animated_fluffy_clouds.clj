(ns quil-sketches.gen-art.24-animated-fluffy-clouds
  (:require
   [quil.core :as q]
   [quil.helpers.calc :refer [mul-add]]
   [quil.helpers.seqs :refer [range-incl seq->stream steps]]))

;; Example 24 - Animated Fluffy Clouds
;; Taken from Listing 5.3, p89

;; float xstart, xnoise, ystart, ynoise;

;; void setup() {
;;   size(300, 300);
;;   smooth();
;;   background(0);
;;   frameRate(24);

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
;;     for(int x = 0; x <= width; x+= 5){
;;       xnoise += 0.1;
;;       drawPoint(x, y, noise(xnoise, ynoise));
;;     }
;;   }
;; }

;; void drawPoint(float x, float y, float noiseFactor) {
;;   pushMatrix();
;;   translate(x, y);
;;   rotate(noiseFactor * radians(540));
;;   noStroke();
;;   float edgeSize = noiseFactor * 35;
;;   float grey = 150 + (noiseFactor * 120);
;;   float alph = 150 + (noiseFactor * 120);
;;   fill(grey, alph);
;;   ellipse(0, 0, edgeSize, edgeSize/2);
;;   popMatrix();
;; }

(defn draw-point
  [x y noise-factor]
  (q/push-matrix)
  (q/translate x y)
  (q/rotate (* noise-factor (q/radians 540)))
  (let [edge-size (* noise-factor 35)
        grey (mul-add noise-factor 120 150)
        alph (mul-add noise-factor 120 150)]
    (q/no-stroke)
    (q/fill grey alph)
    (q/ellipse 0 0 edge-size (/ edge-size 2))
    (q/pop-matrix)))

(defn draw-all-points [x-start y-start]
  (let [step-size 5
        x-idxs    (range-incl 0 (/ (q/width) step-size))
        y-idxs    (range-incl 0 (/ (q/height) step-size))]
    (doseq [x-idx x-idxs
            y-idx y-idxs]
      (let [x       (* step-size x-idx)
            y       (* step-size y-idx)
            x-noise (mul-add x-idx 0.1 x-start)
            y-noise (mul-add y-idx 0.1 y-start)]
        (draw-point x y (q/noise x-noise y-noise))))))


(defn draw []
  (q/background 0)
  (let [starts-str        (q/state :starts-str)
        [x-start y-start] (starts-str)]
    (draw-all-points x-start y-start)))

(defn setup []
  (q/smooth)
  (q/background 0)
  (q/frame-rate 24)

  (let [x-starts (steps (q/random 10) 0.01)
        y-starts (steps (q/random 10) 0.01)
        starts   (map list x-starts y-starts)
        starts-str (seq->stream starts)]

    (q/set-state! :starts-str starts-str)))

(q/defsketch gen-art-24
  :title "Animated Fluffy Clouds"
  :setup setup
  :draw draw
  :size [300 300])

(defn -main [& args])
