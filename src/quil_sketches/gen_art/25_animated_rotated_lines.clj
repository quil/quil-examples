(ns quil-sketches.gen-art.25-animated-rotated-lines
  (:require
   [quil.core :as q]
   [quil.helpers.calc :refer [mul-add]]
   [quil.helpers.seqs :refer [indexed-range-incl seq->stream steps tally]]))

;; Example 25 - Animated Rotated Lines
;; Taken from Listing 5.4, p91

;; float xstart, xnoise, ystart, ynoise;
;; float xstartNoise, ystartNoise;

;; void setup() {
;;   size(300, 300);
;;   smooth();
;;   background(255);
;;   frameRate(24);

;;   xstartNoise = random(20);
;;   ystartNoise = random(20);

;;   xstart = random(10);
;;   ystart = random(10);
;; }

;; void draw() {
;;   background(255);

;;   xstart += 0.01;
;;   ystart += 0.01;

;;   xstartNoise += 0.01;
;;   ystartNoise += 0.01;
;;   xstart += (noise(xstartNoise) * 0.5) - 0.25;
;;   ystart += (noise(ystartNoise) * 0.5) - 0.25;

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
;;   rotate(noiseFactor * radians(360));
;;   stroke(0, 150);
;;   line(0, 0, 20, 0);
;;   popMatrix();
;; }

(defn draw-point
  [x y noise-factor]
  (q/push-matrix)
  (q/translate x y)
  (q/rotate (* noise-factor (q/radians 360)))
  (q/stroke 0 150)
  (q/line 0 0 20 0)
  (q/pop-matrix))

(defn draw-all-points
  [x-start y-start step-size]
  (dorun
   (for [[x-idx x] (indexed-range-incl 0 (q/width) step-size)
         [y-idx y] (indexed-range-incl 0 (q/height) step-size)]
     (let [x-noise-shift (* x-idx 0.1)
           y-noise-shift (* y-idx 0.1)
           x-noise (+ x-start x-noise-shift)
           y-noise (+ y-start y-noise-shift)]
       (draw-point x y (q/noise x-noise y-noise))))))

(defn starts-seq []
  (let [noise-steps (steps (q/random 20) 0.01)
        noises      (map q/noise noise-steps)
        noises      (mul-add noises 0.5 -0.25)
        noise-tally (tally noises)]
    (map +
         (steps (q/random 10) 0.01)
         noise-tally)))

(defn setup []
  (q/smooth)
  (q/background 255)
  (q/frame-rate 24)

  (let [x-starts      (starts-seq)
        y-starts      (starts-seq)
        starts-str    (seq->stream (map list x-starts y-starts))]
    (q/set-state! :starts-str starts-str)))

(defn draw []
  (q/background 255)
  (let [[x-start y-start] ((q/state :starts-str))]
    (draw-all-points x-start y-start 5)))

(q/defsketch gen-art-25
  :title "Animated Rotated Lines"
  :setup setup
  :draw draw
  :size [300 300])

(defn -main [& args])
