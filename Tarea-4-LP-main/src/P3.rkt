#lang scheme

;; Función para rotar una lista `n` veces
;; rotar-n : (list any) -> number -> (list any)
;;
;; lista : Lista de elementos a rotar
;; n : Número de veces que se rota la lista a la izquierda

(define (rotar-n lista n)
  ;; Si n es 0, devuelve la lista sin cambios
  (if (= n 0)
      lista  
      ;; Aplica rotación recursivamente
      (rotar-n (append (cdr lista) (list (car lista))) (- n 1))))  ;; Mueve el primer elemento al final de la lista

;; Función recursiva para aplicar cada función en la lista a un número en secuencia
;; aplicar-funciones : (list (-> any any)) -> any -> any
;;
;; funciones : Lista de funciones a aplicar
;; numero : Número al que se le aplican las funciones

(define (aplicar-funciones funciones numero)
  ;; Si no hay más funciones, devuelve el número resultante
  (if (null? funciones)
      numero  
      ;; Aplica la primera función y continúa con el resto
      (aplicar-funciones (cdr funciones) ((car funciones) numero))))  ;; Aplica la función actual al número

;; Función auxiliar que incluye el índice de rotación
;; evaluador-aux : (list (-> any any)) -> (list any) -> number -> (list any)
;;
;; funciones : Lista de funciones a aplicar
;; numeros : Lista de números a evaluar
;; index : Índice actual para la rotación de las funciones

(define (evaluador-aux funciones numeros index)
  ;; Si no hay más números, devuelve una lista vacía
  (if (null? numeros)
      '()  
      ;; Aplica las funciones rotadas al primer número y evalúa el siguiente
      (cons (aplicar-funciones (rotar-n funciones index) (car numeros))  ;; Aplica funciones rotadas al primer número
            (evaluador-aux funciones (cdr numeros) (+ index 1)))))  ;; Continua con el resto de los números y aumenta el índice

;; Evaluador principal que empieza con el índice en 0
;; evaluador : (list (-> any any)) -> (list any) -> (list any)
;;
;; funciones : Lista de funciones a aplicar
;; numeros : Lista de números a evaluar

(define (evaluador funciones numeros)
  ;; Inicializa el evaluador auxiliar con el índice 0
  (evaluador-aux funciones numeros 0))

;; Ejemplos de uso:
(evaluador (list (lambda (x) (+ x 1)) (lambda (x) (* x x)) (lambda (x) (- x 2))) '(2 5 7))
(evaluador (list (lambda (x) (/ x 3.2)) (lambda (x) (+ (* x 2) x)) (lambda (x) (- x (* 5.40 (* x x))))) '(5 2 -7))
(evaluador '() '())

