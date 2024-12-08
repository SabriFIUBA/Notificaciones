import java.util.*;

public class Notificaciones {
    public static int contarNotificaciones(int[] gastos, int d) {
        int notificaciones = 0;
        int n = gastos.length;

        TreeMap<Integer, Integer> gastosOrdenados = new TreeMap<>();

        for (int i = 0; i < d; i++) {
            gastosOrdenados.put(gastos[i], gastosOrdenados.getOrDefault(gastos[i], 0) + 1);
        }

        for (int i = d; i < n; i++) {
            double mediana = calcularMediana(gastosOrdenados, d);

            if (gastos[i] >= 2 * mediana) {
                notificaciones++;
            }

            int gastoAntiguo = gastos[i - d];
            gastosOrdenados.put(gastoAntiguo, gastosOrdenados.get(gastoAntiguo) - 1);
            if (gastosOrdenados.get(gastoAntiguo) == 0) {
                gastosOrdenados.remove(gastoAntiguo);
            }

            gastosOrdenados.put(gastos[i], gastosOrdenados.getOrDefault(gastos[i], 0) + 1);
        }

        return notificaciones;
    }

    private static double calcularMediana(TreeMap<Integer, Integer> gastosOrdenados, int d) {
        int count = 0;
        int mid1 = (d + 1) / 2;
        int mid2 = d % 2 == 0 ? mid1 + 1 : mid1;

        int mediana1 = 0, mediana2 = 0;

        for (Map.Entry<Integer, Integer> entry : gastosOrdenados.entrySet()) {
            count += entry.getValue();
            if (count >= mid1 && mediana1 == 0) {
                mediana1 = entry.getKey();
            }
            if (count >= mid2) {
                mediana2 = entry.getKey();
                break;
            }
        }

        return d % 2 == 0 ? (mediana1 + mediana2) / 2.0 : mediana1;
    }

    public static void main(String[] args) {
        int[] gastos = {10, 20, 30, 40, 50};
        int d = 3;
        int notificaciones = contarNotificaciones(gastos, d);
        System.out.println("Notificaciones: " + notificaciones);
    }
}
