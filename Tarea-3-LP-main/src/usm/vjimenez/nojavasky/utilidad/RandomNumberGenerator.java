package usm.vjimenez.nojavasky.utilidad;
import java.util.Random;


public class RandomNumberGenerator {
    
    public static int rand(int a, int b) {
        /**
         * Genera un número aleatorio en un rango específico.
         * 
         * Este método genera un número entero aleatorio entre los valores
         * proporcionados, incluyendo el límite inferior 'a' y excluyendo
         * el límite superior 'b'. Se utiliza una instancia de la clase Random
         * para generar un número decimal aleatorio, el cual se ajusta al rango
         * y se redondea al entero más cercano.
         *
         * @param a El límite inferior del rango (inclusive).
         * @param b El límite superior del rango (exclusivo).
         * @return Un número entero aleatorio entre 'a' y 'b'.
         */
        Random rand = new Random();
        return Math.round(a + (rand.nextFloat() * (b - a)));
    }

    public static float randF(float a, float b) {
        /**
         * Genera un número de punto flotante aleatorio en un rango específico.
         * 
         * Este método genera un número de punto flotante aleatorio entre los valores
         * proporcionados, incluyendo el límite inferior 'a' y excluyendo el límite 
         * superior 'b'. Utiliza una instancia de la clase Random para generar un 
         * número decimal aleatorio, el cual se ajusta al rango y se redondea al 
         * número de punto flotante más cercano.
         *
         * @param a El límite inferior del rango (inclusive).
         * @param b El límite superior del rango (exclusivo).
         * @return Un número de punto flotante aleatorio entre 'a' y 'b'.
         */
        Random rand = new Random();
        return (float) Math.round(a + (rand.nextFloat() * (b - a)));
    }

}
