(ns quil-sketches.gen-art.31-oo-circles
  (:require [quil.core :as q]))

;; Example 31 - OO Circles
;; Taken from Listing 6.4, p117

;; int _num = 10;
;; Circle[] _circleArr = {};

;; void setup() {
;;   size(500, 300);
;;   background(255);
;;   smooth();
;;   strokeWeight(1);
;;   fill(150, 50);
;;   drawCircles();
;; }

;; void draw() {
;;   background(255);
;;   for(int i = 0; i < _circleArr.length; i++) {
;;     Circle thisCirc = _circleArr[i];
;;     thisCirc.updateMe();
;;   }
;; }

;; void mouseReleased() {
;;   drawCircles();
;; }

;; void drawCircles() {
;;   for(int i = 0; i < _num; i++) {
;;     Circle thisCirc = new Circle();
;;     thisCirc.drawMe();
;;     _circleArr = (Circle[])append(_circleArr, thisCirc);
;;   }
;; }

;; //=========== objects

;; class Circle {
;;   float x, y;
;;   float radius;
;;   color linecol, fillcol;
;;   float alph;
;;   float xmove, ymove;

;;   Circle() {
;;     x = random(width);
;;     y = random(height);
;;     radius = random(100) + 10;
;;     linecol = color(random(255), random(255), random(255));
;;     fillcol = color(random(255), random(255), random(255));
;;     alph = random(255);
;;     xmove = random(10) - 5;
;;     ymove = random(10) - 5;
;;   }

;;   void drawMe() {
;;     noStroke();
;;     fill(fillcol, alph);
;;     ellipse(x, y, radius*2, radius*2);
;;     stroke(linecol, 150);
;;     noFill();
;;     ellipse(x, y, 10, 10);
;;   }

;;   void updateMe() {
;;     x += xmove;
;;     y += ymove;
;;     if(x > (width + radius)) {x = 0 - radius;}
;;     if(x < (0 - radius)) {x = width + radius;}
;;     if(y > (height + radius)) {y = 0 - radius;}
;;     if(y < (0 - radius)) {y = height + radius;}
;;     drawMe();
;;   }
;; }

(def num-circles 10)

(defn mk-circle []
  {:x        (q/random (q/width))
   :y        (q/random (q/height))
   :radius   (+ 10 (q/random 100))
   :line-col (q/color (q/random 255) (q/random 255) (q/random 255))
   :fill-col [(q/random 255) (q/random 255) (q/random 255) (q/random 255)]
   :xmove    (- (q/random 10) 5)
   :ymove    (- (q/random 10) 5)})

(defn add-circles
  [circles*]
  (dotimes [_ num-circles]
    (let [c (mk-circle)]
      (swap! circles* conj c))))

(defn mouse-released
  []
  (add-circles (q/state :circles)))

(defn setup []
  (q/background 255)
  (q/smooth)
  (q/stroke-weight 1)
  (q/fill 150 50)
  (let [circles* (atom [])]
    (add-circles circles*)
    (q/set-state! :circles circles*)))

(defn update-circle
  [{:keys [x y xmove ymove radius] :as circle}]

  (let [new-x (+ x xmove)
        new-x (if (< new-x (- 0 radius)) (+ (q/width) radius) new-x)
        new-x (if (> new-x (+ (q/width) radius)) (- 0 radius) new-x)
        new-y (+ y ymove)
        new-y (if (< new-y (- 0 radius)) (+ (q/height) radius) new-y)
        new-y (if (> new-y (+ (q/height) radius)) (- 0 radius) new-y)]
    (assoc circle :x new-x :y new-y)))

(defn update-circles
  [circles]
  (map update-circle circles))

(defn draw-circle
  [{:keys [x y radius line-col fill-col]}]
  (q/no-stroke)
  (apply q/fill fill-col)
  (q/ellipse x y (* 2 radius) (* 2 radius))
  (q/stroke line-col 150)
  (q/no-fill)
  (q/ellipse x y 10 10))

(defn draw []
  (q/background 255)
  (let [circles* (q/state :circles)
        circles (swap! circles* update-circles)]
    (doseq [c circles]
      (draw-circle c))))

(q/defsketch gen-art-31
  :title "OO Circles"
  :setup setup
  :draw draw
  :mouse-released mouse-released
  :size [500 300])

(defn -main [& args])
